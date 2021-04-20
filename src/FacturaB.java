import java.util.Calendar;

public class FacturaB extends Factura {

	FacturaB(){}
	
	FacturaB(Calendar a, Calendar b, long d, Pago e,
			Detalles[] f, Mayoristas g, String h, int i){
		super(a,b,d,e,f,g,h,i);
		
	}
	
	public void imprimirInfo() {
		
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
		double totalIVA=0;
		
		for(int i=0; i<deta.length; i++) {
				
					if(((Empaquetadas)this.deta[i].getGolo()).getEs2x1()) {
						total = (deta[i].getCantidad() * 2) * deta[i].getGolo().getPrecioVenta(); 
						totalIVA = totalIVA +  total * (IVA/100);
					}
					else {
						total = (deta[i].getCantidad() * deta[i].getGolo().getPrecioVenta());
						totalIVA = totalIVA + total * (IVA/100);
					}
			total = deta[i].getGolo().getPrecioVenta();	
			totalIVA = totalIVA + total * (IVA/100);
			
		}
		return totalIVA;
	}
	
	public Calendar calculoVencimiento() {

		return null;
	}
}
