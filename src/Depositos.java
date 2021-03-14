
public class Depositos {

	private String nombre;
	private String domicilio;
	private boolean lugar;
	
	Depositos(){}
	
	Depositos(String nombre, String domicilio, boolean lugar){
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.lugar = lugar;
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
	public boolean getLugar() {
		return lugar;
	}
	public void setLugar(boolean lugar) {
		this.lugar = lugar;
	}
}
