package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;
import mx.com.qtx.cotizadorv1ds.servicios.DetPromocionDTO;

public class DetPromocionMySqlDAO implements DetPromocionDAO {
	
	private static DetPromocionMySqlDAO instance;

    public static synchronized DetPromocionMySqlDAO getInstance() {
        if (instance == null) {
            instance = new DetPromocionMySqlDAO();
        }
        return instance;
    }

	@Override
	public DetPromocionDTO obtener(Long numDetPromocion, Long numPromocion) throws SQLException {
		DetPromocionDTO detalle = null;
        String sql = "SELECT numDetPromocion, numPromocion, nombre, descripcion, esBase, "
        		          + "llevarN, pagueM, porcDsctoPlan, tipoPromAcumulable, tipoPromBase "
        		   + "FROM det_promocion "
        		   + "WHERE numDetPromocion = ? "
        		   + "AND numPromocion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, numDetPromocion);
            statement.setLong(2, numPromocion);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                detalle = new DetPromocionDTO();
                detalle.setNumDetPromocion(resultSet.getLong("numDetPromocion"));
                detalle.setNumPromocion(resultSet.getLong("numPromocion"));
                detalle.setNombre(resultSet.getString("nombre"));
                detalle.setEsBase(resultSet.getBoolean("esBase"));
                detalle.setLlevarN(resultSet.getInt("llevarN"));
                detalle.setPagueM(resultSet.getInt("pagueM"));
                detalle.setDescripcion(resultSet.getString("descripcion"));
                detalle.setPorcDsctoPlan(resultSet.getDouble("porcDsctoPlan"));
                detalle.setTipoPromAcumulable(resultSet.getString("tipoPromAcumulable"));
                detalle.setTipoPromBase(resultSet.getString("tipoPromBase"));
            }
        }
        return detalle;
	}

	@Override
	public List<DetPromocionDTO> obtenerTodosPorPromocion(Long numPromocion) throws SQLException {
		List<DetPromocionDTO> detalles = new ArrayList<>();
        String sql = "SELECT numDetPromocion, numPromocion, nombre, descripcion, esBase, "
		          		+ "llevarN, pagueM, porcDsctoPlan, tipoPromAcumulable, tipoPromBase "
		           + "FROM det_promocion "
		           + "WHERE numPromocion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, numPromocion);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	DetPromocionDTO detalle = new DetPromocionDTO();
            	detalle = new DetPromocionDTO();
                detalle.setNumDetPromocion(resultSet.getLong("numDetPromocion"));
                detalle.setNumPromocion(resultSet.getLong("numPromocion"));
                detalle.setNombre(resultSet.getString("nombre"));
                detalle.setEsBase(resultSet.getBoolean("esBase"));
                detalle.setLlevarN(resultSet.getInt("llevarN"));
                detalle.setPagueM(resultSet.getInt("pagueM"));
                detalle.setDescripcion(resultSet.getString("descripcion"));
                detalle.setPorcDsctoPlan(resultSet.getDouble("porcDsctoPlan"));
                detalle.setTipoPromAcumulable(resultSet.getString("tipoPromAcumulable"));
                detalle.setTipoPromBase(resultSet.getString("tipoPromBase"));
                detalles.add(detalle);
            }
        }
        return detalles;
	}

	@Override
	public List<DetPromocionDTO> obtenerTodos() throws SQLException {
		List<DetPromocionDTO> detalles = new ArrayList<>();
        String sql = "SELECT numDetPromocion, numPromocion, nombre, descripcion, esBase, "
		          		+ "llevarN, pagueM, porcDsctoPlan, tipoPromAcumulable, tipoPromBase "
		           + "FROM det_promocion";
        try (Connection conn = DatabaseConnector.getConnection();
           	 Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
            	DetPromocionDTO detalle = new DetPromocionDTO();
            	detalle = new DetPromocionDTO();
                detalle.setNumDetPromocion(resultSet.getLong("numDetPromocion"));
                detalle.setNumPromocion(resultSet.getLong("numPromocion"));
                detalle.setNombre(resultSet.getString("nombre"));
                detalle.setEsBase(resultSet.getBoolean("esBase"));
                detalle.setLlevarN(resultSet.getInt("llevarN"));
                detalle.setPagueM(resultSet.getInt("pagueM"));
                detalle.setDescripcion(resultSet.getString("descripcion"));
                detalle.setPorcDsctoPlan(resultSet.getDouble("porcDsctoPlan"));
                detalle.setTipoPromAcumulable(resultSet.getString("tipoPromAcumulable"));
                detalle.setTipoPromBase(resultSet.getString("tipoPromBase"));
                detalles.add(detalle);
            }
        }
        return detalles;
	}

	@Override
	public DetPromocionDTO insertar(DetPromocionDTO entidad) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(DetPromocionDTO entidad) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Long numDetPromocion, Long numPromocion) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPorPromocion(Long numPromocion) throws SQLException {
		// TODO Auto-generated method stub

	}

}
