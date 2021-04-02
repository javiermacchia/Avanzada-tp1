import java.util.*;

public class Principal {

	public static Scanner leer = new Scanner(System.in);
	
	public static Validaciones vali = new Validaciones();
	
	public static void main(String[] args) {
		
		
		Depositos[] depo = new Depositos[1]; // son 5
		
		System.out.println("Ingreso de depositos \n");
		
		cargaDepositos(depo);
		
		
		Mayoristas[] mayo = new Mayoristas[1]; // son 15
		
		System.out.println("Ingreso de clientes \n");
		
		cargaClientes(mayo);
				
		
		Golosinas[] golo = new Golosinas[1]; // Definir dimension 
		
		System.out.println("Ingreso de golosinas \n");
		
		cargaGolosinas(golo, depo);
		

		Factura[] fact = new Factura[1];
		
		System.out.println("Carga de facturas \n");
		
		cargaFacturas(fact,golo,mayo);
		
		int opcion = 0;
		
		Menu(opcion,fact,mayo,depo);
		
	} // cierra el main
	
	public static void Menu(int a, Factura[] fact, Mayoristas[] mayo, Depositos[] depo) {
		
		boolean menu = true;
		
		System.out.println("Bienvenido al menu \n");
		
		while(menu) {

			System.out.println("Ingrese opcion: \n");
			System.out.println("1-Mostrar factura");
			System.out.println("2-Registro / modificacion de factura");
			System.out.println("3-Mostrar Cliente");
			System.out.println("4-Cantidad de facturas vendidas externos");
			System.out.println("6-Cantidad de facturas de depositos externos");
			System.out.println("10-Salir");
			a = vali.Entero();
			
			switch(a) {
		
			case 1:
				mostrarInfo(fact);
				break;
				
			case 2:
				System.out.println("Ingrese numero de factura: ");
				long busquedaFactura = vali.Long();
				
				regModPago(fact, busquedaFactura);
				
				break;
				
			case 3:
				mostrarCliente(mayo, fact);
				break;
				
			case 4:
				cantPaquetes(fact);
				break;
				
			case 6:
				externos(fact, depo);
				break;
				
			case 7:
				K(fact);
				break;
				
			case 10:
				System.out.println("Gracias");
				menu=false;
				break;
				
			default:
				System.out.println("Opcion invalida, ingrese nueva opcion: ");
				break;
			}
		}
		
	}
	
	public static void periodoAnio(Factura[] fact) {
		
		/*Para cada periodo del año actual (mes y año), mostrar por pantalla la cantidad de
		facturas A emitidas, la cantidad de facturas B emitidas y el total de IVA a informar a la
		AFIP (total de IVA facturado).*/
		
		Calendar hoy = Calendar.getInstance();
		int mes = 1;
		boolean ciclo = true;
		
		while(ciclo) {
			int cantFacturas = 0;
			double totalIva = 0;
			
			for(int i=0; i<fact.length; i++) {
				
				if(fact[i].getFechaEmision().get(Calendar.MONTH)==mes && fact[i].getFechaEmision().get(Calendar.YEAR)==hoy.get(Calendar.YEAR)) {
				
					cantFacturas++;
					
					for(int j=0; j<fact[i].getDeta().length; j++) {
						totalIva = fact[i].calculoIva(fact[i].getDeta()[j].getGolo().getPrecioVenta());
					}
				}
			}
			
			System.out.println("Mes numero: "+mes+"\n");
			System.out.println("Cantidad de facturas A: "+((FacturaA)fact[0]).getCantFacturaA());
			System.out.println("Cantidad de facturas B: "+(cantFacturas-((FacturaA)fact[0]).getCantFacturaA()));
			System.out.println("Total de Iva Facturado "+totalIva);
			
			mes++;
			if(mes==12) {
				ciclo=false;
			}
		}
	} 
	
	public static void cantidadFacturasA(Factura[] fact) {
		System.out.println("Cantidad de facturas A"+((FacturaA)fact[0]).getCantFacturaA());
	}
	
