package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import Dao.NoSportartFoundException;
import Dao.SportartDao;
import tabellenklassen.Sportart;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameSportart extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton buttonSuchen;
	private JLabel labelNewLabel;
	private JTextField textFieldId;
	private JLabel labelName;
	private JTextField textFieldName;
	private JButton buttonAendern;
	private JButton buttonHinzufuegen;
	private JButton buttonLoeschen;

	private SportartDao dao;
	private Sportart sportart;
	private JButton buttonFirst;
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JButton buttonLast;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameSportart frame = new JFrameSportart();
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
	public JFrameSportart() {
		initGUI();
		try {
			dao = new SportartDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void initGUI() {
		setTitle("Sportarten verwalten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			textField = new JTextField();
			textField.setToolTipText("Geben Sie hier den Namen oder die Id der Sportart ein, die Sie suchen wollen");
			textField.setBounds(10, 11, 196, 20);
			contentPane.add(textField);
			textField.setColumns(10);
		}
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.setMnemonic('S');
			buttonSuchen.setToolTipText("Sportart nach Namen oder Id suchen (Alt + S)");
			buttonSuchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String eingabe = textField.getText();
				Sportart sportartAktiv;
				boolean prüfen;
				int id;
				try {
					prüfen = dao.eingabePruefen(eingabe);
					sportartAktiv = new Sportart();
					if(prüfen==true) {
						id = Integer.parseInt(eingabe);
						sportartAktiv = dao.select(id);
					}
					else {
						sportartAktiv = dao.select(eingabe);
					}
					showSportart(sportartAktiv);
				} catch (NoSportartFoundException e1) {
					showErrorPane(e1);
				}

				}
			});
			buttonSuchen.setBounds(216, 10, 89, 23);
			contentPane.add(buttonSuchen);
		}
		{
			labelNewLabel = new JLabel("ID");
			labelNewLabel.setBounds(10, 65, 22, 14);
			contentPane.add(labelNewLabel);
		}
		{
			textFieldId = new JTextField();
			textFieldId.setToolTipText("ID der Sportart");
			textFieldId.setEditable(false);
			textFieldId.setBounds(46, 62, 105, 20);
			contentPane.add(textFieldId);
			textFieldId.setColumns(10);
		}
		{
			labelName = new JLabel("Name");
			labelName.setBounds(10, 105, 46, 14);
			contentPane.add(labelName);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setToolTipText("Name der Sportart");
			textFieldName.setBounds(46, 102, 251, 20);
			contentPane.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			buttonAendern = new JButton("\u00C4nderung speichern");
			buttonAendern.setMnemonic('Ä');
			buttonAendern.setToolTipText("\u00C4nderungen an der Sportart speichern (Alt + \u00C4)");
			buttonAendern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonAendernActionPerformed(e);
					sportart.setName(textFieldName.getText());
					try {
						dao.update(sportart);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			buttonAendern.setBounds(315, 10, 141, 23);
			contentPane.add(buttonAendern);
		}
		{
			buttonHinzufuegen = new JButton("Sportart hinzuf\u00FCgen");
			buttonHinzufuegen.setMnemonic('h');
			buttonHinzufuegen.setToolTipText("Eine neue Sportart hinzuf\u00FCgen (Alt + H)");
			buttonHinzufuegen.setBounds(315, 61, 141, 23);
			buttonHinzufuegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textFieldName.getText();
					sportart = new Sportart();
					sportart.setName(name);

					try {
						dao.insert(sportart);
						textFieldId.setText(Integer.toString(sportart.getId()));
						showInfoPane("Sportart", sportart.getId());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			contentPane.add(buttonHinzufuegen);
		}
		{
			buttonLoeschen = new JButton("Sportart l\u00F6schen");
			buttonLoeschen.setMnemonic('l');
			buttonLoeschen.setToolTipText("Die ausgew\u00E4hlte Sportart l\u00F6schen (Alt + L)");
			buttonLoeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sportart.setName((textFieldName.getText()));
					try {
						dao.delete(sportart);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			buttonLoeschen.setBounds(315, 101, 141, 23);
			contentPane.add(buttonLoeschen);
		}
		{
			buttonFirst = new JButton("|<");
			buttonFirst.setToolTipText("Erste Sportart");
			buttonFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonFirstActionPerformed(e);
				}
			});
			buttonFirst.setBounds(20, 130, 62, 23);
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
			buttonPrevious.setBounds(89, 130, 62, 23);
			contentPane.add(buttonPrevious);
		}
		{
			buttonNext = new JButton(">>");
			buttonNext.setToolTipText("N\u00E4chste Sportart");
			buttonNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Sportart sportartAktiv = new Sportart();
					sportartAktiv.setId(Integer.parseInt(textFieldId.getText()));
					sportartAktiv.setName(textFieldName.getText());
					try {
						Sportart sportartNext = dao.next(sportartAktiv);
						showSportart(sportartNext);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			buttonNext.setBounds(163, 130, 62, 23);
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
			buttonLast.setBounds(235, 130, 62, 23);
			contentPane.add(buttonLast);
		}
	}
	protected void buttonAendernActionPerformed(ActionEvent e) {
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
			e1.printStackTrace();
		}
	}
	protected void buttonLastActionPerformed(ActionEvent e) {
		try {
			Sportart sportartLast = dao.last();
			showSportart(sportartLast);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void showErrorPane(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}
	
	private void showInfoPane(String text, int id) {
		JOptionPane.showMessageDialog(this, text +" mit der ID " +id +" wurde hinzugefügt", "Informationsmeldung", JOptionPane.INFORMATION_MESSAGE);
	}
}


