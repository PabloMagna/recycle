package com.recycle.recycleapp.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class PruebaController {
    @Autowired
    private PruebaRepository repository;

    @PostMapping("/ingreso")

    public String sumar(@RequestBody Prueba prueba){
        repository.save(prueba);
        return "exito";
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String traerPrueba(@PathVariable Integer id){
        Prueba prueba = repository.findById(id).orElse(null);
        return prueba.getP();
    }
}
