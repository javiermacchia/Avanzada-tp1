import java.util.*;

public class Principal {

	public static Scanner leer = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Validaciones vali = new Validaciones();
		
		Depositos[] depo = new Depositos[2]; // son 5
		
		depo[0] = new Depositos();
		depo[0].setNombre("ECentro");
		depo[0].setDomicilio("CABA");
		depo[0].setLugar(true);
		
		depo[1] = new Depositos();
		depo[1].setNombre("EProvincia");
		depo[1].setDomicilio("Martinez");
		depo[1].setLugar(false);
		
		Mayoristas[] mayo = new Mayoristas[2]; // son 15
		
		mayo[0] = new Mayoristas();
		mayo[0].setCuit(1257486);
		mayo[0].setRazonSocial("PepitosSA");
		mayo[0].setCondicionIva(true);
		
		mayo[1] = new Mayoristas();
		mayo[1].setCuit(4852179);
		mayo[1].setRazonSocial("PilasSRL");
		mayo[1].setCondicionIva(true);
		
		Golosinas[] golo = new Golosinas[1];
		
		for(int i=0; i<golo.length; i++) {
			
			System.out.println("Ingrese codigo de golosina: ");
			int codigo = vali.Entero();
			
			System.out.println("Ingrese descripcion: ");
			String descripcion = leer.nextLine();
			
			System.out.println("Ingrese precio de vente: ");
			double precioVenta = vali.Decimal();
			
			String[] sabo = new String[3];
			
			for(int j=0; j<sabo.length; j++) {
				System.out.println("Ingrese del sabor numero: "+(j+1));
				String sabor = leer.nextLine();
				sabo[j] = sabor;
			}
			
			System.out.println("Ingrese 1 para golosina por kilo o 2 para empaquetada: ");
			boolean opcion = vali.ValidarBool();
		
			if(opcion==true) {
				golo[i] = new Kilo();
				
				System.out.println("Ingrese 1 si el producto tiene oferta o 2 si no tiene: ");
				boolean oferta = vali.ValidarBool();
				((Kilo)golo[i]).setOferta(oferta);
				
				if(((Kilo)golo[i]).getOferta()==true) {
					System.out.println("Ingrese porcentaje de descuento: ");
					double porcentaje = vali.Decimal(); // hay que validar que no sea mas de 100
					((Kilo)golo[i]).setPorcentaje(porcentaje);
				}
				
			}
			else {
				golo[i] = new Empaquetadas();
				
				System.out.println("Ingrese 1 si es 2x1 o 2 si no lo es: ");
				boolean promo = vali.ValidarBool();
				((Empaquetadas)golo[i]).setEs2x1(promo);
				
				boolean buscar = true;
				
				while(buscar) {
					
					System.out.println("Ingrese nombre del deposito: ");
					String nombreDepo = leer.nextLine();
					
					for(int j=0; j<depo.length; j++) {
						if(nombreDepo.equalsIgnoreCase(depo[j].getNombre())) {
							((Empaquetadas)golo[i]).setDepo(depo[j]);
							buscar = false;
						}
					}
					if(buscar) {
						System.out.println("Error, deposito inexistente");
					}
					
				}
				
			}
			
			golo[i].setCodigo(codigo);
			golo[i].setDescripcion(descripcion);
			golo[i].setPrecioVenta(precioVenta);
			golo[i].setSabores(sabo);
			
		}
		

		Factura[] fact = new Factura[1];
		
		for(int i=0; i<fact.length; i++) {
			
			Calendar fecha = Calendar.getInstance();
			
			System.out.println("Fecha de vencimiento: ");
			Calendar fechaVencimiento = Calendar.getInstance();
			
			System.out.println("Ingrese centro emisor");
			String cEmisor = vali.centroEmisor();
			
			System.out.println("Ingrese numero factura: ");
			int numFactu = vali.numFact();
			
			String numFacturaComp = cEmisor+"-"+numFactu;
			
			System.out.println("Ingrese numero de cuit del mayoristas: ");
			int numMayorista = vali.Entero();
			
			System.out.println("Ingrese 1 para factura A o 2 para factura B");
			boolean factura = vali.ValidarBool();
			
			if(factura==true) {
				fact[i] = new FacturaA();
			}
			else {
				fact[i] = new FacturaB();
			}
			
			for(int j=0; j<fact[i].getDeta().length; j++) {
				
				System.out.println("Ingrese numero de golosina: ");
				int numeroBusqueda = vali.Entero();
				
				for(int c=0; c<golo.length; c++) {
					if(numeroBusqueda==golo[c].getCodigo()) {
						fact[i].getDeta()[j].setGolo(golo[c]);
					}
				}
				
			}
			
			
			
		}
		
		
		
	}

}
