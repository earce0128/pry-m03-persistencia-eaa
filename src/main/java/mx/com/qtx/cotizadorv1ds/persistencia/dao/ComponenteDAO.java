package mx.com.qtx.cotizadorv1ds.persistencia.dao;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.dto.ComponenteDTO;

public interface ComponenteDAO extends BaseDAO<ComponenteDTO> {
    List<ComponenteDTO> obtenerTodosPorCategoria(String categoria) throws SQLException;
}