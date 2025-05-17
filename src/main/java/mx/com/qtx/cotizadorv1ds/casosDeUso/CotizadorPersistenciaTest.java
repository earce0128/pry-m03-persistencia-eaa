package mx.com.qtx.cotizadorv1ds.casosDeUso;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.config.Config;
import mx.com.qtx.cotizadorv1ds.core.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.core.componentes.DiscoDuro;
import mx.com.qtx.cotizadorv1ds.core.componentes.Monitor;
import mx.com.qtx.cotizadorv1ds.core.componentes.Pc;
import mx.com.qtx.cotizadorv1ds.core.componentes.TarjetaVideo;
import mx.com.qtx.cotizadorv1ds.core.cotizaciones.Cotizacion;
import mx.com.qtx.cotizadorv1ds.core.cotizaciones.ICotizador;
import mx.com.qtx.cotizadorv1ds.servicios.IGestorBDCotizador;
import mx.com.qtx.cotizadorv1ds.servicios.ImpGestorBDCotizadorMySQLDAO;

public class CotizadorPersistenciaTest {

	private static IGestorBDCotizador gestorBD = new ImpGestorBDCotizadorMySQLDAO();

	public static void main(String[] args) {

//      Pruebas Componentes
//    	testBuscarComponentesPersistencia();
//    	testCrearComponentesPersistencia();
//    	testAcualizarComponentesPersistencia();
//		testEliminarComponentesPersistencia();
		
//		Pruebas del Cotizador
//		testBuscarCotizaciones();
//		testInsertarCotizacion();
//		testActualizarCotizacion();
//		testEliminarCotizacion();
    	testCotizadorPersistencia();
		
	}

	private static void testEliminarCotizacion() {
		ICotizador cotizador = Config.getCotizador();
		
		try {
			
			//Obteniendo los componentes del cotizador
			Componente pc1 = gestorBD.obtenerPC("PC-0001");
			Componente monitor = gestorBD.obtenerMonitor("MON-0004");
			Componente tarjeta = gestorBD.obtenerTarjetaVideo("TVID-0002");
			Componente disco = gestorBD.obtenerDiscoDuro("DD-0005");
			
			cotizador.agregarComponente(2, pc1);
			cotizador.agregarComponente(3, monitor);
			cotizador.agregarComponente(1, tarjeta);
			cotizador.agregarComponente(3, disco);
			
			Cotizacion cotizacionAEliminar = cotizador.generarCotizacion();
			cotizacionAEliminar = gestorBD.insertarCotizacion(cotizacionAEliminar);
			cotizacionAEliminar.emitirComoReporte();
			
			cotizacionAEliminar = gestorBD.obtenerCotizacion(cotizacionAEliminar.getNum());
			gestorBD.eliminarCotizacion(cotizacionAEliminar.getNum());
			
						
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		}
	}

	private static void testActualizarCotizacion() {
		ICotizador cotizador = Config.getCotizador();
		
		try {
			
			//Obteniendo la cotizacion
			Cotizacion cotizacion = gestorBD.obtenerCotizacion(31L);
			
			//Obteniendo los componentes del cotizador
			Componente pc1 = gestorBD.obtenerPC("PC-0001");
			Componente monitor = gestorBD.obtenerMonitor("MON-0004");
			Componente tarjeta = gestorBD.obtenerTarjetaVideo("TVID-0002");
			Componente disco = gestorBD.obtenerDiscoDuro("DD-0005");
			
			cotizador.agregarComponente(2, pc1);
			cotizador.agregarComponente(3, monitor);
			cotizador.agregarComponente(1, tarjeta);
			cotizador.agregarComponente(3, disco);
			
			Cotizacion cotizacionEmitida = cotizador.generarCotizacion();
			cotizacionEmitida.setFecha(cotizacion.getFecha());
			cotizacionEmitida.setNum(cotizacion.getNum());
			
			gestorBD.actualizarCotizacion(cotizacionEmitida);
			cotizacionEmitida = gestorBD.obtenerCotizacion(cotizacionEmitida.getNum());
			cotizacionEmitida.emitirComoReporte();
			
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		}
	}

