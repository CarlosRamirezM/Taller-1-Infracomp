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

	public synchronized void tomarCubierto()
	{
		try 
		{
			if(numCubiertosT1 > 0)
			{
				numCubiertosT1--;

				if(numCubiertosT2 > 0)
				{
					numCubiertosT2--;
				}
				else
				{
					numCubiertosT1++;
					wait();
					tomarCubierto();
				}
			}
			else
			{
				wait();
				tomarCubierto();
			}			
		}
		catch(Exception e)
		{
			
		}
	}	
	
	public synchronized void ponerCubiertos()
	{
		numCubiertosT1++;
		numCubiertosT2++;
	}
	
	
	public int getNumPlatos()
	{
		return numPlatos;
	}
}
