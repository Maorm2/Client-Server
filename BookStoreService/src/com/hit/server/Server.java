package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.hit.service.Controller;

public class Server implements Runnable, PropertyChangeListener {

	private ServerSocket server;
	private Executor executor;
	private int threadPoolSize;
	private int port;
	private boolean serverPower;
	private boolean client = true;

	public Server(int port) throws IOException {
		this.port = port;
		this.serverPower = false;
		this.threadPoolSize = 3;
	}

	@Override
	public void run() {
		executor = Executors.newFixedThreadPool(threadPoolSize);

		Socket socket;
		executor.execute(Controller.getInstance());
		System.out.println("Server is listening on port: " + port);

		try {
			server = new ServerSocket(port);

			while (serverPower) {
				try {
					System.out.println("server.accept()...");
					socket = server.accept();
					if (client) {
						System.out.println("New client connected");
					}
					executor.execute(new HandleRequest(socket, client));
				} catch (IOException e) {
					System.out.println("Server is down");
				}
			}
		} catch (IOException e) {

		} finally {
			try {
				if (server != null)
					server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

		String action = (String) event.getNewValue();

		switch (action) {

		case "start":
			if (!serverPower) {
				serverPower = true;
				new Thread(this).start();
				break;
			} else
				System.out.println("Server is on");

		case "stop":
			if (!serverPower) {
				System.out.println("Server is off");
			} else {
				try {
					serverPower = false;
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
