package main;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Comensal extends Thread {

	private int id; 
	
	public static Mesa mesa;

	public static Fregadero fregadero;

	public static CyclicBarrier barrera;

	private int plato;

	public Comensal(int id)
	{	
		this.id = id;
		plato = 0;
	}


	public void comer()
	{
		Random r = new Random();
		double randomValue = 1 + (2.01) * r.nextDouble();

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

			if(this.plato == mesa.getMitadNumPlatos())
			{
				try 
				{
					barrera.await();
				} 
				catch (Exception e) 
				{	
				} 
			}
		}
	}
}
