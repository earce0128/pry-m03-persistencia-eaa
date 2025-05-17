package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;
import mx.com.qtx.cotizadorv1ds.servicios.PromocionDTO;

public class PromocionMySqlDAO implements PromocionDAO {
	
	private static PromocionMySqlDAO instance;

    public static synchronized PromocionMySqlDAO getInstance() {
        if (instance == null) {
            instance = new PromocionMySqlDAO();
        }
        return instance;
    }

	@Override
	public PromocionDTO obtener(Object id) throws SQLException {
		PromocionDTO promocion = null;
        String sql = "SELECT numPromocion, nombre, descripcion, fechVigenciaDesde, fechVigenciaHasta "
        		   + "FROM promocion "
        		   + "WHERE numPromocion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, (Long) id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	promocion = new PromocionDTO();
            	promocion.setNumPromocion(resultSet.getLong("numPromocion"));
            	promocion.setNombre(resultSet.getString("nombre"));
            	promocion.setDescripcion(resultSet.getString("descripcion"));
            	promocion.setFechVigenciaDesde(resultSet.getDate("fechVigenciaDesde").toLocalDate());
            	if(resultSet.getDate("fechVigenciaHasta") != null) {
            		promocion.setFechVigenciaHasta(resultSet.getDate("fechVigenciaHasta").toLocalDate());
            	}
            }
        }
        return promocion;
	}

	@Override
	public List<PromocionDTO> obtenerTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromocionDTO insertar(PromocionDTO entidad) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(PromocionDTO entidad) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Object id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
