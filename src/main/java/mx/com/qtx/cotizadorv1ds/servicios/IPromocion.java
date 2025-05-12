package mx.com.qtx.cotizadorv1ds.servicios;

import java.math.BigDecimal;

public interface IPromocion {
	BigDecimal calcularImportePromocion(int cant, BigDecimal precioBase);
}
