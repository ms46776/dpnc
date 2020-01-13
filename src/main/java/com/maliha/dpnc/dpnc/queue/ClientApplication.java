package com.maliha.dpnc.dpnc.queue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class ClientApplication {
	
	private final ClientSocketRunnable clientSocketRunnable;

	public ClientApplication(int serverPortNumber) {
		clientSocketRunnable = new ClientSocketRunnable(serverPortNumber);
	}

	/**
	 * Implementor should decode inputs from input stream, process inputs and
	 * put encoded outputs to output stream.
	 * 
	 * @param in
	 * @param out
	 * @throws InterruptedException
	 */
	public abstract void process(DataInputStream in, DataOutputStream out) throws InterruptedException;

	public void run() {
		new Thread(clientSocketRunnable).start();
	}

	class ClientSocketRunnable implements Runnable {
		private final Socket socket;
		private final DataInputStream in;
		private final DataOutputStream out;

		ClientSocketRunnable(int serverPortNumber) {
			String hostName = "localhost";
			try {
				this.socket = new Socket(hostName, serverPortNumber);
				this.in = new DataInputStream(socket.getInputStream());
				this.out = new DataOutputStream(socket.getOutputStream());
			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					try {
						process(in, out);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
				}
			} finally {
				close();
			}
		}

		private void close() {
			try {
				socket.close();
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
