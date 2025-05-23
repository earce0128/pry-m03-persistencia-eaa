package mx.com.qtx.cotizadorv1ds.servicios;

import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.core.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.core.cotizaciones.Cotizacion;
import mx.com.qtx.cotizadorv1ds.core.cotizaciones.DetalleCotizacion;
import mx.com.qtx.cotizadorv1ds.core.promos.Promocion;

public interface IGestorBDCotizador {

    // Métodos para Componentes
    Componente obtenerDiscoDuro(String idDiscoDuro) throws SQLException;
    Componente obtenerMonitor(String idMonitor) throws SQLException;
    Componente obtenerTarjetaVideo(String idTarjetaVideo) throws SQLException;
    Componente obtenerPC(String idPc) throws SQLException;
    Promocion obtenerPromocionXId(Long numProm) throws SQLException;
    List<Componente> obtenerTodosComponentes() throws SQLException;

    Componente insertarDiscoDuro(Componente compDiscoDuro) throws SQLException;
    Componente insertarMonitor(Componente compMonitor) throws SQLException;
    Componente insertarTarjetaVideo(Componente compTarjetaVideo) throws SQLException;
    Componente insertarPC(Componente compPc) throws SQLException;
    Componente agregarPromocion(Promocion prom, Componente comp) throws SQLException;

    void actualizarDiscoDuro(Componente compDiscoDuro) throws SQLException;
    void actualizarMonitor(Componente compMonitor) throws SQLException;
    void actualizarTarjetaVideo(Componente compTarjetaVideo) throws SQLException;
    void actualizarPC(Componente compPc) throws SQLException;

    boolean eliminarDiscoDuro(String idDiscoDuro) throws SQLException;
    boolean eliminarMonitor(String idMonitor) throws SQLException;
    boolean eliminarTarjetaVideo(String idTarjetaVideo) throws SQLException;
    boolean eliminarPC(String idPc) throws SQLException;

    // Métodos para Cotizaciones
    Cotizacion obtenerCotizacion(Long idCotizacion) throws SQLException;
    List<Cotizacion> obtenerTodasCotizaciones() throws SQLException;
    Cotizacion insertarCotizacion(Cotizacion cotizacion) throws SQLException;
    void actualizarCotizacion(Cotizacion cotizacion) throws SQLException;
    boolean eliminarCotizacion(Long idCotizacion) throws SQLException;

    // Métodos para Detalles de Cotizacion
    //DetalleCotizacion obtenerDetalleCotizacion(Long idCotizacion, String idComponente) throws SQLException;
    List<DetalleCotizacion> obtenerDetallesPorCotizacion(Long idCotizacion) throws SQLException;
}
