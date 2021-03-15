import java.util.*;
public class Pago {
	
	private Calendar fechaPago;
	private String formaPago;
	private long numRecibo;
	private long numTransaccion;
	private boolean pagos;
	
	Pago(){}
	
	Pago(Calendar fechaPago, String formaPago, long numRecibo, long numTransaccion, boolean pagos){
		this.fechaPago = fechaPago;
		this.formaPago = formaPago;
		this.numRecibo = numRecibo;
		this.numTransaccion = numTransaccion;
		this.pagos = pagos;
	}

	public Calendar getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Calendar fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public long getNumRecibo() {
		return numRecibo;
	}
	public void setNumRecibo(long numRecibo) {
		this.numRecibo = numRecibo;
	}
	public long getNumTransaccion() {
		return numTransaccion;
	}
	public void setNumTransaccion(long numTransaccion) {
		this.numTransaccion = numTransaccion;
	}
	public boolean getPagos() {
		return pagos;
	}
	public void setPagos(boolean pagos) {
		this.pagos = pagos;
	}
}
