package com.example.practica4_segundocorte.service.serviceImpl;

import com.example.practica4_segundocorte.service.LoginService;
import com.example.practica4_segundocorte.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped

public class LoginServiceSessionImpl implements LoginService {
    @Inject
    private LoginService auth;
    @Inject
    private UsuarioService service;

}
