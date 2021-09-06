package main;


public class Fregadero {

	private int tamanio;

	private int cantidad;
	
	private boolean terminado;
	
	public static int totalPlatos;

	public Fregadero(int tamanio)
	{
		this.tamanio = tamanio;		
		this.cantidad = 0;
		terminado = false;
	}

	public  void meterCubiertos(int id)
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
	
	public void notificarTerminacion() {
		terminado = true;		
	}
	
	public boolean terminacion() {
		return ( terminado && cantidad == 0) ? true: false;
	}
	
	public synchronized void platoComido()
	{
		totalPlatos--;
	}
}
