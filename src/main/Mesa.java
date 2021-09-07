package main;

public class Mesa {

	private int numCubiertosT1;

	private int numCubiertosT2;

	private int numPlatos;

	private int mitadNumPlatos;
	
	public Mesa(int numCT1, int numCT2, int numPlatos)
	{
		numCubiertosT1 = numCT1;
		numCubiertosT2 = numCT2;
		this.numPlatos = numPlatos;
		this.mitadNumPlatos = numPlatos/2;
	}

	/**
	 * Método que permite que el Comensal tome un par de cubiertos para comer un plato
	 * @param id - El identificador del comensal
	 */
	public synchronized void tomarCubierto(int id)
	{
		try 
		{
			if(numCubiertosT1 > 0)
			{
				//Hay disponibilidad de cubierto T1 entonces lo toma
				numCubiertosT1--;
				System.out.println("Comensal " + id + " tomó el cubierto T1.");

				if(numCubiertosT2 > 0)
				{
					//Hay disponibilidad de cubierto T2 entonces lo toma
					numCubiertosT2--;
					System.out.println("Comensal " + id + " tomó el cubierto T2.");
				}
				else
				{
					//No hay disponibilidad de cubierto T2 entonces devuelve el T1
					numCubiertosT1++;
					System.out.println("Comensal " + id + " devolvió cubierto T1, no pudo tomar el T2");
					//Espera a que le notifiquen que hay nuevos cubiertos en la mesa
					wait();
					//Vuelve a intentar tomar los cubiertos
					tomarCubierto(id);
				}
			}
			else
			{
				System.out.println("Comensal " + id + " no pudo tomar cubierto T1, esperando");
				//Espera a que le notifiquen que hay nuevos cubiertos en la mesa
				wait();
				//Vuelve a intentar tomar los cubiertos
				tomarCubierto(id);
			}			
		}
		catch(Exception e) {}
	}	
	
	/**
	 * Método que permite que el Lavaplatos deje un par de cubiertos sobre la mesa
	 */
	public synchronized void ponerCubiertos()
	{
		//Aumenta la cantidad de cubiertos T1 y T2
		numCubiertosT1++;
		numCubiertosT2++;
		//Notifica a los Comensales que están esperando sobre la Mesa que hay nueva disponibilidad de cubiertos
		notifyAll();
	}
	
	/**
	 * Método que retorna la cantidad de platos totales que debe consumir cada Comensal
	 * @return numPlatos
	 */
	public int getNumPlatos()
	{
		return numPlatos;
	}
	
	/**
	 * Método que retorna la mitad de la cantidad de platos totales que debe consumir cada Comensal
	 * @return mitadNumPlatos
	 */
	public int getMitadNumPlatos()
	{
		return mitadNumPlatos;
	}
}
