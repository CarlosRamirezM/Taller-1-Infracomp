package main;

public class Fregadero {

	private int tamanio;

	private int cantidad;	

	public Fregadero(int tamanio)
	{
		this.tamanio = tamanio;		
	}

	public synchronized void meterCubiertos()
	{
		while(cantidad == tamanio)
		{
			Thread.yield();
		}
		cantidad++;
	}

	public synchronized void tomarCubiertos()
	{
		while(cantidad == 0)
		{
			Thread.yield();
		}
		cantidad--;
	}
}
