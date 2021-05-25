package com.hit.driver;

import java.awt.EventQueue;

import javax.swing.UnsupportedLookAndFeelException;

import com.hit.controller.MainWindowController;
import com.hit.model.Model;
import com.hit.view.MainWindow;
import com.hit.view.SplashScreen;

public class Driver {

	public static void main(String[] args) throws Exception {
		new SplashScreen();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Model model = new Model("localhost", 34567);
				MainWindow view;

				try {
					view = new MainWindow();
					new MainWindowController(model, view);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

			}
		});

	}

}
