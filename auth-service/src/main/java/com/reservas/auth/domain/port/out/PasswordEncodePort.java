package com.reservas.auth.domain.port.out;

public interface PasswordEncodePort {

    String encriptar(String passwordPlano);

    boolean validar(String passwordPlano, String passwordEncriptado);
}
