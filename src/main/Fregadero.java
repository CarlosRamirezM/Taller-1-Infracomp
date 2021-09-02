package main;

public class Fregadero {

	private int tamanio;

	private int cantidad;	

	public Fregadero(int tamanio)
	{
		this.tamanio = tamanio;		
		this.cantidad = 0;
	}

	public void meterCubiertos(int id)
	{
		while(cantidad == tamanio)
		{			
			Thread.yield();
		}

		synchronized(this) {
			cantidad++;
		}
	}

	public void tomarCubiertos()
	{
		while(cantidad == 0)
		{			
			Thread.yield();
		}			

		synchronized(this) {
			cantidad--;
		}
	}
}
