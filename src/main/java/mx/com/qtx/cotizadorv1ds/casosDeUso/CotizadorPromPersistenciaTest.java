package mx.com.qtx.cotizadorv1ds.casosDeUso;

import java.sql.SQLException;
import java.util.Map;

import mx.com.qtx.cotizadorv1ds.config.Config;
import mx.com.qtx.cotizadorv1ds.core.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.core.cotizaciones.Cotizacion;
import mx.com.qtx.cotizadorv1ds.core.cotizaciones.ICotizador;
import mx.com.qtx.cotizadorv1ds.core.promos.PromDsctoPlano;
import mx.com.qtx.cotizadorv1ds.core.promos.PromDsctoXcantidad;
import mx.com.qtx.cotizadorv1ds.core.promos.PromNXM;
import mx.com.qtx.cotizadorv1ds.core.promos.PromSinDescto;
import mx.com.qtx.cotizadorv1ds.core.promos.Promocion;
import mx.com.qtx.cotizadorv1ds.core.promos.PromocionBuilder;
import mx.com.qtx.cotizadorv1ds.servicios.IGestorBDCotizador;
import mx.com.qtx.cotizadorv1ds.servicios.ImpGestorBDCotizadorMySQLDAO;

public class CotizadorPromPersistenciaTest {
	
	private static IGestorBDCotizador gestorBD = new ImpGestorBDCotizadorMySQLDAO();
	
	private static Map<Integer, Double> mapDsctos = Map.of(0,  0.0,
			   3,  5.0,
			   6, 10.0,
			   9, 12.0);

	public static void main(String[] args) {
		testGenerarCotizacionPersistencia();

	}
	private static void testGenerarCotizacionPersistencia() {
		
		try {
		
		ICotizador cotizador = getCotizadorActual();
		
		Componente monitor = gestorBD.obtenerMonitor("MON-0004");
		
		Promocion promoMonitor = getPromo_2x1_mas_5_mas_10();
		Promocion.mostrarEstructuraPromocion(promoMonitor);
		
		monitor.setPromo(promoMonitor);
		cotizador.agregarComponente(10, monitor);

		Componente monitor2 = gestorBD.obtenerMonitor("MON-0001");
		
		monitor2.setPromo(Promocion.getBuilder()
				                   .conPromocionBaseSinDscto()
				                   .agregarDsctoXcantidad(mapDsctos)
				                   .build());
		cotizador.agregarComponente(4, monitor2);
		
		Componente disco = gestorBD.obtenerDiscoDuro("DD-0003");
		cotizador.agregarComponente(10, disco);
	
	    Componente tarjeta = gestorBD.obtenerTarjetaVideo("TVID-0005"); 
	    		
	    tarjeta.setPromo(Promocion.getBuilder().conPromocionBaseNXM(3, 2).build());
		cotizador.agregarComponente(10, tarjeta);
	    
		Componente miPc = gestorBD.obtenerPC("PC-0003"); 
    	
		miPc.setPromo(Promocion.getBuilder()
				                   .conPromocionBaseSinDscto()
				                   .agregarDsctoPlano(30f)
				                   .agregarDsctoPlano(20f)
				                   .build());
		cotizador.agregarComponente(1, miPc);

		Cotizacion cotizacion = cotizador.generarCotizacion();
		cotizacion = gestorBD.insertarCotizacion(cotizacion);
		cotizacion.emitirComoReporte();
		
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		} 
	}
	
	private static Promocion getPromo_SinDscto() {
		Promocion promo = new PromSinDescto();
		return promo;
	}
	
	private static Promocion getPromo_3x2() {
		Promocion promo = new PromNXM(3, 2);
		return promo;
	}
	
	private static Promocion getPromo_2x1_mas_5() {
		Promocion promo = new PromDsctoPlano(new PromNXM(2,1), 5.0f);
		return promo;
	}

	private static Promocion getPromo_2x1_mas_5_mas_10() {
		Promocion promo = new PromDsctoPlano(
								new PromDsctoPlano(
										new PromNXM(2,1), 
										5.0f),
								10.0f);
		return promo;
	}

	private static Promocion getPromo_2x1_mas_dsctosXcantidad() {
		Promocion promo = new PromDsctoXcantidad(new PromNXM(2,1), 
				                                 mapDsctos);
		return promo;
	}
	
	private static Promocion getPromo_3x2_mas_5_mas_10_mas_dsctosXcantidad() {
		PromocionBuilder promoBuilder = Promocion.getBuilder();
		
		Promocion promo = promoBuilder.conPromocionBaseNXM(3,2)
		            				  .agregarDsctoPlano(5.0f)
		            				  .agregarDsctoPlano(10f)
		            				  .agregarDsctoXcantidad(mapDsctos)
		            				  .build();
		
		return promo;
	}

	private static ICotizador getCotizadorActual() {
		return Config.getCotizador();
	}

}
