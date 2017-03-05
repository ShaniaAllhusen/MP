package gui;

//Imports
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import tabellenklassen.Mannschaft;
import tabellenklassen.Sportart;
import Dao.MannschaftDao;
import Dao.NoMannschaftFound;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameTraining extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<?> comboBox;
	private JLabel labelWochentag;
	private JTextField textFieldMannschaftSuchen;
	private JButton buttonMannschaftSuchen;
	private JLabel labelMannschaftName;
	private JTextField textFieldMannschaftName;
	private JLabel labelMannschaftId;
	private JTextField textFieldMannschaftId;
	private JButton buttonFirst;
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JButton buttonLast;
	private JPanel panel;
	private JLabel labelDauer;

	private MannschaftDao mannschaftDao;

	/**
	 * @wbp.nonvisual location=54,-41
	 */
	private JLabel labelSportart;
	private JTextField textFieldSportartName;
	private JLabel labelSportart_1;
	private JTextField textFieldSportartId;
	private JLabel labelBeginn;
	private JTextField textFieldBeginn;
	private JTextField textFieldDauer;
	private JButton buttonNeu;
	private JButton buttonLschen;
	private JButton buttonSpeichern;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameTraining frame = new JFrameTraining();
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
	public JFrameTraining() throws ClassNotFoundException {
		mannschaftDao = new MannschaftDao();
		initGUI();
	}
	private void initGUI() {
		setTitle("Training verwalten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			comboBox = new JComboBox();
			comboBox.setToolTipText("W\u00E4hlen Sie hier den Wochentag an, dem das Training stattfinden soll");
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"}));
			comboBox.setBounds(108, 20, 93, 20);
			contentPane.add(comboBox);
		}
		{
			labelWochentag = new JLabel("Wochentag");
			labelWochentag.setBounds(19, 20, 105, 20);
			contentPane.add(labelWochentag);
		}
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Training ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(19, 74, 405, 193);
			contentPane.add(panel);
			panel.setLayout(null);
			{
				textFieldMannschaftSuchen = new JTextField();
				textFieldMannschaftSuchen.setToolTipText("Geben Sie hier die ID oder den Namen der Mannschaft an, die Sie suchen wollen");
				textFieldMannschaftSuchen.setBounds(10, 27, 175, 20);
				panel.add(textFieldMannschaftSuchen);
				textFieldMannschaftSuchen.setColumns(10);
			}
			{
				buttonMannschaftSuchen = new JButton("Suchen");
				buttonMannschaftSuchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMannschaftSuchenActionPerformed(e);
					}
				});
				buttonMannschaftSuchen.setMnemonic('S');
				buttonMannschaftSuchen.setToolTipText("Mannschaft suchen\r\n (Alt + S)");
				buttonMannschaftSuchen.setBounds(195, 26, 89, 23);
				panel.add(buttonMannschaftSuchen);
			}
			{
				labelMannschaftName = new JLabel("Name");
				labelMannschaftName.setBounds(11, 59, 66, 23);
				panel.add(labelMannschaftName);
			}
			{
				textFieldMannschaftName = new JTextField();
				textFieldMannschaftName.setEditable(false);
				textFieldMannschaftName.setToolTipText("Name der ausgew\u00E4hlten Mannschaft");
				textFieldMannschaftName.setBounds(47, 60, 138, 20);
				panel.add(textFieldMannschaftName);
				textFieldMannschaftName.setColumns(10);
			}
			{
				labelMannschaftId = new JLabel("Id");
				labelMannschaftId.setBounds(11, 88, 32, 20);
				panel.add(labelMannschaftId);
			}
			{
				textFieldMannschaftId = new JTextField();
				textFieldMannschaftId.setToolTipText("Id der ausgew\u00E4hlten Mannschaft");
				textFieldMannschaftId.setEditable(false);
				textFieldMannschaftId.setBounds(47, 88, 138, 20);
				panel.add(textFieldMannschaftId);
				textFieldMannschaftId.setColumns(10);
			}
			{
				buttonFirst = new JButton("|<");
				buttonFirst.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonFirstActionPerformed(e);
					}
				});
				buttonFirst.setToolTipText("Erste Mannschaft");
				buttonFirst.setBounds(11, 123, 79, 23);
				panel.add(buttonFirst);
			}
			{
				buttonPrevious = new JButton("<<");
				buttonPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonPreviousActionPerformed(e);
					}
				});
				buttonPrevious.setToolTipText("Vorherige Mannschaft");
				buttonPrevious.setBounds(106, 123, 79, 23);
				panel.add(buttonPrevious);
			}
			{
				buttonNext = new JButton(">>");
				buttonNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonNextActionPerformed(e);
					}
				});
				buttonNext.setToolTipText("N\u00E4chste Mannschaft");
				buttonNext.setBounds(219, 123, 79, 23);
				panel.add(buttonNext);
			}
			{
				buttonLast = new JButton(">|");
				buttonLast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonLastActionPerformed(e);
					}
				});
				buttonLast.setToolTipText("Letzte Mannschaft");
				buttonLast.setBounds(316, 123, 79, 23);
				panel.add(buttonLast);
			}
			{
				labelSportart = new JLabel("Sportart");
				labelSportart.setToolTipText("");
				labelSportart.setBounds(205, 60, 89, 20);
				panel.add(labelSportart);
			}
			{
				textFieldSportartName = new JTextField();
				textFieldSportartName.setToolTipText("Sportart der ausgew\u00E4hlten Mannschaft");
				textFieldSportartName.setEditable(false);
				textFieldSportartName.setBounds(266, 60, 129, 20);
				panel.add(textFieldSportartName);
				textFieldSportartName.setColumns(10);
			}
			{
				labelSportart_1 = new JLabel("Id");
				labelSportart_1.setBounds(209, 87, 66, 23);
				panel.add(labelSportart_1);
			}
			{
				textFieldSportartId = new JTextField();
				textFieldSportartId.setToolTipText("Id der Sportart der asgew\u00E4hlten Mannschaft");
				textFieldSportartId.setEditable(false);
				textFieldSportartId.setBounds(266, 88, 129, 20);
				panel.add(textFieldSportartId);
				textFieldSportartId.setColumns(10);
			}
			{
				buttonNeu = new JButton("Neu");
				buttonNeu.setBounds(10, 157, 119, 23);
				panel.add(buttonNeu);
			}
			{
				buttonLschen = new JButton("L\u00F6schen");
				buttonLschen.setBounds(147, 157, 119, 23);
				panel.add(buttonLschen);
			}
			{
				buttonSpeichern = new JButton("Speichern");
				buttonSpeichern.setBounds(276, 157, 119, 23);
				panel.add(buttonSpeichern);
			}
		}
		{
			labelDauer = new JLabel("Dauer");
			labelDauer.setBounds(224, 51, 105, 23);
			contentPane.add(labelDauer);
		}
		{
			labelBeginn = new JLabel("Beginn");
			labelBeginn.setBounds(224, 20, 76, 20);
			contentPane.add(labelBeginn);
		}
		{
			textFieldBeginn = new JTextField();
			textFieldBeginn.setToolTipText("Geben Sie hier die Uhrzeit ein, wann das Training beginnen soll");
			textFieldBeginn.setBounds(269, 20, 115, 20);
			contentPane.add(textFieldBeginn);
			textFieldBeginn.setColumns(10);
		}
		{
			textFieldDauer = new JTextField();
			textFieldDauer.setToolTipText("Geben Sie hier an, wie lange das Training dauern soll");
			textFieldDauer.setBounds(269, 52, 115, 20);
			contentPane.add(textFieldDauer);
			textFieldDauer.setColumns(10);
		}
	}
	//Wenn Button Mannschaft suchen gedrückt -> Mannschaft wird gesucht
	protected void buttonMannschaftSuchenActionPerformed(ActionEvent e) {
		String eingabe = textFieldMannschaftSuchen.getText();
		Mannschaft mannschaftAktiv;
		boolean prüfen;
		int id;
		try {
			prüfen = mannschaftDao.eingabePruefen(eingabe);
			mannschaftAktiv = new Mannschaft();
			if(prüfen==true) {
				id = Integer.parseInt(eingabe);
				mannschaftAktiv = mannschaftDao.select(id);
			}
			else {
				mannschaftAktiv = mannschaftDao.select(eingabe);
			}
			showMannschaft(mannschaftAktiv);
		} catch (NoMannschaftFound e1) {
			showErrorPane(e1);
		}
	}

	//Erste Mannschaft anzeigen
	protected void buttonFirstActionPerformed(ActionEvent e) {
		try {
			Mannschaft mannschaftFirst = new Mannschaft();
			mannschaftFirst = mannschaftDao.first();
			showMannschaft(mannschaftFirst);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	// vorherige Mannschaft anzeigen
	protected void buttonPreviousActionPerformed(ActionEvent e) {
		Mannschaft mannschaftAktiv = new Mannschaft();
		mannschaftAktiv = create();
		try {
			Mannschaft mannschaftPrevious = mannschaftDao.previous(mannschaftAktiv);
			showMannschaft(mannschaftPrevious);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	//nächste Mannschaft anzeigen
	protected void buttonNextActionPerformed(ActionEvent e) {
		Mannschaft mannschaftAktiv = new Mannschaft();
		mannschaftAktiv = create();
		try {
			Mannschaft mannschaftNext = mannschaftDao.next(mannschaftAktiv);
			showMannschaft(mannschaftNext);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	//letzte Mannschaft anzeigen
	protected void buttonLastActionPerformed(ActionEvent e) {
		try {
			Mannschaft mannschaftLast = mannschaftDao.last();
			showMannschaft(mannschaftLast);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	//Methode showMannschaft()-> Mannschaft anzeigen lassen
	private void showMannschaft(Mannschaft mannschaft) {
		textFieldMannschaftId.setText(Integer.toString(mannschaft.getId()));
		textFieldMannschaftName.setText(mannschaft.getName());
		textFieldSportartId.setText(Integer.toString(mannschaft.getSportart().getId()));
		textFieldSportartName.setText(mannschaft.getSportart().getName());
	}

	private void showErrorPane(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}

	//Methode create() -> neue Mannschaft erstellen
	private Mannschaft create() {
		Mannschaft mannschaftAktiv = new Mannschaft();
		Sportart sportartAktiv = new Sportart();
		try{
			sportartAktiv.setId(Integer.parseInt(textFieldSportartId.getText()));
			sportartAktiv.setName(textFieldSportartName.getText());
			mannschaftAktiv.setSportart(sportartAktiv);
			mannschaftAktiv.setId(Integer.parseInt(textFieldMannschaftId.getText()));
			mannschaftAktiv.setName(textFieldMannschaftName.getText());
		}catch(NumberFormatException n){
			n.getMessage();
		}
		return mannschaftAktiv;
	}
}
