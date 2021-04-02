
public class Depositos {

	private String nombre;
	private String domicilio;
	private boolean esPropio;
	
	Depositos(){}
	
	Depositos(String nombre, String domicilio, boolean esPropio){
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.esPropio = esPropio;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public boolean getEsPropio() {
		return esPropio;
	}
	public void setEsPropio(boolean esPropio) {
		this.esPropio = esPropio;
	}
}
