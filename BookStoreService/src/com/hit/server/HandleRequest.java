package com.hit.server;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.dm.Book;
import com.hit.dm.BookStore;
import com.hit.dm.DataModel;
import com.hit.service.Controller;
import com.hit.service.ServiceId;

public class HandleRequest implements Runnable {

	Gson gson = new Gson();
	Gson prettyPrinting = new GsonBuilder().setPrettyPrinting().create();
	private Socket socket;
	private boolean client;
	ServiceId service = new ServiceId();

	public HandleRequest(Socket socket, boolean client) {
		this.socket = socket;
		this.client = client;
	}

	@Override
	public void run() {

		try {
			ActionHandler();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void ActionHandler() throws IOException, IllegalArgumentException {
		Request request = readRequest();
		String action = request.getHeaders().get("action");

		Controller controller = Controller.getInstance();

		switch (action) {
		case "GETDATA":
			getData(controller, request.getContent());
			break;
		case "GET":
			getBook(controller, request.getContent());
			break;
		case "UPDATE":
			saveBook(controller, request.getContent());
			break;
		case "DELETE":
			deleteBook(controller, request.getContent());
			break;
		}
	}

	private Request readRequest() throws IOException {
		String socketText = readFromSocket();
		Request request = gson.fromJson(socketText, Request.class);

		if (client) {
			System.out.println("Client request:" + prettyPrinting.toJson(request));
		}
		return request;
	}

	private String readFromSocket() throws IOException {
		BufferedReader reader;
		String input = "";
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			input = reader.readLine();
		} catch (IOException e) {
			responseSend(new Response.Build().Error().setContent(e.getMessage()).create());
		}
		return input;
	}

	private void responseSend(Response response) {
		try {
			PrintStream outputStream = new PrintStream(socket.getOutputStream());
			String responseToJson = gson.toJson(response);

			if (client) {
				String text = prettyPrinting.toJson(response);
				System.out.println("Response from the server:" + text);
			}
			outputStream.println(responseToJson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void getData(Controller controller, String content) throws IOException {
		Response response = new Response.Build().Ok().setContent(controller.getData()).create();
		responseSend(response);
	}

	private void getBook(Controller controller, String content) throws IllegalArgumentException, IOException {
		if (content != null) {
			String patt = gson.fromJson(content, String.class);
			try {
				Response response = new Response.Build().Ok().setContent(controller.find(patt)).create();
				responseSend(response);
			}

			catch (IllegalArgumentException | IOException e) {
				e.printStackTrace();
				System.out.println("get Book error");
				Response response = new Response.Build().Error().setContent(patt).create();
				responseSend(response);
			}
		}

	}

		private void saveBook(Controller controller, String content) throws IOException {

		try {
			BookStore bookStore = gson.fromJson(content, BookStore.class);
			Book book = bookStore.getBook();
			DataModel<Object> insert = new DataModel<Object>(book, null);
			controller.save(insert);
			Response response = new Response.Build().Ok().setContent(book).create();
			responseSend(response);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			Response response = new Response.Build().Error().setContent(content).create();
			responseSend(response);
		}

	}

		private void deleteBook(Controller controller, String content) {
		UUID deleteId = gson.fromJson(content, UUID.class);
		try {
			controller.delete(deleteId);
			Response response = new Response.Build().Ok().setContent(deleteId).create();
			responseSend(response);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
			Response response = new Response.Build().Error().setContent(deleteId).create();
			responseSend(response);
		}
	}

}
