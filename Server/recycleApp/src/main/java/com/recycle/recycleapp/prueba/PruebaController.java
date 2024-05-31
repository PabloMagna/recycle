package com.recycle.recycleapp.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class PruebaController {
    @Autowired
    private PruebaService service;

    @PostMapping("/ingreso")
    @PreAuthorize("hasRole('ADMIN')")
    public String ingreso(@RequestBody Prueba prueba, Authentication jwt){
        service.ingresoTexto(prueba,jwt);
        return "exito";
    }

    @GetMapping("{id}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Prueba> traerPrueba(@PathVariable Integer id, Authentication jwt) {
        Prueba prueba = service.traerPrueba(id, jwt);
        if (prueba != null) {
            return ResponseEntity.ok(prueba);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
