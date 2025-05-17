package mx.com.qtx.cotizadorv1ds.persistencia;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.servicios.SubComponenteDTO;

public interface SubComponenteDAO {

    SubComponenteDTO obtener(String idPc, String idSubComponente) throws SQLException;
    List<SubComponenteDTO> obtenerTodos() throws SQLException;
    List<SubComponenteDTO> obtenerSubComponentesPorPc(String idPc) throws SQLException;
    SubComponenteDTO insertar(SubComponenteDTO subComponenteDTO) throws SQLException;
    void actualizar(SubComponenteDTO subComponenteDTO) throws SQLException;
    void eliminar(String idPc, String idSubComponente) throws SQLException;
    void eliminarSubComponentesPorPc(String idPc) throws SQLException;
}
