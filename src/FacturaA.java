import java.util.Calendar;

public class FacturaA extends Factura {
	
	private static String operadorLogistico;
	private static int cantFacturaA;

	FacturaA(){}
	
	FacturaA(Calendar a, Calendar b, long d, Pago e, 
			Detalles[] f, Mayoristas g, String h,
			int i, String operadorLogistico, int cantFacturaA){
		super(a,b,d,e,f,g,h,i);
		this.operadorLogistico = operadorLogistico;
		this.cantFacturaA = cantFacturaA;
	}
	
	public static String getOperadorLogistico() {
		return operadorLogistico;
	}

	public static void setOperadorLogistico(String operadorLogistic) {
		operadorLogistico = operadorLogistic;
	}

	public int getCantFacturaA() {
		return cantFacturaA;
	}

	public static void setCantFacturaA(int canFacturaA) {
		cantFacturaA = canFacturaA;
	}
	
	public void imprimirInfo() {
		System.out.println("Operador logistico: "+operadorLogistico);
	}
	
	public double calcularTotal() {
	
		double total = 0;
		double IVA = calcularIva();
		double totalFacturado = 0;
		
		for(int i=0; i<deta.length; i++) {
				
				if(((Empaquetadas)this.deta[i].getGolo()).getEs2x1()) {
					total = total + (deta[i].getCantidad() * 2) * deta[i].getGolo().getPrecioVenta(); 
				}
				else {
					total = total + (deta[i].getCantidad() * deta[i].getGolo().getPrecioVenta()) - (((Kilo)deta[i].getGolo()).getPorcentaje()/100);
				}
			total = total + deta[i].getGolo().getPrecioVenta();	
		}
		
		totalFacturado = total + IVA;
		
		return totalFacturado;
	}

	public double calcularIva() {
	
		double total = 0;
		double totalIVA = 0;
		
		for(int i=0; i<deta.length; i++) {
				
				if(((Empaquetadas)this.deta[i].getGolo()).getEs2x1()) {
					total = total + (deta[i].getCantidad() * 2) * deta[i].getGolo().getPrecioVenta(); 
				}
				else {
					total = total + (deta[i].getCantidad() * deta[i].getGolo().getPrecioVenta()) - (((Kilo)deta[i].getGolo()).getPorcentaje()/100);
				}
			total = total + deta[i].getGolo().getPrecioVenta();	
		}
		
		totalIVA = total * (IVA/100);
		
		return totalIVA;
	}


	public Calendar calculoVencimiento() {

		return null;
	}
}
