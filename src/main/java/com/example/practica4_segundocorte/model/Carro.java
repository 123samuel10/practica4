package com.example.practica4_segundocorte.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;

@SessionScoped
public class Carro implements Serializable {
    @Inject
    private Carro carro;
}
