package com.example.vahiculo17.utils;

import org.springframework.jdbc.core.RowMapper;

import com.example.vahiculo17.dto.Alquiler;

public class RwMp {
    
    public static RowMapper<Alquiler> alquilerRM = (rs, i) -> {
        return new Alquiler(
            rs.getInt("id_alquiler"),
            rs.getInt("id_empleado"),
            rs.getInt("id_cliente"),
            rs.getInt("id_vehiculo"),
            rs.getTimestamp("fecha_inicio").toLocalDateTime(),
            rs.getTimestamp("fecha_fin").toLocalDateTime(),
            rs.getTimestamp("fecha_entrega").toLocalDateTime(),
            rs.getDouble("costo")
        );
    };
}
