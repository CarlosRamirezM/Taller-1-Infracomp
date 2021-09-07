package main;


public class Fregadero {

	private int tamanio;

	private int cantidad;
	
	public static int totalPlatos;

	public Fregadero(int tamanio)
	{
		this.tamanio = tamanio;		
		this.cantidad = 0;
	}

	/**
	 * Método que permite que un Comensal deje los cubiertos en el fregadero siempre que el buffer tenga espacio
	 */
	public  void meterCubiertos()
	{
		while(cantidad == tamanio)
		{			
			Thread.yield();
		}

		//Bloque sincronizado que permite que la modificación de la variable cantidad sea atómica
		synchronized(this) {
			cantidad++;	
		}		
	}

	/**
	 * Método que permite que el Lavaplatos tome cubiertos para lavarlos
	 */
	public void tomarCubiertos()
	{
		while(cantidad == 0)
		{			
			Thread.yield();
		}			

		//Bloque sincronizado que permite que la modificación de la variable cantidad sea atómica
		synchronized(this) {
			cantidad--;
		}
	}
	
	/**
	 * Método que determina si ya se han comido todos los platos y no hay más cubiertos en el buffer
	 * @return true si ha terminado o false de lo contrario
	 */
	public boolean terminacion() {
		return totalPlatos == 0 && cantidad == 0;
	}
	
	/**
	 * Método que permite disminuir la cantidad de platos comidos de manera atómica
	 */
	public synchronized void platoComido()
	{
		totalPlatos--;
	}
}
