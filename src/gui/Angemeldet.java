package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import Dao.TestRenderer;
import Dao.VereinDao;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Angemeldet extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button_Abmelden;
	private JLabel labelHerzlichWillkommen;
	private JTable table;

	private JButton buttonAktualisieren;
	private JButton buttonSportartVerwalten;
	private JButton buttonMannschaftenVerwalten;
	private JPanel panel;
	private JButton buttonVerwaltung;
	private JButton btnNewButtonZeitblockaendern;
	private JLabel labelBild;
	private JButton buttonTrainingVerwalten;

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

	// Konstruktor
	public Angemeldet() {
		try {
			initGUI();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setLocationRelativeTo(null);
	}

	private void initGUI() throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Mittelstufenprojekt\\sport.jpg"));
		setTitle("Sie sind angemeldet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		{
			{
				panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Verwaltung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				labelBild = new JLabel(new ImageIcon(Angemeldet.class.getResource("/gui/Unbenannt.png")));
				labelBild.setToolTipText("Sport");
				labelBild.setHorizontalAlignment(SwingConstants.CENTER);
				{
					// Button Abmelden
					button_Abmelden = new JButton("Abmelden");
					button_Abmelden.setVerifyInputWhenFocusTarget(false);
					button_Abmelden.setMnemonic('l');
					button_Abmelden.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								button_AbmeldenActionPerformed(e);
							} catch (ClassNotFoundException e1) {
								e1.printStackTrace();
							}
						}
					});
					button_Abmelden.setToolTipText("Hier koennen sie sich wieder abmelden");
				}
				{
					// Button Aktualisieren
					buttonAktualisieren = new JButton("Aktualisieren");
					buttonAktualisieren.setMnemonic('a');
					buttonAktualisieren.setToolTipText("Wenn Sie hier klicken wird die Tabelle aktualisiert");
					{
						{
							// Button Sportarten
							buttonSportartVerwalten = new JButton("Sportarten verwalten");
							buttonSportartVerwalten.setMnemonic('s');
							buttonSportartVerwalten.setToolTipText("Hier k\u00F6nnen Sie Sportarten anzeigen lassen, hinzuf\u00FCgen, \u00E4ndern und l\u00F6schen.");
							{
								// Button Mannschaften
								buttonMannschaftenVerwalten = new JButton("Mannschaften verwalten");
								buttonMannschaftenVerwalten.setMnemonic('m');
								buttonMannschaftenVerwalten.setToolTipText("Hier k\u00F6nnen Sie Mannschaften anzeigen lassen, hinzuf\u00FCgen, \u00E4ndern und l\u00F6schen.");

								// Button Zeitblöcke
								btnNewButtonZeitblockaendern = new JButton("Zeitbl\u00F6cke verwalten");
								btnNewButtonZeitblockaendern.setMnemonic('z');
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
								{
									// Button Verwaltung
									buttonVerwaltung = new JButton("Verwaltung");
									buttonVerwaltung.setMnemonic('v');
									buttonVerwaltung.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											try {
												buttonBenutzerVerwaltenActionPerformed(e);
											} catch (ClassNotFoundException e1) {
												e1.printStackTrace();
											}
										}
									});
								}
								
								// ActionListener zu den Button
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
			labelHerzlichWillkommen.setHorizontalAlignment(SwingConstants.CENTER);
			labelHerzlichWillkommen.setToolTipText("Schön, dass sie da sind!");
		}

		{
			new ImageIcon("Unbenannt.png");
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

		// Tabelle in ScrollPane und Festlegung der Attribute
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Dies ist der Wochenplan, der oben ausgewaehlten Halle");
		scrollPane.setBounds(10, 35, 522, 318);
		contentPane.add(scrollPane);
		JTable table;
		VereinDao dao = new VereinDao();
		table = new JTable(new DefaultTableModel(dao.getDaten(),new String[] {"Zeit", "Montag", "Dienstag", "Mittwoch","Donnerstag","Freitag","Samstag","Sonntag" }));
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setDefaultRenderer(String.class, new TestRenderer());  // TODO
		scrollPane.setViewportView(table);
//			TableModelSelfMade model = new TableModelSelfMade(dao.getDaten());
//			table.setModel(model);
		table.updateUI();
		repaint();


		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(114)
										.addComponent(labelHerzlichWillkommen, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addContainerGap()
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 603, GroupLayout.PREFERRED_SIZE)
												.addGap(28)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addGap(11)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(labelHerzlichWillkommen, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)))
												.addGap(470))
				);

		// Button Training verwalten
		buttonTrainingVerwalten = new JButton("Training verwalten");
		buttonTrainingVerwalten.setMnemonic('t');
		buttonTrainingVerwalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonTrainingVerwaltenActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(3)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(labelBild, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(button_Abmelden, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonTrainingVerwalten, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonSportartVerwalten, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonVerwaltung, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonMannschaftenVerwalten, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButtonZeitblockaendern, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonAktualisieren, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
								.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(buttonAktualisieren, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButtonZeitblockaendern, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(buttonMannschaftenVerwalten, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(buttonVerwaltung, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(buttonSportartVerwalten)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(buttonTrainingVerwalten)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_Abmelden, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(labelBild, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
//	public String[][] getTableContentArray(){
	//		String[][] tableContentArray = new String[96][8];
	//		
	//		// Zeitblöcke von DAO holen
	//		for(Block zeitblock: zeitblöcke){
	//			int column;
	//		}
	//		int start = Block.getZeitbeginn();
	//		// Start == 60
	//		int row = (start)
	//		
	//		return tableContentArray;
	//}

	// Wenn Button anmelden gedrückt, dann rufe wieder JFrame HallenPlan auf
	protected void button_AbmeldenActionPerformed(ActionEvent e) throws ClassNotFoundException { 
		HallenPlan frame1 = new HallenPlan();
		frame1.setVisible(true);
		this.dispose();
		frame1.setLocationRelativeTo(null);
	}

	// Wenn Button Sportart verwalten gedrückt, dann rufe JFrame Sportart auf
	protected void buttonSportartVerwaltenActionPerformed(ActionEvent e) {
		JFrameSportart jFrameSportart = new JFrameSportart();
		jFrameSportart.setVisible(true);
	}

	// Wenn Button Mannschaft gedrückt, dann rufe JFrame Mannschaft auf
	protected void buttonMannschaftenVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameMannschaft jFrameMannschaft = new JFrameMannschaft();
		jFrameMannschaft.setVisible(true);
	}

	// Wenn Button Training verwalten gedrückt, dann rufe JFrame Training auf
	protected void buttonTrainingVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameTraining jFrameTraining = new JFrameTraining();
		jFrameTraining.setVisible(true);
	}

	// Wenn Button Verwaaltung gedrückt, dann rufe JFrame Verwaltung auf
	protected void buttonBenutzerVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameVerwaltung jFrameVerwaltung = new JFrameVerwaltung();
		jFrameVerwaltung.setVisible(true);
	}
}
