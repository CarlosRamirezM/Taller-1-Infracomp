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

	/**
	 * Método que duerme el Comensal mientras come su plato
	 */
	public void comer()
	{
		Random r = new Random();
		//Genera un tiempo aleatorio entre 3 y 5
		double randomValue = 3 + (2.01) * r.nextDouble();

		System.out.println("Comensal " + this.id + " durará " + String. format("%.2f", randomValue) + " segundos comiendo.");

		try
		{			
			//El thread duerme por el tiempo generado
			sleep((long) randomValue*1000);
		}
		catch(Exception e) {}

		//Aumenta la cantidad de platos comidos por el comensal
		plato++;
		//Disminuye la cantidad de platos restantes entre todos los comensales
		fregadero.platoComido();
		System.out.println("Quedan " + Fregadero.totalPlatos + " platos.");
	}

	/**
	 * Método que se ejecuta con start del Comensal y que se encarga de seguir el protocolo para comer platos
	 */
	public void run() {

		while(this.plato < mesa.getNumPlatos())
		{
			System.out.println("Comensal " + this.id + " tomando cubiertos para el plato " + (this.plato + 1) );
			mesa.tomarCubierto(this.id);
			System.out.println("Comensal " + this.id + " tomó cubiertos, empezando a comer.");
			comer();
			System.out.println("Comensal " + this.id + " comió, intentando dejar cubiertos en el fregadero.");
			fregadero.meterCubiertos();
			System.out.println("Comensal " + this.id + " dejó los cubiertos.");

			//Revisa si ha llegado a la mitad de los platos para esperar mediante una barrera
			if(this.plato == mesa.getMitadNumPlatos())
			{
				try 
				{
					System.out.println("Comensal " + this.id + " llegó a la mitad de los platos, esperando...");

					//Si es el último en llegar imprime un mensaje de que va a romper la barrera
					if(barrera.getNumberWaiting() == ( barrera.getParties() - 1 ))
					{
						System.out.println("El último comensal ("+this.id+") va a llegar a la mitad de los platos.");
					}
					barrera.await();
				} 
				catch (Exception e) {} 
			}
		}
	}
}
