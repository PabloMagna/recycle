package com.recycle.recycleapp.prueba;

import com.recycle.recycleapp.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PruebaService {
    private final PruebaRepository pruebaRepository;


    public Prueba traerPrueba(Integer id, Authentication jwt) {
        UserEntity user = ((UserEntity) jwt.getPrincipal());
        if(user != null){
            return pruebaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Prueba no encontrada"));
        }else {
            return null;
        }
    }

    public void ingresoTexto(Prueba prueba, Authentication jwt) {
        UserEntity user = ((UserEntity) jwt.getPrincipal());
        prueba.setP(prueba.getP() + "creado por: " + user.getEmail());
        pruebaRepository.save(prueba);
    }
}
