package com.hit.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hit.controller.MainWindowController;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JTextField txtDeleteBook;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtPublisher;
	private JTextField txtPublishYear;
	private JTextField txtFindBook;
	private JTextField textPages;
	private MainWindowController controller = MainWindowController.getInstance();

	public MainWindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		this.setTitle("Welcome");
		setSize(609, 763);
		getContentPane().setLayout(null);
		setResizable(false);
		this.setLocationRelativeTo(null);

		JLabel lblDeleteBook = new JLabel("Delete Book By ID");
		lblDeleteBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeleteBook.setBounds(12, 586, 121, 25);
		getContentPane().add(lblDeleteBook);

		txtDeleteBook = new JTextField();
		txtDeleteBook.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDeleteBook.setBounds(116, 616, 320, 25);
		getContentPane().add(txtDeleteBook);
		txtDeleteBook.setColumns(10);

		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBookId.setBounds(12, 616, 82, 16);
		getContentPane().add(lblBookId);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(451, 616, 140, 25);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtDeleteBook.getText() != null && !txtDeleteBook.getText().equals("")) {
					JLabel label = new JLabel("Are you sure you want to delete the book?");
					label.setFont(new Font("Arial", Font.BOLD, 14));
					int dialogResult = JOptionPane.showConfirmDialog(null, label);
					if (dialogResult == JOptionPane.YES_OPTION) {
						try {
							controller.deleteBook(txtDeleteBook.getText());
						} catch (Throwable e) {
							e.printStackTrace();
						}
						txtDeleteBook.setText("");

					}

				} else {
					JLabel label = new JLabel("Delete Book field is empty");
					label.setFont(new Font("Arial", Font.BOLD, 14));
					JOptionPane.showMessageDialog(null, label, "Warning", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		getContentPane().add(btnDelete);

		JLabel lblBookStore = new JLabel("Book Store ");
		lblBookStore.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblBookStore.setBounds(211, 39, 160, 48);

		getContentPane().add(lblBookStore);

		JLabel lblAddBook = new JLabel("Add New Book");
		lblAddBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddBook.setBounds(12, 299, 106, 16);
		getContentPane().add(lblAddBook);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 287, 430, 2);
		getContentPane().add(separator);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(12, 335, 71, 16);
		getContentPane().add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTitle.setBounds(116, 335, 320, 25);
		getContentPane().add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAuthor.setBounds(12, 365, 56, 16);
		getContentPane().add(lblAuthor);

		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(116, 365, 320, 25);
		getContentPane().add(txtAuthor);

		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPublisher.setBounds(12, 395, 106, 16);
		getContentPane().add(lblPublisher);

		txtPublisher = new JTextField();
		txtPublisher.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtPublisher.setColumns(10);
		txtPublisher.setBounds(116, 395, 320, 25);
		getContentPane().add(txtPublisher);

		JLabel lblPublishYear = new JLabel("Publish Year");
		lblPublishYear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPublishYear.setBounds(12, 425, 106, 16);
		getContentPane().add(lblPublishYear);

		txtPublishYear = new JTextField();
		txtPublishYear.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtPublishYear.setColumns(10);
		txtPublishYear.setBounds(116, 425, 320, 25);
		getContentPane().add(txtPublishYear);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(451, 455, 140, 25);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int success = 1;
				if (!txtTitle.getText().equals("") && !txtAuthor.getText().equals("")
						&& !txtPublisher.getText().equals("") && !txtPublishYear.getText().equals("")) {
					try {
						controller.add(txtTitle.getText(), txtAuthor.getText(), txtPublisher.getText(),
								Integer.parseInt(txtPublishYear.getText()), Integer.parseInt(textPages.getText()));
					} catch (Throwable e) {
						success = 0;
						JLabel label = new JLabel("illigal character");
						label.setFont(new Font("Arial", Font.BOLD, 14));
						JOptionPane.showMessageDialog(null, label, "Warning", JOptionPane.WARNING_MESSAGE);
						e.printStackTrace();
					}

					txtTitle.setText("");
					txtAuthor.setText("");
					txtPublisher.setText("");
					txtPublishYear.setText("");
					textPages.setText("");
					if (success == 1)
						JOptionPane.showMessageDialog(null, "Book was added");
				} else {
					JLabel label = new JLabel("One of the fields is empty");
					label.setFont(new Font("Arial", Font.BOLD, 14));
					JOptionPane.showMessageDialog(null, label, "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		getContentPane().add(btnAdd);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 495, 430, 2);
		getContentPane().add(separator_1);

		JLabel lblFindBook = new JLabel("Find Book By ID");
		lblFindBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFindBook.setBounds(12, 513, 165, 16);
		getContentPane().add(lblFindBook);

		txtFindBook = new JTextField();
		txtFindBook.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtFindBook.setColumns(10);
		txtFindBook.setBounds(116, 545, 320, 25);
		getContentPane().add(txtFindBook);

		JButton btnFind = new JButton("Find");
		btnFind.setBounds(451, 545, 140, 25);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtFindBook.getText().equals("") && txtFindBook.getText() != null) {
					try {
						controller.getBook(txtFindBook.getText());
					} catch (Throwable e) {

						e.printStackTrace();
					}
					txtFindBook.setText("");
				} else {
					JLabel label = new JLabel("Find Book field is empty");
					label.setFont(new Font("Arial", Font.BOLD, 14));
					JOptionPane.showMessageDialog(null, label, "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		getContentPane().add(btnFind);

		JLabel lblEnterBookId = new JLabel("Book ID");
		lblEnterBookId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterBookId.setBounds(12, 545, 82, 16);
		getContentPane().add(lblEnterBookId);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(12, 579, 430, 2);
		getContentPane().add(separator_1_1);

		JLabel lblGetStore = new JLabel("Get All Store Books");
		lblGetStore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGetStore.setBounds(15, 255, 207, 16);
		getContentPane().add(lblGetStore);

		JButton btnGetStore = new JButton("Get");
		btnGetStore.setBounds(451, 255, 140, 25);
		btnGetStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.getData();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(btnGetStore);

		JLabel lblPages = new JLabel("Pages");
		lblPages.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPages.setBounds(12, 455, 69, 24);
		getContentPane().add(lblPages);

		textPages = new JTextField();
		textPages.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPages.setBounds(116, 455, 320, 25);
		getContentPane().add(textPages);
		textPages.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(221, 93, 148, 142);
		getContentPane().add(lblNewLabel);
		java.awt.Image image = new ImageIcon(this.getClass().getResource("/book.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(image));

		JLabel lblCopyright = new JLabel("All Rights Reserved 2020");
		lblCopyright.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblCopyright.setBounds(163, 681, 215, 36);
		getContentPane().add(lblCopyright);
		java.awt.Image copyrightIcon = new ImageIcon(this.getClass().getResource("/copyrightIcon.png")).getImage();
		lblCopyright.setIcon(new ImageIcon(copyrightIcon));
		setVisible(true);

		java.awt.Image icon = new ImageIcon(this.getClass().getResource("/bookIcon.png")).getImage();
		setIconImage(icon);

	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		new MainWindow();
	}
}
