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
	
	public String getOperadorLogistico() {
		return operadorLogistico;
	}

	public void setOperadorLogistico(String operadorLogistico) {
		this.operadorLogistico = operadorLogistico;
	}

	public int getCantFacturaA() {
		return cantFacturaA;
	}

	public void setCantFacturaA(int cantFacturaA) {
		this.cantFacturaA = cantFacturaA;
	}
	
	public void imprimirInfo() {
		
		System.out.println("Nombre de la tienda: "+nombreTienda);
		System.out.println("Operador logistico: "+operadorLogistico);
		System.out.println("Numero de factura: "+centroEmisor+"-"+numeroFactura);
		System.out.println("Nombre de la tienda: "+nombreTienda);
		System.out.println("Fecha de emision: "+fechaEmision.get(Calendar.DATE)+"/"+(fechaEmision.get(Calendar.MONTH)+1)+"/"+fechaEmision.get(Calendar.YEAR));
		System.out.println("Fecha de vencimiento: "+fechaVencimiento.get(Calendar.DATE)+"/"+(fechaVencimiento.get(Calendar.MONTH)+1)+"/"+fechaVencimiento.get(Calendar.YEAR)+"\n");
		
		System.out.println("Detalles: \n");
		for(int i=0; i<deta.length; i++) {
			System.out.println("Cantidad de golosinas: "+deta[i].getCantidad());
			System.out.println("Codigo: "+deta[i].getGolo().getCodigo());
			System.out.println("Descripcion: "+deta[i].getGolo().getDescripcion());
			System.out.println("Precio de venta: "+deta[i].getGolo().getPrecioVenta());	
			for(int j=0; j<deta[i].getGolo().getSabores().length; j++) {
				System.out.println("Sabor: "+deta[i].getGolo().getSabores()[j]);
			}
			System.out.println(deta[i].getGolo().getPromo()?"Esta en promoxion":"No esta en promocion");
			
			if(deta[i].getGolo() instanceof Kilo) {
				System.out.println("Porcentaje de descuento: "+((Kilo)this.deta[i].getGolo()).getPorcentaje());
			}
			else {
				System.out.println(((Empaquetadas)this.deta[i].getGolo()).getEs2x1()?"Es promo 2x1":"No es promo");
				System.out.println("Nombre del deposito: "+((Empaquetadas)this.deta[i].getGolo()).getDepo().getNombre());
				System.out.println("Domicilio del deposito: "+((Empaquetadas)this.deta[i].getGolo()).getDepo().getDomicilio());
				System.out.println(((Empaquetadas)this.deta[i].getGolo()).getDepo().getEsPropio()?"El deposito es propio":"El deposito no es propio");
			}
			
			System.out.println("IVA total: "+String.format("%.3f", calculoIva())+"\n");
		}
		
		System.out.println();
		System.out.println("Datos del mayorista \n");
		System.out.println("Razon social: "+mayo.getRazonSocial());
		System.out.println("Cuit: "+mayo.getCuit());
		System.out.println(mayo.getCondicionIva()?"Condicion IVA: responsable inscripto":"Condicion IVA: otros");
		
		System.out.println();
		
		if(pago.getFormaPago()==null) {
			System.out.println("No se realizo el pago");
		}
		else {
			System.out.println("Datos del pago: \n");
			System.out.println("Numero de transaccion: "+pago.getNumTransaccion());
			System.out.println("Numero de recibo: "+pago.getNumRecibo());
			System.out.println("Forma de pago: "+pago.getFormaPago());
			System.out.println("Fecha de pago: "+pago.getFechaPago().get(Calendar.DATE)+"/"+(pago.getFechaPago().get(Calendar.MONTH)+1)+"/"+pago.getFechaPago().get(Calendar.YEAR));
		}
		
		System.out.println("Subtotal $"+(calcularTotal()-calculoIva()));
		System.out.println("IVA $"+String.format("%.3f", calculoIva()));
		System.out.println("Total $"+calcularTotal());
		
	}
	
	public double calcularTotal() {
	
		double total = 0;
		double IVA = calculoIva();
		double totalFacturado = 0;
		
		for(int i=0; i<deta.length; i++) {
			
			if(deta[i].getGolo().getPromo()) {
				
				if(((Empaquetadas)this.deta[i].getGolo()).getEs2x1()) {
					total = total + (deta[i].getCantidad() * 2) * deta[i].getGolo().getPrecioVenta(); 
				}
				else {
					total = total + (deta[i].getCantidad() * deta[i].getGolo().getPrecioVenta()) - (((Kilo)deta[i].getGolo()).getPorcentaje()/100);
				}
			}
			total = total + deta[i].getGolo().getPrecioVenta();	
			
		}
		
		totalFacturado = total + IVA;
		
		return totalFacturado;
	}

	public double calculoIva() {
	
		double total = 0;
		double totalIVA = 0;
		
		for(int i=0; i<deta.length; i++) {
			
			if(deta[i].getGolo().getPromo()) {
				
				if(((Empaquetadas)this.deta[i].getGolo()).getEs2x1()) {
					total = total + (deta[i].getCantidad() * 2) * deta[i].getGolo().getPrecioVenta(); 
				}
				else {
					total = total + (deta[i].getCantidad() * deta[i].getGolo().getPrecioVenta()) - (((Kilo)deta[i].getGolo()).getPorcentaje()/100);
				}
			}
			total = total + deta[i].getGolo().getPrecioVenta();	
		}
		
		totalIVA = total * (IVA/100);
		
		return totalIVA;
	}
}
