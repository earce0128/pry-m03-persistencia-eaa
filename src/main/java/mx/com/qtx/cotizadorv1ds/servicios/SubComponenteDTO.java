package mx.com.qtx.cotizadorv1ds.servicios;

public class SubComponenteDTO {
    private String idPC;
    private String idSubComponente;
    private Integer cantidad;

    public SubComponenteDTO() {
    }

    public String getIdPC() {
        return idPC;
    }

    public void setIdPC(String idPC) {
        this.idPC = idPC;
    }

    public String getIdSubComponente() {
        return idSubComponente;
    }

    public void setIdSubComponente(String idSubComponente) {
        this.idSubComponente = idSubComponente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

	@Override
	public String toString() {
		return "SubComponenteDTO [idPC=" + idPC + ", idSubComponente=" + idSubComponente + ", cantidad=" + cantidad
				+ "]";
	}
    
}
