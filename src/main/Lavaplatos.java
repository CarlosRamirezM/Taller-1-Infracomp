package main;

import java.util.Random;

public class Lavaplatos extends Thread {

	public static Mesa mesa;

	public static Fregadero fregadero;	

	public Lavaplatos() {
	}
	
	public void run() {

		while(true)
		{		
			
			System.out.println("El lavaplatos va a tomar un par de cubiertos del fragadero.");
			fregadero.tomarCubiertos();
			System.out.println("El lavaplatos tom� un par de cubiertos y va a lavarlos.");
			this.lavarCubiertos();
			System.out.println("El lavaplatos lav� un par de cubiertos, va a dejarlos en la mesa.");
			mesa.ponerCubiertos();
			System.out.println("El lavaplatos dej� un par de cubiertos en la mesa.");
			
			if ( fregadero.terminacion() ) {
				System.out.println("El lavaplatos ha terminado de lavar todos los cubiertos y los comensales han comido todos los platos");
				break;
			}			
		}
	}
	
	public void lavarCubiertos()
	{
		Random r = new Random();

		try
		{
			if(r.nextBoolean())
			{
				sleep(1000);
			}
			else
			{
				sleep(2000);
			}
		}
		catch(Exception e)
		{

		}
	}
}
