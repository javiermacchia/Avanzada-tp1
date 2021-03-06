import java.util.*;

public class Validaciones {

	public static Scanner leer = new Scanner(System.in);
	
	public String valiDosLetras() {
		
		String letras = "";
		boolean ciclo = true;	
		
		letras = leer.nextLine();
		
		while(ciclo) {
			
			if(letras.matches("[a-zA-Z][a-zA-Z]")==false || letras.length()!=2) {
				System.out.println("Error, tienen que ser dos letras, ingrese nuevamente");
				letras = leer.nextLine();
			}
			else {
				ciclo = false;
			}
		}
		
		return letras;
	}
	
	public String mostrarFecha(Calendar f) {
		
		String a ="";
		
		a = f.get(Calendar.DATE)+"/"+(f.get(Calendar.MONTH)+1)+"/"+f.get(Calendar.YEAR);
	
		return a;
	}
	
	public String medioPago() {
		int a = 0;
		String b = "";
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<=0 || a>3) {
			System.out.println("Error, debe ingresar 1, 2 o 3");
			leer.nextLine();
		}
		
		switch(a) {
		
		case 1:
			b = "Tarjeta credito";
			break;
			
		case 2:
			b = "Tarjeta debito";
			break;
		
		case 3:
			b = "Tranferencia";
			break;
		}
		
		return b;
	}
	
	public  int Entero() {
		int a = 0;
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<0) {
			System.out.println("Error, el n?mero debe ser igual o mayor a cero");
			leer.nextLine();
		}
		return a;
	}
		
	public double Decimal() {
		double a = 0;
		
		while(leer.hasNextDouble()==false || (a=leer.nextDouble())<0) {
			System.out.println("Error, el n?mero debe ser igual o mayor a cero");
			leer.nextLine();
		}
		return a;
	}
	
	public long valiLong() {
		long a = 0;
		
		while(leer.hasNextLong()==false || (a=leer.nextLong())<0) {
			System.out.println("Error, el n?mero debe ser igual o mayor a cero");
			leer.nextLine();
		}
		return a; 
	}
	
	public boolean ValidarBool() {
		boolean a = true;
		int b = 0;
		
		while(leer.hasNextInt()==false || (b=leer.nextInt())<1 || b>2) {
			System.out.println("Error, el n?mero debe ser 1 o 2");
			leer.nextLine();
		}

		if(b==1) {
			a = true;
		}
		else {
			a = false;
		}
	
		return a;
	}
	
	public int Dia() {
		int a=0;
		int b=0;
		
		System.out.println("Ingrese mes");
		b=Mes();
		
		switch(b) {
		
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			while(leer.hasNextInt()==false || (a=leer.nextInt())<0 || a>31) {
				leer.nextLine();
			}
		break;
		
		case 2:
			while(leer.hasNextInt()==false || (a=leer.nextInt())<0 || a>28) {
				leer.nextLine();
			}
			break;
			
		case 4: case 6: case 9: case 11:
			while(leer.hasNextInt()==false || (a=leer.nextInt())<0 || a>30) {
				leer.nextLine();
			}
			break;
		}
		return a;
	}

	public int Mes() {
		int a = 0;
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<1 || 12>a) {
			System.out.println("Error, el n?mero debe ser un numero de mes (entre 1 y 12)");
			leer.nextLine();
		}
		return a;
	}
	
	public int anio() {
		int a = 0;
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<1920 || 2021>a) {
			leer.nextLine();
		}
		return a;
	}
	
	public int centroEmisor() {
		int a = 0;
	
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<1000 || a>9999) {
			System.out.println("Error, el n?mero debe tener entre 1 y 4 dijitos");
			leer.nextLine();
		}
		return a;
	}
	
	public double Descuento() {
		double a = 0;
		
		while(leer.hasNextDouble()==false || (a=leer.nextDouble())<=0 || a>=100) {
			System.out.println("Error, el n?mero debe ser mayor a 0 o menor a 100");
			leer.nextLine();
		}
		return a;
	}

}
