package mx.com.qtx.cotizadorv1ds.persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorv1ds.componentes.Componente;
import mx.com.qtx.cotizadorv1ds.componentes.DiscoDuro;
import mx.com.qtx.cotizadorv1ds.componentes.Monitor;
import mx.com.qtx.cotizadorv1ds.componentes.Pc;
import mx.com.qtx.cotizadorv1ds.componentes.TarjetaVideo;
import mx.com.qtx.cotizadorv1ds.cotizador.Cotizacion;
import mx.com.qtx.cotizadorv1ds.cotizador.DetalleCotizacion;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.ComponenteDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.ComponenteMySqlDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.CotizacionDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.CotizacionMySqlDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.DetCotizacionDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.DetCotizacionMySqlDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.SubComponenteDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dao.SubComponenteMySqlDAO;
import mx.com.qtx.cotizadorv1ds.persistencia.dto.ComponenteDTO;
import mx.com.qtx.cotizadorv1ds.persistencia.dto.CotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistencia.dto.DetCotizacionDTO;
import mx.com.qtx.cotizadorv1ds.persistencia.dto.SubComponenteDTO;
import mx.com.qtx.cotizadorv1ds.persistencia.jdbc.DatabaseConnector;
import mx.com.qtx.cotizadorv1ds.servicios.IGestorBDCotizador;

public class ImpGestorBDCotizadorMySQLDAO implements IGestorBDCotizador {
	
	private final ComponenteDAO componenteDAO = ComponenteMySqlDAO.getInstance();
    private final CotizacionDAO cotizacionDAO = CotizacionMySqlDAO.getInstance();
    private final DetCotizacionDAO detCotizacionDAO = DetCotizacionMySqlDAO.getInstance();
    private final SubComponenteDAO subComponenteDAO = SubComponenteMySqlDAO.getInstance();
    
    private final String DISCO_DURO = "Disco Duro";
    private final String MONITOR = "Monitor";
    private final String TARJETA_VIDEO = "Tarjeta de Video";
    private final String PC = "PC";

    private boolean esSubComponente(String idComponente) throws SQLException {
        List<SubComponenteDTO> subComponentes = subComponenteDAO.obtenerTodos();
        return subComponentes.stream().anyMatch(sc -> sc.getIdSubComponente().equals(idComponente));
    }
    
    private boolean estaEnDetalleCotizacion(String idComponente) throws SQLException {
        List<DetCotizacionDTO> detalles = detCotizacionDAO.obtenerTodos();
        return detalles.stream().anyMatch(dc -> dc.getIdComponente().equals(idComponente));
    }
    
	@Override
	public Componente obtenerDiscoDuro(String idDiscoDuro) throws SQLException {
		ComponenteDTO dto = componenteDAO.obtener(idDiscoDuro);
		if (dto != null && DISCO_DURO.equals(dto.getCategoria())) {
            Componente discoDuro = Componente.crearDiscoDuro(dto.getIdComponente(), 
            		                                         dto.getDescripcion(), 
            		                                         dto.getMarca(),
            		                                         dto.getModelo(),
            		                                         new BigDecimal(dto.getCosto()),
            		                                         new BigDecimal(dto.getPrecioBase()),
            		                                         dto.getCapAlmacenamiento()); 
            return discoDuro;
        }
		return null;
	}

	@Override
	public Componente obtenerMonitor(String idMonitor) throws SQLException {
		ComponenteDTO dto = componenteDAO.obtener(idMonitor);
        if (dto != null && MONITOR.equals(dto.getCategoria())) {
            Componente monitor = Componente.crearMonitor(dto.getIdComponente(), 
            											 dto.getDescripcion(), 
            											 dto.getMarca(), 
            											 dto.getModelo(), 
            											 new BigDecimal(dto.getCosto()), 
            											 new BigDecimal(dto.getPrecioBase())); 
            return monitor;
        }
		return null;
	}

	@Override
	public Componente obtenerTarjetaVideo(String idTarjetaVideo) throws SQLException {
		ComponenteDTO dto = componenteDAO.obtener(idTarjetaVideo);
        if (dto != null && TARJETA_VIDEO.equals(dto.getCategoria())) {
            Componente tarjetaVideo = Componente.crearTarjetaVideo(dto.getIdComponente(), 
            													   dto.getDescripcion(), 
            													   dto.getMarca(), 
            													   dto.getModelo(), 
            													   new BigDecimal(dto.getCosto()), 
            													   new BigDecimal(dto.getPrecioBase()), 
            		               								   dto.getMemoria()); 
            return tarjetaVideo;
        }
		return null;
	}

