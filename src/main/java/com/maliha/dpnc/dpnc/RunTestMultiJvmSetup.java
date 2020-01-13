package com.maliha.dpnc.dpnc;

import com.maliha.dpnc.dpnc.prime.PrimeApplicationRun;
import com.maliha.dpnc.dpnc.randomizer.RandomizerApplicationRun;

public class RunTestMultiJvmSetup {
	
public static void main(String[] args) {
		
		// run server (spawn JVM)
		runJava(RandomizerApplicationRun.class);

		// run 5 clients (spawns 5 JVMs)
		runJava(PrimeApplicationRun.class);
		runJava(PrimeApplicationRun.class);
		runJava(PrimeApplicationRun.class);
		runJava(PrimeApplicationRun.class);
		runJava(PrimeApplicationRun.class);
	}
	
	private static void runJava(Class<?> classToRun) {
		new Thread(() -> {
			runJava_(classToRun);
		}).start();
	}

	private static void runJava_(Class<?> classToRun) {
		String classpath = System.getProperty("java.class.path");
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
				"java",
				"-cp", classpath,
				classToRun.getName())
			.inheritIO();
			Process process = processBuilder.start();
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	

}
