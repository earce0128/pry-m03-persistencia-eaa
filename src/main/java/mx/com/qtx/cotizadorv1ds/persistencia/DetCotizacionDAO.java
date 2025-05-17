package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.servicios.DetCotizacionDTO;

public interface DetCotizacionDAO {
    DetCotizacionDTO obtener(Long idCotizacion, String idComponente) throws SQLException;
    List<DetCotizacionDTO> obtenerTodosPorCotizacion(Long idCotizacion) throws SQLException;
    List<DetCotizacionDTO> obtenerTodos() throws SQLException;
    DetCotizacionDTO insertar(DetCotizacionDTO entidad) throws SQLException; // Modificado
    void actualizar(DetCotizacionDTO entidad) throws SQLException;
    void eliminar(Long idCotizacion, String idComponente) throws SQLException;
    void eliminarPorCotizacion(Long idCotizacion) throws SQLException;
}
