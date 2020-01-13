package com.maliha.dpnc.dpnc.randomizer;

public class RandomizerApplicationRun {
	
	private static final int SERVER_PORT = 15678;

	public static void main(String[] args) {
		System.out.println("Starting Randomizer on port " + SERVER_PORT);
		new RandomizerApplication(SERVER_PORT).run();
	}

}
