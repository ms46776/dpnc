package com.maliha.dpnc.dpnc.queue;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface ClientProxyProcess<IT,OT> {
	
	/**
	 * Implementor should encode inputs and place them to output stream, then
	 * wait for result to appear on the input stream. When appears, decode and
	 * return in required type.
	 * 
	 * @param out
	 * @param in
	 * @throws InterruptedException
	 */
	OT process(
			DataOutputStream out,
			DataInputStream in,
			IT input);
	
	
	

}
