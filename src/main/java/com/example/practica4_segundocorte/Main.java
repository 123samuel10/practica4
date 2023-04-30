package com.example.practica4_segundocorte;

import com.example.practica4_segundocorte.model.Producto;
import com.example.practica4_segundocorte.model.TransactionalInterceptor;
import com.example.practica4_segundocorte.service.serviceImpl.CategoriaRepositoryImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //agregar
        CategoriaRepositoryImpl productoRepository=new CategoriaRepositoryImpl();
        Producto producto=new Producto();
        producto.setNombre("aguapanela");
        producto.setPrecio(3000.0);
        producto.setFechaRegistro(LocalDate.now());
        productoRepository.save(producto);


//        //modificar---------------------------
//        producto.setNombre("cambiando nombre");
//        Producto productoUpdate=productoRepository.getById(5L);
//        productoUpdate.setNombre("cambio de nombre UPDATE");
//        productoRepository.update(productoUpdate);
//        //eliminar----------
//        Producto productoEliminar=productoRepository.getById(7L);
//        productoRepository.delateById(productoEliminar.getId());
    }
}
