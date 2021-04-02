
public class Detalles {

	private Golosinas golo = new Golosinas();
	private double cantidad;
	
	Detalles(){}
	
	Detalles(Golosinas golo, double cantidad){
		this.golo = golo;
		this.cantidad = cantidad;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Golosinas getGolo() {
		return golo;
	}

	public void setGolo(Golosinas golo) {
		this.golo = golo;
	}
}
