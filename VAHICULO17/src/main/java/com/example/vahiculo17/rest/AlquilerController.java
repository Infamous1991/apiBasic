package com.example.vahiculo17.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vahiculo17.service.AlquilerService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("alquiler")
public class AlquilerController {

    @Autowired
    AlquilerService Al_srv;


    @PostMapping("nuevoAlquiler")
    public ResponseEntity<?> nuevoAlquiler(@RequestBody HashMap<String, Object> entity) {

        try{
            List<Object> resultado= Al_srv.nuevoAlquiler((int)entity.get("cliente"), (int)entity.get("empleado"), (int)entity.get("vehiculo"), LocalDateTime.parse((String)entity.get("inicio")), (int)entity.get("dias"));

            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    
}
