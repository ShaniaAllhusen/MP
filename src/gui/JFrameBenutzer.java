package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import tabellenklassen.Benutzer;
import Dao.BenutzerDao;
import Dao.NoBenutzerFound;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameBenutzer extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSuchen;
	private JButton buttonSuchen;
	private JButton buttonNewButton;
	private JButton buttonHinzufuegen;
	private JButton buttonLoeschen;
	private JLabel labelId;
	private JTextField textFieldID;
	private JLabel labelBenutzername;
	private JTextField textFieldUsername;
	private JLabel labelPasswort;
	private JTextField textFieldPasswort;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;

	private BenutzerDao benutzerDao;
	private Benutzer benutzer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameBenutzer frame = new JFrameBenutzer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public JFrameBenutzer() throws ClassNotFoundException {
		benutzerDao = new BenutzerDao();
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			textFieldSuchen = new JTextField();
			textFieldSuchen.setBounds(10, 11, 170, 20);
			contentPane.add(textFieldSuchen);
			textFieldSuchen.setColumns(10);
		}
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSuchenActionPerformed(e);
				}
			});
			buttonSuchen.setBounds(190, 10, 89, 23);
			contentPane.add(buttonSuchen);
		}
		{
			buttonNewButton = new JButton("\u00C4nderung speichern");
			buttonNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonNewButtonActionPerformed(e);
				}
			});
			buttonNewButton.setBounds(289, 110, 135, 23);
			contentPane.add(buttonNewButton);
		}
		{
			buttonHinzufuegen = new JButton("Benutzer hinzuf\u00FCgen");
			buttonHinzufuegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonHinzufuegenActionPerformed(e);
				}
			});
			buttonHinzufuegen.setBounds(289, 144, 135, 23);
			contentPane.add(buttonHinzufuegen);
		}
		{
			buttonLoeschen = new JButton("Benutzer l\u00F6schen");
			buttonLoeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonLoeschenActionPerformed(e);
				}
			});
			buttonLoeschen.setBounds(289, 178, 135, 23);
			contentPane.add(buttonLoeschen);
		}
		{
			labelId = new JLabel("ID");
			labelId.setBounds(10, 82, 46, 14);
			contentPane.add(labelId);
		}
		{
			textFieldID = new JTextField();
			textFieldID.setBounds(94, 79, 89, 20);
			contentPane.add(textFieldID);
			textFieldID.setColumns(10);
		}
		{
			labelBenutzername = new JLabel("Benutzername");
			labelBenutzername.setBounds(10, 117, 76, 14);
			contentPane.add(labelBenutzername);
		}
		{
			textFieldUsername = new JTextField();
			textFieldUsername.setBounds(94, 114, 158, 20);
			contentPane.add(textFieldUsername);
			textFieldUsername.setColumns(10);
		}
		{
			labelPasswort = new JLabel("Passwort");
			labelPasswort.setBounds(10, 153, 46, 14);
			contentPane.add(labelPasswort);
		}
		{
			textFieldPasswort = new JTextField();
			textFieldPasswort.setBounds(94, 150, 158, 20);
			contentPane.add(textFieldPasswort);
			textFieldPasswort.setColumns(10);
		}
		{
			button = new JButton("|<");
			button.setBounds(10, 178, 53, 23);
			contentPane.add(button);
		}
		{
			button_1 = new JButton("<<");
			button_1.setBounds(73, 178, 53, 23);
			contentPane.add(button_1);
		}
		{
			button_2 = new JButton(">>");
			button_2.setBounds(136, 178, 53, 23);
			contentPane.add(button_2);
		}
		{
			button_3 = new JButton(">|");
			button_3.setBounds(199, 178, 53, 23);
			contentPane.add(button_3);
		}
	}
	protected void buttonSuchenActionPerformed(ActionEvent e) {
		String eingabe = textFieldSuchen.getText();
		Benutzer benutzerAktiv;
		boolean prüfen;
		int id;
		try {
			prüfen = benutzerDao.eingabePruefen(eingabe);
			benutzerAktiv = new Benutzer();
			if(prüfen==true) {
				id = Integer.parseInt(eingabe);
				benutzerAktiv = benutzerDao.select(id);
			}
			else {
				benutzerAktiv = benutzerDao.select(eingabe);
			}
			showBenutzer(benutzerAktiv);
		} catch (NoBenutzerFound e1) {
			showErrorPane(e1);
		}

	}



	private void showBenutzer(Benutzer benutzer) {
		textFieldSuchen.setText(Integer.toString(benutzer.getId()));
		textFieldUsername.setText(benutzer.getUsername());
		textFieldPasswort.setText(benutzer.getPasswort());

	}

	private void showErrorPane(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}
	protected void buttonHinzufuegenActionPerformed(ActionEvent e) {
		String username = textFieldUsername.getText();
		String passwort = textFieldPasswort.getText();
		Benutzer benutzer = new Benutzer();
		benutzer.setUsername(username);
		benutzer.setPasswort(passwort);

		try {
			benutzerDao.insert(benutzer);
			textFieldID.setText(Integer.toString(benutzer.getId()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonNewButtonActionPerformed(ActionEvent e) {
		int id = Integer.parseInt(textFieldID.getText());
		String username = textFieldUsername.getText();
		String passwort = textFieldPasswort.getText();
		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);
		benutzer.setUsername(username);
		benutzer.setPasswort(passwort);
		try {
			benutzerDao.update(benutzer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonLoeschenActionPerformed(ActionEvent e) {
		benutzer.setUsername((textFieldUsername.getText()));
		try {
			benutzerDao.delete(benutzer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
