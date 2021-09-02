package main;

public class Fregadero {

	private int tamanio;

	private int cantidad;	

	public Fregadero(int tamanio)
	{
		this.tamanio = tamanio;		
	}

	public synchronized void meterCubiertos(int id)
	{
		while(cantidad == tamanio)
		{
			System.out.println("Comensal " + id + " no pudo dejar los cubiertos en el fregadero, esperando.");
			Thread.yield();
		}
		cantidad++;
	}

	public synchronized void tomarCubiertos()
	{
		while(cantidad == 0)
		{
			System.out.println("El lavaplatos no pudo tomar los cubiertos del fregadero, esperando.");
			Thread.yield();
		}
		cantidad--;
	}
}
