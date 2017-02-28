package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private JLabel labelSportartId;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldSportart;
	private JTextField textFieldSportartId;
	private JButton buttonnderungenSpeichern;
	private JButton buttonMannschaftLschen;
	private JButton buttonMannschaftHinzufgen;
	private JButton buttonSportartAuswhlen;
	
	private MannschaftDao mannschaftDao;
	private JButton buttonFirst;
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JButton buttonLast;
	
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
		setBounds(100, 100, 537, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.setToolTipText("Mannschaft suchen");
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
			textFieldSuche.setToolTipText("Geben Sie hier den Namen oder die Id der Mannschaft ein, die Sie suchen wollen");
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
			textFieldID.setToolTipText("Id der Mannschaft");
			textFieldID.setEditable(false);
			textFieldID.setBounds(99, 74, 118, 20);
			contentPane.add(textFieldID);
			textFieldID.setColumns(10);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setToolTipText("Name der Mannschaft");
			textFieldName.setBounds(99, 106, 217, 20);
			contentPane.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			textFieldSportart = new JTextField();
			textFieldSportart.setToolTipText("Name der Sportart der Mannschaft");
			textFieldSportart.setEditable(false);
			textFieldSportart.setBounds(99, 137, 118, 20);
			contentPane.add(textFieldSportart);
			textFieldSportart.setColumns(10);
		}
		{
			buttonnderungenSpeichern = new JButton("\u00C4nderungen speichern");
			buttonnderungenSpeichern.setToolTipText("\u00C4nderungen der Mannschaft speichern");
			buttonnderungenSpeichern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonnderungenSpeichernActionPerformed(e);
				}
			});
			buttonnderungenSpeichern.setBounds(340, 140, 171, 23);
			contentPane.add(buttonnderungenSpeichern);
		}
		{
			buttonMannschaftLschen = new JButton("Mannschaft l\u00F6schen");
			buttonMannschaftLschen.setToolTipText("ausgew\u00E4hlte Mannschaft l\u00F6schen");
			buttonMannschaftLschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonMannschaftLschenActionPerformed(e);
				}
			});
			buttonMannschaftLschen.setBounds(340, 108, 171, 23);
			contentPane.add(buttonMannschaftLschen);
		}
		{
			buttonMannschaftHinzufgen = new JButton("Mannschaft hinzuf\u00FCgen");
			buttonMannschaftHinzufgen.setToolTipText("Neue Mannschaft hinzuf\u00FCgen");
			buttonMannschaftHinzufgen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonMannschaftHinzufgenActionPerformed(e);
				}
			});
			buttonMannschaftHinzufgen.setBounds(339, 76, 172, 23);
			contentPane.add(buttonMannschaftHinzufgen);
		}
		{
			textFieldSportartId = new JTextField();
			textFieldSportartId.setToolTipText("Id der Sportart der Mannschaft");
			textFieldSportartId.setEditable(false);
			textFieldSportartId.setBounds(249, 137, 67, 20);
			contentPane.add(textFieldSportartId);
			textFieldSportartId.setColumns(10);
		}
		{
			labelSportartId = new JLabel("ID");
			labelSportartId.setBounds(227, 137, 24, 20);
			contentPane.add(labelSportartId);
		}
		{
			buttonSportartAuswhlen = new JButton("Sportart ausw\u00E4hlen");
			buttonSportartAuswhlen.setToolTipText("Sportart ausw\u00E4hlen");
			buttonSportartAuswhlen.setBounds(99, 168, 217, 23);
			contentPane.add(buttonSportartAuswhlen);
		}
		{
			buttonFirst = new JButton("|<");
			buttonFirst.setToolTipText("Erste Mannschaft");
			buttonFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonFirstActionPerformed(e);
				}
			});
			buttonFirst.setBounds(14, 220, 67, 23);
			contentPane.add(buttonFirst);
		}
		{
			buttonPrevious = new JButton("<<");
			buttonPrevious.setToolTipText("Vorherige Mannschaft");
			buttonPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonPreviousActionPerformed(e);
				}
			});
			buttonPrevious.setBounds(91, 220, 67, 23);
			contentPane.add(buttonPrevious);
		}
		{
			buttonNext = new JButton(">>");
			buttonNext.setToolTipText("N\u00E4chste Mannschaft");
			buttonNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonNextActionPerformed(e);
				}
			});
			buttonNext.setBounds(168, 220, 67, 23);
			contentPane.add(buttonNext);
		}
		{
			buttonLast = new JButton(">|");
			buttonLast.setToolTipText("Letzte Mannschaft");
			buttonLast.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonLastActionPerformed(e);
				}
			});
			buttonLast.setBounds(249, 220, 67, 23);
			contentPane.add(buttonLast);
		}
	}
	protected void buttonSuchenActionPerformed(ActionEvent e) {
		String eingabe = textFieldSuche.getText();
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
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}

	}
	protected void buttonnderungenSpeichernActionPerformed(ActionEvent e) {
	}
	protected void buttonMannschaftLschenActionPerformed(ActionEvent e) {
	}
	
	protected void buttonMannschaftHinzufgenActionPerformed(ActionEvent e) {
		String name;
		String sportartName;
		String sportartId;
		Mannschaft mannschaftAktiv;
		Sportart sportart;
		boolean prüfen;
		int id;
		
		try {
			name = textFieldName.getText();
			sportartName = textFieldSportart.getText();
			sportartId = textFieldSportartId.getText();
			mannschaftAktiv = new Mannschaft();
			sportart = new Sportart();
			prüfen = mannschaftDao.eingabePruefen(sportartId);
			
			if(prüfen == true) {
				id = Integer.parseInt(sportartId);
				sportart.setId(id);
				sportart.setName(sportartName);
				mannschaftAktiv.setName(name);
				mannschaftAktiv.setSportart(sportart);
				mannschaftDao.insert(mannschaftAktiv);
				showMannschaft(mannschaftAktiv);
			}
			else {
				JOptionPane.showMessageDialog(this, "Falsche Eingaben", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	protected void buttonFirstActionPerformed(ActionEvent e) {
		try {
			Mannschaft mannschaftFirst = new Mannschaft();
			mannschaftFirst = mannschaftDao.first();
			showMannschaft(mannschaftFirst);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void buttonPreviousActionPerformed(ActionEvent e) {
		Mannschaft mannschaftAktiv = new Mannschaft();
		mannschaftAktiv.setId(Integer.parseInt(textFieldID.getText()));
		mannschaftAktiv.setName(textFieldName.getText());
		try {
			Mannschaft mannschaftPrevious = mannschaftDao.previous(mannschaftAktiv);
			showMannschaft(mannschaftPrevious);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void buttonNextActionPerformed(ActionEvent e) {
		Mannschaft mannschaftAktiv = new Mannschaft();
		mannschaftAktiv.setId(Integer.parseInt(textFieldID.getText()));
		mannschaftAktiv.setName(textFieldName.getText());
		try {
			Mannschaft mannschaftNext = mannschaftDao.next(mannschaftAktiv);
			showMannschaft(mannschaftNext);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void buttonLastActionPerformed(ActionEvent e) {
		try {
			Mannschaft mannschaftLast = mannschaftDao.last();
			showMannschaft(mannschaftLast);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void showMannschaft(Mannschaft mannschaft) {
		textFieldID.setText(Integer.toString(mannschaft.getId()));
		textFieldName.setText(mannschaft.getName());
		textFieldSportartId.setText(Integer.toString(mannschaft.getSportart().getId()));
		textFieldSportart.setText(mannschaft.getSportart().getName());
	}
}