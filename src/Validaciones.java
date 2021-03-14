import java.util.*;

public class Validaciones {

	public static Scanner leer = new Scanner(System.in);
	
	public static int Entero() {
		int a = 0;
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<0) {
			System.out.println("Error, el número debe ser igual o mayor a cero");
			leer.nextLine();
		}
		return a;
	}
	
	
	public static double Decimal() {
		double a = 0;
		
		while(leer.hasNextDouble()==false || (a=leer.nextDouble())<0) {
			System.out.println("Error, el número debe ser igual o mayor a cero");
			leer.nextLine();
		}
		
		return a;
	}
	
	public static long Long() {
		long a = 0;
		
		while(leer.hasNextLong()==false || (a=leer.nextLong())<0) {
			System.out.println("Error, el número debe ser igual o mayor a cero");
			leer.nextLine();
		}
		
		return a; 
	}
	
	public static boolean ValidarBool() {
		boolean a = true;
		int b = 0;
		
		while(leer.hasNextInt()==false || (b=leer.nextInt())<1 || b>2) {
			System.out.println("Error, el número debe ser 1 o 2");
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
	
	public static int Mes() {
		int a = 0;
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<1 || 12>a) {
			System.out.println("Error, el número debe ser un numero de mes (entre 1 y 12)");
			leer.nextLine();
		}
		
		return a;
	}

	public static String centroEmisor() {
		int a = 0;
		String b = "";
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<0 || a>9999) {
			System.out.println("Error, el número debe tener entre 1 y 4 dijitos");
			leer.nextLine();
		}
		
		if(a<10) {
			b = "000"+a;
		}
		else if(a<100) {
			b = "00"+a;
		}
		else if(a<1000) {
			b = "0"+a;
		}
		
		return b;
	}
	
	public static int numFact() {
		int a = 0;
		
		while(leer.hasNextInt()==false || (a=leer.nextInt())<9999999 || a>99999999) {
			System.out.println("Error, el número debe tener 7 u 8 dijitos");
			leer.nextLine();
		}
		return a;
	}
	
	public static double Descuento() {
		double a = 0;
		
		while(leer.hasNextDouble()==false || (a=leer.nextDouble())<=0 || a>=100) {
			System.out.println("Error, el número debe ser igual o mayor a cero");
			leer.nextLine();
		}
		
		return a;
	}

}
