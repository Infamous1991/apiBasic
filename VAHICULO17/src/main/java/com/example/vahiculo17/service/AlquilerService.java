package com.example.vahiculo17.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.vahiculo17.dto.Alquiler;
import com.example.vahiculo17.utils.RwMp;

@Service
public class AlquilerService {

    @Autowired
    JdbcTemplate conn;

    private static final String empleadoExiste= "SELECT COUNT(*) FROM EMPLEADO WHERE id_empleado= ?";
    private static final String clienteExiste= "SELECT COUNT(*) FROM CLIENTE WHERE id_cliente = ?";
    private static final String vehiculoExiste= "SELECT COUNT(*) FROM VEHICULO WHERE id_vehiculo = ?";
    private static final String vehiculoDisponibleFecha= "SELECT * FROM ALQUILER WHERE id_vehiculo = ?";
    private static final String vehiculoDisponible= "SELECT id_estado FROM VEHICULO WHERE id_vehiculo = ?";
    private static final String reservar= "UPDATE VEHICULO SET id_estado = 2 WHERE id_vehiculo= ?";
    private static final String precioVehiculo= "SELECT costo_alquiler FROM TIPO WHERE id_tipo= (SELECT id_tipo FROM VEHICULO WHERE id_vehiculo= ?)";
    private static final String nuevoAlquiler= "INSERT INTO ALQUILER (id_empleado, id_cliente, id_vehiculo, fecha_inicio, fecha_fin, fecha_entrega, costo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ultimo= "SELECT * FROM ALQUILER WHERE id_alquiler = (SELECT IDENT_CURRENT('ALQUILER'))";

    @Transactional(propagation=Propagation.MANDATORY, rollbackFor = Exception.class)
    public void empleadoExiste(int empleado){
        if (conn.queryForObject(empleadoExiste, int.class, empleado) != 1){
            throw new RuntimeException("Empleado no Existe");
        };
    }

    @Transactional(propagation=Propagation.MANDATORY, rollbackFor = Exception.class)
    public void clienteExiste(int cliente){
        if (conn.queryForObject(clienteExiste, int.class, cliente) != 1){
            throw new RuntimeException("Cliente no Existe");
        };
    }

    @Transactional(propagation=Propagation.MANDATORY, rollbackFor = Exception.class)
    public void vehiculoExiste(int vehiculo){
        if (conn.queryForObject(vehiculoExiste, int.class, vehiculo) != 1){
            throw new RuntimeException("Vehiculo no Existe");
        };
    }

    @Transactional(propagation=Propagation.MANDATORY, rollbackFor = Exception.class)
    public void vehiculoDisponible(int vehiculo, LocalDateTime inicio, int dias){
        List<Alquiler> alquileres= conn.query(vehiculoDisponibleFecha, RwMp.alquilerRM, vehiculo);

        LocalDateTime fin = inicio.plusDays(dias);

        if (conn.queryForObject(vehiculoDisponible,int.class, vehiculo) != 1){
            throw new RuntimeException("Vehiculo no Disponible");
        }

        if (fin.isBefore(inicio)) throw new RuntimeException("Fechas no Válidas");

        for (Alquiler i: alquileres){
            if ((inicio.isBefore(i.fechaInicio()) && fin.isAfter(i.fechaInicio()) ) || (inicio.isBefore(i.fechaFin()) && fin.isAfter(i.fechaFin()))){
                throw new RuntimeException("Vehiculo no Disponible para la fecha solicitada");   
            }
        }
    }

    @Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<Object> nuevoAlquiler(int idCliente, int idEmpleado, int idVehiculo, LocalDateTime inicio, int days){

        empleadoExiste(idEmpleado);
        clienteExiste(idCliente);
        vehiculoExiste(idVehiculo);
        vehiculoDisponible(idVehiculo, inicio, days);

        conn.update(reservar, idVehiculo);
        double precioPorDia= conn.queryForObject(precioVehiculo, double.class, idVehiculo);
        double precioFinal= precioPorDia * days;
        
        conn.update(nuevoAlquiler, idEmpleado, idCliente, idVehiculo, inicio, inicio.plusDays(days), LocalDateTime.now(), precioFinal);

        return  List.of("Solicitud de alquiler correctamente procesada", (Alquiler)conn.queryForObject(ultimo, RwMp.alquilerRM));
    }

}