	@Override
	public Componente obtenerPC(String idPc) throws SQLException {
		
		ComponenteDTO dto = componenteDAO.obtener(idPc);
        if (dto != null && PC.equals(dto.getCategoria())) {
            
        	List<Componente> subComp = new ArrayList<Componente>();
        	List<SubComponenteDTO> subCompDTO = subComponenteDAO.obtenerSubComponentesPorPc(idPc);
        	
        	for (SubComponenteDTO subDTO : subCompDTO) {
                ComponenteDTO compDTO = componenteDAO.obtener(subDTO.getIdSubComponente());
                if (compDTO != null) {
                	if (DISCO_DURO.equals(compDTO.getCategoria())) {
                		subComp.add(obtenerDiscoDuro(compDTO.getIdComponente()));
                    } else if (MONITOR.equals(compDTO.getCategoria())) {
                    	subComp.add(obtenerMonitor(compDTO.getIdComponente()));
                    } else if (TARJETA_VIDEO.equals(compDTO.getCategoria())) {
                    	subComp.add(obtenerTarjetaVideo(compDTO.getIdComponente()));
                    }
                }
            }
        	Componente pc = Componente.crearPc(dto.getIdComponente(), 
        									   dto.getDescripcion(), 
        									   dto.getMarca(), 
        									   dto.getModelo(), 
        									   subComp); 
            return pc;
        }
		return null;
	}

	@Override
	public List<Componente> obtenerTodosComponentes() throws SQLException {
		List<ComponenteDTO> dtos = componenteDAO.obtenerTodos();
        List<Componente> componentes = new ArrayList<>();
        for (ComponenteDTO dto : dtos) {
            if (DISCO_DURO.equals(dto.getCategoria())) {
                componentes.add(obtenerDiscoDuro(dto.getIdComponente()));
            } else if (MONITOR.equals(dto.getCategoria())) {
                componentes.add(obtenerMonitor(dto.getIdComponente()));
            } else if (TARJETA_VIDEO.equals(dto.getCategoria())) {
                componentes.add(obtenerTarjetaVideo(dto.getIdComponente()));
            } else if (PC.equals(dto.getCategoria())) {
                componentes.add(obtenerPC(dto.getIdComponente()));
            }
        }
        return componentes;
	}

	@Override
	public Componente insertarDiscoDuro(Componente compDiscoDuro) throws SQLException {
		
		DiscoDuro discoDuro = (DiscoDuro) compDiscoDuro; 

		ComponenteDTO dto = new ComponenteDTO();
        dto.setIdComponente(discoDuro.getId());
        dto.setMarca(discoDuro.getMarca());
        dto.setModelo(discoDuro.getModelo());
        dto.setDescripcion(discoDuro.getDescripcion());
        dto.setCosto(discoDuro.getCosto().doubleValue());
        dto.setPrecioBase(discoDuro.getPrecioBase().doubleValue());
        dto.setCapAlmacenamiento(discoDuro.getCapacidadAlm());
        dto.setCategoria(DISCO_DURO);
        ComponenteDTO discoDTO = componenteDAO.insertar(dto);
        if(discoDTO != null)
        	return obtenerDiscoDuro(discoDTO.getIdComponente());
        return null;
	}

	@Override
	public Componente insertarMonitor(Componente compMonitor) throws SQLException {
		Monitor monitor = (Monitor) compMonitor; 

		ComponenteDTO dto = new ComponenteDTO();
        dto.setIdComponente(monitor.getId());
        dto.setMarca(monitor.getMarca());
        dto.setModelo(monitor.getModelo());
        dto.setDescripcion(monitor.getDescripcion());
        dto.setCosto(monitor.getCosto().doubleValue());
        dto.setPrecioBase(monitor.getPrecioBase().doubleValue());
        dto.setCategoria(MONITOR);
        ComponenteDTO monitorDTO = componenteDAO.insertar(dto);
        if(monitorDTO != null)
        	return obtenerMonitor(monitorDTO.getIdComponente());
        return null;
	}

