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
		setTitle("Benutzer verwalten");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					buttonNewButtonActionPerformed(e);
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
		textFieldID.setText(Integer.toString(benutzer.getId()));
		textFieldUsername.setText(benutzer.getUsername());
		textFieldPasswort.setText(benutzer.getPasswort());
	}

	private void showErrorPane(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}
	private void showInfoPane(String text, int id) {
		JOptionPane.showMessageDialog(this, text +" mit der ID " +id +" wurde hinzugefügt", "Informationsmeldung", JOptionPane.INFORMATION_MESSAGE);
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
			showInfoPane("Benutzer", benutzer.getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonNewButtonActionPerformed(ActionEvent e) {
		Benutzer benutzer = create();
		try {
			benutzerDao.update(benutzer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonLoeschenActionPerformed(ActionEvent e) {
		Benutzer benutzer = create();
		try {
			benutzerDao.delete(benutzer);
			felderLeeren();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

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

	private void felderLeeren() {
		textFieldID.setText("");
		textFieldUsername.setText("");
		textFieldPasswort.setText("");
	}
	protected void buttonActionPerformed(ActionEvent e) {
		try {
			Benutzer benutzerFirst = benutzerDao.first();
			showBenutzer(benutzerFirst);
		} catch (Exception e1) {
			e1.printStackTrace();

		}
	}
	protected void button_1ActionPerformed(ActionEvent e) {
		Benutzer benutzerAktiv = create();

		try {
			Benutzer benutzerPrevious = benutzerDao.previous(benutzerAktiv);
			showBenutzer(benutzerPrevious);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void button_2ActionPerformed(ActionEvent e) {
		Benutzer benutzerAktiv = create();
		try {
			Benutzer benutzerNext = benutzerDao.next(benutzerAktiv);
			showBenutzer(benutzerNext);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void button_3ActionPerformed(ActionEvent e) {
		try {
			Benutzer benutzerLast = benutzerDao.last();
			showBenutzer(benutzerLast);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
