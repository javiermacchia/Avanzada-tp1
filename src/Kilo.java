
public final class Kilo extends Golosinas {

	private double porcentaje;
	
	Kilo(){}
	
	Kilo(int a, String b, double c, String[] d, boolean e, boolean oferta, double porcentaje){
		super(a,b,c,d,e);
		this.porcentaje = porcentaje;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

}
