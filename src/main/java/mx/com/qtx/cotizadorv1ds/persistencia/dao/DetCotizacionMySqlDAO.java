package mx.com.qtx.cotizadorv1ds.persistencia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.dto.DetCotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;

public class DetCotizacionMySqlDAO implements DetCotizacionDAO {
    private static DetCotizacionMySqlDAO instance;

    public static synchronized DetCotizacionMySqlDAO getInstance() {
        if (instance == null) {
            instance = new DetCotizacionMySqlDAO();
        }
        return instance;
    }

    @Override
    public DetCotizacionDTO obtener(Long idCotizacion, String idComponente) throws SQLException {
        DetCotizacionDTO detalle = null;
        String sql = "SELECT idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, "
        		          + "precioBase "
        		   + "FROM det_cotizacion "
        		   + "WHERE idCotizacion = ? "
        		   + "AND idComponente = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, idCotizacion);
            statement.setString(2, idComponente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                detalle = new DetCotizacionDTO();
                detalle.setIdCotizacion(resultSet.getLong("idCotizacion"));
                detalle.setIdComponente(resultSet.getString("idComponente"));
                detalle.setCantidad(resultSet.getInt("cantidad"));
                detalle.setCategoria(resultSet.getString("categoria"));
                detalle.setDescripcion(resultSet.getString("descripcion"));
                detalle.setImporteCotizado(resultSet.getDouble("importeCotizado"));
                detalle.setPrecioBase(resultSet.getDouble("precioBase"));
            }
        }
        return detalle;
    }

    @Override
    public List<DetCotizacionDTO> obtenerTodosPorCotizacion(Long idCotizacion) throws SQLException {
        List<DetCotizacionDTO> detalles = new ArrayList<>();
        String sql = "SELECT idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, "
        		          + "precioBase "
        		   + "FROM det_cotizacion "
        		   + "WHERE idCotizacion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, idCotizacion);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DetCotizacionDTO detalle = new DetCotizacionDTO();
                detalle.setIdCotizacion(resultSet.getLong("idCotizacion"));
                detalle.setIdComponente(resultSet.getString("idComponente"));
                detalle.setCantidad(resultSet.getInt("cantidad"));
                detalle.setCategoria(resultSet.getString("categoria"));
                detalle.setDescripcion(resultSet.getString("descripcion"));
                detalle.setImporteCotizado(resultSet.getDouble("importeCotizado"));
                detalle.setPrecioBase(resultSet.getDouble("precioBase"));
                detalles.add(detalle);
            }
        }
        return detalles;
    }

    @Override
    public List<DetCotizacionDTO> obtenerTodos() throws SQLException {
        List<DetCotizacionDTO> detalles = new ArrayList<>();
        String sql = "SELECT idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, "
        		          + "precioBase "
        		          + "FROM det_cotizacion";
        try (Connection conn = DatabaseConnector.getConnection();
        	 Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DetCotizacionDTO detalle = new DetCotizacionDTO();
                detalle.setIdCotizacion(resultSet.getLong("idCotizacion"));
                detalle.setIdComponente(resultSet.getString("idComponente"));
                detalle.setCantidad(resultSet.getInt("cantidad"));
                detalle.setCategoria(resultSet.getString("categoria"));
                detalle.setDescripcion(resultSet.getString("descripcion"));
                detalle.setImporteCotizado(resultSet.getDouble("importeCotizado"));
                detalle.setPrecioBase(resultSet.getDouble("precioBase"));
                detalles.add(detalle);
            }
        }
        return detalles;
    }

    @Override
    public DetCotizacionDTO insertar(DetCotizacionDTO detalle) throws SQLException {
        String sql = "INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, "
        		                               + "descripcion, importeCotizado, precioBase) "
        		   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, detalle.getIdCotizacion());
            statement.setString(2, detalle.getIdComponente());
            statement.setInt(3, detalle.getCantidad());
            statement.setString(4, detalle.getCategoria());
            statement.setString(5, detalle.getDescripcion());
            statement.setDouble(6, detalle.getImporteCotizado());
            statement.setDouble(7, detalle.getPrecioBase());
            statement.executeUpdate();
        }
        return detalle;
    }

    @Override
    public void actualizar(DetCotizacionDTO detalle) throws SQLException {
        String sql = "UPDATE det_cotizacion SET cantidad = ?, categoria = ?, descripcion = ?, "
        		                             + "importeCotizado = ?, precioBase = ? "
        		   + "WHERE idCotizacion = ? "
        		   + "AND idComponente = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, detalle.getCantidad());
            statement.setString(2, detalle.getCategoria());
            statement.setString(3, detalle.getDescripcion());
            statement.setDouble(4, detalle.getImporteCotizado());
            statement.setDouble(5, detalle.getPrecioBase());
            statement.setLong(6, detalle.getIdCotizacion());
            statement.setString(7, detalle.getIdComponente());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long idCotizacion, String idComponente) throws SQLException {
        String sql = "DELETE FROM det_cotizacion "
        		   + "WHERE idCotizacion = ? "
        		   + "AND idComponente = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        		PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, idCotizacion);
            statement.setString(2, idComponente);
            statement.executeUpdate();
        }
    }
    
    @Override
    public void eliminarPorCotizacion(Long idCotizacion) throws SQLException {
        String sql = "DELETE FROM det_cotizacion "
        		   + "WHERE idCotizacion = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, idCotizacion);
            statement.executeUpdate();
        }
    }
}
