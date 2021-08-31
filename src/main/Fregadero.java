package main;

public class Fregadero {

	private int tamanio;

	private int cantidadT1;

	private int cantidadT2;

	public Fregadero(int tamanio)
	{
		this.tamanio = tamanio;
		cantidadT1 = 0;
		cantidadT2 = 0; 
	}

	public synchronized void meterCubiertoT1()
	{
		try {

			if(cantidadT1 + cantidadT2 == tamanio)
			{
				wait();
			}
			cantidadT1++;
		}
		catch(Exception e)
		{

		}
	}

	public synchronized void meterCubiertoT2()
	{
		try {

			if(cantidadT1 + cantidadT2 == tamanio)
			{
				wait();
			}
			cantidadT2++;
		}
		catch(Exception e)
		{

		}
	}

}
