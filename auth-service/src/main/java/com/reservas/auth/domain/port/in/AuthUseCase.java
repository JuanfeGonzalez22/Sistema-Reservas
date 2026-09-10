package com.reservas.auth.domain.port.in;

import com.reservas.auth.domain.model.ResultadoAutenticacion;
import com.reservas.auth.domain.model.Usuario;

public interface AuthUseCase {

    ResultadoAutenticacion registrarUsuario(String email, String passwordPlano, String nombre);

    ResultadoAutenticacion autenticarUsuario(String email, String passwordPlano);
}
