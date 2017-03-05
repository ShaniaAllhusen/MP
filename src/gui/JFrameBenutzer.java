package gui;

//Imports
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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSuchen;
	private JButton buttonSuchen;
	private JButton buttonAendern;
	private JButton buttonHinzufuegen;
	private JButton buttonLoeschen;
	private JLabel labelId;
	private JTextField textFieldID;
	private JLabel labelBenutzername;
	private JTextField textFieldUsername;
	private JLabel labelPasswort;
	private JTextField textFieldPasswort;
	private JButton buttonFirst;
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JButton buttonLast;

	private BenutzerDao benutzerDao;

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

	//Konstruktor
	public JFrameBenutzer() throws ClassNotFoundException {
		benutzerDao = new BenutzerDao();
		initGUI();
	}
	private void initGUI() {
		setTitle("Benutzer verwalten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			textFieldSuchen = new JTextField();
			textFieldSuchen.setToolTipText("Geben Sie hier die Id oder den Benutzernamen des Benutzerprofils an, dass Sie suchen wollen");
			textFieldSuchen.setBounds(10, 11, 170, 20);
			contentPane.add(textFieldSuchen);
			textFieldSuchen.setColumns(10);
		}
		{

			buttonSuchen = new JButton("Suchen");
			buttonSuchen.setMnemonic('B');
			buttonSuchen.setToolTipText("Hier k\u00F6nnen Sie ein Benutzerprofil nach der Id oder nem Benutzernamen suchen (Alt + B)");
			buttonSuchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSuchenActionPerformed(e);
				}
			});
			buttonSuchen.setBounds(190, 10, 89, 23);
			contentPane.add(buttonSuchen);
		}
		{
			buttonAendern = new JButton("\u00C4nderung speichern");
			buttonAendern.setMnemonic('S');
			buttonAendern.setToolTipText("\u00C4nderungen am Benutzerprofil speichern (Alt + S)");
			buttonAendern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonAendernActionPerformed(e);
				}
			});
			buttonAendern.setBounds(289, 110, 135, 23);
			contentPane.add(buttonAendern);
		}
		{
			buttonHinzufuegen = new JButton("Benutzer hinzuf\u00FCgen");
			buttonHinzufuegen.setMnemonic('H');
			buttonHinzufuegen.setToolTipText("Neues Benutzerprofil anlegen (Alt + H)");
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
			buttonLoeschen.setMnemonic('L');
			buttonLoeschen.setToolTipText("Das ausgew\u00E4hlte Benutzerprofil l\u00F6schen (Alt + L)");
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
			textFieldID.setToolTipText("Das ist die Id des ausgew\u00E4hlten Benutzerprofils");
			textFieldID.setEditable(false);
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
			textFieldUsername.setToolTipText("Das ist der Benutzername des ausgew\u00E4hlten Benutzerprofils");
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
			textFieldPasswort.setToolTipText("Das ist das Passwort des ausgew\u00E4hlten Benutzerprofils");
			textFieldPasswort.setBounds(94, 150, 158, 20);
			contentPane.add(textFieldPasswort);
			textFieldPasswort.setColumns(10);
		}
		{
			buttonFirst = new JButton("|<");
			buttonFirst.setToolTipText("Erstes Benutzerprofil anzeigen ");
			buttonFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonActionPerformed(e);
				}
			});
			buttonFirst.setBounds(10, 178, 53, 23);
			contentPane.add(buttonFirst);
		}
		{
			buttonPrevious = new JButton("<<");
			buttonPrevious.setToolTipText("Vorherieges Benutzerprofil anzeigen");
			buttonPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_1ActionPerformed(e);
				}
			});
			buttonPrevious.setBounds(73, 178, 53, 23);
			contentPane.add(buttonPrevious);
		}
		{
			buttonNext = new JButton(">>");
			buttonNext.setToolTipText("N\u00E4chstes Benutzerprofil anzeigen");
			buttonNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_2ActionPerformed(e);
				}
			});
			buttonNext.setBounds(136, 178, 53, 23);
			contentPane.add(buttonNext);
		}
		{
			buttonLast = new JButton(">|");
			buttonLast.setToolTipText("Letztes Benutzerprofil anzeigen");
			buttonLast.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_3ActionPerformed(e);
				}
			});
			buttonLast.setBounds(199, 178, 53, 23);
			contentPane.add(buttonLast);
		}
	}
	//Wenn Button Suchen gedr�ckt -> Benutzer wird gesucht
	protected void buttonSuchenActionPerformed(ActionEvent e) {
		String eingabe = textFieldSuchen.getText();
		Benutzer benutzerAktiv;
		boolean pr�fen;
		int id;
		try {
			pr�fen = benutzerDao.eingabePruefen(eingabe);
			benutzerAktiv = new Benutzer();
			if(pr�fen==true) {
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
		textFieldID.setText(Integer.toString(benutzer.getId()));
		textFieldUsername.setText(benutzer.getUsername());
		textFieldPasswort.setText(benutzer.getPasswort());
	}

	private void showErrorPane(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}
	private void showInfoPane(String text, int id) {
		JOptionPane.showMessageDialog(this, text +" mit der ID " +id +" wurde hinzugef�gt", "Informationsmeldung", JOptionPane.INFORMATION_MESSAGE);
	}

	//Wenn Button Hinzuf�gen gedr�ckt -> Benutzer wird hinzugef�gt
	protected void buttonHinzufuegenActionPerformed(ActionEvent e) {
		boolean eingabeBenutzername = felderPruefen(textFieldUsername);
		boolean eingabePasswort = felderPruefen(textFieldPasswort);

		if(eingabeBenutzername == true && eingabePasswort == true) {
			String username = textFieldUsername.getText();
			String passwort = textFieldPasswort.getText();
			Benutzer benutzer = new Benutzer();
			benutzer.setUsername(username);
			benutzer.setPasswort(passwort);
			try {
				benutzerDao.insert(benutzer);
				textFieldID.setText(Integer.toString(benutzer.getId()));
				showInfoPane("Benutzer", benutzer.getId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Bitte geben Sie einen Benutzername und Passwort ein.",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	//Wenn Button �ndern gedr�ckt -> �nderung wird gespeichert
	protected void buttonAendernActionPerformed(ActionEvent e) { 
		boolean pruefe = felderPruefen(textFieldID);
		if(pruefe == true) {
			boolean eingabeBenutzername = felderPruefen(textFieldUsername);
			boolean eingabePasswort = felderPruefen(textFieldPasswort);

			if(eingabeBenutzername == true && eingabePasswort == true) {
				Benutzer benutzer = create();
				try {
					benutzerDao.update(benutzer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Bitte geben Sie einen Benutzername und Passwort ein.",
						"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Bitte w�hlen Sie ein Benutzerprofil aus.",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	//Wenn Button L�schen gedr�ckt -> Benutzer wird gel�scht
	protected void buttonLoeschenActionPerformed(ActionEvent e) {
		boolean pruefe = felderPruefen(textFieldID);
		if(pruefe == true) {
			Benutzer benutzer = create();
			try {
				benutzerDao.delete(benutzer);
				felderLeeren();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Bitte w�hlen Sie ein Benutzerprofil aus.",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Methode Create() = Neuer Benutzer wird angelegt
	private Benutzer create(){
		int id = Integer.parseInt(textFieldID.getText());
		String username = textFieldUsername.getText();
		String passwort = textFieldPasswort.getText();
		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);
		benutzer.setUsername(username);
		benutzer.setPasswort(passwort);
		return benutzer;
	}

	// Methode felderLeeren() = Eintragungen in den Textfeldern werden raus genommen
	private void felderLeeren() {
		textFieldID.setText("");
		textFieldUsername.setText("");
		textFieldPasswort.setText("");
	}

	// Ersten Benutzer anzeigen
	protected void buttonActionPerformed(ActionEvent e) {
		try {
			Benutzer benutzerFirst = benutzerDao.first();
			showBenutzer(benutzerFirst);
		} catch (Exception e1) {
			e1.printStackTrace();

		}
	}
	// vorherigen Benutzer anzeigen
	protected void button_1ActionPerformed(ActionEvent e) {
		Benutzer benutzerAktiv = new Benutzer();
		if(felderPruefen(textFieldID)==true) {
			benutzerAktiv = create();

			try {
				Benutzer benutzerPrevious = benutzerDao.previous(benutzerAktiv);
				showBenutzer(benutzerPrevious);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Bitte w�hlen Sie zuerst ein Benutzerprofil aus.",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}

	}

	// n�chsten Benutzer anzeigen
	protected void button_2ActionPerformed(ActionEvent e) {
		if(felderPruefen(textFieldID)==true) {
			Benutzer benutzerAktiv = create();
			try {
				Benutzer benutzerNext = benutzerDao.next(benutzerAktiv);
				showBenutzer(benutzerNext);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Bitte w�hlen Sie zuerst ein Benutzerprofil aus.",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	// letzten Benutzer eintragen
	protected void button_3ActionPerformed(ActionEvent e) {
		try {
			Benutzer benutzerLast = benutzerDao.last();
			showBenutzer(benutzerLast);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// es wird gepr�ft, ob ein Feld leer ist
	private boolean felderPruefen(JTextField textField) {
		boolean pruefe = true;
		if (textField.getText() == null){
			pruefe = false;
		}
		else if(textField.getText().equals("")) {
			pruefe = false;
		}
		else if(textField.getText().isEmpty() == true) {
			pruefe = false;
		}
		else if(textField.getText().equals(" ")) {
			pruefe = false;
		}
		return pruefe;
	}
}
