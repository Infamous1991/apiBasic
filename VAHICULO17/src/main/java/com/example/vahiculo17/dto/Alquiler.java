package com.example.vahiculo17.dto;

import java.time.LocalDateTime;

public record Alquiler(int id, int idEmpleado, int idCliente, int idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime fechaEntrega, double precio) {
    
}
