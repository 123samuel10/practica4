package com.example.practica4_segundocorte.service.serviceImpl;


import com.example.practica4_segundocorte.model.ProducerResources;
import com.example.practica4_segundocorte.model.Producto;
import com.example.practica4_segundocorte.service.CrudRepository;
import com.example.practica4_segundocorte.service.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepositoryImpl  implements CrudRepository<Producto> {

    private Connection conexionDB;
    ProducerResources producerResources=new ProducerResources();

    private Connection getConnection() throws SQLException {
        return ProducerResources.getInstance();
    }
    private Producto createProduct(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getDouble("precio"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro").toLocalDate());
        return producto;
    }


    @Override
    public List<Producto> getList() {
        List<Producto>productosList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT  * FROM  Producto;
                        """
            ))
        {
            while (resultSet.next()){
                Producto producto=createProduct(resultSet);
                productosList.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosList;
    }

    @Override
    public Producto getById(Long id) {
        Producto producto=null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(" SELECT  * FROM  Producto where id=?")
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                producto=createProduct(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void save(Producto producto) {
        try (PreparedStatement preparedStatement=getConnection().prepareStatement("""
                          INSERT INTO  Producto(nombre,precio,fecha_registro)values (?,?,?)
""")){
            preparedStatement.setString(1,producto.getNombre());
            preparedStatement.setDouble(2,producto.getPrecio());
            Date fechaResgitro= Date.valueOf(producto.getFechaRegistro());
            preparedStatement.setDate(3,fechaResgitro);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delateById(Long id) {
        Producto producto=null;
        try (PreparedStatement preparedStatement=getConnection().prepareStatement("DELETE FROM  Producto WHERE id=?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Producto producto) {
        try(PreparedStatement preparedStatement=getConnection().prepareStatement("""
UPDATE Producto SET  nombre=?,precio=?,fecha_registro=? WHERE id=?
""")){
            preparedStatement.setString(1,producto.getNombre());
            preparedStatement.setDouble(2,producto.getPrecio());
            Date fechaResgitro= Date.valueOf(producto.getFechaRegistro());
            preparedStatement.setDate(3,fechaResgitro);
            preparedStatement.setLong(4,producto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
