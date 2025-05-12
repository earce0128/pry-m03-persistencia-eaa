package mx.com.qtx.cotizadorv1ds.persistencia.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    T obtener(Object id) throws SQLException;
    List<T> obtenerTodos() throws SQLException;
    T insertar(T entidad) throws SQLException; 
    void actualizar(T entidad) throws SQLException;
    void eliminar(Object id) throws SQLException;
}
