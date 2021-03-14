
public class Kilo extends Golosinas {

	private boolean oferta;
	private double porcentaje;
	
	Kilo(){}
	
	Kilo(int a, String b, double c, String[] d, boolean oferta, double porcentaje){
		super(a,b,c,d);
		this.oferta = oferta;
		this.porcentaje = porcentaje;
	}
	
	public boolean getOferta() {
		return oferta;
	}

	public void setOferta(boolean oferta) {
		this.oferta = oferta;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	
	public void Mostrar() {
		
	}
}
