package mx.com.qtx.cotizadorv1ds.persistencia.test;

import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.persistencia.ComponenteMySqlDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.CotizacionMySqlDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.DetCotizacionMySqlDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.SubComponenteMySqlDAO;
import mx.com.qtx.cotizadorv1ds.servicios.ComponenteDTO;
import mx.com.qtx.cotizadorv1ds.servicios.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.servicios.DetCotizacionDTO;
import mx.com.qtx.cotizadorv1ds.servicios.SubComponenteDTO;

public class DAOTest {
	
	// Obtener instancias de los DAOs (Singleton)
	static CotizacionMySqlDAO cotizacionDAO = CotizacionMySqlDAO.getInstance();
    static DetCotizacionMySqlDAO detCotizacionDAO = DetCotizacionMySqlDAO.getInstance();
    static ComponenteMySqlDAO componenteDAO = ComponenteMySqlDAO.getInstance();
    static SubComponenteMySqlDAO subComponenteDAO = SubComponenteMySqlDAO.getInstance();
	
	public static void main(String[] args) {
		testComponenteDAO();
    	testSubComponenteDAO();
		testCotizacionDAO();
    	testDetalleCotizacionDAO();
	}

	private static void testSubComponenteDAO() {
		try {
            System.out.println("\n--- Pruebas de SubComponenteDAO ---");
            // Insertar subcomponentes (necesitamos componentes existentes)
            ComponenteDTO pcPrueba = new ComponenteDTO();
            pcPrueba.setIdComponente("PC-TEST");
            pcPrueba.setCategoria("PC");
            pcPrueba.setDescripcion("PC de prueba");
            pcPrueba.setCosto(50.00);
            pcPrueba.setPrecioBase(75.00);
            pcPrueba = componenteDAO.insertar(pcPrueba);

            ComponenteDTO subComponentePrueba1 = new ComponenteDTO();
            subComponentePrueba1.setIdComponente("SUB1-TEST");
            subComponentePrueba1.setCategoria("TestSub");
            subComponentePrueba1.setDescripcion("Subcomponente Pc de prueba");
            subComponentePrueba1.setCosto(50.0);
            subComponentePrueba1.setPrecioBase(75.0);
            subComponentePrueba1 = componenteDAO.insertar(subComponentePrueba1);
            
            ComponenteDTO subComponentePrueba2 = new ComponenteDTO();
            subComponentePrueba2.setIdComponente("SUB2-TEST");
            subComponentePrueba2.setCategoria("TestSub");
            subComponentePrueba2.setDescripcion("Subcomponente Pc de prueba");
            subComponentePrueba2.setCosto(50.0);
            subComponentePrueba2.setPrecioBase(75.0);
            subComponentePrueba2 = componenteDAO.insertar(subComponentePrueba2);
            
            ComponenteDTO subComponentePrueba3 = new ComponenteDTO();
            subComponentePrueba3.setIdComponente("SUB3-TEST");
            subComponentePrueba3.setCategoria("TestSub");
            subComponentePrueba3.setDescripcion("Subcomponente Pc de prueba");
            subComponentePrueba3.setCosto(50.0);
            subComponentePrueba3.setPrecioBase(75.0);
            subComponentePrueba3 = componenteDAO.insertar(subComponentePrueba3);

            SubComponenteDTO nuevoSubComponente = new SubComponenteDTO();
            nuevoSubComponente.setIdPC(pcPrueba.getIdComponente());
            nuevoSubComponente.setIdSubComponente(subComponentePrueba1.getIdComponente());
            nuevoSubComponente.setCantidad(1);
            nuevoSubComponente = subComponenteDAO.insertar(nuevoSubComponente); // Recuperamos la entidad insertada (sin PK autogenerada)
            System.out.println("SubComponente insertado: PC ID=" + nuevoSubComponente.getIdPC() + ", SubComponente ID=" + nuevoSubComponente.getIdSubComponente());

            SubComponenteDTO nuevoSubComponente2 = new SubComponenteDTO();
            nuevoSubComponente2.setIdPC(pcPrueba.getIdComponente());
            nuevoSubComponente2.setIdSubComponente(subComponentePrueba2.getIdComponente());
            nuevoSubComponente2.setCantidad(2);
            nuevoSubComponente2 = subComponenteDAO.insertar(nuevoSubComponente2); // Recuperamos la entidad insertada (sin PK autogenerada)
            System.out.println("SubComponente insertado: PC ID=" + nuevoSubComponente2.getIdPC() + ", SubComponente ID=" + nuevoSubComponente2.getIdSubComponente());

            SubComponenteDTO nuevoSubComponente3 = new SubComponenteDTO();
            nuevoSubComponente3.setIdPC(pcPrueba.getIdComponente());
            nuevoSubComponente3.setIdSubComponente(subComponentePrueba3.getIdComponente());
            nuevoSubComponente3.setCantidad(3);
            nuevoSubComponente3 = subComponenteDAO.insertar(nuevoSubComponente3); // Recuperamos la entidad insertada (sin PK autogenerada)
            System.out.println("SubComponente insertado: PC ID=" + nuevoSubComponente3.getIdPC() + ", SubComponente ID=" + nuevoSubComponente3.getIdSubComponente());
            
            // Obtener el subcomponente
            SubComponenteDTO subComponenteObtenido = subComponenteDAO.obtener(nuevoSubComponente.getIdPC(), nuevoSubComponente.getIdSubComponente());
            System.out.println("SubComponente obtenido: Cantidad=" + subComponenteObtenido.getCantidad());

            // Obtener subcomponentes por componente principal
            List<SubComponenteDTO> subComponentesPorPC = subComponenteDAO.obtenerSubComponentesPorPc(pcPrueba.getIdComponente());
            System.out.println("SubComponentes para PC ID " + pcPrueba.getIdComponente() + ": " + subComponentesPorPC.size());

            // Actualizar el subcomponente
            subComponenteObtenido.setCantidad(2);
            subComponenteDAO.actualizar(subComponenteObtenido);
            System.out.println("SubComponente actualizado: Cantidad=" + subComponenteObtenido.getCantidad());

            // Obtener todos los subcomponentes
            List<SubComponenteDTO> todosSubComponentes = subComponenteDAO.obtenerTodos();
            System.out.println("Total de subcomponentes: " + todosSubComponentes.size());
            
            // Obtener todos los subcomponentes
            List<SubComponenteDTO> todosSubComponentesPc = subComponenteDAO.obtenerSubComponentesPorPc(pcPrueba.getIdComponente());
            for(SubComponenteDTO subcomponente: todosSubComponentesPc) {
            	System.out.println(subcomponente);
            }
            System.out.println("Total de subcomponentesPc: " + todosSubComponentesPc.size());
            
            // Eliminar el subcomponente
            subComponenteDAO.eliminar(nuevoSubComponente.getIdPC(), nuevoSubComponente.getIdSubComponente());
            System.out.println("SubComponente para PC ID " + nuevoSubComponente.getIdPC() + " y SubComponente ID " + nuevoSubComponente.getIdSubComponente() + " eliminado.");

            // Eliminar los subcomponentes de una PC
            subComponenteDAO.eliminarSubComponentesPorPc(pcPrueba.getIdComponente());
            System.out.println("SubComponentes para PC ID " + pcPrueba.getIdComponente() + "  eliminados.");
            
            // Limpiar pruebas de componentes
            componenteDAO.eliminar(pcPrueba.getIdComponente());
            componenteDAO.eliminar(subComponentePrueba1.getIdComponente());
            componenteDAO.eliminar(subComponentePrueba2.getIdComponente());
            componenteDAO.eliminar(subComponentePrueba3.getIdComponente());

            System.out.println("\n--- Pruebas completadas ---");

        } catch (SQLException e) {
            System.err.println("Error durante las pruebas de DAO: " + e.getMessage());
            e.printStackTrace();
        } 
	}

