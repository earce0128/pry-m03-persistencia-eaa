package mx.com.qtx.cotizadorv1ds.servicios;

import java.time.LocalDate;

public class PromocionDTO {
	
	private long numPromocion;
	private String nombre;
	private String descripcion;
	private LocalDate fechVigenciaDesde;
	private LocalDate fechVigenciaHasta;
	
	public PromocionDTO() {
	}

	public long getNumPromocion() {
		return numPromocion;
	}


	public void setNumPromocion(long numPromocion) {
		this.numPromocion = numPromocion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public LocalDate getFechVigenciaDesde() {
		return fechVigenciaDesde;
	}


	public void setFechVigenciaDesde(LocalDate fechVigenciaDesde) {
		this.fechVigenciaDesde = fechVigenciaDesde;
	}


	public LocalDate getFechVigenciaHasta() {
		return fechVigenciaHasta;
	}


	public void setFechVigenciaHasta(LocalDate fechVigenciaHasta) {
		this.fechVigenciaHasta = fechVigenciaHasta;
	}


	@Override
	public String toString() {
		return "PromocionDTO [numPromocion=" + numPromocion + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", fechVigenciaDesde=" + fechVigenciaDesde + ", fechVigenciaHasta=" + fechVigenciaHasta + "]";
	}
	
}
