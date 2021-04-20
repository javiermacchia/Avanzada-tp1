import java.util.*;

public interface ICalculo {

	public double calcularTotal();
	
	public double calcularIva();
	
	public Calendar calculoVencimiento(Calendar emision);
	
	int IVA = 21;
	
	int DIA = 30;
}
