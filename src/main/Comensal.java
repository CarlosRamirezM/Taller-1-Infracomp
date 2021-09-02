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
		
		System.out.println("Comensal " + this.id + " durar� " + randomValue + " segundos comiendo.");
		
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
			System.out.println("Comensal " + this.id + " tomando cubiertos para el plato " + (this.plato + 1) );
			mesa.tomarCubierto(this.id);
			System.out.println("Comensal " + this.id + " tom� cubiertos, empezando a comer.");
			comer();
			System.out.println("Comensal " + this.id + " comi�, intentando dejar cubiertos en el fregadero.");
			fregadero.meterCubiertos(this.id);
			System.out.println("Comensal " + this.id + " dej� los cubiertos.");

			if(this.plato == mesa.getMitadNumPlatos())
			{
				try 
				{
					System.out.println("Comensal " + this.id + " lleg� a la mitad de los platos, esperando...");
					
					if(barrera.getNumberWaiting() == ( barrera.getParties() - 1 ))
					{
						System.out.println("El �ltimo comensal ("+this.id+") va a llegar a la mitad de los platos.");
					}
					
					barrera.await();
				} 
				catch (Exception e) 
				{	
				} 
			}			
		}
	}
}
