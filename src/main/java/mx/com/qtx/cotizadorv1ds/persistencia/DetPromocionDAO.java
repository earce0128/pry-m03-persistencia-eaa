package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.servicios.DetPromocionDTO;

public interface DetPromocionDAO {
	DetPromocionDTO obtener(Long numDetPromocion, Long numPromocion) throws SQLException;
    List<DetPromocionDTO> obtenerTodosPorPromocion(Long numPromocion) throws SQLException;
    List<DetPromocionDTO> obtenerTodos() throws SQLException;
    DetPromocionDTO insertar(DetPromocionDTO entidad) throws SQLException; // Modificado
    void actualizar(DetPromocionDTO entidad) throws SQLException;
    void eliminar(Long numDetPromocion, Long numPromocion) throws SQLException;
    void eliminarPorPromocion(Long numPromocion) throws SQLException;
}