	private static void testInsertarCotizacion() {
		ICotizador cotizador = Config.getCotizador();
		
		try {
			//Obteniendo los componentes del cotizador
			Componente pc1 = gestorBD.obtenerPC("PC-0001");
			Componente pc2 = gestorBD.obtenerPC("PC-0003");
			Componente monitor = gestorBD.obtenerMonitor("MON-0004");
			Componente tarjeta = gestorBD.obtenerTarjetaVideo("TVID-0002");
			Componente disco = gestorBD.obtenerDiscoDuro("DD-0005");
			
			cotizador.agregarComponente(2, pc1);
			cotizador.agregarComponente(1, pc2);
			cotizador.agregarComponente(3, monitor);
			cotizador.agregarComponente(5, tarjeta);
			cotizador.agregarComponente(8, disco);
			
			Cotizacion cotizacion = cotizador.generarCotizacion();
			cotizacion = gestorBD.insertarCotizacion(cotizacion);
			cotizacion.emitirComoReporte();
			
			
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de insertar cotizacion: " + e.getMessage());
		}
		
	}

	private static void testBuscarCotizaciones() {
		System.out.println("\n--------------------- Consultar Cotizaciones ---------------------\n");
		try {
			// Obtener cotización por ID
			Cotizacion cot = gestorBD.obtenerCotizacion(6L);
			cot.emitirComoReporte();
			System.out.println();
			
			// Obtener todas las cotizaciones
			List<Cotizacion> cotizaciones = gestorBD.obtenerTodasCotizaciones();
			for(Cotizacion cotI : cotizaciones ) {
				cotI.emitirComoReporte();
				System.out.println();
			}
			
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de consultar cotizacion: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de consultar cotizacion: " + e.getMessage());
		} 
		
	}

	private static void testCrearComponentesPersistencia() {
		System.out.println("\n--------------------- Creando Componentes ---------------------\n");
		try {
			
			Componente disco = Componente.crearDiscoDuro("DD-0006", "Disco Duro Prueba", "Samsung", "SSS-TEST",
					new BigDecimal(60.00), new BigDecimal(100.00), "250GB");

			gestorBD.insertarDiscoDuro(disco);

			Componente monitor = Componente.crearMonitor("MON-0006", "Monitor Prueba", "LG", "LG-TEST",
					new BigDecimal(300.00), new BigDecimal(500.00));

			gestorBD.insertarMonitor(monitor);

			Componente tarjeta = Componente.crearTarjetaVideo("TVID-0006", "Tarjeta V Prueba", "ASUS", "ASUS-TEST",
					new BigDecimal(300.00), new BigDecimal(450.00), "12GB");

			gestorBD.insertarTarjetaVideo(tarjeta);

			Componente disco1 = Componente.crearDiscoDuro("DD-0007", "Disco Duro Prueba2", "Samsung", "SSS-TEST-2",
					new BigDecimal(80.00), new BigDecimal(120.00), "500GB");

			gestorBD.insertarDiscoDuro(disco1);

			DiscoDuro discoPc = (DiscoDuro) disco;
			DiscoDuro disco2Pc = (DiscoDuro) disco1;
			Monitor monitorPc = (Monitor) monitor;
			TarjetaVideo tarjetaPc = (TarjetaVideo) tarjeta;

			// Creamos una PC con utilizando Builder
			Componente pc = Componente.getPcBuilder().definirId("PC-0006")
					.definirDescripcion("Laptop 15000 s300 de prueba").definirMarcaYmodelo("Dell", "Terminator")
					.agregarDisco(discoPc.getId(), discoPc.getDescripcion(), discoPc.getMarca(), discoPc.getModelo(),
							discoPc.getCosto(), discoPc.getPrecioBase(), discoPc.getCapacidadAlm())
					.agregarMonitor(monitorPc.getId(), monitorPc.getDescripcion(), monitorPc.getMarca(),
							monitorPc.getModelo(), monitorPc.getCosto(), monitorPc.getPrecioBase())
					.agregarTarjetaVideo(tarjetaPc.getId(), tarjetaPc.getDescripcion(), tarjetaPc.getMarca(),
							tarjetaPc.getModelo(), tarjetaPc.getCosto(), tarjetaPc.getPrecioBase(),
							tarjetaPc.getMemoria())
					.agregarDisco(disco2Pc.getId(), disco2Pc.getDescripcion(), disco2Pc.getMarca(),
							disco2Pc.getModelo(), disco2Pc.getCosto(), disco2Pc.getPrecioBase(),
							disco2Pc.getCapacidadAlm())
					.build();
			gestorBD.insertarPC(pc);
			
			// Obtener todos los componentes
			List<Componente> componentes = gestorBD.obtenerTodosComponentes();
			for(Componente compI : componentes)  compI.mostrarCaracteristicas();
			
			// Limpiando los datos de prueba
			gestorBD.eliminarPC(pc.getId());
			gestorBD.eliminarDiscoDuro(disco.getId());
			gestorBD.eliminarDiscoDuro(disco1.getId());
			gestorBD.eliminarMonitor(monitor.getId());
			gestorBD.eliminarTarjetaVideo(tarjeta.getId());
			
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de crear componentes: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de crear componentes: " + e.getMessage());
		}
		
	}

