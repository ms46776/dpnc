package com.maliha.dpnc.dpnc;

import com.maliha.dpnc.dpnc.prime.PrimeApplicationRun;
import com.maliha.dpnc.dpnc.randomizer.RandomizerApplicationRun;
import com.maliha.dpnc.dpnc.randomizer.RandomizerApplication;



public class RunTestSetup {
	
	public static void main(String[] args) {
		// run server (in-process)
		RandomizerApplicationRun.main(args);
		
		// run 5 clients (all also in-process)
		PrimeApplicationRun.main(args);
		PrimeApplicationRun.main(args);
		PrimeApplicationRun.main(args);
		PrimeApplicationRun.main(args);
		PrimeApplicationRun.main(args);
	}

}
