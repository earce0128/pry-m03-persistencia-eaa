package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.servicios.ComponenteDTO;

public interface ComponenteDAO extends BaseDAO<ComponenteDTO> {
    List<ComponenteDTO> obtenerTodosPorCategoria(String categoria) throws SQLException;
}