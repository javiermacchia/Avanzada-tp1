import java.util.*;

public class Factura implements ICalculo{

	protected Calendar fechaEmision;
	protected Calendar fechaVencimiento;
	protected String numeroFactura;
	protected Pago pago = new Pago();
	protected Detalles[] deta = new Detalles[2];
	protected Mayoristas mayo = new Mayoristas();
	
	Factura(){
		pago = new Pago();
		for(int i=0; i<deta.length; i++) {
			deta[i] = new Detalles();
		}
	}
	
	Factura(Calendar fechaEmision, Calendar fechaVencimiento, 
			String numeroFactura, Pago pago, Detalles[] deta, Mayoristas mayo){
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroFactura = numeroFactura;
		this.pago = pago;
		this.deta = deta;
		this.mayo = mayo;
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
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Calendar fechaPago, String formaPago, long numRecibo, long numTransaccion, boolean pagos) { // composicion 
		pago.setFechaPago(fechaPago);
		pago.setFormaPago(formaPago);
		pago.setNumRecibo(numRecibo);
		pago.setNumTransaccion(numTransaccion);
		pago.setPagos(pagos);
	}
	public Detalles[] getDeta() {
		return deta;
	}
	public void setDeta(Golosinas golo, int i) {
		deta[i].setGolo(golo);
	}
	public Mayoristas getMayo() {
		return mayo;
	}
	public void setMayo(Mayoristas mayo) {
		this.mayo = mayo;
	}
	
	public double calcularTotal(double a ) {
		
		return a;
	}
	
	public void iva() {
		
	}

	public void calculoIva() {
		
	}
}