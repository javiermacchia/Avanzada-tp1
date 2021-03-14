
public class Mayoristas {

	private boolean condicionIva;
	private String razonSocial;
	private long cuit;

	Mayoristas(){}
	
	Mayoristas(boolean condicionIva, String razonSocial, long cuit){
		this.condicionIva = condicionIva;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
	}
	
	public boolean getCondicionIva() {
		return condicionIva;
	}
	public void setCondicionIva(boolean condicionIva) {
		this.condicionIva = condicionIva;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public long getCuit() {
		return cuit;
	}
	public void setCuit(long cuit) {
		this.cuit = cuit;
	}
	
}