	private static void testComponenteDAO() {
		try {
			System.out.println("\n--- Pruebas de ComponenteDAO ---");
            // Insertar un nuevo componente
            ComponenteDTO nuevoComponente = new ComponenteDTO();
            nuevoComponente.setIdComponente("TEST-0001");
            nuevoComponente.setCategoria("Test");
            nuevoComponente.setDescripcion("Componente de prueba");
            nuevoComponente.setCosto(10.00);
            nuevoComponente.setPrecioBase(20.00);
            nuevoComponente = componenteDAO.insertar(nuevoComponente); // Recuperamos la entidad insertada (aunque la PK se asigna antes)
            System.out.println("Componente insertado con ID: " + nuevoComponente.getIdComponente());

            // Obtener el componente insertado
            ComponenteDTO componenteObtenido = componenteDAO.obtener(nuevoComponente.getIdComponente());
            System.out.println("Componente obtenido: Categoría=" + componenteObtenido.getCategoria() + ", Descripción=" + componenteObtenido.getDescripcion());

            // Obtener todos los componentes por categoría
            List<ComponenteDTO> componentesTest = componenteDAO.obtenerTodosPorCategoria("Test");
            for(ComponenteDTO componente : componentesTest) {
            	System.out.println(componente);
            }
            System.out.println("Componentes en categoría 'Test': " + componentesTest.size());
            
            // Obtener todos los componentes
            List<ComponenteDTO> todosComponentes = componenteDAO.obtenerTodos();
            for(ComponenteDTO componente : todosComponentes) {
            	System.out.println(componente);
            }
            System.out.println("Total de componentes: " + todosComponentes.size());
            
            // Actualizar el componente
            componenteObtenido.setMarca("Marca-Test");
            componenteObtenido.setModelo("Modelo-Test");
            componenteObtenido.setCapAlmacenamiento("CapAlm-Test");
            componenteObtenido.setMemoria("Memoria-Test");
            componenteObtenido.setPrecioBase(25.00);
            componenteDAO.actualizar(componenteObtenido);
            System.out.println("Componente actualizado: " + componenteObtenido);
            
            // Eliminar el componente
            componenteDAO.eliminar(nuevoComponente.getIdComponente());
            System.out.println("Componente con ID " + nuevoComponente.getIdComponente() + " eliminado.");
			
		} catch (SQLException e) {
            System.err.println("Error durante las pruebas de DAO: " + e.getMessage());
            e.printStackTrace();
		}
	}

