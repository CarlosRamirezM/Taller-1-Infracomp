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

	public synchronized void tomarCubierto(int id)
	{
		try 
		{
			if(numCubiertosT1 > 0)
			{
				numCubiertosT1--;
				System.out.println("Comensal " + id + " tomó el cubierto T1.");

				if(numCubiertosT2 > 0)
				{
					numCubiertosT2--;
					System.out.println("Comensal " + id + " tomó el cubierto T2.");
				}
				else
				{
					numCubiertosT1++;
					System.out.println("Comensal " + id + " devolvió cubierto T1, no pudo tomar el T2");
					wait();
					tomarCubierto(id);
				}
			}
			else
			{
				System.out.println("Comensal " + id + " no pudo tomar cubierto T1, esperando");
				wait();
				tomarCubierto(id);
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
		System.out.println("El lavaplatos dejó los cubiertos en la mesa.");
		notifyAll();
	}
	
	
	public int getNumPlatos()
	{
		return numPlatos;
	}
	
	public int getMitadNumPlatos()
	{
		return mitadNumPlatos;
	}
}
