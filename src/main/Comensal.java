package main;

import java.util.Random;

public class Comensal extends Thread {

	public static Mesa mesa;

	public static Fregadero fregadero;

	private int plato;

	public Comensal() {		
		plato = 0;
	}


	public void comer()
	{
		Random r = new Random();
		double randomValue = 1 + (3 - 1) * r.nextDouble();
		
		try
		{
			sleep((long) randomValue*1000);
		}
		catch(Exception e)
		{
			
		}
		
		plato++;		
	}

	public void run() {

		while(this.plato < mesa.getNumPlatos())
		{
			mesa.tomarCubierto();
			comer();
			fregadero.meterCubiertos();
		}
	}
}
