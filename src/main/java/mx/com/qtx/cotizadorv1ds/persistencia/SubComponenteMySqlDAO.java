package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;
import mx.com.qtx.cotizadorv1ds.servicios.SubComponenteDTO;

public class SubComponenteMySqlDAO implements SubComponenteDAO {

	
	private static SubComponenteMySqlDAO instance;
	/*private final DatabaseConnector connector;

	private SubComponenteMySqlDAO() {
		this.connector = DatabaseConnector.getInstance();
	}
	*/

	public static SubComponenteMySqlDAO getInstance() {
		if (instance == null) {
			synchronized (SubComponenteMySqlDAO.class) {
				if (instance == null) {
					instance = new SubComponenteMySqlDAO();
				}
			}
		}
		return instance;
	}

	@Override
	public SubComponenteDTO obtener(String idPc, String idSubComponente) throws SQLException {
		String sql = "SELECT idPC, idSubComponente, cantidad " 
	               + "FROM sub_componente_pc " 
				   + "WHERE idPc = ? "
				   + "AND idSubComponente = ?";
		try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idPc);
			stmt.setString(2, idSubComponente);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToDTO(rs);
				}
			}
		}
		return null;
	}

	@Override
	public List<SubComponenteDTO> obtenerTodos() throws SQLException {
		String sql = "SELECT idPC, idSubComponente, cantidad " 
	               + "FROM sub_componente_pc";
		List<SubComponenteDTO> subComponentes = new ArrayList<>();
		try (Connection conn = DatabaseConnector.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				subComponentes.add(mapResultSetToDTO(rs));
			}
		}
		return subComponentes;
	}
	
	@Override
	public List<SubComponenteDTO> obtenerSubComponentesPorPc(String idPc) throws SQLException {
		String sql = "SELECT idPC, idSubComponente, cantidad " 
	               + "FROM sub_componente_pc "
				   + "WHERE idPc = ?";
		List<SubComponenteDTO> subComponentes = new ArrayList<>();
		try (Connection conn = DatabaseConnector.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idPc);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					subComponentes.add(mapResultSetToDTO(rs));
				}
			}
		}
		return subComponentes;
	}

	@Override
	public SubComponenteDTO insertar(SubComponenteDTO subComponenteDTO) throws SQLException {
		String sql = "INSERT INTO sub_componente_pc (idPC, idSubComponente, cantidad) "
				   + "VALUES (?, ?, ?)";
		
		try (Connection conn = DatabaseConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, subComponenteDTO.getIdPC());
			stmt.setString(2, subComponenteDTO.getIdSubComponente());
			stmt.setInt(3, subComponenteDTO.getCantidad());
			stmt.executeUpdate();
		}
		return subComponenteDTO;
	}

	@Override
	public void actualizar(SubComponenteDTO subComponenteDTO) throws SQLException {
		String sql = "UPDATE sub_componente_pc "
				   + "SET cantidad = ? "
				   + "WHERE idPC = ?"
				   + "AND idSubComponente = ?";
		try (Connection conn = DatabaseConnector.getConnection(); 
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, subComponenteDTO.getCantidad());
			stmt.setString(2, subComponenteDTO.getIdPC());
			stmt.setString(3, subComponenteDTO.getIdSubComponente());
			stmt.executeUpdate();
		}
	}

	@Override
	public void eliminar(String idPc, String idSubComponente) throws SQLException {
		String sql = "DELETE FROM sub_componente_pc "
				   + "WHERE idPC = ? "
				   + "AND idSubComponente = ?";
		try (Connection conn = DatabaseConnector.getConnection(); 
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idPc);
			stmt.setString(2, idSubComponente);
			stmt.executeUpdate();
		}
	}

	@Override
	public void eliminarSubComponentesPorPc(String idPc) throws SQLException {
		String sql = "DELETE FROM sub_componente_pc "
				    + "WHERE idPC = ?";
		try (Connection conn = DatabaseConnector.getConnection(); 
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idPc);
			stmt.executeUpdate();
		}
	}

	private SubComponenteDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
		SubComponenteDTO dto = new SubComponenteDTO();
		dto.setIdPC(rs.getString("idPC"));
		dto.setIdSubComponente(rs.getString("idSubComponente"));
		dto.setCantidad(rs.getInt("cantidad"));
		return dto;
	}
}
