package mx.com.qtx.cotizadorv1ds.componentes;

import java.math.BigDecimal;

public class TarjetaVideo extends ComponenteSimple {
	private String memoria;

	protected TarjetaVideo(String id, String descripcion, String marca, String modelo, BigDecimal costo,
			BigDecimal precioBase, String memoria) {
		super(id, descripcion, marca, modelo, costo, precioBase);
		this.memoria = memoria;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	
	@Override
	public String getCategoria() {
		return "Tarjeta de Video";
	}
	
	@Override
	public void mostrarCaracteristicas() {
		super.mostrarCaracteristicas();
        System.out.println("Memoria: " + this.memoria);
		
	}
	
}
