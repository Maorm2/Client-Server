package util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI implements Runnable {

	private Scanner in;
	private PrintWriter out;
	private String value = "";
	private final PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public CLI(InputStream in, PrintStream out) {
		this.in = new Scanner(in);
		this.out = new PrintWriter(out);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.changes.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		this.changes.removePropertyChangeListener(pcl);
	}

	@Override
	public void run() {
		String clientInput = null;
		System.out.println("Write 'start' to start the server");
		while (true) {
			clientInput = in.nextLine().toLowerCase();
			switch (clientInput) {
			case "start":
				System.out.println("Server is starting");
				out.flush();
				setValue(clientInput);
				break;
			case "stop":
				System.out.println("Server is stoping");
				out.flush();
				setValue(clientInput);
				break;
			default:
				System.out.println(("Unknown command ! Try again" + clientInput));
				out.flush();
				break;
			}
		}
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String newValue) {
		this.value = newValue;
		this.changes.firePropertyChange("value", null, newValue);
	}

}
