package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.LineBorder;

import tabellenklassen.Training;
import Dao.VereinDao;

import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Angemeldet extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button_loeschen;
	private JButton button_Mannschaft;
	private JButton button_Abmelden;
	private JButton button_speichern;
	private JComboBox<String> comboBox_Halle;
	private JLabel labelHerzlichWillkommen;
	private JLabel labelNewLabel;
	private JTable table;

	private VereinDao vereinDao;
	private JButton btnZeitBloeckeAendern;

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
		contentPane.setLayout(null);
		{
			button_loeschen = new JButton("L\u00F6schen");
			button_loeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_loeschenActionPerformed(e);
				}
			});
			button_loeschen.setToolTipText("Mit einem Klick auf diesen Button k\u00F6nnen Sie den ausgew\u00E4hlten Datensatz l\u00F6schen");
			button_loeschen.setBounds(544, 91, 205, 23);
			contentPane.add(button_loeschen);
		}
		{
			button_Mannschaft = new JButton("?");
			button_Mannschaft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_MannschaftActionPerformed(e);
				}
			});
			button_Mannschaft.setToolTipText("Hier k\u00F6nnen Sie eine Mannschaft hinzuf\u00FCgen oder entfernen");
			button_Mannschaft.setBounds(544, 125, 205, 23);
			contentPane.add(button_Mannschaft);
		}
		{
			button_Abmelden = new JButton("Abmelden");
			button_Abmelden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button_AbmeldenActionPerformed(e);
				}
			});
			button_Abmelden.setToolTipText("Hier k\u00F6nnen sie sich wieder abmelden");
			button_Abmelden.setBounds(544, 159, 205, 23);
			contentPane.add(button_Abmelden);
		}
		{
			button_speichern = new JButton("\u00C4nderungen speichern");
			button_speichern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//					button_speichernActionPerformed(e);
				}
			});
			button_speichern.setToolTipText("Wenn Sie hier klicken, werden Ihre \u00C4nderungen abgespeichert");
			button_speichern.setBounds(544, 57, 205, 23);
			contentPane.add(button_speichern);
		}
		{
			comboBox_Halle = new JComboBox<String>();
			comboBox_Halle.setToolTipText("Hier k\u00F6nnen Sie die Halle ausw\u00E4hlen, von welcher Sie den Plan angezeigt bekommen wollen");
			comboBox_Halle.setModel(new DefaultComboBoxModel<String>(new String[] {"Halle 1", "Halle 2", "Halle 3"}));
			comboBox_Halle.setBounds(10, 23, 522, 20);
			contentPane.add(comboBox_Halle);
		}
		{
			labelHerzlichWillkommen = new JLabel("Herzlich Willkommen!");
			labelHerzlichWillkommen.setHorizontalAlignment(SwingConstants.CENTER);
			labelHerzlichWillkommen.setToolTipText("Sch\u00F6n, dass sie da sind!");
			labelHerzlichWillkommen.setBounds(78, 1, 304, 23);
			contentPane.add(labelHerzlichWillkommen);
		}
		
		{

			Icon _icon = new ImageIcon("Unbenannt.png");
			JLabel labelBild = new JLabel(new ImageIcon("F:\\workspace\\MP\\src\\gui\\Unbenannt.png"));
			labelBild.setToolTipText("Sport");
			labelBild.setHorizontalAlignment(SwingConstants.CENTER);
			//labelNewLabel.setIcon(new ImageIcon("Unbenannt.png"));
			labelBild.setBounds(543, 203, 206, 150);
			contentPane.add(labelBild);
		}
		{
			
			String[] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
			String[][] data = new String[7][22];
			String test = null;
			ArrayList <Training> ArrayListTraining = new ArrayList<Training>();
			for (int i = 0; i < 7; i++) {
				for ( int j = 8; j <= 22; j++){
					ArrayListTraining = vereinDao.select(comboBox_Halle.getSelectedIndex(),columnName[i], j);
					for(Training t : ArrayListTraining) {
						   test = t.getMannschaft().getName() + t.getMannschaft().getSportart().getName();
						}
					data[i][j] = test;
				}
			}
		}
		{
			btnZeitBloeckeAendern = new JButton("ZeitBl\u00F6cke \u00C4ndern");
			btnZeitBloeckeAendern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								ZeitBloecke frame = new ZeitBloecke();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
			btnZeitBloeckeAendern.setBounds(544, 23, 205, 23);
			contentPane.add(btnZeitBloeckeAendern);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 522, 299);
		contentPane.add(scrollPane);
		table = new JTable(data, columnName);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Zeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
			}
		));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
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

	protected void button_loeschenActionPerformed(ActionEvent e) { //Delete		

	}

	protected void button_MannschaftActionPerformed(ActionEvent e) { //Update

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
}