	public static void K (Factura[] fact) {
		
		/*Mostrar por pantalla, número de factura, fecha de pago (si existe) de las facturas
		donde todas las golosinas vendidas comienzan con las dos primeras letras ingresadas
		por teclado.*/
		
		String letras = leer.nextLine();
		int cont = 0;
		
		for(int i=0; i<fact.length; i++) {
			
			for(int j=0; j<fact[i].getDeta().length; j++) {
				
				if(letras.equalsIgnoreCase(fact[i].getDeta()[j].getGolo().getDescripcion().substring(0, 1))) {
					cont++;
				}
			}
			
			if(cont==fact[i].getDeta().length) {
				System.out.println("Numero de factura: "+fact[i].getCentroEmisor()+"-"+fact[i].getNumeroFactura());
				if(fact[i].getPago().getFormaPago()!=null) {
					System.out.println("Fecha de pago: "+vali.mostrarFecha(fact[i].getPago().getFechaPago()));
				}
			}	
		}
	}
	
	public static void externos(Factura[] fact, Depositos[] depo) {
		
		/*Para cada depósito externo, indicar la cantidad de facturas cuya cantidad vendida, de
		  alguno de sus productos, sea menor a un número generado al azar de tres dígitos
		  enteros.*/
		
		int cont = 0;
		
		Random rand = new Random();
		int random = rand.nextInt(999);
		
		for(Depositos d: depo) {
			if(d.getEsPropio()==false) {
				
				for(int i=0; i<fact.length; i++) {
					
					int cont2 = 0;
					
					for(int j=0; j<fact[i].getDeta().length; j++) {
						
						if(d.getNombre().equalsIgnoreCase(((Empaquetadas)fact[i].getDeta()[j].getGolo()).getDepo().getNombre())) {
							
							if(fact[i].getDeta()[j].getCantidad()<random) {
							cont2++; 
							}
						}
					}
					if(cont2!=0) {
						cont++;
					}
				}
			}
		}
		System.out.println("Cantidad de facturas: "+cont);
	}	
	
	public static void cantPaquetes(Factura[] fact) {
		
		/*Indicar la cantidad de golosinas en paquete, que no fueron vendidas durante el año en
		curso a ningún cliente responsable inscripto.*/
		
		Calendar hoy = Calendar.getInstance();
		int anioCurso = hoy.get(Calendar.YEAR);
		
		int cantGoloPaquetes = 0;
		
		for(int i=0; i<fact.length; i++) {
			
			if(fact[i].getFechaEmision().get(Calendar.YEAR)==anioCurso && fact[i].getMayo().getCondicionIva()==false && fact[i].getPago().getFormaPago()==null) {
				
				for(int j=0; j<fact[i].getDeta().length; j++) {
					if(fact[i].getDeta()[j].getGolo() instanceof Empaquetadas) {
						cantGoloPaquetes++;
					}
				}
			}
		}
		
		System.out.println("Cantidad de golosinas por paquete: "+cantGoloPaquetes);
	}
	
	public static void vencidasImpagas(Factura[] fact) {

		Calendar hoy = Calendar.getInstance();
		
		double[] total = new double[2]; 
		
		for(int i=0; i<fact.length; i++) {
			
			for(int j=0; j<fact[i].getDeta().length; j++) {
			
				total[j] = fact[i].getDeta()[j].getGolo().getPrecioVenta();
				
			}
		}
		
		
		
		for(Factura f: fact) {
			
			if(f.getFechaVencimiento().after(hoy) && f.getPago().getFormaPago()==null) {
				
				
				
			}
			
		}
		
	}
		
	public static void mostrarInfo(Factura[] fact) {
		
		for(int i = 0; i<fact.length; i++) {
			fact[i].imprimirInfo();
		}
	}
	
