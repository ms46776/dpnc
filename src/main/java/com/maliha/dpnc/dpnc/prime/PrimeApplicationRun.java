package com.maliha.dpnc.dpnc.prime;

public class PrimeApplicationRun {
	
	private static final int SERVER_PORT = 15678;

	public static void main(String[] args) {
		System.out.println("Starting Prime client connecting to port " + SERVER_PORT);
		new PrimeApplication(SERVER_PORT).run();
	}

}
