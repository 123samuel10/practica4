package com.example.practica4_segundocorte.model;


    public class ServiceJdbcException extends  RuntimeException{
        public ServiceJdbcException(String message) {
            super(message);
        }
        public ServiceJdbcException(String message, Throwable cause) {
            super(message, cause);
        }

    }

