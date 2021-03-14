import java.util.Calendar;

public class FacturaB extends Factura {

	private int contador;

	FacturaB(){}
	
	FacturaB(Calendar a, Calendar b, String d, Pago e, Detalles[] f, Mayoristas g, int contador){
		super(a,b,d,e,f,g);
		this.contador = contador;
	}
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public double calcularTotal(double a) {
		return a;
	}
	
	public void iva() {}

	public void calculoIva() {}
	
}
