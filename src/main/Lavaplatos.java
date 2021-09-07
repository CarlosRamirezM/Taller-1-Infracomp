package main;

import java.util.Random;

public class Lavaplatos extends Thread {

	public static Mesa mesa;

	public static Fregadero fregadero;	

	public Lavaplatos() {
	}
	
	/**
	 * Método que se ejecuta con start del Lavaplatos y que se encarga de seguir el protocolo para lavar platos
	 */
	public void run() {

		while(true)
		{		
			
			System.out.println("El lavaplatos va a tomar un par de cubiertos del fragadero.");
			fregadero.tomarCubiertos();
			System.out.println("El lavaplatos tomó un par de cubiertos y va a lavarlos.");
			this.lavarCubiertos();
			System.out.println("El lavaplatos lavó un par de cubiertos, va a dejarlos en la mesa.");
			mesa.ponerCubiertos();
			System.out.println("El lavaplatos dejó un par de cubiertos en la mesa.");
			
			//Revisa si ya ha terminado su tarea para finalizar la ejecución del thread
			if ( fregadero.terminacion() ) {
				System.out.println("El lavaplatos ha terminado de lavar todos los cubiertos y los comensales han comido todos los platos");
				break;
			}			
		}
	}
	
	/**
	 * Método que duerme el Lavaplatos mientras lava un par de cubiertos
	 */
	public void lavarCubiertos()
	{
		Random r = new Random();
		//Genera un tiempo aleatorio entre 1 y 2
		double randomValue = 1 + (1.01) * r.nextDouble();
		System.out.println("El lavaplatos durará " + String. format("%.2f", randomValue) + " segundos lavando.");

		try
		{
			//El thread duerme por el tiempo generado
			sleep((long) randomValue * 1000);
		}
		catch(Exception e) {}
	}
}
