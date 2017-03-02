package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import tabellenklassen.Benutzer;
import tabellenklassen.Mannschaft;
import tabellenklassen.Mitglied;
<<<<<<< HEAD
import tabellenklassen.Sportart;
import Dao.BenutzerDao;
import Dao.NoBenutzerFound;
import Dao.NoMannschaftFound;
=======
import Dao.MitgliedDao;
import Dao.NoMannschaftFound;
import Dao.NoMitgliedFound;
>>>>>>> branch 'master' of https://github.com/shania3/MP.git

	import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameMitglied extends JFrame {

	private JPanel contentPane;
	private JLabel labelMitgliedId;
	private JLabel labelVorname;
	private JLabel labelNachname;
	private JLabel labelGeburtsdatum;
	private JLabel labelStrae;
	private JLabel labelPostleitzahl;
	private JLabel labelOrt;
	private JPanel panel;
	private JTextField textFieldMitgliedId;
	private JTextField textFieldVorname;
	private JTextField textFieldNachname;
	private JTextField textFieldGeburtsdatum;
	private JTextField textFieldStrasse;
	private JTextField textFieldPostleitzahl;
	private JTextField textFieldOrt;
	private JLabel labelBenutzername;
	private JLabel labelBenutzerid;
	private JLabel labelPasswort;
	private JTextField textFieldBenutzerId;
	private JTextField textFieldBenutzername;
	private JTextField textFieldPasswort;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton buttonMitgliedHinzufuegen;
	private JButton buttonMitgliedLoeschen;
	private JButton buttonDatenAktualisieren;
	private JTextField textFieldBenutzerprofilSuchen;
	private JButton buttonBenutzerprofilSuchen;
	private JLabel labelBenutzerSucheId;
	private JLabel labelBenutzerSucheName;
	private JLabel labelBenutzerSuchePasswort;
	private JTextField textFieldBenutzerSucheID;
	private JTextField textFieldBenutzerSucheName;
	private JTextField textFieldBenutzerSuchePasswort;
	private JButton buttonBenutzerprofilUebernehmen;
	private JPanel panel_3;
	private JTextField textFieldMitgliedSuchen;
	private JButton buttonMitgliedSuchen;
	private JButton buttonMitgliedFirst;
	private JButton buttonMitgliedPrevious;
	private JButton buttonMitgliedNext;
	private JButton buttonMitgliedLast;
	private JPanel panel_4;
	private JButton buttonBenutzerFirst;
	private JButton buttonBenutzerPrevious;
	private JButton buttonBenutzerNext;
	private JButton buttonBenutzerLast;
	private JButton buttonBenutzerprofilHinzufgen;


	private BenutzerDao benutzerDao;


	private JButton buttonndern;

	private MitgliedDao mitgliedDao;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMitglied frame = new JFrameMitglied();
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
	public JFrameMitglied() throws ClassNotFoundException {
		mitgliedDao = new MitgliedDao();
		initGUI();
	}
	private void initGUI() {
		setTitle("Mitglieder verwalten");
		setDefaultCloseOperation(JFrameMannschaft.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pers\u00F6nliche Daten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(6, 16, 316, 126);
			contentPane.add(panel_2);
			panel_2.setLayout(null);
			{
				labelMitgliedId = new JLabel("Id");
				labelMitgliedId.setBounds(6, 17, 200, 26);
				panel_2.add(labelMitgliedId);
			}
			{
				labelVorname = new JLabel("Vorname");
				labelVorname.setBounds(6, 40, 200, 26);
				panel_2.add(labelVorname);
			}
			{
				labelNachname = new JLabel("Nachname");
				labelNachname.setBounds(6, 66, 200, 26);
				panel_2.add(labelNachname);
			}
			{
				labelGeburtsdatum = new JLabel("Geburtsdatum");
				labelGeburtsdatum.setBounds(6, 91, 200, 26);
				panel_2.add(labelGeburtsdatum);
			}
			{
				textFieldMitgliedId = new JTextField();
				textFieldMitgliedId.setToolTipText("Das ist die Id des Mitglieds");
				textFieldMitgliedId.setBounds(102, 16, 204, 20);
				panel_2.add(textFieldMitgliedId);
				textFieldMitgliedId.setEditable(false);
				textFieldMitgliedId.setColumns(10);
			}
			{
				textFieldVorname = new JTextField();
				textFieldVorname.setToolTipText("Das ist der Vorname des ausgew\u00E4hlten Mitglieds");
				textFieldVorname.setBounds(102, 40, 204, 20);
				panel_2.add(textFieldVorname);
				textFieldVorname.setColumns(10);
			}
			{
				textFieldNachname = new JTextField();
				textFieldNachname.setToolTipText("Das ist der Nachname des ausgew\u00E4hlten Mitglieds");
				textFieldNachname.setBounds(102, 69, 204, 20);
				panel_2.add(textFieldNachname);
				textFieldNachname.setColumns(10);
			}
			{
				textFieldGeburtsdatum = new JTextField();
				textFieldGeburtsdatum.setToolTipText("Das ist das geburtsdatum des ausgew\u00E4hlten Mitglieds");
				textFieldGeburtsdatum.setBounds(102, 99, 204, 20);
				panel_2.add(textFieldGeburtsdatum);
				textFieldGeburtsdatum.setColumns(10);
			}
		}
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Adresse", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(4, 144, 316, 117);
			contentPane.add(panel);
			panel.setLayout(null);
			{
				labelStrae = new JLabel("Stra\u00DFe");
				labelStrae.setBounds(6, 19, 200, 26);
				panel.add(labelStrae);
			}
			{
				labelPostleitzahl = new JLabel("Postleitzahl");
				labelPostleitzahl.setBounds(6, 51, 200, 26);
				panel.add(labelPostleitzahl);
			}
			{
				labelOrt = new JLabel("Ort");
				labelOrt.setBounds(6, 78, 200, 26);
				panel.add(labelOrt);
			}
			{
				textFieldStrasse = new JTextField();
				textFieldStrasse.setBounds(106, 21, 200, 22);
				panel.add(textFieldStrasse);
				textFieldStrasse.setColumns(10);
			}
			{
				textFieldPostleitzahl = new JTextField();
				textFieldPostleitzahl.setBounds(106, 53, 200, 22);
				panel.add(textFieldPostleitzahl);
				textFieldPostleitzahl.setColumns(10);
			}
			{
				textFieldOrt = new JTextField();
				textFieldOrt.setBounds(106, 81, 200, 20);
				panel.add(textFieldOrt);
				textFieldOrt.setColumns(10);
			}
		}
		{
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Benutzerprofil", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(6, 272, 316, 117);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			{
				labelBenutzername = new JLabel("Benutzername");
				labelBenutzername.setBounds(10, 53, 200, 26);
				panel_1.add(labelBenutzername);
			}
			{
				labelBenutzerid = new JLabel("Benutzer-ID");
				labelBenutzerid.setBounds(10, 30, 200, 26);
				panel_1.add(labelBenutzerid);
			}
			{
				labelPasswort = new JLabel("Passwort");
				labelPasswort.setBounds(10, 79, 200, 26);
				panel_1.add(labelPasswort);
			}
			{
				textFieldBenutzerId = new JTextField();
				textFieldBenutzerId.setBounds(106, 30, 200, 20);
				panel_1.add(textFieldBenutzerId);
				textFieldBenutzerId.setEditable(false);
				textFieldBenutzerId.setColumns(10);
			}
			{
				textFieldBenutzername = new JTextField();
				textFieldBenutzername.setEditable(false);
				textFieldBenutzername.setBounds(106, 56, 200, 20);
				panel_1.add(textFieldBenutzername);
				textFieldBenutzername.setColumns(10);
			}
			{
				textFieldPasswort = new JTextField();
				textFieldPasswort.setEditable(false);
				textFieldPasswort.setBounds(106, 80, 200, 20);
				panel_1.add(textFieldPasswort);
				textFieldPasswort.setColumns(10);
			}
		}
		{
			buttonMitgliedHinzufuegen = new JButton("Mitglied hinzuf\u00FCgen");
			buttonMitgliedHinzufuegen.setBounds(332, 104, 279, 23);
			contentPane.add(buttonMitgliedHinzufuegen);
		}
		{
			buttonMitgliedLoeschen = new JButton("Mitglied l\u00F6schen");
			buttonMitgliedLoeschen.setBounds(332, 135, 279, 23);
			contentPane.add(buttonMitgliedLoeschen);
		}
		{
			buttonDatenAktualisieren = new JButton("Daten aktualisieren");
			buttonDatenAktualisieren.setBounds(332, 162, 279, 23);
			contentPane.add(buttonDatenAktualisieren);
		}
		{
			panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Benutzerprofil ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_3.setBounds(330, 190, 285, 214);
			contentPane.add(panel_3);
			panel_3.setLayout(null);
			{
				textFieldBenutzerprofilSuchen = new JTextField();
				textFieldBenutzerprofilSuchen.setBounds(6, 17, 114, 20);
				panel_3.add(textFieldBenutzerprofilSuchen);
				textFieldBenutzerprofilSuchen.setColumns(10);
			}
			{
				buttonBenutzerprofilSuchen = new JButton("Benutzerprofil suchen");
				buttonBenutzerprofilSuchen.setBounds(130, 16, 149, 23);
				panel_3.add(buttonBenutzerprofilSuchen);
				{
					labelBenutzerSucheId = new JLabel("Benutzer-ID");
					labelBenutzerSucheId.setBounds(11, 56, 114, 14);
					panel_3.add(labelBenutzerSucheId);
				}
				{
					labelBenutzerSucheName = new JLabel("Benutzername");
					labelBenutzerSucheName.setBounds(11, 79, 114, 14);
					panel_3.add(labelBenutzerSucheName);
				}
				{
					labelBenutzerSuchePasswort = new JLabel("Passwort");
					labelBenutzerSuchePasswort.setBounds(11, 104, 114, 14);
					panel_3.add(labelBenutzerSuchePasswort);
				}
				{
					textFieldBenutzerSucheID = new JTextField();
					textFieldBenutzerSucheID.setBounds(135, 50, 144, 20);
					panel_3.add(textFieldBenutzerSucheID);
					textFieldBenutzerSucheID.setEditable(false);
					textFieldBenutzerSucheID.setColumns(10);
				}
				{
					textFieldBenutzerSucheName = new JTextField();
					textFieldBenutzerSucheName.setBounds(135, 76, 144, 20);
					panel_3.add(textFieldBenutzerSucheName);
					textFieldBenutzerSucheName.setColumns(10);
				}
				{
					textFieldBenutzerSuchePasswort = new JTextField();
					textFieldBenutzerSuchePasswort.setBounds(135, 101, 144, 20);
					panel_3.add(textFieldBenutzerSuchePasswort);
					textFieldBenutzerSuchePasswort.setColumns(10);
				}
				{
					buttonBenutzerprofilUebernehmen = new JButton("\u00DCbernehmen");
					buttonBenutzerprofilUebernehmen.setBounds(6, 191, 273, 23);
					panel_3.add(buttonBenutzerprofilUebernehmen);
				}
				{
					buttonBenutzerFirst = new JButton("|<");
					buttonBenutzerFirst.setBounds(6, 129, 67, 23);
					panel_3.add(buttonBenutzerFirst);
				}
				{
					buttonBenutzerPrevious = new JButton("<<");
					buttonBenutzerPrevious.setBounds(75, 129, 67, 23);
					panel_3.add(buttonBenutzerPrevious);
				}
				{
					buttonBenutzerNext = new JButton(">>");
					buttonBenutzerNext.setBounds(145, 129, 67, 23);
					panel_3.add(buttonBenutzerNext);
				}
				{
					buttonBenutzerLast = new JButton(">|");
					buttonBenutzerLast.setBounds(212, 129, 67, 23);
					panel_3.add(buttonBenutzerLast);
				}
				{
					buttonBenutzerprofilHinzufgen = new JButton("Neu");
					buttonBenutzerprofilHinzufgen.setBounds(6, 159, 136, 23);
					panel_3.add(buttonBenutzerprofilHinzufgen);
				}
				{
					buttonndern = new JButton("\u00C4ndern");
					buttonndern.setBounds(155, 159, 124, 23);
					panel_3.add(buttonndern);
				}
				buttonBenutzerprofilSuchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							buttonBenutzerprofilSuchenActionPerformed(e);
						} catch (NoBenutzerFound e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}
		{
			panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mitglied ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_4.setBounds(326, 16, 291, 78);
			contentPane.add(panel_4);
			panel_4.setLayout(null);
			{
				textFieldMitgliedSuchen = new JTextField();
				textFieldMitgliedSuchen.setBounds(6, 17, 153, 20);
				panel_4.add(textFieldMitgliedSuchen);
				textFieldMitgliedSuchen.setColumns(10);
			}
			{
				buttonMitgliedSuchen = new JButton("Suchen");
				buttonMitgliedSuchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedSuchenActionPerformed(e);
					}
				});

				buttonMitgliedSuchen.setBounds(169, 16, 116, 23);
				panel_4.add(buttonMitgliedSuchen);
			}
			{
				buttonMitgliedFirst = new JButton("|<");
				buttonMitgliedFirst.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedFirstActionPerformed(e);
					}
				});
				buttonMitgliedFirst.setBounds(6, 48, 67, 23);
				panel_4.add(buttonMitgliedFirst);
			}
			{
				buttonMitgliedPrevious = new JButton("<<");
				buttonMitgliedPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedPreviousActionPerformed(e);
					}
				});
				buttonMitgliedPrevious.setBounds(75, 48, 67, 23);
				panel_4.add(buttonMitgliedPrevious);
			}
			{
				buttonMitgliedNext = new JButton(">>");
				buttonMitgliedNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedNextActionPerformed(e);
					}
				});
				buttonMitgliedNext.setBounds(151, 48, 67, 23);
				panel_4.add(buttonMitgliedNext);
			}
			{
				buttonMitgliedLast = new JButton(">|");
				buttonMitgliedLast.setBounds(218, 48, 67, 23);
				panel_4.add(buttonMitgliedLast);
			}
		}
	}
	protected void buttonBenutzerprofilSuchenActionPerformed(ActionEvent e) throws NoBenutzerFound {
		String eingabe = textFieldBenutzerprofilSuchen.getText();
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
		textFieldBenutzerSucheID.setText(Integer.toString(benutzer.getId()));
		textFieldBenutzerSucheName.setText(benutzer.getUsername());
		textFieldBenutzerSuchePasswort.setText(benutzer.getPasswort());

	}
	private void showErrorPane(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}


	protected void buttonMitgliedSuchenActionPerformed(ActionEvent e) {
		String eingabe = textFieldMitgliedSuchen.getText();
		Mitglied mitglied;
		int id;
		try {
			mitglied = new Mitglied();
			id = Integer.parseInt(eingabe);
			mitglied = mitgliedDao.select(id);
			showMitglied(mitglied);
		} catch (NoMitgliedFound e1) {
			showErrorPane(e1);
		}
	}


	private void showMitglied(Mitglied mitglied) {
		textFieldMitgliedId.setText(Integer.toString(mitglied.getId()));
		textFieldVorname.setText(mitglied.getVorname());
		textFieldNachname.setText(mitglied.getNachname());
		textFieldGeburtsdatum.setText(mitglied.getGeburtsdatum());
		textFieldStrasse.setText(mitglied.getStrasse());
		textFieldPostleitzahl.setText(mitglied.getPlz());
		textFieldOrt.setText(mitglied.getOrt());
		textFieldBenutzerId.setText(Integer.toString(mitglied.getBenutzer().getId()));
		textFieldBenutzername.setText(mitglied.getBenutzer().getUsername());
		textFieldPasswort.setText(mitglied.getBenutzer().getPasswort());
	}
	protected void buttonMitgliedFirstActionPerformed(ActionEvent e) {
		Mitglied mitgliedFirst = new Mitglied();
		mitgliedFirst = mitgliedDao.first();
		showMitglied(mitgliedFirst);
	}
	protected void buttonMitgliedPreviousActionPerformed(ActionEvent e) {
		Mitglied mitgliedAktiv = new Mitglied();
		mitgliedAktiv = create();
		try {
			Mitglied mitgliedPrevious  = mitgliedDao.previous(mitgliedAktiv);
			showMitglied(mitgliedPrevious);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public Mitglied create() {
		Benutzer benutzerAktiv = new Benutzer();
		Mitglied mitgliedAktiv = new Mitglied();
		benutzerAktiv.setId(Integer.parseInt(textFieldBenutzerId.getText()));
		benutzerAktiv.setUsername(textFieldBenutzername.getText());
		benutzerAktiv.setPasswort(textFieldPasswort.getText());
		mitgliedAktiv.setBenutzer(benutzerAktiv);
		mitgliedAktiv.setId(Integer.parseInt(textFieldMitgliedId.getText()));
		mitgliedAktiv.setVorname(textFieldVorname.getText());
		mitgliedAktiv.setNachname(textFieldNachname.getText());
		mitgliedAktiv.setGeburtsdatum(textFieldGeburtsdatum.getText());
		mitgliedAktiv.setStrasse(textFieldStrasse.getText());
		mitgliedAktiv.setPlz(textFieldPostleitzahl.getText());
		mitgliedAktiv.setOrt(textFieldOrt.getText());
		return mitgliedAktiv;
	}
	protected void buttonMitgliedNextActionPerformed(ActionEvent e) {

	}

}
