package mx.com.qtx.cotizadorv1ds.persistencia.dto;

public class ComponenteDTO {
    private String idComponente;
    private String categoria;
    private String descripcion;
    private String memoria;
    private String capAlmacenamiento;
    private Double costo;
    private Double precioBase;
    private String marca;
    private String modelo;

    public ComponenteDTO() {
    }

    public String getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(String idComponente) {
        this.idComponente = idComponente;
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

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getCapAlmacenamiento() {
        return capAlmacenamiento;
    }

    public void setCapAlmacenamiento(String capAlmacenamiento) {
        this.capAlmacenamiento = capAlmacenamiento;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

	@Override
	public String toString() {
		return "ComponenteDTO [idComponente=" + idComponente + ", categoria=" + categoria + ", descripcion="
				+ descripcion + ", memoria=" + memoria + ", capAlmacenamiento=" + capAlmacenamiento + ", costo=" + costo
				+ ", precioBase=" + precioBase + ", marca=" + marca + ", modelo=" + modelo + "]";
	}
}