	private static void testBuscarComponentesPersistencia() {
		System.out.println("\n--------------------- Buscando Componentes ---------------------\n");
		try {
			// Obtener todos los componentes
			List<Componente> componentes = gestorBD.obtenerTodosComponentes();
			for (Componente compI : componentes) {
				compI.mostrarCaracteristicas();
			}
		}catch (SQLException e) {
			System.err.println("Error durante las pruebas de buscar componentes: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de buscar componentes: " + e.getMessage());
		}
	}

	private static void testEliminarComponentesPersistencia() {
		System.out.println("\n--------------------- Eliminando Componentes ---------------------\n");
		try {

			// Obtener los componentes a ser eliminados
			Componente disco = gestorBD.obtenerDiscoDuro("DD-0005");
			Componente monitor = gestorBD.obtenerMonitor("MON-0005");
			Componente tarjeta = gestorBD.obtenerTarjetaVideo("TVID-0005");
			Componente pc = gestorBD.obtenerPC("PC-0005");

			// Estos componentes ya están asociados a PCs o Cotizaciones por lo que no se
			// eliminarán
			gestorBD.eliminarDiscoDuro(disco.getId());
			gestorBD.eliminarMonitor(monitor.getId());
			gestorBD.eliminarTarjetaVideo(tarjeta.getId());
			gestorBD.eliminarPC(pc.getId());
			
			// Se crearán componentes para ser eliminados
			Componente discoE = Componente.crearDiscoDuro("DD-0007", "Desc DD-0007", "MarcaDD", "DD-007",
					new BigDecimal(50.00), new BigDecimal(100.00), "128GB");
			Componente monitorE = Componente.crearMonitor("MON-0007", "Desc MON-007", "MarcaMON", "MON-0007",
					new BigDecimal(150.00), new BigDecimal(200));
			Componente tarjetaE = Componente.crearTarjetaVideo("TVID-0007", "Desc TV-0007", "MarcaTVID-0007",
					"TVID-0007", new BigDecimal(300), new BigDecimal(400), "12GB");
			
			// Componentes especiales para la PC
			Componente disco1 = Componente.crearDiscoDuro("DD-0008", "Desc DD-0008", "MarcaDD", "DD-008",
					new BigDecimal(50.00), new BigDecimal(100.00), "128GB");
			Componente monitor1 = Componente.crearMonitor("MON-0008", "Desc MON-008", "MarcaMON", "MON-0008",
					new BigDecimal(150.00), new BigDecimal(200));
			Componente tarjeta1 = Componente.crearTarjetaVideo("TVID-0008", "Desc TV-0008", "MarcaTVID-0008",
					"TVID-0007", new BigDecimal(300), new BigDecimal(400), "12GB");

			DiscoDuro discoPc = (DiscoDuro) disco1;
			Monitor monitorPc = (Monitor) monitor1;
			TarjetaVideo tarjetaPc = (TarjetaVideo) tarjeta1;
			
			// Creamos una PC con utilizando Builder
			Componente pc1 = Componente.getPcBuilder().definirId("PC-0007")
					.definirDescripcion("Laptop 15000 s300 de prueba")
					.definirMarcaYmodelo("Dell", "Terminator-0007")
					.agregarDisco(discoPc.getId(), discoPc.getDescripcion(), discoPc.getMarca(), discoPc.getModelo(),
							discoPc.getCosto(), discoPc.getPrecioBase(), discoPc.getCapacidadAlm())
					.agregarMonitor(monitorPc.getId(), monitorPc.getDescripcion(), monitorPc.getMarca(),
							monitorPc.getModelo(), monitorPc.getCosto(), monitorPc.getPrecioBase())
					.agregarTarjetaVideo(tarjetaPc.getId(), tarjetaPc.getDescripcion(), tarjetaPc.getMarca(),
							tarjetaPc.getModelo(), tarjetaPc.getCosto(), tarjetaPc.getPrecioBase(),
							tarjetaPc.getMemoria())
					.build();
			gestorBD.insertarDiscoDuro(disco1);
			gestorBD.insertarMonitor(monitor1);
			gestorBD.insertarTarjetaVideo(tarjeta1);
			gestorBD.insertarPC(pc1);
			
			// Eliminar componentes
			gestorBD.eliminarDiscoDuro(discoE.getId());
			gestorBD.eliminarMonitor(monitorE.getId());
			gestorBD.eliminarTarjetaVideo(tarjetaE.getId());
			
			gestorBD.eliminarPC(pc1.getId());
			gestorBD.eliminarDiscoDuro(disco1.getId());
			gestorBD.eliminarMonitor(monitor1.getId());
			gestorBD.eliminarTarjetaVideo(tarjeta1.getId());
			
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de eliminar componentes: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de eliminar componentes: " + e.getMessage());
		}

	}

