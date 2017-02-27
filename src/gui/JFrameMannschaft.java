package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import tabellenklassen.Mannschaft;
import tabellenklassen.Sportart;
import Dao.MannschaftDao;

public class JFrameMannschaft extends JFrame {

	private JPanel contentPane;
	private JButton buttonSuchen;
	private JTextField textFieldSuche;
	private JLabel labelId;
	private JLabel labelName;
	private JLabel labelSportart;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldSportart;
	private JButton buttonnderungenSpeichern;
	private JButton buttonMannschaftLschen;
	private JButton buttonMannschaftHinzufgen;

	private MannschaftDao mannschaftDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMannschaft frame = new JFrameMannschaft();
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
	public JFrameMannschaft() throws ClassNotFoundException {
		mannschaftDao = new MannschaftDao();
		initGUI();
	}
	private void initGUI() {
		setTitle("Mannschaften verwalten");
		setDefaultCloseOperation(JFrameMannschaft.DISPOSE_ON_CLOSE); //frame.setVisible(false)
		setBounds(100, 100, 342, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSuchenActionPerformed(e);
				}
			});
			buttonSuchen.setBounds(227, 21, 89, 23);
			contentPane.add(buttonSuchen);
		}
		{
			textFieldSuche = new JTextField();
			textFieldSuche.setBounds(10, 22, 207, 20);
			contentPane.add(textFieldSuche);
			textFieldSuche.setColumns(10);
		}
		{
			labelId = new JLabel("ID");
			labelId.setBounds(17, 70, 86, 29);
			contentPane.add(labelId);
		}
		{
			labelName = new JLabel("Name");
			labelName.setBounds(17, 102, 86, 29);
			contentPane.add(labelName);
		}
		{
			labelSportart = new JLabel("Sportart");
			labelSportart.setBounds(17, 137, 86, 29);
			contentPane.add(labelSportart);
		}
		{
			textFieldID = new JTextField();
			textFieldID.setEditable(false);
			textFieldID.setBounds(99, 74, 118, 20);
			contentPane.add(textFieldID);
			textFieldID.setColumns(10);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setBounds(99, 106, 217, 20);
			contentPane.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			textFieldSportart = new JTextField();
			textFieldSportart.setBounds(99, 137, 217, 20);
			contentPane.add(textFieldSportart);
			textFieldSportart.setColumns(10);
		}
		{
			buttonnderungenSpeichern = new JButton("\u00C4nderungen speichern");
			buttonnderungenSpeichern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonnderungenSpeichernActionPerformed(e);
				}
			});
			buttonnderungenSpeichern.setBounds(10, 184, 141, 23);
			contentPane.add(buttonnderungenSpeichern);
		}
		{
			buttonMannschaftLschen = new JButton("Mannschaft l\u00F6schen");
			buttonMannschaftLschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonMannschaftLschenActionPerformed(e);
				}
			});
			buttonMannschaftLschen.setBounds(162, 184, 154, 23);
			contentPane.add(buttonMannschaftLschen);
		}
		{
			buttonMannschaftHinzufgen = new JButton("Mannschaft hinzuf\u00FCgen");
			buttonMannschaftHinzufgen.setBounds(161, 218, 155, 23);
			contentPane.add(buttonMannschaftHinzufgen);
		}
	}
	protected void buttonSuchenActionPerformed(ActionEvent e) {
		String eingabe = textFieldSuche.getText();
		Mannschaft mannschaftAktiv;
		int id;
		try {
			mannschaftAktiv = new Mannschaft();
			id = Integer.parseInt(eingabe);
			mannschaftAktiv = mannschaftDao.select(id);
			showMannschaft(mannschaftAktiv);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			mannschaftDao.select(eingabe);
		}

	}
	protected void buttonnderungenSpeichernActionPerformed(ActionEvent e) {
	}
	protected void buttonMannschaftLschenActionPerformed(ActionEvent e) {
	}
	
	private void showMannschaft(Mannschaft mannschaft) {
		textFieldID.setText(Integer.toString(mannschaft.getId()));
		textFieldName.setText(mannschaft.getName());
		textFieldSportart.setText(Integer.toString(mannschaft.getSportart().getId()));
	}
}
