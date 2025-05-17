package mx.com.qtx.cotizadorv1ds.servicios;

public class DetPromDsctoXCantDTO {
	
	private double numPromDsctoCant;
	private double numDetPromocion;
	private double numPromocion;
	private int cantidad;
	private double dscto;
	
	public DetPromDsctoXCantDTO() {
		super();
	}

	public double getNumPromDsctoCant() {
		return numPromDsctoCant;
	}

	public void setNumPromDsctoCant(double numPromDsctoCant) {
		this.numPromDsctoCant = numPromDsctoCant;
	}

	public double getNumDetPromocion() {
		return numDetPromocion;
	}

	public void setNumDetPromocion(double numDetPromocion) {
		this.numDetPromocion = numDetPromocion;
	}

	public double getNumPromocion() {
		return numPromocion;
	}

	public void setNumPromocion(double numPromocion) {
		this.numPromocion = numPromocion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getDscto() {
		return dscto;
	}

	public void setDscto(double dscto) {
		this.dscto = dscto;
	}

	@Override
	public String toString() {
		return "DetPromDsctoXCantDTO [numPromDsctoCant=" + numPromDsctoCant + ", numDetPromocion=" + numDetPromocion
				+ ", numPromocion=" + numPromocion + ", cantidad=" + cantidad + ", dscto=" + dscto + "]";
	}
	
}
