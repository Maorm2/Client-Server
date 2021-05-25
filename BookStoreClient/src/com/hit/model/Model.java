package com.hit.model;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.swing.event.SwingPropertyChangeSupport;

import com.google.gson.Gson;
import com.hit.controller.MainWindowController;

public class Model {

	private String address;
	private SwingPropertyChangeSupport propChangeFire;
	private int port;

	Gson gson = new Gson();
	MainWindowController controller = MainWindowController.getInstance();

	public Model(String address, int port) {
		this.address = address;
		this.port = port;
		propChangeFire = new SwingPropertyChangeSupport(this);
	}

	public void addListener(PropertyChangeListener prop) {
		propChangeFire.addPropertyChangeListener(prop);
	}

	public Socket connect() throws UnknownHostException, IOException {
		return new Socket(address, port);
	}

	public void writeToServer(Socket socket, Request request) throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		String jsonRequest = gson.toJson(request);
		out.println(jsonRequest);
	}

	public Response readServerResponse(Socket socket) throws Throwable {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String s = "";
		s = in.readLine();
		if (s == "") {
			throw new IOException("Error of reading server response");
		}
		Response response = gson.fromJson(s, Response.class);
		return response;
	}

	public void getDataFromJson() throws Throwable {
		Socket socket = connect();
		Request request = new Request.Builder().setAction("GETDATA").build();
		try {
			writeToServer(socket, request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Response response = readServerResponse(socket);
		controller.serverRespond(response);
	}

	public void get(String pattern) throws Throwable {
		Socket socket = connect();
		String content = gson.toJson(pattern);
		Request request = new Request.Builder().setAction("GET").setContent(content).build();
		writeToServer(socket, request);
		Response response = readServerResponse(socket);
		controller.serverRespond(response);
	}

	public void save(DataModel<Object> book) throws Throwable {
		Socket socket = connect();
		String content = gson.toJson(book);
		Request request = new Request.Builder().setAction("UPDATE").setContent(content).build();
		writeToServer(socket, request);
	}

	public void delete(UUID id) throws Throwable {
		Socket socket = connect();
		String content = gson.toJson(id);
		Request request = new Request.Builder().setAction("DELETE").setContent(content).build();
		writeToServer(socket, request);
		Response response = readServerResponse(socket);
		if (response.getHeaders().get("status").equals("Error")) {
			controller.serverRespond(response);
		}
	}
}