	public static void mostrarCliente(Mayoristas[] mayo, Factura[] fact) {
		
		Calendar hoy = Calendar.getInstance();
		Calendar fechaVencimiento = Calendar.getInstance();
		fechaVencimiento.add(Calendar.MONTH, 3);
		int contFacturas=0;
		
		for(int i=0; i<mayo.length; i++) {
			
			System.out.println("Razon social: "+mayo[i].getRazonSocial());
			System.out.println("Cuit: "+mayo[i].getCuit());
			
			for(int j=0; j<fact.length; j++) {
				
				contFacturas++;
				
				if(mayo[i].getCuit()==fact[j].getMayo().getCuit()) {
					System.out.println("Numero de factura: "+fact[j].getNumeroFactura());
					
					System.out.println((fact[j] instanceof FacturaA)?"Factura A":"Factura B");
								
					if(fact[j].getFechaEmision().before(fechaVencimiento) && fact[j].getFechaEmision().after(hoy)) {
						
						System.out.println("Fecha de emision: "+vali.mostrarFecha(fact[i].getFechaEmision()));
						System.out.println("Fecha de vencimiento: "+vali.mostrarFecha(fact[i].getFechaVencimiento()));
					
					}
					else {
						System.out.println("No hay vencimientos en las fechas indicadas");
					}
					
					for(int c=0; c<fact[j].getDeta().length; c++) {
						
						System.out.println("Descripcion de la golosina: "+fact[j].getDeta()[c].getGolo().getDescripcion());
						System.out.println("Cantidad: "+fact[j].getDeta()[c].getCantidad());
						System.out.println("Precio de golosinas: "+(fact[j].getDeta()[c].getCantidad()+fact[j].getDeta()[c].getGolo().getPrecioVenta()));
						System.out.println(fact[j].getDeta()[c].getGolo().getPromo()?"Tiene promocion":"No tiene promocion");
						
					}		
				}
				else {
					System.out.println("No posee facturas");
				}
			}	
			System.out.println("Cantidad de facturas: "+contFacturas);
		}
	}

	public static void regModPago(Factura[] fact, long numero) {
		
		System.out.println("Ingrese numero de factura: ");
		long numFactura = vali.Long();
		
			for(int i=0; i<fact.length; i++) {
					
				if(fact[i].getNumeroFactura()==numFactura) {
				
				Calendar fecha = Calendar.getInstance();	
					
				System.out.println("Ingrese la fecha del pago");	
					
				System.out.println("Ingrese dia: ");
				int dia = leer.nextInt();
					
				System.out.println("Ingrese mes: ");
				int mes = leer.nextInt();
					
				System.out.println("Ingrese año: ");
				int año = leer.nextInt();
					
				fecha.set(año, mes-1, dia);
				fact[i].getPago().setFechaPago(fecha);
					
				System.out.println("Ingrese 1 credito, 2 debito o 3 transferencia: ");
				String medioPagoMod = vali.medioPago();
				fact[i].getPago().setFormaPago(medioPagoMod);
				
				System.out.println("Ingrese numero de recibo: ");
				long numReciboMod = vali.Long();
				fact[i].getPago().setNumRecibo(numReciboMod);
							
				System.out.println("Ingrese numero de transaccion: ");
				long numTransaccMod = vali.Long();
				fact[i].getPago().setNumTransaccion(numTransaccMod);
							
				}							
		}
	}
	
	public static void cargaClientes(Mayoristas[] mayo) {
		
		for(int i = 0; i<mayo.length; i++) {
			
			mayo[i] = new Mayoristas();
			
			System.out.println("Ingrese razon social: ");
			String razonSocial = leer.nextLine();
			mayo[i].setRazonSocial(razonSocial);
			
			System.out.println("Ingrese numero de cuit: ");
			long cuit = vali.Long();
			mayo[i].setCuit(cuit);
			
			System.out.println("Ingrese 1 si es responsable inscripto o 2 si no");
			boolean condicion = vali.ValidarBool();
			mayo[i].setCondicionIva(condicion);
			
		}
		
	}
	
	public static void cargaDepositos(Depositos[] depo) {
		
		for(int i=0; i<depo.length; i++) {
			
			depo[i] = new Depositos();
			
			System.out.println("Ingrese nombre del domicilio: ");
			String nombre = leer.nextLine();
			depo[i].setNombre(nombre);
			
			System.out.println("Ingrese domicilio");
			String domicilio = leer.nextLine();
			depo[i].setDomicilio(domicilio);
			
			System.out.println("Ingrese 1 si es propio o 2 si no lo es: ");
			boolean propio = vali.ValidarBool();
			depo[i].setEsPropio(propio);
			
		}
		
	}
		
