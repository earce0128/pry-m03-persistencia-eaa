package mx.com.qtx.cotizadorv1ds.core.componentes;

import java.math.BigDecimal;

public class Monitor extends ComponenteSimple {
	
	protected Monitor(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase) {
		super(id, descripcion, marca, modelo, costo, precioBase);
	}
	
	@Override
	public String getCategoria() {
		return "Monitor";
	}

	@Override
	public String toString() {
		return "Monitor [id=" + this.id + 
				", categoria=" + this.getCategoria() +
				", marca=" + this.marca +
				", modelo=" + this.modelo +
				", descripcion=" + this.descripcion +
				", costo=" + this.costo +
				", precio base=" + this.precioBase +
				", utilidad=" + this.calcularUtilidad() + "]";
	}

}
