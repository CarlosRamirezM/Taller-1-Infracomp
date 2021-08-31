package main;

public class Comensal extends Thread {

	public static Mesa mesa;

	public  static Fregadero fregadero;

	private int plato;

	public Comensal() {		
		plato = 0;
	}


	public void comer()
	{
		plato++;		
	}

	public void run() {

		while(this.plato < mesa.getNumPlatos())
		{
			mesa.tomarCubierto();
			comer();
			fregadero.meterCubiertoT1();
			fregadero.meterCubiertoT2();
		}
	}
}
