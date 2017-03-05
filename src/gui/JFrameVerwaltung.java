package gui;

//Imports
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

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class JFrameVerwaltung extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button_Abmelden;
	private JLabel labelHerzlichWillkommen;
	private JTable table;

	private JButton buttonAktualisieren;
	private JButton buttonSportartVerwalten;
	private JButton buttonMannschaftenVerwalten;
	private JPanel panel;
	private JButton btnNewButtonZeitblockaendern;
	private JLabel labelBild;
	private JButton buttonTrainingVerwalten;
	private JButton buttonNewMitgliederVerwalten;
	private JButton buttonBenutzerVerwalten;

	/**
	 * Launch the application.
	 */



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameVerwaltung frame = new JFrameVerwaltung();
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
	
	//Konstruktor
	public JFrameVerwaltung() {
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
		setBounds(100, 100, 775, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		{
			{
				panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Verwaltung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				labelBild = new JLabel(new ImageIcon(JFrameVerwaltung.class.getResource("/gui/Unbenannt.png")));
				labelBild.setToolTipText("Sport");
				labelBild.setHorizontalAlignment(SwingConstants.CENTER);
				{
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
					buttonAktualisieren = new JButton("Aktualisieren");
					buttonAktualisieren.setMnemonic('A');
					buttonAktualisieren.setToolTipText("Wenn Sie hier klicken wird die Tabelle aktualisiert");
					{
						{
							buttonSportartVerwalten = new JButton("Sportarten verwalten");
							buttonSportartVerwalten.setMnemonic('S');
							buttonSportartVerwalten.setToolTipText("Hier k\u00F6nnen Sie Sportarten anzeigen lassen, hinzuf\u00FCgen, \u00E4ndern und l\u00F6schen (Alt + S)");
							{
								buttonMannschaftenVerwalten = new JButton("Mannschaften verwalten");
								buttonMannschaftenVerwalten.setMnemonic('M');
								buttonMannschaftenVerwalten.setToolTipText("Hier k\u00F6nnen Sie Mannschaften anzeigen lassen, hinzuf\u00FCgen, \u00E4ndern und l\u00F6schen (Alt + M)");

								btnNewButtonZeitblockaendern = new JButton("Zeitbl\u00F6cke verwalten");
								btnNewButtonZeitblockaendern.setToolTipText("Hier k\u00F6nnen Sie die Zeitbl\u00F6cke verwalten (Alt + Z)");
								btnNewButtonZeitblockaendern.setMnemonic('Z');
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
			labelHerzlichWillkommen.setToolTipText("Schoen, dass sie da sind!");
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Dies ist der Wochenplan, der oben ausgewaehlten Halle");
		table = new JTable(48,8);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);
 
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
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 365, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(labelHerzlichWillkommen, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)))
					.addGap(7))
		);
		
		buttonTrainingVerwalten = new JButton("Training verwalten");
		buttonTrainingVerwalten.setMnemonic('T');
		buttonTrainingVerwalten.setToolTipText("Hier k\u00F6nnen Sie ihre Training verwalten (Alt + T)");
		buttonTrainingVerwalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonTrainingVerwaltenActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonNewMitgliederVerwalten = new JButton("Mitglieder verwalten");
		buttonNewMitgliederVerwalten.setMnemonic('G');
		buttonNewMitgliederVerwalten.setToolTipText("Hier k\u00F6nnen Sie ihre Mitglieder verwalten (Alt +G)");
		buttonNewMitgliederVerwalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonNewMitgliederVerwaltenActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonBenutzerVerwalten = new JButton("Benutzer verwalten");
		buttonBenutzerVerwalten.setMnemonic('B');
		buttonBenutzerVerwalten.setToolTipText("Hier k\u00F6nnen Sie die Benutzerprofile verwalten (Alt + B)\r\n");
		buttonBenutzerVerwalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonBenutzerVerwaltenActionPerformed(e);
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
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(labelBild, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(buttonNewMitgliederVerwalten, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonTrainingVerwalten, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonSportartVerwalten, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonBenutzerVerwalten, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonMannschaftenVerwalten, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonAktualisieren, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButtonZeitblockaendern, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
							.addComponent(button_Abmelden, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(buttonAktualisieren)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButtonZeitblockaendern)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonMannschaftenVerwalten, GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonBenutzerVerwalten)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonSportartVerwalten)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonTrainingVerwalten)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonNewMitgliederVerwalten)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_Abmelden)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelBild, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);


	}

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

	public void tabellebilden() {
		String[][] tabellenanzeige = new String[48][8];
		for (int i = 0; i < tabellenanzeige.length; i++) {

		}
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

	// Wenn Button Training verwalten gedrückt -> öffne JFrame Training
	protected void buttonTrainingVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameTraining jFrameTraining = new JFrameTraining();
		jFrameTraining.setVisible(true);
	}
	// Wenn Button Mitglied verwalten gedrückt -> öffne JFrame Mitglied
	protected void buttonNewMitgliederVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameMitglied jFrameMitglied = new JFrameMitglied();
		jFrameMitglied.setVisible(true);
	}
	// Wenn Button Benutzer verwalten gedrückt -> öffne JFrame Benutzer
	protected void buttonBenutzerVerwaltenActionPerformed(ActionEvent e) throws ClassNotFoundException {
		JFrameBenutzer jFrameBenutzer = new JFrameBenutzer();
		jFrameBenutzer.setVisible(true);
	}
}
