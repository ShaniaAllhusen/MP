package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

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
	 */
	public JFrameMitglied() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Mitglieder verwalten");
		setDefaultCloseOperation(JFrameMannschaft.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 311);
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
				textFieldMitgliedId.setBounds(102, 16, 200, 20);
				panel_2.add(textFieldMitgliedId);
				textFieldMitgliedId.setEditable(false);
				textFieldMitgliedId.setColumns(10);
			}
			{
				textFieldVorname = new JTextField();
				textFieldVorname.setToolTipText("Das ist der Vorname des ausgew\u00E4hlten Mitglieds");
				textFieldVorname.setBounds(102, 40, 200, 20);
				panel_2.add(textFieldVorname);
				textFieldVorname.setColumns(10);
			}
			{
				textFieldNachname = new JTextField();
				textFieldNachname.setToolTipText("Das ist der Nachname des ausgew\u00E4hlten Mitglieds");
				textFieldNachname.setBounds(102, 69, 200, 20);
				panel_2.add(textFieldNachname);
				textFieldNachname.setColumns(10);
			}
			{
				textFieldGeburtsdatum = new JTextField();
				textFieldGeburtsdatum.setToolTipText("Das ist das geburtsdatum des ausgew\u00E4hlten Mitglieds");
				textFieldGeburtsdatum.setBounds(102, 99, 200, 20);
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
				textFieldStrasse.setBounds(99, 21, 200, 22);
				panel.add(textFieldStrasse);
				textFieldStrasse.setColumns(10);
			}
			{
				textFieldPostleitzahl = new JTextField();
				textFieldPostleitzahl.setBounds(99, 53, 200, 22);
				panel.add(textFieldPostleitzahl);
				textFieldPostleitzahl.setColumns(10);
			}
			{
				textFieldOrt = new JTextField();
				textFieldOrt.setBounds(99, 81, 200, 20);
				panel.add(textFieldOrt);
				textFieldOrt.setColumns(10);
			}
		}
		{
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Benutzerprofil", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(325, 144, 286, 117);
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
				textFieldBenutzerId.setBounds(102, 30, 182, 20);
				panel_1.add(textFieldBenutzerId);
				textFieldBenutzerId.setEditable(false);
				textFieldBenutzerId.setColumns(10);
			}
			{
				textFieldBenutzername = new JTextField();
				textFieldBenutzername.setBounds(102, 56, 182, 20);
				panel_1.add(textFieldBenutzername);
				textFieldBenutzername.setColumns(10);
			}
			{
				textFieldPasswort = new JTextField();
				textFieldPasswort.setBounds(102, 80, 182, 20);
				panel_1.add(textFieldPasswort);
				textFieldPasswort.setColumns(10);
			}
		}
		{
			buttonMitgliedHinzufuegen = new JButton("Mitglied hinzuf\u00FCgen");
			buttonMitgliedHinzufuegen.setBounds(363, 22, 211, 23);
			contentPane.add(buttonMitgliedHinzufuegen);
		}
		{
			buttonMitgliedLoeschen = new JButton("Mitglied l\u00F6schen");
			buttonMitgliedLoeschen.setBounds(363, 53, 211, 23);
			contentPane.add(buttonMitgliedLoeschen);
		}
		{
			buttonDatenAktualisieren = new JButton("Daten aktualisieren");
			buttonDatenAktualisieren.setBounds(363, 80, 211, 23);
			contentPane.add(buttonDatenAktualisieren);
		}
	}

}
