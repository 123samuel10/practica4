package com.example.practica4_segundocorte.service;

import java.util.List;

public interface CrudRepository<T> {
    List<T>getList();
    T getById(Long id);
    void save(T t);
    void  delateById(Long id);
    void update(T t);
}