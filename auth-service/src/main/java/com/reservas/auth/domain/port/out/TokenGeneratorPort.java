package com.reservas.auth.domain.port.out;

import com.reservas.auth.domain.model.Usuario;

public interface TokenGeneratorPort {

     String generarToken(Usuario usuario);
     String extraerEmail(String token);
     boolean esValido(String token);

}
