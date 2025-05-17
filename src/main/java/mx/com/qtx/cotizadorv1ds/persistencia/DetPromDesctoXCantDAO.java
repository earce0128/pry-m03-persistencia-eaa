package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.servicios.DetPromDsctoXCantDTO;

public interface DetPromDesctoXCantDAO {
	List<DetPromDsctoXCantDTO> obtenerPorDetProm(Long numDetPromocion,Long numPromocion) throws SQLException;
	DetPromDsctoXCantDTO insertar(DetPromDsctoXCantDTO entidad) throws SQLException; // Modificado
    void actualizar(DetPromDsctoXCantDTO entidad) throws SQLException;
    void eliminarPorDetPromocion(Long numDetPromocion, Long numPromocion) throws SQLException;
}