	private static void testDetalleCotizacionDAO() {
		try {
			
			System.out.println("\n--- Pruebas de DetCotizacionDAO ---");
            // Insertar un detalle de cotización (necesitamos una cotización y un componente existentes)
            CotizacionDTO cotizacionPrueba = new CotizacionDTO();
            cotizacionPrueba.setFechaCotizacion(LocalDate.now());
            cotizacionPrueba.setTotal(40.00);
            cotizacionPrueba = cotizacionDAO.insertar(cotizacionPrueba);

            ComponenteDTO componentePrueba = new ComponenteDTO();
            componentePrueba.setIdComponente("TEST-COMP");
            componentePrueba.setCategoria("Test");
            componentePrueba.setDescripcion("Componente de prueba para detalle");
            componentePrueba.setCosto(5.00);
            componentePrueba.setPrecioBase(10.00);
            componentePrueba = componenteDAO.insertar(componentePrueba);
            
            ComponenteDTO componentePrueba1 = new ComponenteDTO();
            componentePrueba1.setIdComponente("TEST-COMP1");
            componentePrueba1.setCategoria("Test");
            componentePrueba1.setDescripcion("Componente de prueba para detalle");
            componentePrueba1.setCosto(5.00);
            componentePrueba1.setPrecioBase(10.00);
            componentePrueba1 = componenteDAO.insertar(componentePrueba1);
            
            ComponenteDTO componentePrueba2 = new ComponenteDTO();
            componentePrueba2.setIdComponente("TEST-COMP2");
            componentePrueba2.setCategoria("Test");
            componentePrueba2.setDescripcion("Componente de prueba para detalle");
            componentePrueba2.setCosto(5.00);
            componentePrueba2.setPrecioBase(10.00);
            componentePrueba2 = componenteDAO.insertar(componentePrueba2);
            
            DetCotizacionDTO nuevoDetalle = new DetCotizacionDTO();
            nuevoDetalle.setIdCotizacion(cotizacionPrueba.getIdCotizacion());
            nuevoDetalle.setIdComponente(componentePrueba.getIdComponente());
            nuevoDetalle.setCantidad(2);
            nuevoDetalle.setCategoria("Test");
            nuevoDetalle.setDescripcion("Detalle de prueba");
            nuevoDetalle.setImporteCotizado(20.00);
            nuevoDetalle.setPrecioBase(10.00);
            nuevoDetalle = detCotizacionDAO.insertar(nuevoDetalle); // Recuperamos la entidad insertada (sin PK autogenerada)
            System.out.println("Detalle de cotización insertado para Cotización ID " 
                              + nuevoDetalle.getIdCotizacion() 
                              + " y Componente ID " 
                              + nuevoDetalle.getIdComponente());

            DetCotizacionDTO nuevoDetalle1 = new DetCotizacionDTO();
            nuevoDetalle1.setIdCotizacion(cotizacionPrueba.getIdCotizacion());
            nuevoDetalle1.setIdComponente(componentePrueba1.getIdComponente());
            nuevoDetalle1.setCantidad(1);
            nuevoDetalle1.setCategoria("Test");
            nuevoDetalle1.setDescripcion("Detalle de prueba");
            nuevoDetalle1.setImporteCotizado(10.00);
            nuevoDetalle1.setPrecioBase(10.00);
            nuevoDetalle1 = detCotizacionDAO.insertar(nuevoDetalle1); // Recuperamos la entidad insertada (sin PK autogenerada)
            System.out.println("Detalle de cotización insertado para Cotización ID " 
                              + nuevoDetalle1.getIdCotizacion() 
                              + " y Componente ID " 
                              + nuevoDetalle1.getIdComponente());
            
            DetCotizacionDTO nuevoDetalle2 = new DetCotizacionDTO();
            nuevoDetalle2.setIdCotizacion(cotizacionPrueba.getIdCotizacion());
            nuevoDetalle2.setIdComponente(componentePrueba2.getIdComponente());
            nuevoDetalle2.setCantidad(1);
            nuevoDetalle2.setCategoria("Test");
            nuevoDetalle2.setDescripcion("Detalle de prueba");
            nuevoDetalle2.setImporteCotizado(10.00);
            nuevoDetalle2.setPrecioBase(10.00);
            nuevoDetalle2 = detCotizacionDAO.insertar(nuevoDetalle2); // Recuperamos la entidad insertada (sin PK autogenerada)
            System.out.println("Detalle de cotización insertado para Cotización ID " 
                             + nuevoDetalle2.getIdCotizacion() 
                             + " y Componente ID " 
                             + nuevoDetalle2.getIdComponente());
            
            // Obtener el detalle insertado
            DetCotizacionDTO detalleObtenido = detCotizacionDAO.obtener(nuevoDetalle.getIdCotizacion(), nuevoDetalle.getIdComponente());
            System.out.println("Detalle obtenido: Cantidad=" + detalleObtenido.getCantidad() + ", Importe=" + detalleObtenido.getImporteCotizado());

            // Obtener todos los detalles por cotización
            List<DetCotizacionDTO> detallesPorCotizacion = detCotizacionDAO.obtenerTodosPorCotizacion(nuevoDetalle.getIdCotizacion());
            for(DetCotizacionDTO detCot : detallesPorCotizacion) {
            	System.out.println(detCot);
            }
            System.out.println("Detalles para Cotización ID " + nuevoDetalle.getIdCotizacion() + ": " + detallesPorCotizacion.size());

            // Actualizar el detalle
            detalleObtenido.setCantidad(3);
            detalleObtenido.setImporteCotizado(30.00);
            detCotizacionDAO.actualizar(detalleObtenido);
            System.out.println("Detalle actualizado: Cantidad=" + detalleObtenido.getCantidad() + ", Importe=" + detalleObtenido.getImporteCotizado());
            
            // Actualizar el Total de cotizador
            cotizacionPrueba.setTotal(50.0);
            cotizacionDAO.actualizar(cotizacionPrueba);

            // Obtener todos los detalles
            List<DetCotizacionDTO> todosDetalles = detCotizacionDAO.obtenerTodos();
            for(DetCotizacionDTO detCot : todosDetalles) {
            	System.out.println(detCot);
            }
            System.out.println("Total de detalles de cotización: " + todosDetalles.size());

            // Eliminar el detalle
            detCotizacionDAO.eliminar(cotizacionPrueba.getIdCotizacion(), nuevoDetalle.getIdComponente());
            System.out.println("Detalle para Cotización ID " + cotizacionPrueba.getIdCotizacion() 
                             + " y Componente ID " + nuevoDetalle.getIdComponente() + " eliminado.");
            
            // Eliminar el detalle por Cotizacion
            detCotizacionDAO.eliminarPorCotizacion(cotizacionPrueba.getIdCotizacion());
            System.out.println("Detalle para Cotización ID " + cotizacionPrueba.getIdCotizacion() 
                             + " eliminados.");
            
            // Limpiar las pruebas de cotización y componente
            cotizacionDAO.eliminar(cotizacionPrueba.getIdCotizacion());
            componenteDAO.eliminar(componentePrueba.getIdComponente());
            componenteDAO.eliminar(componentePrueba1.getIdComponente());
            componenteDAO.eliminar(componentePrueba2.getIdComponente());
			
		} catch (SQLException e) {
            System.err.println("Error durante las pruebas de DAO: " + e.getMessage());
            e.printStackTrace();
		}
		
	}

