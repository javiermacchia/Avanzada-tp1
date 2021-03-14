
public class Empaquetadas extends Golosinas {

	private boolean es2x1;
	private Depositos depo = new Depositos(); 
	
	Empaquetadas(){}
	
	Empaquetadas(int a, String b, double c, String[] d, boolean es2x1, Depositos depo){
		super(a,b,c,d);
		this.es2x1 = es2x1;
		this.depo = depo;
	}
	
	public boolean getEs2x1() {
		return es2x1;
	}

	public void setEs2x1(boolean es2x1) {
		this.es2x1 = es2x1;
	}
	 
	public Depositos getDepo() {
		return depo;
	}

	public void setDepo(Depositos depo) {
		this.depo = depo;
	}
	
	public void Mostrar() {}
	
}
