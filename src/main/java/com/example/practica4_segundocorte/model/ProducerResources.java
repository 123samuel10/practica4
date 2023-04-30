package com.example.practica4_segundocorte.model;

import com.example.practica4_segundocorte.service.MysqlConn;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {
    @Inject
    private Logger log;
    //@Resource(name="jdbc/mysqlDB")
    @Resource(name="practicadb")
    private DataSource ds;

    private static String url = "jdbc:mysql://localhost:3306/practicadb";
    private static String user = "root";
    private static String password = "admin";
    private static Connection connection;


    public static Connection getInstance() throws SQLException {
        if(connection==null){
            connection = DriverManager.getConnection(url,user,password);
        }
        return connection;
    }

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {
 Context initContext = new InitialContext();
 Context envContext = (Context)
        initContext.lookup("java:/comp/env");
// DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return
                Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    public void close(@Disposes @MysqlConn Connection connection) throws
            SQLException {
        connection.close();
        log.info("cerrando la conexion a la bbdd mysql!");
    }


}