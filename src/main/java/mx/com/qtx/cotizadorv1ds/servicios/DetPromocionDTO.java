package mx.com.qtx.cotizadorv1ds.servicios;

public class DetPromocionDTO {
	
	private double numDetPromocion;
	private double numPromocion;
	private String nombre;
	private String descripcion;
	private boolean esBase;
	private int llevarN;
	private int pagueM;
	private double porcDsctoPlan;
	private String tipoPromAcumulable;
	private String tipoPromBase;
	
	
	public DetPromocionDTO() {
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

	public boolean isEsBase() {
		return esBase;
	}

	public void setEsBase(boolean esBase) {
		this.esBase = esBase;
	}

	public int getLlevarN() {
		return llevarN;
	}

	public void setLlevarN(int llevarN) {
		this.llevarN = llevarN;
	}

	public int getPagueM() {
		return pagueM;
	}

	public void setPagueM(int pagueM) {
		this.pagueM = pagueM;
	}

	public double getPorcDsctoPlan() {
		return porcDsctoPlan;
	}

	public void setPorcDsctoPlan(double porcDsctoPlan) {
		this.porcDsctoPlan = porcDsctoPlan;
	}

	public String getTipoPromAcumulable() {
		return tipoPromAcumulable;
	}

	public void setTipoPromAcumulable(String tipoPromAcumulable) {
		this.tipoPromAcumulable = tipoPromAcumulable;
	}

	public String getTipoPromBase() {
		return tipoPromBase;
	}

	public void setTipoPromBase(String tipoPromBase) {
		this.tipoPromBase = tipoPromBase;
	}

	@Override
	public String toString() {
		return "DetPromocionDTO [numDetPromocion=" + numDetPromocion + ", numPromocion=" + numPromocion + ", nombre="
				+ nombre + ", descripcion=" + descripcion + ", esBase=" + esBase + ", llevarN=" + llevarN + ", pagueM="
				+ pagueM + ", porcDsctoPlan=" + porcDsctoPlan + ", tipoPromAcumulable=" + tipoPromAcumulable
				+ ", tipoPromBase=" + tipoPromBase + "]";
	}
	
}
