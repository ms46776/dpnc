package com.maliha.dpnc.dpnc.queue;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DistributedProcessQueue<IT,OT> {
	
	
	int MAX_BUFFERED_INPUTS = 100;
	private final BlockingQueue<IT> inputQueue = new LinkedBlockingQueue<IT>(MAX_BUFFERED_INPUTS);
	private final BlockingQueue<OT> outputQueue = new LinkedBlockingQueue<OT>();
	private final ClientProxyProcess<IT, OT> workerProcess;
	private final ServerSocketRunnable serverSocketRunnable;
	
	public DistributedProcessQueue(ClientProxyProcess<IT, OT> workerProcess, int serverPortNumber) {
		this.workerProcess = workerProcess;
		serverSocketRunnable = new ServerSocketRunnable(serverPortNumber);
	}
	
	public void start() {
		System.out.println("Starting DistributedProcessQueue");
		new Thread(serverSocketRunnable).start();
	}
	
	/**
	 * Equivalent of {@link BlockingQueue#put(Object)}
	 */
	public void put(IT e) throws InterruptedException {
		// when MAX_BUFFERED_INPUTS is reached caller will be blocked
		inputQueue.put(e);
	}
	
	/**
	 * Equivalent of {@link BlockingQueue#take()}
	 */
	public OT take() throws InterruptedException {
		return outputQueue.take();
	}
	
	class ServerSocketRunnable implements Runnable {
		private final int serverPortNumber;
		
		public ServerSocketRunnable(int serverPortNumber) {
			this.serverPortNumber = serverPortNumber;
		}

		@Override
		public void run() {
			ServerSocket serverSocket = null;
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(serverPortNumber);
				while (true) {
					socket = serverSocket.accept();
					new Thread(new ClientProxy<IT, OT>(socket, workerProcess, inputQueue, outputQueue)).start();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				System.out.println("Closing DistributedProcessQueue");
				try {
					if (serverSocket != null) serverSocket.close();
				} catch (IOException e) {
					System.out.println("Exception when closing ServerSocket");
				}
			}
		}
	}

}