	private static void testAcualizarComponentesPersistencia() {
		System.out.println("\n--------------------- Actualizando Componentes ---------------------\n");

		try {
			// Obtener los componentes a ser actualizados
			DiscoDuro disco = (DiscoDuro) gestorBD.obtenerDiscoDuro("DD-0005");
			Monitor monitor = (Monitor) gestorBD.obtenerMonitor("MON-0005");
			TarjetaVideo tarjeta = (TarjetaVideo) gestorBD.obtenerTarjetaVideo("TVID-0005");
			Pc pc = (Pc) gestorBD.obtenerPC("PC-0005");

			disco.setDescripcion("Disco Duro Prueba Actualizado");
			disco.setCosto(new BigDecimal(70.00));
			disco.setPrecioBase(new BigDecimal(115.00));
			disco.setCapacidadAlm("250GB");
			gestorBD.actualizarDiscoDuro(disco);

			monitor.setDescripcion("Monitor Prueba Actualizado");
			monitor.setCosto(new BigDecimal(400.00));
			monitor.setPrecioBase(new BigDecimal(600.00));
			monitor.setModelo("LG-TEST-ACT");
			gestorBD.actualizarMonitor(monitor);

			tarjeta.setDescripcion("Tarjeta V Prueba Act");
			tarjeta.setMarca("ASUS Act");
			tarjeta.setModelo("ASUS-TEST ACT");
			tarjeta.setCosto(new BigDecimal(150.00));
			tarjeta.setPrecioBase(new BigDecimal(300.00));
			gestorBD.actualizarTarjetaVideo(tarjeta);

			// Creamos una PC con utilizando Builder
			pc.setDescripcion("Laptop 15000 s300 de prueba actualizada");
			pc.setModelo("Terminator-Actalizado");

			pc = Componente.getPcBuilder().definirId(pc.getId()).definirDescripcion(pc.getDescripcion())
					.definirMarcaYmodelo(pc.getMarca(), pc.getModelo())
					.agregarDisco(disco.getId(), disco.getDescripcion(), disco.getMarca(), disco.getModelo(),
							disco.getCosto(), disco.getPrecioBase(), disco.getCapacidadAlm())
					.agregarMonitor(monitor.getId(), monitor.getDescripcion(), monitor.getMarca(), monitor.getModelo(),
							monitor.getCosto(), monitor.getPrecioBase())
					.agregarTarjetaVideo(tarjeta.getId(), tarjeta.getDescripcion(), tarjeta.getMarca(),
							tarjeta.getModelo(), tarjeta.getCosto(), tarjeta.getPrecioBase(), tarjeta.getMemoria())
					.build();
			gestorBD.actualizarPC(pc);

			// Obtener todos los componentes
			List<Componente> componentes = gestorBD.obtenerTodosComponentes();
			for (Componente compI : componentes) {
				compI.mostrarCaracteristicas();
			}

		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de actualizar componentes: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de actualizar componentes: " + e.getMessage());
		}
	}

