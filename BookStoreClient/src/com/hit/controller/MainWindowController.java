package com.hit.controller;

import java.awt.Font;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hit.model.Book;
import com.hit.model.DataModel;
import com.hit.model.Model;
import com.hit.model.Response;
import com.hit.view.MainWindow;

public class MainWindowController {

	private static Model model;
	private static MainWindow view;
	private static MainWindowController Instance = null;

	public static MainWindowController getInstance() {
		return Instance == null ? new MainWindowController() : Instance;
	}

	public MainWindowController(Model model, MainWindow view) {
		MainWindowController.model = model;
		MainWindowController.view = view;
	}

	private MainWindowController() {
		initiallize();
	}

	static void initiallize() {
		new MainWindowController(model, view);
	}

	public void deleteBook(String id) throws Throwable {
		UUID idClient = UUID.fromString(id);
		try {
			model.delete(idClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getBook(String pattern) throws Throwable {
		model.get(pattern);
	}

	public void add(String title, String author, String publisher, int year, int numOfPages) throws Throwable {
		Book book = new Book(title, author, publisher, year, numOfPages);
		DataModel<Object> b = new DataModel<Object>(book);
		model.save(b);
	}

	public void getData() throws Throwable {
		try {
			model.getDataFromJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Handle with error response
	public void serverRespond(Response response) { 
		String n = null;
		if (response != null && (response.getHeaders().get("status").equals("OK"))) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String result = response.getContent();
			JsonElement element = JsonParser.parseString(result);
			result = gson.toJson(element);
			
			JOptionPane.showMessageDialog(null, result, "Content", JOptionPane.INFORMATION_MESSAGE);

		} else {
			JLabel label = new JLabel("Error Occourd");
			label.setFont(new Font("Arial", Font.BOLD, 14));
			JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
