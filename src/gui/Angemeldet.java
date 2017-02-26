package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

import tabellenklassen.Training;
import Dao.VereinDao;

import java.awt.Color;

public class Angemeldet extends JFrame {

	private JPanel contentPane;
	private JButton button_loeschen;
	private JButton button_Abmelden;
	private JButton button_speichern;
	private JComboBox comboBox_Halle;
	private JLabel labelHerzlichWillkommen;
	private JLabel labelNewLabel;
	private JTable table;

	private VereinDao vereinDao;
	private JButton buttonAktualisieren;
	private JButton buttonHinzufuegen;
	private JButton buttonMannschaftenVerwalten;
	private JButton buttonSportart;

	/**
	 * Launch the application.
	 */



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Angemeldet frame = new Angemeldet();
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
	public Angemeldet() {
		initGUI();
		this.setLocationRelativeTo(null);
	}
	private void initGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Mittelstufenprojekt\\sport.jpg"));
		setTitle("Sie sind angemeldet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		{
			button_loeschen = new JButton("Loeschen");
			button_loeschen.setBounds(542, 90, 205, 23);
			button_loeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_löschenActionPerformed(e);
				}
			});
			contentPane.setLayout(null);
			button_loeschen.setToolTipText("Mit einem Klick auf diesen Button koennen Sie den ausgewaehlten Datensatz loeschen");
			contentPane.add(button_loeschen);
		}
		{
			button_Abmelden = new JButton("Abmelden");
			button_Abmelden.setMnemonic('l');
			button_Abmelden.setBounds(542, 158, 205, 23);
			button_Abmelden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_AbmeldenActionPerformed(e);
				}
			});
			button_Abmelden.setToolTipText("Hier koennen sie sich wieder abmelden");
			contentPane.add(button_Abmelden);
		}
		{
			button_speichern = new JButton("\u00C4nderungen speichern");
			button_speichern.setBounds(542, 124, 205, 23);
			button_speichern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//					button_speichernActionPerformed(e);
				}
			});
			button_speichern.setToolTipText("Wenn Sie hier klicken, werden Ihre Aenderungen abgespeichert");
			contentPane.add(button_speichern);
		}
		{
			comboBox_Halle = new JComboBox();
			comboBox_Halle.setBounds(10, 23, 522, 20);
			comboBox_Halle.setToolTipText("Hier koennen Sie die Halle auswaehlen, von welcher Sie den Plan angezeigt bekommen wollen");
			comboBox_Halle.setModel(new DefaultComboBoxModel(new String[] {"Halle 1", "Halle 2", "Halle 3"}));
			comboBox_Halle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			contentPane.add(comboBox_Halle);
		}
		{
			labelHerzlichWillkommen = new JLabel("Herzlich Willkommen!");
			labelHerzlichWillkommen.setBounds(78, 1, 304, 23);
			labelHerzlichWillkommen.setHorizontalAlignment(SwingConstants.CENTER);
			labelHerzlichWillkommen.setToolTipText("Sch\u00F6n, dass sie da sind!");
			contentPane.add(labelHerzlichWillkommen);
		}

		{

			Icon _icon = new ImageIcon("Unbenannt.png");
			JLabel labelBild = new JLabel(new ImageIcon(Angemeldet.class.getResource("/gui/Unbenannt.png")));
			labelBild.setBounds(541, 256, 206, 97);
			labelBild.setToolTipText("Sport");
			labelBild.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(labelBild);
		}
		{


			//			String[] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
			//			String[][] data = new String[7][22];
			//			String test = null;
			//			ArrayList <Training> ArrayListTraining = new ArrayList<Training>();
			//			for (int i = 0; i < 7; i++) {
			//				for ( int j = 8; j <= 22; j++){
			//					ArrayListTraining = vereinDao.select(comboBox_Halle.getSelectedIndex(),columnName[i], j);
			//					for(Training t : ArrayListTraining) {
			//						test = t.getMannschaft().getName() + t.getMannschaft().getSportart().getName();
			//					}
			//					data[i][j] = test;
			//				}
			//			}

		}
		{
			buttonAktualisieren = new JButton("Aktualisieren");
			buttonAktualisieren.setToolTipText("Wenn Sie hier klicken wird die Tabelle aktualisiert");
			buttonAktualisieren.setBounds(544, 22, 205, 23);
			buttonAktualisieren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(comboBox_Halle.getSelectedIndex()+1);
					anzeigenJTable();
				}
			});
			contentPane.add(buttonAktualisieren);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Dies ist der Wochenplan, der oben ausgewaehlten Halle");
		scrollPane.setBounds(10, 54, 522, 299);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columns = new String[] {
				"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
		};
		try {
			table.setModel(new DefaultTableModel(new VereinDao().getTable(), columns));
			table.updateUI();
			repaint();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		{
			buttonHinzufuegen = new JButton("Hinzufuegen");
			buttonHinzufuegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonHinzufuegenActionPerformed(e);
				}
			});
			buttonHinzufuegen.setBounds(542, 56, 205, 23);
			contentPane.add(buttonHinzufuegen);
		}
		{
			buttonMannschaftenVerwalten = new JButton("Mannschaften verwalten");
			buttonMannschaftenVerwalten.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						buttonMannschaftenVerwaltenActionPerformed(e);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonMannschaftenVerwalten.setBounds(542, 192, 205, 23);
			contentPane.add(buttonMannschaftenVerwalten);
		}
		{
			buttonSportart = new JButton("Sportarten verwalten");
			buttonSportart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrameSportart jFrameSportart = new JFrameSportart();
					jFrameSportart.setVisible(true);
				}
			});
			buttonSportart.setBounds(542, 226, 205, 23);
			contentPane.add(buttonSportart);
		}
	}
	//	protected void button_speichernActionPerformed(VereinDao vereinDao, Training training) throws Exception { //Update
	//		ArrayList<Training> arrayListTraining = VereinDao.select(vereinDao);
	//		try{
	//			Training = Dao.VereinDao.select();
	//			
	//			
	//		}catch(Exception d){
	//			showErrorPane(d);
	//		}
	//	
	//	}
	public void anzeigenJTable()  { //JTable füllen
		String[][] data = new String[7][22];
		String[] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
		String test = null;
		ArrayList <Training> ArrayListTraining = new ArrayList<Training>();
		for (int i = 0; i < 7; i++) {
			for ( int j = 8; j <= 22; j++){
				ArrayListTraining = vereinDao.select(comboBox_Halle.getSelectedIndex()+1,columnName[i], j);
				for(Training t : ArrayListTraining) {
					test = t.getMannschaft().getName() + t.getMannschaft().getSportart().getName();
				}
				data[i][j] = test;
			}
		}
		table = new JTable(data, columnName);

	}

	protected void button_löschenActionPerformed(ActionEvent e) { //Delete		

	}

	protected void button_AbmeldenActionPerformed(ActionEvent e) { //Ende GUI 1 anzeigen
		HallenPlan frame1 = new HallenPlan();
		frame1.setVisible(true);
		this.dispose();
		frame1.setLocationRelativeTo(null);
	}

	public JTable getTable() {
		return table;
	}
	protected void buttonHinzufuegenActionPerformed(ActionEvent e) {

	}
	protected void buttonMannschaftenVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameMannschaft jFrameMannschaft = new JFrameMannschaft();
		jFrameMannschaft.setVisible(true);
	}
}