	private static void testCotizadorPersistencia() {

		ICotizador cotizador = getCotizadorActual();
		try {
			Componente disco1 = gestorBD.obtenerDiscoDuro("DD-0004");
			Componente monitor1 = gestorBD.obtenerMonitor("MON-0003");
			Componente tarjeta1 = gestorBD.obtenerTarjetaVideo("TVID-0001");
			Componente pc1 = gestorBD.obtenerPC("PC-0002");

			Componente disco2 = Componente.crearDiscoDuro("DD-0006", "Disco Duro Prueba", "Samsung", "SSS-TEST",
					new BigDecimal(60.00), new BigDecimal(100.00), "250GB");

			gestorBD.insertarDiscoDuro(disco2);

			Componente monitor2 = Componente.crearMonitor("MON-0006", "Monitor Prueba", "LG", "LG-TEST",
					new BigDecimal(300.00), new BigDecimal(500.00));

			gestorBD.insertarMonitor(monitor2);

			Componente tarjeta2 = Componente.crearTarjetaVideo("TVID-0006", "Tarjeta V Prueba", "ASUS", "ASUS-TEST",
					new BigDecimal(300.00), new BigDecimal(450.00), "12GB");

			gestorBD.insertarTarjetaVideo(tarjeta2);

			Componente disco3 = Componente.crearDiscoDuro("DD-0007", "Disco Duro Prueba2", "Samsung", "SSS-TEST-2",
					new BigDecimal(80.00), new BigDecimal(120.00), "500GB");

			 gestorBD.insertarDiscoDuro(disco3);

			DiscoDuro discoPc = (DiscoDuro) disco2;
			DiscoDuro disco2Pc = (DiscoDuro) disco3;
			Monitor monitorPc = (Monitor) monitor2;
			TarjetaVideo tarjetaPc = (TarjetaVideo) tarjeta2;

			// Creamos una PC con utilizando Builder
			Componente pc2 = Componente.getPcBuilder().definirId("PC-0006")
					.definirDescripcion("Laptop 15000 s300 de prueba").definirMarcaYmodelo("Dell", "Terminator")
					.agregarDisco(discoPc.getId(), discoPc.getDescripcion(), discoPc.getMarca(), discoPc.getModelo(),
							discoPc.getCosto(), discoPc.getPrecioBase(), discoPc.getCapacidadAlm())
					.agregarMonitor(monitorPc.getId(), monitorPc.getDescripcion(), monitorPc.getMarca(),
							monitorPc.getModelo(), monitorPc.getCosto(), monitorPc.getPrecioBase())
					.agregarTarjetaVideo(tarjetaPc.getId(), tarjetaPc.getDescripcion(), tarjetaPc.getMarca(),
							tarjetaPc.getModelo(), tarjetaPc.getCosto(), tarjetaPc.getPrecioBase(),
							tarjetaPc.getMemoria())
					.agregarDisco(disco2Pc.getId(), disco2Pc.getDescripcion(), disco2Pc.getMarca(),
							disco2Pc.getModelo(), disco2Pc.getCosto(), disco2Pc.getPrecioBase(),
							disco2Pc.getCapacidadAlm())
					.build();

			pc2.mostrarCaracteristicas();
			gestorBD.insertarPC(pc2);

			cotizador.agregarComponente(3, monitor1);
			cotizador.agregarComponente(1, disco1);
			cotizador.agregarComponente(2, tarjeta1);
			cotizador.agregarComponente(4, pc1);
			cotizador.agregarComponente(1, disco2);
			cotizador.agregarComponente(5, monitor2);
			cotizador.agregarComponente(1, tarjeta2);
			cotizador.agregarComponente(2, pc2);

			Cotizacion cotizacion = cotizador.generarCotizacion();
			cotizacion = gestorBD.insertarCotizacion(cotizacion);
			cotizacion.emitirComoReporte();
			
		} catch (SQLException e) {
			System.err.println("Error durante las pruebas de Cotizador: " + e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error durante las pruebas de Cotizador: " + e.getMessage());
			// e.printStackTrace();
		}
	}
	
	private static ICotizador getCotizadorActual() {
		return Config.getCotizador();
	}

}