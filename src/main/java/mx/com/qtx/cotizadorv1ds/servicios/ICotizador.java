package mx.com.qtx.cotizadorv1ds.servicios;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.cotizador.Cotizacion;
import mx.com.qtx.cotizadorv1ds.exception.ComponenteInvalidoException;

public interface ICotizador {
    void agregarComponente(int cantidad, Componente componente);
    void eliminarComponente(String idComponente) throws ComponenteInvalidoException;
    Cotizacion generarCotizacion();
    void listarComponentes();
}
