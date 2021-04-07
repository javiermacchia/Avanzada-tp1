import java.util.*;

public abstract class Factura implements ICalculo{

	protected Calendar fechaEmision;
	protected Calendar fechaVencimiento;
	protected static int centroEmisor;
	protected long numeroFactura;
	protected static final String nombreTienda = "TODO DULCE";
	protected Pago pago = new Pago();
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

	public void setCentroEmisor(int centroEmisor) {
		this.centroEmisor = centroEmisor;
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
	
	public double calcularTotal() {
		double total = 0;
		
		return total;
	}

	public double calcularIva() {
		double iva = 0;
		
		return iva;
	}
	
	public abstract void imprimirInfo();
}