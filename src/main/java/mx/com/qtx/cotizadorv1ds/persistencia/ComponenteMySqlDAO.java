package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;
import mx.com.qtx.cotizadorv1ds.servicios.ComponenteDTO;

public class ComponenteMySqlDAO implements ComponenteDAO {
    
	private static ComponenteMySqlDAO instance;
 
    public static synchronized ComponenteMySqlDAO getInstance() {
        if (instance == null) {
            instance = new ComponenteMySqlDAO();
        }
        return instance;
    }

    @Override
    public ComponenteDTO obtener(Object id) throws SQLException {
        ComponenteDTO componente = null;
        String sql = "SELECT idComponente, categoria, descripcion, memoria, capAlmacenamiento, costo, "
        		          + "precioBase, marca, modelo "
        		   + "FROM componente "
        		   + "WHERE idComponente = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, (String) id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                componente = new ComponenteDTO();
                componente.setIdComponente(resultSet.getString("idComponente"));
                componente.setCategoria(resultSet.getString("categoria"));
                componente.setDescripcion(resultSet.getString("descripcion"));
                componente.setMemoria(resultSet.getString("memoria"));
                componente.setCapAlmacenamiento(resultSet.getString("capAlmacenamiento"));
                componente.setCosto(resultSet.getDouble("costo"));
                componente.setPrecioBase(resultSet.getDouble("precioBase"));
                componente.setMarca(resultSet.getString("marca"));
                componente.setModelo(resultSet.getString("modelo"));
            }
        }
        return componente;
    }

    @Override
    public List<ComponenteDTO> obtenerTodos() throws SQLException {
        List<ComponenteDTO> componentes = new ArrayList<>();
        String sql = "SELECT idComponente, categoria, descripcion, memoria, capAlmacenamiento, costo, "
        		          + "precioBase, marca, modelo "
        		          + "FROM componente";
        try (Connection conn = DatabaseConnector.getConnection();  
        	 Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                ComponenteDTO componente = new ComponenteDTO();
                componente.setIdComponente(resultSet.getString("idComponente"));
                componente.setCategoria(resultSet.getString("categoria"));
                componente.setDescripcion(resultSet.getString("descripcion"));
                componente.setMemoria(resultSet.getString("memoria"));
                componente.setCapAlmacenamiento(resultSet.getString("capAlmacenamiento"));
                componente.setCosto(resultSet.getDouble("costo"));
                componente.setPrecioBase(resultSet.getDouble("precioBase"));
                componente.setMarca(resultSet.getString("marca"));
                componente.setModelo(resultSet.getString("modelo"));
                componentes.add(componente);
            }
        }
        return componentes;
    }
    
    @Override
    public List<ComponenteDTO> obtenerTodosPorCategoria(String categoria) throws SQLException {
        List<ComponenteDTO> componentes = new ArrayList<>();
        String sql = "SELECT idComponente, categoria, descripcion, memoria, capAlmacenamiento, costo, "
        		          + "precioBase, marca, modelo "
        		          + "FROM componente "
        		          + "WHERE categoria = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, categoria);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComponenteDTO componente = new ComponenteDTO();
                componente.setIdComponente(resultSet.getString("idComponente"));
                componente.setCategoria(resultSet.getString("categoria"));
                componente.setDescripcion(resultSet.getString("descripcion"));
                componente.setMemoria(resultSet.getString("memoria"));
                componente.setCapAlmacenamiento(resultSet.getString("capAlmacenamiento"));
                componente.setCosto(resultSet.getDouble("costo"));
                componente.setPrecioBase(resultSet.getDouble("precioBase"));
                componente.setMarca(resultSet.getString("marca"));
                componente.setModelo(resultSet.getString("modelo"));
                componentes.add(componente);
            }
        }
        return componentes;
    }

    @Override
    public ComponenteDTO insertar(ComponenteDTO componente) throws SQLException {
        String sql = "INSERT INTO componente (idComponente, categoria, descripcion, memoria, "
        		                           + "capAlmacenamiento, costo, precioBase, marca, modelo) "
        		                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, componente.getIdComponente());
            statement.setString(2, componente.getCategoria());
            statement.setString(3, componente.getDescripcion());
            statement.setString(4, componente.getMemoria());
            statement.setString(5, componente.getCapAlmacenamiento());
            statement.setDouble(6, componente.getCosto());
            statement.setDouble(7, componente.getPrecioBase());
            statement.setString(8, componente.getMarca());
            statement.setString(9, componente.getModelo());
            statement.executeUpdate();
        }
        return componente; // Devuelve la misma instancia ya que la PK no es auto-increment
    }

    @Override
    public void actualizar(ComponenteDTO componente) throws SQLException {
        String sql = "UPDATE componente SET categoria = ?, descripcion = ?, memoria = ?, "
        		                         + "capAlmacenamiento = ?, costo = ?, precioBase = ?, "
        		                         + "marca = ?, modelo = ? "
        		   + "WHERE idComponente = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	 PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, componente.getCategoria());
            statement.setString(2, componente.getDescripcion());
            statement.setString(3, componente.getMemoria());
            statement.setString(4, componente.getCapAlmacenamiento());
            statement.setDouble(5, componente.getCosto());
            statement.setDouble(6, componente.getPrecioBase());
            statement.setString(7, componente.getMarca());
            statement.setString(8, componente.getModelo());
            statement.setString(9, componente.getIdComponente());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Object id) throws SQLException {
        String sql = "DELETE FROM componente "
        		   + "WHERE idComponente = ?";
        try (Connection conn = DatabaseConnector.getConnection();
        	PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, (String) id);
            statement.executeUpdate();
        }
    }
    
}