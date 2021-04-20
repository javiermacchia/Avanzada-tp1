import java.util.*;

public abstract class Factura implements ICalculo{

	protected Calendar fechaEmision;
	protected Calendar fechaVencimiento;
	protected static int centroEmisor;
	protected long numeroFactura;
	protected static final String nombreTienda = "TODO DULCE";
	protected Pago pago;
	protected Detalles[] deta = new Detalles[1];
	protected Mayoristas mayo = new Mayoristas();
	
	Factura(){
		pago = new Pago();
		for(int i=0; i<deta.length; i++) {
			deta[i] = new Detalles();
		}
	}
	
	Factura(Calendar fechaEmision, Calendar fechaVencimiento, 
			long numeroFactura, Pago pago, Detalles[] deta, Mayoristas mayo, String nombreTienda, int centroEmisor){
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroFactura = numeroFactura;
		this.pago = pago;
		this.deta = deta;
		this.mayo = mayo;
		this.centroEmisor = centroEmisor;
	}
	
	public String getNombreTienda() {
		return nombreTienda;
	}
	
	public Calendar getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Calendar fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Calendar fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public long getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public int getCentroEmisor() {
		return centroEmisor;
	}

	public void setCentroEmisor(int centroEmiso) {
		centroEmisor = centroEmiso;
	}

	public Pago getPago() {
		return pago;
	}
	public void setPago(Calendar fechaPago, String formaPago, long numRecibo, long numTransaccion) { // composicion 
		pago.setFechaPago(fechaPago);
		pago.setFormaPago(formaPago);
		pago.setNumRecibo(numRecibo);
		pago.setNumTransaccion(numTransaccion);
	}
	public Detalles[] getDeta() {
		return deta;
	}
	public void setDeta(double cantidad, Golosinas golo, int i) {
		deta[i].setGolo(golo);
		deta[i].setCantidad(cantidad);
	}
	public Mayoristas getMayo() {
		return mayo;
	}
	public void setMayo(Mayoristas mayo) {
		this.mayo = mayo;
	}
	
	public Calendar calculoVencimiento(Calendar fechaVencimiento) {
		
		fechaVencimiento.add(Calendar.DATE, DIA);
		
		return fechaVencimiento;
	}
	
	public abstract double calcularTotal();

	public abstract double calcularIva();
	
	public void imprimirInfo() {
		System.out.println("Nombre de la tienda: "+nombreTienda);
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
			System.out.println(((Kilo)deta[i].getGolo()).getPorcentaje()!=0 || (((Empaquetadas)deta[i].getGolo()).getEs2x1())?"Esta en promoxion":"No esta en promocion");
			
			if(deta[i].getGolo() instanceof Kilo) {
				System.out.println("Porcentaje de descuento: "+((Kilo)this.deta[i].getGolo()).getPorcentaje());
			}
			else {
				System.out.println(((Empaquetadas)this.deta[i].getGolo()).getEs2x1()?"Es promo 2x1":"No es promo");
				System.out.println("Nombre del deposito: "+((Empaquetadas)this.deta[i].getGolo()).getDepo().getNombre());
				System.out.println("Domicilio del deposito: "+((Empaquetadas)this.deta[i].getGolo()).getDepo().getDomicilio());
				System.out.println(((Empaquetadas)this.deta[i].getGolo()).getDepo().getEsPropio()?"El deposito es propio":"El deposito no es propio");
			}
		}
		
		System.out.println();
		System.out.println("Datos del mayorista \n");
		System.out.println("Razon social: "+mayo.getRazonSocial());
		System.out.println("Cuit: "+mayo.getCuit());
		System.out.println(mayo.getCondicionIva()?"Condicion IVA: responsable inscripto \n":"Condicion IVA: otros \n");
		
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
		
		System.out.println("Subtotal $"+(calcularTotal()-calcularIva()));
		System.out.println("IVA $"+String.format("%.3f", calcularIva()));
		System.out.println("Total $"+calcularTotal());
		
	}
}