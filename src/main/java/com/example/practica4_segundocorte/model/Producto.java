package com.example.practica4_segundocorte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private  Long id;
    private String nombre;
    private Double precio;
    private LocalDate fechaRegistro;




}