	public static void cargaGolosinas(Golosinas[] golo, Depositos[] depo) {
	
		for(int i=0; i<golo.length; i++) {
				
				System.out.println("Ingrese codigo de golosina: ");
				int codigo = vali.Entero();
				
				System.out.println("Ingrese descripcion: ");
				String descripcion = leer.nextLine();
				
				System.out.println("Ingrese precio de venta: ");
				double precioVenta = vali.Decimal();
				
				String[] sabo = new String[3];
				
				String sabor = "";
				
				for(int j=0; j<sabo.length; j++) {
					System.out.println("Ingrese del sabor numero: "+(j+1));
					sabor = leer.nextLine();
					sabo[j] = sabor;
				}
				
				System.out.println("Ingrese 1 para golosina por kilo o 2 para empaquetada: ");
				boolean opcion = vali.ValidarBool();
			
				if(opcion==true) {
					golo[i] = new Kilo();
					
					if(golo[i].getPromo()==true) {
						System.out.println("Ingrese porcentaje de descuento: ");
						double porcentaje = vali.Descuento(); 
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
	}
		
	public static void cargaFacturas(Factura[] fact, Golosinas[] golo, Mayoristas[] mayo) {
		
		int contadorFacturaA = 0;
		
		for(int i=0; i<fact.length; i++) {
			
			Calendar fecha = Calendar.getInstance();
			
			Calendar fechaVencimiento = Calendar.getInstance();
			fechaVencimiento.add(Calendar.DATE, 30);
			
			System.out.println("Ingrese centro emisor");
			int cEmisor = vali.centroEmisor();
			
			System.out.println("Ingrese numero factura: ");
			int numFactu = vali.numFact();
			
			
			System.out.println("Ingrese numero de cuit del mayoristas: ");
			long numMayorista = vali.Long();
			boolean condicionIva = false;
			
			for(int d=0; d<mayo.length; d++) {
				if(numMayorista==mayo[d].getCuit()) {
					condicionIva = mayo[d].getCondicionIva();
				}
				else {
					System.out.println("Error, numero de cuit inexistente, ingrese nuevamente: ");
					numMayorista = vali.Long();
				}
			}
			
			Calendar fechaPago = Calendar.getInstance();
			
			System.out.println("Ingrese medio de pago, 1 credito, 2 debito, 3 transferencia: "); 
			String mPago = vali.medioPago();
			
			System.out.println("Ingrese numero de recibo: ");
			long numeroRecibo = vali.Long();
			
			System.out.println("Ingrese numero de transaccion: ");
			long numeroTransaccion = vali.Long();
			
			System.out.println("Ingrese 1 si abono o 2 si no");
			boolean pagos = vali.ValidarBool();
			
			if(condicionIva) {
				fact[i] = new FacturaA();
				
				System.out.println("Ingrese nombre del operador logistico: ");
				String nombre = leer.nextLine();
				((FacturaA)fact[i]).setOperadorLogistico(nombre);
				
				contadorFacturaA++;
				((FacturaA)fact[i]).setCantFacturaA(contadorFacturaA);
				
			}
			else {
				fact[i] = new FacturaB();
			}
					
			for(int j=0; j<fact[i].getDeta().length; j++) {
				
				System.out.println("Ingrese cantidad de golosinas");
				double cant = vali.Decimal();
				fact[i].getDeta()[j].setCantidad(cant);
				
				System.out.println("Ingrese numero de golosina: ");
				int numeroBusqueda = vali.Entero();
				
				for(int c=0; c<golo.length; c++) {
					if(numeroBusqueda==golo[c].getCodigo()) {
						fact[i].getDeta()[j].setGolo(golo[c]);
						
					}
				}
				
			}
			
			for(int c=0; c<mayo.length; c++) {
				if(numMayorista==mayo[c].getCuit()) {
					fact[i].setMayo(mayo[c]);
				}
			}
			
			fact[i].setFechaEmision(fecha);
			fact[i].setFechaVencimiento(fechaVencimiento);
			fact[i].setNumeroFactura(numFactu);
			fact[i].setCentroEmisor(cEmisor);
		
			if(pagos) {
				fact[i].getPago().setFechaPago(fechaPago);
				fact[i].getPago().setFormaPago(mPago);
				fact[i].getPago().setNumRecibo(numeroRecibo);
				fact[i].getPago().setNumTransaccion(numeroTransaccion);
			}
			
		}
	}
}
