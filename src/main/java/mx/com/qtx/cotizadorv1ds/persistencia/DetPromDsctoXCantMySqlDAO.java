package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;
import mx.com.qtx.cotizadorv1ds.servicios.DetPromDsctoXCantDTO;

public class DetPromDsctoXCantMySqlDAO implements DetPromDesctoXCantDAO {
	
	private static DetPromDsctoXCantMySqlDAO instance;

    public static synchronized DetPromDsctoXCantMySqlDAO getInstance() {
        if (instance == null) {
            instance = new DetPromDsctoXCantMySqlDAO();
        }
        return instance;
    }

	@Override
	public List<DetPromDsctoXCantDTO> obtenerPorDetProm(Long numDetPromocion, Long numPromocion) throws SQLException {
		List<DetPromDsctoXCantDTO> detalles = new ArrayList<>();
        String sql = "SELECT numPromDsctoCant, numDetPromocion, numPromocion, cantidad, dscto "
		           + "FROM det_prom_dscto_x_cant "
		           + "WHERE numDetPromocion = ? "
		           + "AND numPromocion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, numDetPromocion);
            statement.setLong(2, numPromocion);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	DetPromDsctoXCantDTO detalle = new DetPromDsctoXCantDTO();
            	detalle = new DetPromDsctoXCantDTO();
            	detalle.setNumPromDsctoCant(resultSet.getLong("numPromDsctoCant"));
                detalle.setNumDetPromocion(resultSet.getLong("numDetPromocion"));
                detalle.setNumPromocion(resultSet.getLong("numPromocion"));
                detalle.setCantidad(resultSet.getInt("cantidad"));
                detalle.setDscto(resultSet.getDouble("dscto"));
                detalles.add(detalle);
            }
        }
        return detalles;
	}

	@Override
	public DetPromDsctoXCantDTO insertar(DetPromDsctoXCantDTO entidad) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(DetPromDsctoXCantDTO entidad) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPorDetPromocion(Long numDetPromocion, Long numPromocion) throws SQLException {
		// TODO Auto-generated method stub

	}

}
