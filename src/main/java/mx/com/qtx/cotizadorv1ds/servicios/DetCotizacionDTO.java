package mx.com.qtx.cotizadorv1ds.servicios;

public class DetCotizacionDTO {
    private Long idCotizacion;
    private String idComponente;
    private Integer cantidad;
    private String categoria;
    private String descripcion;
    private Double importeCotizado;
    private Double precioBase;

    public DetCotizacionDTO() {
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(String idComponente) {
        this.idComponente = idComponente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getImporteCotizado() {
        return importeCotizado;
    }

    public void setImporteCotizado(Double importeCotizado) {
        this.importeCotizado = importeCotizado;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

	@Override
	public String toString() {
		return "DetCotizacionDTO [idCotizacion=" + idCotizacion + ", idComponente=" + idComponente + ", cantidad="
				+ cantidad + ", categoria=" + categoria + ", descripcion=" + descripcion + ", importeCotizado="
				+ importeCotizado + ", precioBase=" + precioBase + "]";
	}
    
}
