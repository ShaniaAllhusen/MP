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
import tabellenklassen.Sportart;
import Dao.BenutzerDao;
import Dao.NoBenutzerFound;
import Dao.NoMannschaftFound;
import Dao.MitgliedDao;
import Dao.NoMannschaftFound;
import Dao.NoMitgliedFound;


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
	private JPanel panelAdresse;
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
	private JPanel panelBeutzerprofil;
	private JPanel panelPersönlicheDaten;
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
	private JPanel panelBenutzerprofilAussuchen;
	private JTextField textFieldMitgliedSuchen;
	private JButton buttonMitgliedSuchen;
	private JButton buttonMitgliedFirst;
	private JButton buttonMitgliedPrevious;
	private JButton buttonMitgliedNext;
	private JButton buttonMitgliedLast;
	private JPanel panelMitgliedAussuchen;
	private JButton buttonBenutzerFirst;
	private JButton buttonBenutzerPrevious;
	private JButton buttonBenutzerNext;
	private JButton buttonBenutzerLast;
	private JButton buttonBenutzerprofilHinzufgen;


	private BenutzerDao benutzerDao;

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
		benutzerDao = new BenutzerDao();
		mitgliedDao = new MitgliedDao();
		initGUI();
	}
	private void initGUI() {
		setTitle("Mitglieder verwalten");
		setDefaultCloseOperation(JFrameMannschaft.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			panelPersönlicheDaten = new JPanel();
			panelPersönlicheDaten.setToolTipText("Das sind die pers\u00F6nlichen Daten des ausgew\u00E4hlten Mitglieds");
			panelPersönlicheDaten.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pers\u00F6nliche Daten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPersönlicheDaten.setBounds(6, 16, 316, 126);
			contentPane.add(panelPersönlicheDaten);
			panelPersönlicheDaten.setLayout(null);
			{
				labelMitgliedId = new JLabel("Id");
				labelMitgliedId.setBounds(6, 17, 200, 26);
				panelPersönlicheDaten.add(labelMitgliedId);
			}
			{
				labelVorname = new JLabel("Vorname");
				labelVorname.setBounds(6, 40, 200, 26);
				panelPersönlicheDaten.add(labelVorname);
			}
			{
				labelNachname = new JLabel("Nachname");
				labelNachname.setBounds(6, 66, 200, 26);
				panelPersönlicheDaten.add(labelNachname);
			}
			{
				labelGeburtsdatum = new JLabel("Geburtsdatum");
				labelGeburtsdatum.setBounds(6, 91, 200, 26);
				panelPersönlicheDaten.add(labelGeburtsdatum);
			}
			{
				textFieldMitgliedId = new JTextField();
				textFieldMitgliedId.setToolTipText("Das ist die Id des ausgew\u00E4hlten Mitglieds");
				textFieldMitgliedId.setBounds(102, 16, 204, 20);
				panelPersönlicheDaten.add(textFieldMitgliedId);
				textFieldMitgliedId.setEditable(false);
				textFieldMitgliedId.setColumns(10);
			}
			{
				textFieldVorname = new JTextField();
				textFieldVorname.setToolTipText("Das ist der Vorname des ausgew\u00E4hlten Mitglieds");
				textFieldVorname.setBounds(102, 40, 204, 20);
				panelPersönlicheDaten.add(textFieldVorname);
				textFieldVorname.setColumns(10);
			}
			{
				textFieldNachname = new JTextField();
				textFieldNachname.setToolTipText("Das ist der Nachname des ausgew\u00E4hlten Mitglieds");
				textFieldNachname.setBounds(102, 69, 204, 20);
				panelPersönlicheDaten.add(textFieldNachname);
				textFieldNachname.setColumns(10);
			}
			{
				textFieldGeburtsdatum = new JTextField();
				textFieldGeburtsdatum.setToolTipText("Das ist das geburtsdatum des ausgew\u00E4hlten Mitglieds");
				textFieldGeburtsdatum.setBounds(102, 99, 204, 20);
				panelPersönlicheDaten.add(textFieldGeburtsdatum);
				textFieldGeburtsdatum.setColumns(10);
			}
		}
		{
			panelAdresse = new JPanel();
			panelAdresse.setToolTipText("Das ist die Adresse des ausgew\u00E4hlten Mitglieds");
			panelAdresse.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Adresse", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelAdresse.setBounds(4, 144, 316, 117);
			contentPane.add(panelAdresse);
			panelAdresse.setLayout(null);
			{
				labelStrae = new JLabel("Stra\u00DFe");
				labelStrae.setBounds(6, 19, 200, 26);
				panelAdresse.add(labelStrae);
			}
			{
				labelPostleitzahl = new JLabel("Postleitzahl");
				labelPostleitzahl.setBounds(6, 51, 200, 26);
				panelAdresse.add(labelPostleitzahl);
			}
			{
				labelOrt = new JLabel("Ort");
				labelOrt.setBounds(6, 78, 200, 26);
				panelAdresse.add(labelOrt);
			}
			{
				textFieldStrasse = new JTextField();
				textFieldStrasse.setToolTipText("Das ist die Strasse, in der das ausgew\u00E4hlte Mitglied wohnt");
				textFieldStrasse.setBounds(106, 21, 200, 22);
				panelAdresse.add(textFieldStrasse);
				textFieldStrasse.setColumns(10);
			}
			{
				textFieldPostleitzahl = new JTextField();
				textFieldPostleitzahl.setBounds(106, 53, 200, 22);
				panelAdresse.add(textFieldPostleitzahl);
				textFieldPostleitzahl.setColumns(10);
			}
			{
				textFieldOrt = new JTextField();
				textFieldOrt.setBounds(106, 81, 200, 20);
				panelAdresse.add(textFieldOrt);
				textFieldOrt.setColumns(10);
			}
		}
		{
			panelBeutzerprofil = new JPanel();
			panelBeutzerprofil.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Benutzerprofil", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelBeutzerprofil.setBounds(6, 272, 316, 117);
			contentPane.add(panelBeutzerprofil);
			panelBeutzerprofil.setLayout(null);
			{
				labelBenutzername = new JLabel("Benutzername");
				labelBenutzername.setBounds(10, 53, 200, 26);
				panelBeutzerprofil.add(labelBenutzername);
			}
			{
				labelBenutzerid = new JLabel("Benutzer-ID");
				labelBenutzerid.setBounds(10, 30, 200, 26);
				panelBeutzerprofil.add(labelBenutzerid);
			}
			{
				labelPasswort = new JLabel("Passwort");
				labelPasswort.setBounds(10, 79, 200, 26);
				panelBeutzerprofil.add(labelPasswort);
			}
			{
				textFieldBenutzerId = new JTextField();
				textFieldBenutzerId.setBounds(106, 30, 200, 20);
				panelBeutzerprofil.add(textFieldBenutzerId);
				textFieldBenutzerId.setEditable(false);
				textFieldBenutzerId.setColumns(10);
			}
			{
				textFieldBenutzername = new JTextField();
				textFieldBenutzername.setEditable(false);
				textFieldBenutzername.setBounds(106, 56, 200, 20);
				panelBeutzerprofil.add(textFieldBenutzername);
				textFieldBenutzername.setColumns(10);
			}
			{
				textFieldPasswort = new JTextField();
				textFieldPasswort.setEditable(false);
				textFieldPasswort.setBounds(106, 80, 200, 20);
				panelBeutzerprofil.add(textFieldPasswort);
				textFieldPasswort.setColumns(10);
			}
		}
		{
			buttonMitgliedHinzufuegen = new JButton("Mitglied hinzuf\u00FCgen");
			buttonMitgliedHinzufuegen.setToolTipText("Hier k\u00F6nnen Sie ein neues Mitglied anlegen (Alt + H)\r\n");
			buttonMitgliedHinzufuegen.setMnemonic('H');
			buttonMitgliedHinzufuegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonMitgliedHinzufuegenActionPerformed(e);
				}
			});
			buttonMitgliedHinzufuegen.setBounds(332, 104, 279, 23);
			contentPane.add(buttonMitgliedHinzufuegen);
		}
		{
			buttonMitgliedLoeschen = new JButton("Mitglied l\u00F6schen");
			buttonMitgliedLoeschen.setToolTipText("Hier k\u00F6nnen Sie das ausgew\u00E4hlte Mitglied l\u00F6schen (Alt + L)");
			buttonMitgliedLoeschen.setMnemonic('L');
			buttonMitgliedLoeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonMitgliedLoeschenActionPerformed(e);
				}
			});
			buttonMitgliedLoeschen.setBounds(332, 135, 279, 23);
			contentPane.add(buttonMitgliedLoeschen);
		}
		{
			buttonDatenAktualisieren = new JButton("Daten aktualisieren");
			buttonDatenAktualisieren.setToolTipText("Hier k\u00F6nnen Sie die Daten des ausgew\u00E4hlten Mitglieds aktualisieren (Alt + A)");
			buttonDatenAktualisieren.setMnemonic('A');
			buttonDatenAktualisieren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonDatenAktualisierenActionPerformed(e);
				}
			});
			buttonDatenAktualisieren.setBounds(332, 162, 279, 23);
			contentPane.add(buttonDatenAktualisieren);
		}
		{
			panelBenutzerprofilAussuchen = new JPanel();
			panelBenutzerprofilAussuchen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Benutzerprofil ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelBenutzerprofilAussuchen.setBounds(330, 196, 285, 191);
			contentPane.add(panelBenutzerprofilAussuchen);
			panelBenutzerprofilAussuchen.setLayout(null);
			{
				textFieldBenutzerprofilSuchen = new JTextField();
				textFieldBenutzerprofilSuchen.setToolTipText("Geben Sie hier die Id oder den Benutzernamen des Benutzerprofils an, das Sie suchen wollen");
				textFieldBenutzerprofilSuchen.setBounds(6, 23, 119, 20);
				panelBenutzerprofilAussuchen.add(textFieldBenutzerprofilSuchen);
				textFieldBenutzerprofilSuchen.setColumns(10);
			}
			{
				buttonBenutzerprofilSuchen = new JButton("Suchen");
				buttonBenutzerprofilSuchen.setToolTipText("Hier k\u00F6nnen Sie ein Benutzerprofil nach der Id oder nem Benutzernamen suchen (Alt + B)");
				buttonBenutzerprofilSuchen.setMnemonic('B');
				buttonBenutzerprofilSuchen.setBounds(135, 22, 144, 23);
				panelBenutzerprofilAussuchen.add(buttonBenutzerprofilSuchen);
				{
					labelBenutzerSucheId = new JLabel("Benutzer-ID");
					labelBenutzerSucheId.setBounds(11, 56, 114, 14);
					panelBenutzerprofilAussuchen.add(labelBenutzerSucheId);
				}
				{
					labelBenutzerSucheName = new JLabel("Benutzername");
					labelBenutzerSucheName.setBounds(11, 79, 114, 14);
					panelBenutzerprofilAussuchen.add(labelBenutzerSucheName);
				}
				{
					labelBenutzerSuchePasswort = new JLabel("Passwort");
					labelBenutzerSuchePasswort.setBounds(11, 104, 114, 14);
					panelBenutzerprofilAussuchen.add(labelBenutzerSuchePasswort);
				}
				{
					textFieldBenutzerSucheID = new JTextField();
					textFieldBenutzerSucheID.setToolTipText("Das ist die Id des ausgew\u00E4hlten Benutzerprofils");
					textFieldBenutzerSucheID.setBounds(135, 50, 144, 20);
					panelBenutzerprofilAussuchen.add(textFieldBenutzerSucheID);
					textFieldBenutzerSucheID.setEditable(false);
					textFieldBenutzerSucheID.setColumns(10);
				}
				{
					textFieldBenutzerSucheName = new JTextField();
					textFieldBenutzerSucheName.setToolTipText("Das ist der Benutzername des ausgew\u00E4hlten Benutzerprofils");
					textFieldBenutzerSucheName.setBounds(135, 76, 144, 20);
					panelBenutzerprofilAussuchen.add(textFieldBenutzerSucheName);
					textFieldBenutzerSucheName.setColumns(10);
				}
				{
					textFieldBenutzerSuchePasswort = new JTextField();
					textFieldBenutzerSuchePasswort.setToolTipText("Das ist das Passwort des ausgew\u00E4hlten Benutzerprofils");
					textFieldBenutzerSuchePasswort.setBounds(135, 101, 144, 20);
					panelBenutzerprofilAussuchen.add(textFieldBenutzerSuchePasswort);
					textFieldBenutzerSuchePasswort.setColumns(10);
				}
				{
					buttonBenutzerprofilUebernehmen = new JButton("\u00DCbernehmen");
					buttonBenutzerprofilUebernehmen.setToolTipText("Benutzerprofil f\u00FCr das ausgew\u00E4hlte Mitglied \u00FCbernehmen (Alt + \u00DC)");
					buttonBenutzerprofilUebernehmen.setMnemonic('Ü');
					buttonBenutzerprofilUebernehmen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							buttonBenutzerprofilUebernehmenActionPerformed(e);
						}
					});
					buttonBenutzerprofilUebernehmen.setBounds(6, 157, 136, 23);
					panelBenutzerprofilAussuchen.add(buttonBenutzerprofilUebernehmen);
				}
				{
					buttonBenutzerFirst = new JButton("|<");
					buttonBenutzerFirst.setToolTipText("Erstes Benutzerprofil anzeigen ");
					buttonBenutzerFirst.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							buttonBenutzerFirstActionPerformed(e);
						}
					});
					buttonBenutzerFirst.setBounds(6, 129, 67, 23);
					panelBenutzerprofilAussuchen.add(buttonBenutzerFirst);
				}
				{
					buttonBenutzerPrevious = new JButton("<<");
					buttonBenutzerPrevious.setToolTipText("Vorherieges Benutzerprofil anzeigen");
					buttonBenutzerPrevious.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							buttonBenutzerPreviousActionPerformed(e);
						}
					});
					buttonBenutzerPrevious.setBounds(75, 129, 67, 23);
					panelBenutzerprofilAussuchen.add(buttonBenutzerPrevious);
				}
				{
					buttonBenutzerNext = new JButton(">>");
					buttonBenutzerNext.setToolTipText("N\u00E4chstes Benutzerprofil anzeigen");
					buttonBenutzerNext.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							buttonBenutzerNextActionPerformed(e);
						}
					});
					buttonBenutzerNext.setBounds(145, 129, 67, 23);
					panelBenutzerprofilAussuchen.add(buttonBenutzerNext);
				}
				{
					buttonBenutzerLast = new JButton(">|");
					buttonBenutzerLast.setToolTipText("Letztes Benutzerprofil anzeigen");
					buttonBenutzerLast.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							buttonBenutzerLastActionPerformed(e);
						}
					});
					buttonBenutzerLast.setBounds(212, 129, 67, 23);
					panelBenutzerprofilAussuchen.add(buttonBenutzerLast);
				}
				{
					buttonBenutzerprofilHinzufgen = new JButton("Neu");
					buttonBenutzerprofilHinzufgen.setToolTipText("Neues Benutzerprofil anlegen (Alt + N)");
					buttonBenutzerprofilHinzufgen.setMnemonic('N');
					buttonBenutzerprofilHinzufgen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							buttonBenutzerprofilHinzufgenActionPerformed(e);
						}
					});
					buttonBenutzerprofilHinzufgen.setBounds(146, 157, 133, 23);
					panelBenutzerprofilAussuchen.add(buttonBenutzerprofilHinzufgen);
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
			panelMitgliedAussuchen = new JPanel();
			panelMitgliedAussuchen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mitglied ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelMitgliedAussuchen.setBounds(326, 16, 291, 83);
			contentPane.add(panelMitgliedAussuchen);
			panelMitgliedAussuchen.setLayout(null);
			{
				textFieldMitgliedSuchen = new JTextField();
				textFieldMitgliedSuchen.setBounds(6, 17, 153, 20);
				panelMitgliedAussuchen.add(textFieldMitgliedSuchen);
				textFieldMitgliedSuchen.setColumns(10);
			}
			{
				buttonMitgliedSuchen = new JButton("Suchen");
				buttonMitgliedSuchen.setMnemonic('S');
				buttonMitgliedSuchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedSuchenActionPerformed(e);
					}
				});

				buttonMitgliedSuchen.setBounds(169, 16, 116, 23);
				panelMitgliedAussuchen.add(buttonMitgliedSuchen);
			}
			{
				buttonMitgliedFirst = new JButton("|<");
				buttonMitgliedFirst.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedFirstActionPerformed(e);
					}
				});
				buttonMitgliedFirst.setBounds(6, 48, 67, 23);
				panelMitgliedAussuchen.add(buttonMitgliedFirst);
			}
			{
				buttonMitgliedPrevious = new JButton("<<");
				buttonMitgliedPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedPreviousActionPerformed(e);
					}
				});
				buttonMitgliedPrevious.setBounds(75, 48, 67, 23);
				panelMitgliedAussuchen.add(buttonMitgliedPrevious);
			}
			{
				buttonMitgliedNext = new JButton(">>");
				buttonMitgliedNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedNextActionPerformed(e);
					}
				});
				buttonMitgliedNext.setBounds(151, 48, 67, 23);
				panelMitgliedAussuchen.add(buttonMitgliedNext);
			}
			{
				buttonMitgliedLast = new JButton(">|");
				buttonMitgliedLast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMitgliedLastActionPerformed(e);
					}
				});
				buttonMitgliedLast.setBounds(218, 48, 67, 23);
				panelMitgliedAussuchen.add(buttonMitgliedLast);
			}
		}
	}
	protected void buttonBenutzerprofilSuchenActionPerformed(ActionEvent e) throws NoBenutzerFound {
		String eingabe = textFieldBenutzerprofilSuchen.getText();
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
		Mitglied mitgliedAktiv = new Mitglied();

		Benutzer benutzerAktiv = new Benutzer();
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

		Mitglied mitgliedAktiv = new Mitglied();
		mitgliedAktiv = create();
		Mitglied mitgliedNext = mitgliedDao.next(mitgliedAktiv);
		showMitglied(mitgliedNext);
	}
	protected void buttonMitgliedLastActionPerformed(ActionEvent e) {
		try {
			Mitglied mitgliedLast = mitgliedDao.last();
			showMitglied(mitgliedLast);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	protected void buttonBenutzerFirstActionPerformed(ActionEvent e) {
		try {
			Benutzer benutzerFirst = benutzerDao.first();
			showBenutzer(benutzerFirst);
		} catch (Exception e1) {
			e1.printStackTrace();

		}
	}
	protected void buttonBenutzerPreviousActionPerformed(ActionEvent e) {
		Benutzer benutzerAktiv = new Benutzer();
		benutzerAktiv.setId(Integer.parseInt(textFieldBenutzerSucheID.getText()));
		benutzerAktiv.setUsername(textFieldBenutzerSucheName.getText());
		benutzerAktiv.setPasswort(textFieldBenutzerSuchePasswort.getText());

		try {
			Benutzer benutzerPrevious = benutzerDao.previous(benutzerAktiv);
			showBenutzer(benutzerPrevious);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void buttonBenutzerNextActionPerformed(ActionEvent e) {
		Benutzer benutzerAktiv = new Benutzer();
		benutzerAktiv.setId(Integer.parseInt(textFieldBenutzerSucheID.getText()));
		benutzerAktiv.setUsername(textFieldBenutzerSucheName.getText());
		benutzerAktiv.setPasswort(textFieldBenutzerSuchePasswort.getText());
		try {
			Benutzer benutzerNext = benutzerDao.next(benutzerAktiv);
			showBenutzer(benutzerNext);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void buttonBenutzerLastActionPerformed(ActionEvent e) {
		try {
			Benutzer benutzerLast = benutzerDao.last();
			showBenutzer(benutzerLast);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void buttonBenutzerprofilHinzufgenActionPerformed(ActionEvent e) {
		String username = textFieldBenutzerSucheName.getText();
		String passwort = textFieldBenutzerSuchePasswort.getText();
		Benutzer benutzer = new Benutzer();
		benutzer.setUsername(username);
		benutzer.setPasswort(passwort);

		try {
			benutzerDao.insert(benutzer);
			textFieldBenutzerSucheID.setText(Integer.toString(benutzer.getId()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonMitgliedHinzufuegenActionPerformed(ActionEvent e) {
		Mitglied mitgliedAktiv = new Mitglied();
		Benutzer benutzerAktiv = new Benutzer();
		mitgliedAktiv.setVorname(textFieldVorname.getText());
		mitgliedAktiv.setNachname(textFieldNachname.getText());
		mitgliedAktiv.setGeburtsdatum(textFieldGeburtsdatum.getText());
		mitgliedAktiv.setStrasse(textFieldStrasse.getText());
		mitgliedAktiv.setPlz(textFieldPostleitzahl.getText());
		mitgliedAktiv.setOrt(textFieldOrt.getText());
		if ((textFieldBenutzerId.getText() != null) && (textFieldBenutzerId.getText() != "") && (textFieldBenutzerId.getText().isEmpty() == false)  && (textFieldBenutzerId.getText() != " ")) {
			benutzerAktiv.setId(Integer.parseInt(textFieldBenutzerId.getText()));
			benutzerAktiv.setUsername(textFieldBenutzername.getText());
			benutzerAktiv.setPasswort(textFieldPasswort.getText());
			mitgliedAktiv.setBenutzer(benutzerAktiv);
			try {
				mitgliedDao.insertMitBenutzer(mitgliedAktiv);
				textFieldMitgliedId.setText(Integer.toString(mitgliedAktiv.getId()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else {
			try {
				mitgliedDao.insertOhneBenutzer(mitgliedAktiv);
				textFieldMitgliedId.setText(Integer.toString(mitgliedAktiv.getId()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	protected void buttonMitgliedLoeschenActionPerformed(ActionEvent e) {
		Mitglied mitglied = create();
		try {
			mitgliedDao.delete(mitglied);
			felderLeeren();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void felderLeeren() {
		textFieldMitgliedId.setText("");
		textFieldVorname.setText("");
		textFieldNachname.setText("");
		textFieldGeburtsdatum.setText("");
		textFieldStrasse.setText("");
		textFieldPostleitzahl.setText("");
		textFieldOrt.setText("");
		textFieldBenutzerId.setText("");
		textFieldBenutzername.setText("");
		textFieldPasswort.setText("");
	}
	protected void buttonDatenAktualisierenActionPerformed(ActionEvent e) {
		Mitglied mitglied = create();
		try {
			mitgliedDao.update(mitglied);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void benutzerSucheFelderLeeren() {
		textFieldBenutzerSucheID.setText("");
		textFieldBenutzerSucheName.setText("");
		textFieldBenutzerSuchePasswort.setText("");
	}

	protected void buttonBenutzerprofilUebernehmenActionPerformed(ActionEvent e) {
		textFieldBenutzerId.setText(textFieldBenutzerSucheID.getText());
		textFieldBenutzername.setText(textFieldBenutzerSucheName.getText());
		textFieldPasswort.setText(textFieldBenutzerSuchePasswort.getText());
		benutzerSucheFelderLeeren();
	}
}

