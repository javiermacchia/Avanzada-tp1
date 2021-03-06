import java.util.*;

public class Principal {

	public static Scanner leer = new Scanner(System.in);
	
	public static Validaciones vali = new Validaciones();
	
	public static void main(String[] args) {
			
		Depositos[] depo = new Depositos[1]; 
		
		System.out.println("Ingreso de depositos \n");
		
		cargaDepositos(depo);
		
		
		Mayoristas[] mayo = new Mayoristas[1]; 
		
		System.out.println("Ingreso de clientes \n");
		
		cargaClientes(mayo);
				
		
		Golosinas[] golo = new Golosinas[1]; 
		
		System.out.println("Ingreso de golosinas \n");
		
		cargaGolosinas(golo, depo);
		

		Factura[] fact = new Factura[2];
		
		System.out.println("Carga de facturas \n");
		
		cargaFacturas(fact,golo,mayo);
		
		int opcion = 0;
		
		Menu(opcion,fact,mayo,depo,args);
		
	} 
	
	public static void Menu(int a, Factura[] fact, Mayoristas[] mayo, Depositos[] depo, String[] args) {
		
		boolean menu = true;
		
		System.out.println("Bienvenido al menu \n");
		
		while(menu) {

			System.out.println("Ingrese opcion: \n");
			System.out.println("1-Registro / modificacion de factura");
			System.out.println("2-Mostrar Cliente");
			System.out.println("3-Cantidad de facturas vendidas externos");
			System.out.println("4-Numero de factura vencida e impaga");
			System.out.println("5-Cantidad de facturas de depositos externos");
			System.out.println("6-Periodo anio");
			System.out.println("7-Punto K");
			System.out.println("8-Cantidad de facturas A");
			System.out.println("9-Salir");
			a = vali.Entero();
			
			switch(a) {

			case 1:				
				regModPago(fact);
				break;
				
			case 2:
				mostrarCliente(mayo, fact);
				break;
				
			case 3:
				cantPaquetes(fact);
				break;
				
			case 4:
				vencidasImpagas(fact,args);
				break;
				
			case 5:
				externos(fact, depo);
				break;
				
			case 6:
				periodoAnio(fact);
				break;
				
			case 7:
				numeroFactura(fact);
				break;
				
			case 8:
				cantidadFacturasA(fact);
				break;
				
			case 9:
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
		
		/*Para cada periodo del a?o actual (mes y a?o), mostrar por pantalla la cantidad de
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
					totalIva = totalIva + fact[i].calcularIva();
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
		System.out.println("Cantidad de facturas A: "+((FacturaA)fact[0]).getCantFacturaA());
	}
	
	public static void numeroFactura (Factura[] fact) {
		
		/*Mostrar por pantalla, n?mero de factura, fecha de pago (si existe) de las facturas
		donde todas las golosinas vendidas comienzan con las dos primeras letras ingresadas
		por teclado.*/
		
		String letras = vali.valiDosLetras();
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
		
		/*Para cada dep?sito externo, indicar la cantidad de facturas cuya cantidad vendida, de
		  alguno de sus productos, sea menor a un n?mero generado al azar de tres d?gitos
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
		
		/*Indicar la cantidad de golosinas en paquete, que no fueron vendidas durante el a?o en
		curso a ning?n cliente responsable inscripto.*/
		
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
	
	public static void vencidasImpagas(Factura[] fact, String[] args) {
		
		/*Mostrar por pantalla los n?meros de facturas vencidas impagas, ordenados por
		importe en forma descendente cuyo importe total vendido supere el valor ingresado
		como argumento de la aplicaci?n y se trate de ventas exclusivas de golosinas por
		peso.*/

		Calendar hoy = Calendar.getInstance();
		
		double[] importe = new double[fact.length]; 
		double[] importeDescendente = new double[fact.length];
		int c=0;
		
		for(int i=0; i<fact.length; i++) {
		
			importe[i] = fact[i].calcularTotal();
		}
		
		Arrays.sort(importe);
		
		for(int i=importe.length-1; i>=0; i--) {
			importeDescendente[c] = importe[i];
			c++;
		}
		
		for(Double imp: importeDescendente) {
			
			for(int j=0; j<fact.length; j++) {
				
				if(imp==fact[j].calcularTotal() && fact[j].calcularTotal()>Double.parseDouble(args[0])) {
					
					if(hoy.after(fact[j].getFechaVencimiento()) && fact[j].getPago().getFormaPago()==null) {
						for(int z=0; z<fact[j].getDeta().length; z++) {
							if(fact[j].getDeta()[z].getGolo() instanceof Kilo) {
								System.out.println("Numero de factura: "+fact[j].getCentroEmisor()+"-"+fact[j].getNumeroFactura());
							}
						}
					}
				}
			}
		}
	}
	
	public static void mostrarCliente(Mayoristas[] mayo, Factura[] fact) {
	
		/*Para cada uno de los clientes, mostrar su CUIT y raz?n social, n?meros de factura
		correspondientes, tipo (factura A o B), fecha de emisi?n y de vencimiento de aquellas
		emitidas durante los tres ?ltimos meses. Incluir la descripci?n, cantidad, precio de las
		golosinas vendidas y si tiene alguna promoci?n o descuento aplicado, y la cantidad de
		facturas emitidas.*/
		
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
						System.out.println(((Kilo)fact[j].deta[c].getGolo()).getPorcentaje()!=0 || (((Empaquetadas)fact[j].deta[c].getGolo()).getEs2x1())?"Esta en promoxion":"No esta en promocion");
						
					}		
				}
				else {
					System.out.println("No posee facturas");
				}
			}	
			System.out.println("Cantidad de facturas: "+contFacturas);
		}
	}

	public static void regModPago(Factura[] fact) {
		
		/*A partir de un n?mero de factura ingresado por teclado, registrar el pago de la misma
		o bien, modificar el existente.*/
		
		boolean ciclo = true;
		System.out.println("Ingrese numero de factura: ");
		long numFactura = vali.valiLong();
		
		while(ciclo) {
			
			for(int i=0; i<fact.length; i++) {
				
				if(fact[i].getNumeroFactura()==numFactura) {
				
				Calendar fecha = Calendar.getInstance();	
			
				fact[i].getPago().setFechaPago(fecha);
					
				System.out.println("Ingrese 1 credito, 2 debito o 3 transferencia: ");
				String medioPagoMod = vali.medioPago();
				fact[i].getPago().setFormaPago(medioPagoMod);
				
				System.out.println("Ingrese numero de recibo: ");
				long numReciboMod = vali.valiLong();
				fact[i].getPago().setNumRecibo(numReciboMod);
							
				System.out.println("Ingrese numero de transaccion: ");
				long numTransaccMod = vali.valiLong();
				fact[i].getPago().setNumTransaccion(numTransaccMod);
				
				ciclo=false;
							
				}
			
				else {
					System.out.println("Factura no encontrada, ingrese nuevamente: ");
					numFactura = vali.valiLong();
				}
			}		
		}
	}
	
	public static void cargaClientes(Mayoristas[] mayo) {
		
		boolean encontrado = false;
		
		for(int i = 0; i<mayo.length; i++) {
			
			encontrado =false;
			
			mayo[i] = new Mayoristas();
		
			System.out.println("Ingrese numero de cuit: ");
			long cuit = vali.valiLong();
			
			for(int j=0; j<i; j++) {
				if(mayo!=null && cuit==mayo[i].getCuit()) {
					System.out.println("El cliente ya existe, ingrese nuevo nombre ");
					encontrado = true;
					i--;
					break;
				}
			}

			if(encontrado==false) {
				mayo[i].setCuit(cuit);
				
				System.out.println("Ingrese razon social: ");
				String razonSocial = leer.nextLine();
				mayo[i].setRazonSocial(razonSocial);
				
				System.out.println("Ingrese 1 si es responsable inscripto o 2 si no");
				boolean condicion = vali.ValidarBool();
				mayo[i].setCondicionIva(condicion);
			}
			
		}
	}
	
	public static void cargaDepositos(Depositos[] depo) {
		
		boolean encontrado = false;
		
		for(int i=0; i<depo.length; i++) {
			encontrado= false;
			
			depo[i] = new Depositos();
			
			System.out.println("Ingrese nombre del domicilio: ");
			String nombre = leer.nextLine();
	
			for(int j=0; j<i; j++) {
				if(depo!=null && nombre.equalsIgnoreCase(depo[j].getNombre())) {
					System.out.println("El deposito ya existe, ingrese nuevo nombre ");
					encontrado = true;
					i--;
					break;
				}
			}
	
			if(encontrado==false) {
				depo[i].setNombre(nombre);
				
				System.out.println("Ingrese domicilio");
				String domicilio = leer.nextLine();
				depo[i].setDomicilio(domicilio);
				
				System.out.println("Ingrese 1 si es propio o 2 si no lo es: ");
				boolean propio = vali.ValidarBool();
				depo[i].setEsPropio(propio);
			}
		}
	}
		
	public static void cargaGolosinas(Golosinas[] golo, Depositos[] depo) {
	
		boolean encontrado = false; 
		
		for(int i=0; i<golo.length; i++) {
				
				encontrado = false; 
			
				System.out.println("Ingrese codigo de golosina: ");
				int codigo = vali.Entero();
				
				for(int j=0; j<i; j++) {
					if(golo!=null && codigo==golo[i].getCodigo()) {
						System.out.println("La golosina ya existe, ingrese nuevo nombre ");
						encontrado = true;
						i--;
						break;
					}
				}
		
				if(encontrado==false) {
					
					System.out.println("Ingrese descripcion: ");
					String descripcion = leer.nextLine();
					
					System.out.println("Ingrese precio de venta: ");
					double precioVenta = vali.Decimal();
					
					String[] sabo = new String[3];
					
					for(int j=0; j<sabo.length; j++) {
						System.out.println("Ingrese del sabor numero: "+(j+1));
						sabo[j] = leer.nextLine();		
					}
					
					System.out.println("Ingrese 1 para golosina por kilo o 2 para empaquetada: ");
					boolean opcion = vali.ValidarBool();
				
					if(opcion==true) {
						golo[i] = new Kilo();
						
						System.out.println("Ingrese 1 si tiene descuento o 2 si no lo tiene");
						boolean descuento = vali.ValidarBool();
						if(descuento==true) {
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
	}
		
	public static void cargaFacturas(Factura[] fact, Golosinas[] golo, Mayoristas[] mayo) {
		
		int contadorFacturaA = 0;
		boolean golosina = true;
		boolean mayorista = true;
		boolean condicionIva = false;
		int cEmisor = 0;
		
		for(int i=0; i<fact.length; i++) {
			
			Calendar fecha = Calendar.getInstance();
			
			Calendar fechaVencimiento = Calendar.getInstance();
			
			if(i==0) {
				System.out.println("Ingrese centro emisor");
				cEmisor = vali.centroEmisor();
			}
			
			System.out.println("Ingrese numero de cuit del mayoristas: ");
			long numMayorista = vali.valiLong();
			
			
			for(int z=0; z<mayo.length; z++) {
				
				while(mayorista) {
					if(numMayorista==mayo[z].getCuit()) {
						condicionIva = mayo[z].getCondicionIva();
						mayorista=false;
					}
					else {
						System.out.println("Mayorista no encontrado, ingrese nuevamente el cuit: ");
						numMayorista = vali.valiLong();
					}
				}
			}
			
			Calendar fechaPago = Calendar.getInstance();
			
			System.out.println("Ingrese medio de pago, 1 credito, 2 debito, 3 transferencia: "); 
			String mPago = vali.medioPago();
			
			System.out.println("Ingrese numero de recibo: ");
			long numeroRecibo = vali.valiLong();
			
			System.out.println("Ingrese numero de transaccion: ");
			long numeroTransaccion = vali.valiLong();
			
			System.out.println("Ingrese 1 si abono o 2 si no");
			boolean pagos = vali.ValidarBool();
			
			if(condicionIva) {
				fact[i] = new FacturaA();
				
				if(FacturaA.getOperadorLogistico()==null) {
					System.out.println("Ingrese nombre del operador logistico: ");
					String nombre = leer.nextLine();
					FacturaA.setOperadorLogistico(nombre);
				}
						
				contadorFacturaA++;
				FacturaA.setCantFacturaA(contadorFacturaA);
				
			}
			else {
				fact[i] = new FacturaB();
			}
			
			fact[i].calculoVencimiento(fechaVencimiento);
					
			for(int j=0; j<fact[i].getDeta().length; j++) {
				
				System.out.println("Ingrese cantidad de golosinas");
				double cant = vali.Decimal();
				fact[i].getDeta()[j].setCantidad(cant);
				
				System.out.println("Ingrese codigo de golosina: ");
				int numeroBusqueda = vali.Entero();
				
				for(int c=0; c<golo.length; c++) {
					while(golosina) {
						
						if(numeroBusqueda==golo[c].getCodigo()) {
							fact[i].getDeta()[j].setGolo(golo[c]);
							golosina=false;
						}
						else {
							System.out.println("Error, golosina inexistente ingrese nuevamente");
							numeroBusqueda = vali.Entero();
						}
					}
					
				}
				golosina=true;
				
			}
			
			for(int c=0; c<mayo.length; c++) {
				if(numMayorista==mayo[c].getCuit()) {
					fact[i].setMayo(mayo[c]);
				}
			}
			
			fact[i].setFechaEmision(fecha);
			fact[i].setFechaVencimiento(fechaVencimiento);
			if(i==0) {
				fact[i].setNumeroFactura(10000000);
			}
			else {
				long numfact = fact[i-1].getNumeroFactura() + 1;
				fact[i].setNumeroFactura(numfact);
			}
			fact[i].setCentroEmisor(cEmisor);
		
			if(pagos) {
				fact[i].getPago().setFechaPago(fechaPago);
				fact[i].getPago().setFormaPago(mPago);
				fact[i].getPago().setNumRecibo(numeroRecibo);
				fact[i].getPago().setNumTransaccion(numeroTransaccion);
			}	
		fact[i].imprimirInfo();
		}
	}
}
