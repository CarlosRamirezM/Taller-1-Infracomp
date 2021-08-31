package main;

public class Lavaplatos extends Thread {

	private static Mesa mesa;

	private static Fregadero fregadero;

	public Lavaplatos(Mesa mesa, Fregadero fregadero) {
		this.mesa = mesa;
		this.fregadero = fregadero;
	}
}
