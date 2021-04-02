
public class Golosinas {

	protected int codigo;
	protected String descripcion;
	protected double precioVenta; 
	protected String[] sabores = new String[3];
	protected boolean promo;

	Golosinas(){}
	
	Golosinas(int codigo, String descripcion, double precioVenta, String[] sabores, boolean promo){
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.sabores = sabores;
		this.promo = promo;
	}
	
	public boolean getPromo() {
		return promo;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String[] getSabores() {
		return sabores;
	}
	public void setSabores(String[] sabores) {
		this.sabores = sabores;
	}
	
}
