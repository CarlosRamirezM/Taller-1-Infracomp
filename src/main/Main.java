package main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CyclicBarrier;

public class Main {	
	
	public static void main(String[] args)
	{
		try(InputStream input = new FileInputStream("../resources/config.properties"))
		{
			 Properties prop = new Properties();
			 
			 prop.load(input);
			 
			 int numCubiertosT1 = Integer.parseInt(prop.getProperty("concurrencia.numCubiertosT1"));
			 int numCubiertosT2 = Integer.parseInt(prop.getProperty("concurrencia.numCubiertosT2"));
			 int numPlatos = Integer.parseInt(prop.getProperty("concurrencia.numPlatos"));
			 int tamFregadero = Integer.parseInt(prop.getProperty("concurrencia.tamFregadero"));
			 int numComensales = Integer.parseInt(prop.getProperty("concurrencia.numComensales"));
			 
			 Mesa mesa = new Mesa( numCubiertosT1, numCubiertosT2, numPlatos);
			 Fregadero fregadero = new Fregadero(tamFregadero);
			 CyclicBarrier barrera = new CyclicBarrier(numComensales);
			 
			 Comensal.mesa = mesa;
			 Lavaplatos.mesa = mesa;
			 
			 Comensal.fregadero = fregadero;
			 Lavaplatos.fregadero = fregadero;
			 
			 Comensal.barrera = barrera;
			 
			 Lavaplatos lavaplatos = new Lavaplatos();
			 lavaplatos.start();
			 
			 for(int i = 0; i < numComensales; i++)
			 {
				 Comensal comensal = new Comensal(i);
				 comensal.start();
			 }			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
