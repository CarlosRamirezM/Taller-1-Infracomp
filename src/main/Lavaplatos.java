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
			fregadero.tomarCubiertos();
			this.lavarCubiertos();	
			mesa.ponerCubiertos();
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
