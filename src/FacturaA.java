import java.util.Calendar;

public class FacturaA extends Factura {

	FacturaA(){}
	
	FacturaA(Calendar a, Calendar b, String d, Pago e, Detalles[] f, Mayoristas g){
		super(a,b,d,e,f,g);
	}
	
	public double calcularTotal(double a) {
		return a;
	}
	
	public void iva() {}

	public void calculoIva() {}
}