	private static void testCotizacionDAO() {
		try {
	        System.out.println("--- Pruebas de CotizacionDAO ---");
   
	        // Insertar una nueva cotización
	        CotizacionDTO nuevaCotizacion = new CotizacionDTO();
	        nuevaCotizacion.setFechaCotizacion(LocalDate.now());
	        nuevaCotizacion.setTotal(99.99);
	        nuevaCotizacion = cotizacionDAO.insertar(nuevaCotizacion); // Ahora recuperamos la entidad insertada
	        System.out.println("Cotización insertada con ID: " + nuevaCotizacion.getIdCotizacion());
	
	        // Obtener la cotización insertada
	        CotizacionDTO cotizacionObtenida = cotizacionDAO.obtener(nuevaCotizacion.getIdCotizacion());
	        System.out.println("Cotización obtenida: Fecha=" + cotizacionObtenida.getFechaCotizacion() + ", Total=" + cotizacionObtenida.getTotal());
	        
	        // Actualizar la cotización
	        cotizacionObtenida.setTotal(150.50);
	        cotizacionDAO.actualizar(cotizacionObtenida);
	        System.out.println("Cotización actualizada: Total=" + cotizacionObtenida.getTotal());

	        // Obtener todas las cotizaciones
	        List<CotizacionDTO> todasCotizaciones = cotizacionDAO.obtenerTodos();
	        for(CotizacionDTO cotizacion : todasCotizaciones) {
	        	System.out.println(cotizacion);
	        }
	        System.out.println("Total de cotizaciones: " + todasCotizaciones.size());
	    
	        // Eliminar la cotización
	        cotizacionDAO.eliminar(nuevaCotizacion.getIdCotizacion());

	        System.out.println("Cotización con ID " + nuevaCotizacion.getIdCotizacion() + " eliminada.");
	        
	        todasCotizaciones = cotizacionDAO.obtenerTodos();
	        for(CotizacionDTO cotizacion : todasCotizaciones) {
	        	System.out.println(cotizacion);
	        }
	        System.out.println("Total de cotizaciones: " + todasCotizaciones.size());
	    
		} catch (SQLException e) {
            System.err.println("Error durante las pruebas de DAO: " + e.getMessage());
            e.printStackTrace();
		}
		
	}
}
