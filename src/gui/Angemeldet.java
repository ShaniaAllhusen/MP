package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

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

import javax.swing.border.TitledBorder;

public class Angemeldet extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button_Abmelden;
	private JLabel labelHerzlichWillkommen;
	private JLabel labelNewLabel;
	private JTable table;

	private VereinDao vereinDao;
	private JButton buttonAktualisieren;
	private JButton buttonSportartVerwalten;
	private JButton buttonMannschaftenVerwalten;
	private JPanel panel;
	private JButton buttonNewButton;

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
		try {
			initGUI();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLocationRelativeTo(null);
	}
	
	private void initGUI() throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Mittelstufenprojekt\\sport.jpg"));
		setTitle("Sie sind angemeldet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		{
			contentPane.setLayout(null);
			{
				panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Verwaltung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(542, 5, 217, 348);
				contentPane.add(panel);
				panel.setLayout(null);
				JLabel labelBild = new JLabel(new ImageIcon(Angemeldet.class.getResource("/gui/Unbenannt.png")));
				labelBild.setBounds(5, 197, 206, 140);
				panel.add(labelBild);
				labelBild.setToolTipText("Sport");
				labelBild.setHorizontalAlignment(SwingConstants.CENTER);
				{
					button_Abmelden = new JButton("Abmelden");
					button_Abmelden.setVerifyInputWhenFocusTarget(false);
					button_Abmelden.setBounds(6, 152, 205, 23);
					panel.add(button_Abmelden);
					button_Abmelden.setMnemonic('l');
					button_Abmelden.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							button_AbmeldenActionPerformed(e);
						}
					});
					button_Abmelden.setToolTipText("Hier koennen sie sich wieder abmelden");
				}
				{
					buttonAktualisieren = new JButton("Aktualisieren");
					buttonAktualisieren.setBounds(6, 16, 205, 23);
					panel.add(buttonAktualisieren);
					buttonAktualisieren.setToolTipText("Wenn Sie hier klicken wird die Tabelle aktualisiert");
					{
						{
							buttonSportartVerwalten = new JButton("Sportarten verwalten");
							buttonSportartVerwalten.setToolTipText("Hier k\u00F6nnen Sie Sportarten anzeigen lassen, hinzuf\u00FCgen, \u00E4ndern und l\u00F6schen.");
							buttonSportartVerwalten.setBounds(6, 152, 205, 23);

							panel.add(buttonSportartVerwalten);
							{
								buttonMannschaftenVerwalten = new JButton("Mannschaften verwalten");
								buttonMannschaftenVerwalten.setToolTipText("Hier k\u00F6nnen Sie Mannschaften anzeigen lassen, hinzuf\u00FCgen, \u00E4ndern und l\u00F6schen.");
								buttonMannschaftenVerwalten.setBounds(6, 84, 205, 23);
								panel.add(buttonMannschaftenVerwalten);

								JButton btnNewButtonZeitblockaendern = new JButton("Zeitbl\u00F6cke verwalten");
								btnNewButtonZeitblockaendern.addActionListener(new ActionListener() {
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
								btnNewButtonZeitblockaendern.setBounds(6, 50, 205, 23);
								panel.add(btnNewButtonZeitblockaendern);
								{
									buttonNewButton = new JButton("Sportart verwalten");
									buttonNewButton.setBounds(5, 118, 206, 23);
									panel.add(buttonNewButton);
								}
								buttonMannschaftenVerwalten.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											buttonMannschaftenVerwaltenActionPerformed(e);
										} catch (ClassNotFoundException e1) {
											e1.printStackTrace();
										}
									}
								});
							}
							buttonSportartVerwalten.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									buttonSportartVerwaltenActionPerformed(e);
								}
							});
						}
					}
					buttonAktualisieren.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table.updateUI();
						}
					});
				}
			}
		}
		{
			labelHerzlichWillkommen = new JLabel("Herzlich Willkommen!");
			labelHerzlichWillkommen.setBounds(119, 1, 304, 23);
			labelHerzlichWillkommen.setHorizontalAlignment(SwingConstants.CENTER);
			labelHerzlichWillkommen.setToolTipText("Schoen, dass sie da sind!");
			contentPane.add(labelHerzlichWillkommen);
		}

		{
			Icon _icon = new ImageIcon("Unbenannt.png");
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Dies ist der Wochenplan, der oben ausgewaehlten Halle");
		scrollPane.setBounds(10, 35, 522, 318);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setDefaultRenderer(String.class, new TestRenderer());  // TODO
		scrollPane.setViewportView(table);
		String[] columns = new String[] {
				"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
		};
	
		
	//	String[][] tableContent = getTableContentArray(); 
		
	//	table.setModel(new DefaultTableModel(tableContent, tableContent.length));
		table.updateUI();
		repaint();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
	}
	
	// Wenn Button anmelden gedr�ckt, dann rufe wieder JFrame HallenPlan auf
	protected void button_AbmeldenActionPerformed(ActionEvent e) { 
		HallenPlan frame1 = new HallenPlan();
		frame1.setVisible(true);
		this.dispose();
		frame1.setLocationRelativeTo(null);
	}

	// Wenn Button Sportart verwalten gedr�ckt, dann rufe JFrame Sportart auf
	protected void buttonSportartVerwaltenActionPerformed(ActionEvent e) {
		JFrameSportart jFrameSportart = new JFrameSportart();
		jFrameSportart.setVisible(true);
	}
	
	// Wenn Button Mannschaft gedr�ckt, dann rufe JFrame Mannschaft auf
	protected void buttonMannschaftenVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameMannschaft jFrameMannschaft = new JFrameMannschaft();
		jFrameMannschaft.setVisible(true);
	}
	

//	public String[][] getTableContentArray(){
//		String[][] tableContentArray = new String[96][8];
//		
//		// Zeitbl�cke von DAO holen
//		for(Block zeitblock: zeitbl�cke){
//			int column;
//		}
//		int start = Block.getZeitbeginn();
//		// Start == 60
//		int row = (start)
//		
//		return tableContentArray;
	//}
}
