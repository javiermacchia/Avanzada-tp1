import java.util.*;
public class Pago {
	
	private Calendar fechaPago;
	private String formaPago;
	private long numRecibido;
	private long numTransaccion;
	private boolean pagos;
	
	Pago(){}
	
	Pago(Calendar fechaPago, String formaPago, long numRecibido, long numTransaccion, boolean pagos){
		this.fechaPago = fechaPago;
		this.formaPago = formaPago;
		this.numRecibido = numRecibido;
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
	public long getNumRecibido() {
		return numRecibido;
	}
	public void setNumRecibido(long numRecibido) {
		this.numRecibido = numRecibido;
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
