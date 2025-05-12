package mx.com.qtx.cotizadorv1ds.persistencia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.dto.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;

public class CotizacionMySqlDAO implements CotizacionDAO {
    
	private static CotizacionMySqlDAO instance;

    public static synchronized CotizacionMySqlDAO getInstance() {
        if (instance == null) {
            instance = new CotizacionMySqlDAO();
        }
        return instance;
    }

    @Override
    public CotizacionDTO obtener(Object id) throws SQLException {
        CotizacionDTO cotizacion = null;
        String sql = "SELECT idCotizacion, fechaCotizacion, total "
        		   + "FROM cotizacion "
        		   + "WHERE idCotizacion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, (Long) id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cotizacion = new CotizacionDTO();
                cotizacion.setIdCotizacion(resultSet.getLong("idCotizacion"));
                cotizacion.setFechaCotizacion(resultSet.getDate("fechaCotizacion").toLocalDate());
                cotizacion.setTotal(resultSet.getDouble("total"));
            }
        }
        return cotizacion;
    }

    @Override
    public List<CotizacionDTO> obtenerTodos() throws SQLException {
        List<CotizacionDTO> cotizaciones = new ArrayList<>();
        String sql = "SELECT idCotizacion, fechaCotizacion, total "
        		   + "FROM cotizacion";
        try (Connection conn = DatabaseConnector.getConnection();
        	 Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CotizacionDTO cotizacion = new CotizacionDTO();
                cotizacion.setIdCotizacion(resultSet.getLong("idCotizacion"));
                cotizacion.setFechaCotizacion(resultSet.getDate("fechaCotizacion").toLocalDate());
                cotizacion.setTotal(resultSet.getDouble("total"));
                cotizaciones.add(cotizacion);
            }
        }
        return cotizaciones;
    }

    @Override
    public CotizacionDTO insertar(CotizacionDTO cotizacion) throws SQLException {
        String sql = "INSERT INTO cotizacion (fechaCotizacion, total) "
        		   + "VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(cotizacion.getFechaCotizacion()));
            statement.setDouble(2, cotizacion.getTotal());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cotizacion.setIdCotizacion(generatedKeys.getLong(1));
            }
        } 
        
        return cotizacion;
    }

    @Override
    public void actualizar(CotizacionDTO cotizacion) throws SQLException {
        String sql = "UPDATE cotizacion "
        		   + "SET fechaCotizacion = ?, total = ? "
        		   + "WHERE idCotizacion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(cotizacion.getFechaCotizacion()));
            statement.setDouble(2, cotizacion.getTotal());
            statement.setLong(3, cotizacion.getIdCotizacion());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Object id) throws SQLException {
        String sql = "DELETE FROM cotizacion "
        		   + "WHERE idCotizacion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, (Long) id);
            statement.executeUpdate();
        }
    }
}
