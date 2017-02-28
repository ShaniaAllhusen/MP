package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import tabellenklassen.Sportart;
import Dao.SportartDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameSportartAuswählen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSuchen;
	private JButton buttonSuchen;
	private JTextField textFieldName;
	private JTextField textFieldId;
	private JLabel labelId;
	private JLabel labelName;
	private JButton buttonFirst;
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JButton buttonLast;
	
	private SportartDao dao;
	private JButton buttonSportartUebernehmen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameSportartAuswählen frame = new JFrameSportartAuswählen();
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
	public JFrameSportartAuswählen() {
		initGUI();
		try {
			dao = new SportartDao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initGUI() {
		setTitle("Sportart ausw\u00E4hlen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 284, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			textFieldSuchen = new JTextField();
			textFieldSuchen.setToolTipText("Geben Sie hier den Namen der Sportart ein, die Sie suchen wollen");
			textFieldSuchen.setBounds(10, 22, 147, 20);
			contentPane.add(textFieldSuchen);
			textFieldSuchen.setColumns(10);
		}
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.setToolTipText("Sportart suchen");
			buttonSuchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSuchenActionPerformed(e);
				}
			});
			buttonSuchen.setBounds(167, 21, 89, 23);
			contentPane.add(buttonSuchen);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setToolTipText("Name der Sportart");
			textFieldName.setEditable(false);
			textFieldName.setBounds(76, 115, 180, 20);
			contentPane.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			textFieldId = new JTextField();
			textFieldId.setToolTipText("ID der Sportart");
			textFieldId.setEditable(false);
			textFieldId.setBounds(76, 84, 81, 20);
			contentPane.add(textFieldId);
			textFieldId.setColumns(10);
		}
		{
			labelId = new JLabel("Id");
			labelId.setBounds(20, 87, 46, 14);
			contentPane.add(labelId);
		}
		{
			labelName = new JLabel("Name");
			labelName.setBounds(20, 118, 46, 14);
			contentPane.add(labelName);
		}
		{
			buttonFirst = new JButton("|<");
			buttonFirst.setToolTipText("Erste Sportart");
			buttonFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonFirstActionPerformed(e);
				}
			});
			buttonFirst.setBounds(10, 166, 51, 23);
			contentPane.add(buttonFirst);
		}
		{
			buttonPrevious = new JButton("<<");
			buttonPrevious.setToolTipText("Vorherige Sportart");
			buttonPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonPreviousActionPerformed(e);
				}
			});
			buttonPrevious.setBounds(76, 166, 51, 23);
			contentPane.add(buttonPrevious);
		}
		{
			buttonNext = new JButton(">>");
			buttonNext.setToolTipText("N\u00E4chste Sportart");
			buttonNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonNextActionPerformed(e);
				}
			});
			buttonNext.setBounds(144, 166, 51, 23);
			contentPane.add(buttonNext);
		}
		{
			buttonLast = new JButton(">|");
			buttonLast.setToolTipText("Letzte Sportart");
			buttonLast.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonLastActionPerformed(e);
				}
			});
			buttonLast.setBounds(205, 166, 51, 23);
			contentPane.add(buttonLast);
		}
		{
			buttonSportartUebernehmen = new JButton("Sportart \u00FCbernehmen");
			buttonSportartUebernehmen.setToolTipText("Die ausgew\u00E4hlte Sportart \u00FCbernehmen");
			buttonSportartUebernehmen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSportartUebernehmenActionPerformed(e);
				}
			});
			buttonSportartUebernehmen.setBounds(10, 200, 246, 23);
			contentPane.add(buttonSportartUebernehmen);
		}
	}

	protected void buttonSuchenActionPerformed(ActionEvent e) {
		String name = textFieldSuchen.getText(); 
		Sportart sportart;
		try {
			sportart = new Sportart();
			sportart = dao.select(name);
			showSportart(sportart);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void showSportart(Sportart sportart) {
		textFieldId.setText(Integer.toString(sportart.getId()));
		textFieldName.setText(sportart.getName());
		
	}
	protected void buttonFirstActionPerformed(ActionEvent e) {
		try {
			Sportart sportartFirst = dao.first();
			showSportart(sportartFirst);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	protected void buttonPreviousActionPerformed(ActionEvent e) {
		Sportart sportartAktiv = new Sportart();
		sportartAktiv.setId(Integer.parseInt(textFieldId.getText()));
		sportartAktiv.setName(textFieldName.getText());
		try {
			Sportart sportartPrevious = dao.previous(sportartAktiv);
			showSportart(sportartPrevious);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void buttonNextActionPerformed(ActionEvent e) {
		Sportart sportartAktiv = new Sportart();
		sportartAktiv.setId(Integer.parseInt(textFieldId.getText()));
		sportartAktiv.setName(textFieldName.getText());
		try {
			Sportart sportartNext = dao.next(sportartAktiv);
			showSportart(sportartNext);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void buttonLastActionPerformed(ActionEvent e) {
		try {
			Sportart sportartLast = dao.last();
			showSportart(sportartLast);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	protected void buttonSportartUebernehmenActionPerformed(ActionEvent e) {
		
	}
}