	@Override
	public Componente insertarTarjetaVideo(Componente compTarjetaVideo) throws SQLException {
		TarjetaVideo tarjeta = (TarjetaVideo) compTarjetaVideo; 

		ComponenteDTO dto = new ComponenteDTO();
        dto.setIdComponente(tarjeta.getId());
        dto.setMarca(tarjeta.getMarca());
        dto.setModelo(tarjeta.getModelo());
        dto.setDescripcion(tarjeta.getDescripcion());
        dto.setCosto(tarjeta.getCosto().doubleValue());
        dto.setPrecioBase(tarjeta.getPrecioBase().doubleValue());
        dto.setMemoria(tarjeta.getMemoria());
        dto.setCategoria(TARJETA_VIDEO);
        ComponenteDTO tarjetaDTO = componenteDAO.insertar(dto);
        if(tarjetaDTO != null)
        	return obtenerTarjetaVideo(tarjetaDTO.getIdComponente());
		return null;
	}

	@Override
	public Componente insertarPC(Componente compPc) throws SQLException {
		Connection conn = null;
		Pc pc = (Pc) compPc;
		
        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);

            ComponenteDTO dto = new ComponenteDTO();
            dto.setIdComponente(pc.getId());
            dto.setMarca(pc.getMarca());
            dto.setModelo(pc.getModelo());
            dto.setDescripcion(pc.getDescripcion());
            dto.setCosto(pc.getCosto().doubleValue());
            dto.setPrecioBase(pc.getPrecioBase().doubleValue());
            dto.setCategoria(PC);
            ComponenteDTO pcDTO = componenteDAO.insertar(dto);
            for (Componente componente : pc.getSubComponentes()) {
                    SubComponenteDTO subComponenteDTO = new SubComponenteDTO();
                    subComponenteDTO.setIdPC(pcDTO.getIdComponente());
                    subComponenteDTO.setIdSubComponente(componente.getId());
                    subComponenteDTO.setCantidad(1); // Suponiendo cantidad 1 por subcomponente
                    subComponenteDAO.insertar(subComponenteDTO);
            }
            conn.commit();
            return obtenerPC(pc.getId());
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
	}

	@Override
	public void actualizarDiscoDuro(Componente compDiscoDuro) throws SQLException {
	    
		DiscoDuro discoDuro = (DiscoDuro)compDiscoDuro;
		
		ComponenteDTO dto = new ComponenteDTO();
        dto.setIdComponente(discoDuro.getId());
        dto.setMarca(discoDuro.getMarca());
        dto.setModelo(discoDuro.getModelo());
        dto.setDescripcion(discoDuro.getDescripcion());
        dto.setCosto(discoDuro.getCosto().doubleValue());
        dto.setPrecioBase(discoDuro.getPrecioBase().doubleValue());
        dto.setCapAlmacenamiento(discoDuro.getCapacidadAlm());
        dto.setCategoria(DISCO_DURO);
        componenteDAO.actualizar(dto);
	}

	@Override
	public void actualizarMonitor(Componente compMonitor) throws SQLException {
		Monitor monitor = (Monitor) compMonitor; 

		ComponenteDTO dto = new ComponenteDTO();
        dto.setIdComponente(monitor.getId());
        dto.setMarca(monitor.getMarca());
        dto.setModelo(monitor.getModelo());
        dto.setDescripcion(monitor.getDescripcion());
        dto.setCosto(monitor.getCosto().doubleValue());
        dto.setPrecioBase(monitor.getPrecioBase().doubleValue());
        dto.setCategoria(MONITOR);
        componenteDAO.actualizar(dto);

	}

	@Override
	public void actualizarTarjetaVideo(Componente compTarjetaVideo) throws SQLException {
		TarjetaVideo tarjeta = (TarjetaVideo) compTarjetaVideo; 

		ComponenteDTO dto = new ComponenteDTO();
        dto.setIdComponente(tarjeta.getId());
        dto.setMarca(tarjeta.getMarca());
        dto.setModelo(tarjeta.getModelo());
        dto.setDescripcion(tarjeta.getDescripcion());
        dto.setCosto(tarjeta.getCosto().doubleValue());
        dto.setPrecioBase(tarjeta.getPrecioBase().doubleValue());
        dto.setMemoria(tarjeta.getMemoria());
        dto.setCategoria(TARJETA_VIDEO);
        componenteDAO.actualizar(dto);

	}

	@Override
	public void actualizarPC(Componente compPc) throws SQLException {
		Connection conn = null;
		Pc pc = (Pc) compPc;
		
        try {
        	conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);

            ComponenteDTO dto = new ComponenteDTO();
            dto.setIdComponente(pc.getId());
            dto.setMarca(pc.getMarca());
            dto.setModelo(pc.getModelo());
            dto.setDescripcion(pc.getDescripcion());
            dto.setCosto(pc.getCosto().doubleValue());
            dto.setPrecioBase(pc.getPrecioBase().doubleValue());
            dto.setCategoria(PC);
            componenteDAO.actualizar(dto);

            // Sincronizar subcomponentes: primero eliminar los existentes y luego insertar los nuevos
            subComponenteDAO.eliminarSubComponentesPorPc(pc.getId());
            
            for (Componente componente : pc.getSubComponentes()) {
            	SubComponenteDTO subComponenteDTO = new SubComponenteDTO();
                   				 subComponenteDTO.setIdPC(pc.getId());
                   				 subComponenteDTO.setIdSubComponente(componente.getId());
                   				 subComponenteDTO.setCantidad(1); // Suponiendo cantidad 1 por subcomponente
                subComponenteDAO.insertar(subComponenteDTO);
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
	}

	@Override
	public boolean eliminarDiscoDuro(String idDiscoDuro) throws SQLException {
		// Valida que no se encuentre como subcomponente de una Pc o dentro de una cotizacion
		if (esSubComponente(idDiscoDuro) || estaEnDetalleCotizacion(idDiscoDuro)) {
            System.out.println("No se puede eliminar el disco duro porque ya se encuentra asociado a una PC "
            		+ "o en una cotización");
			return false;
        }
        componenteDAO.eliminar(idDiscoDuro);
        return true;
	}

	@Override
	public boolean eliminarMonitor(String idMonitor) throws SQLException {
        // Valida que no se encuentre como subcomponente de una Pc o dentro de una cotizacion
		if (esSubComponente(idMonitor) || estaEnDetalleCotizacion(idMonitor)) {
            System.out.println("No se puede eliminar el monitor porque ya se encuentra asociado a una PC "
            		+ "o en una cotización");
			return false;
        }
        componenteDAO.eliminar(idMonitor);
        return true;
	}

	@Override
	public boolean eliminarTarjetaVideo(String idTarjetaVideo) throws SQLException {
		// Valida que no se encuentre como subcomponente de una Pc o dentro de una cotizacion
		if (esSubComponente(idTarjetaVideo) || estaEnDetalleCotizacion(idTarjetaVideo)) {
            System.out.println("No se puede eliminar la tarjeta de video porque ya se encuentra "
            		+ "asociada a una PC o en una cotización");
			return false;
        }
        componenteDAO.eliminar(idTarjetaVideo);
        return true;
	}

	@Override
	public boolean eliminarPC(String idPc) throws SQLException {
		//Valida que no se encuentre como subcomponente de una Pc o dentro de una cotizacion
		if (estaEnDetalleCotizacion(idPc)) {
            System.out.println("No se puede eliminar la PC porque ya se encuentra "
            		+ "asociada a una cotización");
			return false;
        }
        
		Connection conn = null;
        try {
        	conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);

            subComponenteDAO.eliminarSubComponentesPorPc(idPc);
            componenteDAO.eliminar(idPc);
            conn.commit();

            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
	}

	@Override
	public Cotizacion obtenerCotizacion(Long idCotizacion) throws SQLException {
		CotizacionDTO dto = cotizacionDAO.obtener(idCotizacion);
        if (dto != null) {
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setFecha(dto.getFechaCotizacion());
            cotizacion.setTotal(new BigDecimal(dto.getTotal()));
            cotizacion.setNum(idCotizacion);
            
            List<DetalleCotizacion> detalle = obtenerDetallesPorCotizacion(idCotizacion);
            for(DetalleCotizacion detI : detalle) {
            	cotizacion.agregarDetalle(detI);
            }
            return cotizacion;
        }
        return null;
	}

	@Override
	public List<Cotizacion> obtenerTodasCotizaciones() throws SQLException {
		List<CotizacionDTO> dtos = cotizacionDAO.obtenerTodos();
        List<Cotizacion> cotizaciones = new ArrayList<>();
        for (CotizacionDTO dto : dtos) {
        	Cotizacion cotizacion = new Cotizacion();
            cotizacion.setFecha(dto.getFechaCotizacion());
            cotizacion.setTotal(new BigDecimal(dto.getTotal()));
            
            List<DetalleCotizacion> detalle = obtenerDetallesPorCotizacion(dto.getIdCotizacion());
            for(DetalleCotizacion detI : detalle) {
            	cotizacion.agregarDetalle(detI);
            }
            cotizaciones.add(cotizacion);
        }
        return cotizaciones;
	}

	@Override
	public Cotizacion insertarCotizacion(Cotizacion cotizacion) throws SQLException {
		CotizacionDTO dto = new CotizacionDTO();
		dto.setFechaCotizacion(cotizacion.getFecha() != null ? cotizacion.getFecha() : LocalDate.now());
        dto.setTotal(cotizacion.getTotal().doubleValue());
        CotizacionDTO cotDTO = cotizacionDAO.insertar(dto);
        if (cotDTO != null && cotizacion.getDetalles() != null && !cotizacion.getDetalles().isEmpty()) {
            Connection conn = null;
            try {
            	
            	conn = DatabaseConnector.getConnection();
                conn.setAutoCommit(false);
                
                for(DetalleCotizacion detI: cotizacion.getDetalles().values())  {
                	DetCotizacionDTO detalleDTO = new DetCotizacionDTO();
                	detalleDTO.setIdCotizacion(cotDTO.getIdCotizacion());
                    detalleDTO.setIdComponente(detI.getIdComponente());
                    detalleDTO.setCantidad(detI.getCantidad());
                    detalleDTO.setDescripcion(detI.getDescripcion());
                    detalleDTO.setImporteCotizado(detI.getImporteCotizado().doubleValue());
                    detalleDTO.setPrecioBase(detI.getPrecioBase().doubleValue());
                    detalleDTO.setCategoria(detI.getCategoria());
                    detCotizacionDAO.insertar(detalleDTO);
                }
                conn.commit();
                return obtenerCotizacion(cotDTO.getIdCotizacion());
            } catch (SQLException e) {
                if (conn != null) {
                    conn.rollback();
                }
                throw e;
            } finally {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            }
        }
        return null;
	}

	@Override
	public void actualizarCotizacion(Cotizacion cotizacion) throws SQLException {
		CotizacionDTO dto = new CotizacionDTO();
        dto.setIdCotizacion(cotizacion.getNum());
        dto.setFechaCotizacion(cotizacion.getFecha());
        dto.setTotal(cotizacion.getTotal().doubleValue());

        cotizacionDAO.actualizar(dto);

        // Sincronizar detalles: eliminar los existentes y luego insertar los nuevos
        Connection conn = null;
        try {
        	conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);

            detCotizacionDAO.eliminarPorCotizacion(cotizacion.getNum());
            if (cotizacion.getDetalles() != null) {
            	for(DetalleCotizacion detI: cotizacion.getDetalles().values())  {
                	DetCotizacionDTO detalleDTO = new DetCotizacionDTO();
                	detalleDTO.setIdCotizacion(cotizacion.getNum());
                    detalleDTO.setIdComponente(detI.getIdComponente());
                    detalleDTO.setCantidad(detI.getCantidad());
                    detalleDTO.setDescripcion(detI.getDescripcion());
                    detalleDTO.setImporteCotizado(detI.getImporteCotizado().doubleValue());
                    detalleDTO.setPrecioBase(detI.getPrecioBase().doubleValue());
                    detalleDTO.setCategoria(detI.getCategoria());
                    detCotizacionDAO.insertar(detalleDTO);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
	}

	@Override
	public boolean eliminarCotizacion(Long idCotizacion) throws SQLException {
		Connection conn = null;
        try {
        	conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            
            if(obtenerCotizacion(idCotizacion) == null) {
            	System.out.println("No se encontro la cotización con id: " + idCotizacion + 
            			" para eliminarla");
            	return false;
            }
            
            detCotizacionDAO.eliminarPorCotizacion(idCotizacion);
            cotizacionDAO.eliminar(idCotizacion);
            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
	}

	@Override
	public List<DetalleCotizacion> obtenerDetallesPorCotizacion(Long idCotizacion) throws SQLException {
		List<DetCotizacionDTO> dtos = detCotizacionDAO.obtenerTodosPorCotizacion(idCotizacion);
        List<DetalleCotizacion> detalles = new ArrayList<>();
        int i = 0;
        for (DetCotizacionDTO compI : dtos) {
            DetalleCotizacion detalle = new DetalleCotizacion((i + 1), 
            		                                          compI.getIdComponente(), 
            		                                          compI.getDescripcion(), 
            		                                          compI.getCantidad(), 
            		                                          new BigDecimal(compI.getPrecioBase()), 
            		                                          new BigDecimal(compI.getImporteCotizado()), 
            		                                          compI.getCategoria());
            i++;		
            detalles.add(detalle);
        }
        return detalles;
	}
}
