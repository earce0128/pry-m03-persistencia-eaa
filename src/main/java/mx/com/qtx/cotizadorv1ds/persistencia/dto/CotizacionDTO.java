package mx.com.qtx.cotizadorv1ds.persistencia.dto;

import java.time.LocalDate;

public class CotizacionDTO {
    private Long idCotizacion;
    private LocalDate fechaCotizacion;
    private Double total;

    public CotizacionDTO() {
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public LocalDate getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(LocalDate fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

	@Override
	public String toString() {
		return "CotizacionDTO [idCotizacion=" + idCotizacion + ", fechaCotizacion=" + fechaCotizacion + ", total="
				+ total + "]";
	}
}
