
public class Detalles {

	private Golosinas golo = new Golosinas();
	
	Detalles(){}
	
	Detalles(Golosinas golo){
		this.golo = golo;
	}

	public Golosinas getGolo() {
		return golo;
	}

	public void setGolo(Golosinas golo) {
		this.golo = golo;
	}
}
