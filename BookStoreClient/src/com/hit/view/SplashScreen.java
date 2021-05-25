package com.hit.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class SplashScreen extends JFrame {
	JFrame frame;
	JProgressBar progressBar = new JProgressBar();
	JLabel message = new JLabel();
	Color color = new Color(240, 240, 240);
	Color colorPb = new Color(220, 220, 220);

	public SplashScreen() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		createGUI();
		addIcon();
		addImage();
		addText();
		addProgressBar();
		addMessage();
		runningPBar();
	}

	public void createGUI() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setSize(600, 280);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(color);
		frame.setVisible(true);
	}

	public void addIcon() {
		java.awt.Image icon = new ImageIcon(this.getClass().getResource("/bookIcon.png")).getImage();
		frame.setIconImage(icon);
	}

	public void addImage() {
		JLabel lblNewLabel = new JLabel("");
		frame.getContentPane().add(lblNewLabel);
		java.awt.Image image = new ImageIcon(this.getClass().getResource("/bookLogo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(image));
		lblNewLabel.setBounds(226, 10, 125, 120);
	}

	public void addText() {
	}

	public void addMessage() {
		message.setBounds(246, 138, 190, 40);
		message.setForeground(Color.BLACK);
		message.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(message);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(198, 16, 184, 113);
		frame.getContentPane().add(lblNewLabel_1);

	}

	public void addProgressBar() {
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		progressBar.setBounds(100, 207, 410, 18);
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(colorPb);
		progressBar.setValue(0);
		frame.getContentPane().add(progressBar);
	}

	public void runningPBar() {
		int i = 0;

		while (i <= 100) {
			try {
				Thread.sleep(30);
				progressBar.setValue(i);
				message.setText("Loading... ");
				i++;
				if (i == 100)
					frame.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}