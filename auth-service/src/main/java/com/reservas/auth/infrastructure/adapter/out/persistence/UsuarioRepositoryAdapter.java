package com.reservas.auth.infrastructure.adapter.out.persistence;

import com.reservas.auth.domain.model.Usuario;
import com.reservas.auth.domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;

     public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository) {
         this.jpaRepository = jpaRepository;
     }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setEmail(usuario.getEmail());
        entity.setPassword(usuario.getPassword());
        entity.setNombre(usuario.getNombre());
        entity.setRol(usuario.getRol());

        UsuarioEntity guardado = jpaRepository.save(entity);
        return toDomain(guardado);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public boolean existePorEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    public Usuario toDomain(UsuarioEntity entity){
         return new Usuario(entity.getId(), entity.getEmail(), entity.getPassword(), entity.getRol(), entity.getNombre());
    }
}
