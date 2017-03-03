package gui;

import java.awt.EventQueue;

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
import Dao.NoMannschaftFound;
import Dao.NoSportartFoundException;
import Dao.SportartDao;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

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
	private JTextField textFieldSportartName;
	private JTextField textFieldSportartId;
	private JButton buttonnderungenSpeichern;
	private JButton buttonMannschaftLschen;
	private JButton buttonMannschaftHinzufgen;

	private MannschaftDao mannschaftDao;
	private SportartDao sportartDao;

	private JButton buttonFirst;
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JButton buttonLast;
	private JTextField textFieldSportartSuchen;
	private JButton buttonSportartSuchen;
	private JLabel labelSportartSucheName;
	private JTextField textFieldSportartSucheName;
	private JLabel labelSportartSucheId;
	private JTextField textFieldSportartSucheId;
	private JPanel panel;
	private JButton buttonSportartFirst;
	private JButton buttonSportartPrevious;
	private JButton buttonSportartNext;
	private JButton buttonSportartLast;
	private JButton buttonSportartbernehmen;

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
		sportartDao = new SportartDao();
		initGUI();
	}
	private void initGUI() {
		setTitle("Mannschaften verwalten");
		setDefaultCloseOperation(JFrameMannschaft.DISPOSE_ON_CLOSE); //frame.setVisible(false)
		setBounds(100, 100, 558, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.setMnemonic('S');
			buttonSuchen.setToolTipText("Mannschaft nach Name oder Id suchen (Alt + S)");
			buttonSuchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSuchenActionPerformed(e);
				}
			});
			buttonSuchen.setBounds(178, 21, 89, 23);
			contentPane.add(buttonSuchen);
		}
		{
			textFieldSuche = new JTextField();
			textFieldSuche.setToolTipText("Geben Sie hier den Namen oder die Id der Mannschaft ein, die Sie suchen wollen");
			textFieldSuche.setBounds(10, 22, 158, 20);
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
			textFieldID.setBounds(69, 74, 118, 20);
			contentPane.add(textFieldID);
			textFieldID.setColumns(10);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setToolTipText("Name der Mannschaft");
			textFieldName.setBounds(69, 102, 217, 20);
			contentPane.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			textFieldSportartName = new JTextField();
			textFieldSportartName.setToolTipText("Name der Sportart der Mannschaft");
			textFieldSportartName.setEditable(false);
			textFieldSportartName.setBounds(69, 137, 118, 20);
			contentPane.add(textFieldSportartName);
			textFieldSportartName.setColumns(10);
		}
		{
			buttonnderungenSpeichern = new JButton("\u00C4nderungen speichern");
			buttonnderungenSpeichern.setMnemonic('�');
			buttonnderungenSpeichern.setToolTipText("\u00C4nderungen der Mannschaft speichern (Alt + \u00C4)");
			buttonnderungenSpeichern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonnderungenSpeichernActionPerformed(e);
				}
			});
			buttonnderungenSpeichern.setBounds(10, 229, 276, 23);
			contentPane.add(buttonnderungenSpeichern);
		}
		{
			buttonMannschaftLschen = new JButton("Mannschaft l\u00F6schen");
			buttonMannschaftLschen.setMnemonic('l');
			buttonMannschaftLschen.setToolTipText("ausgew\u00E4hlte Mannschaft l\u00F6schen (Alt + L)");
			buttonMannschaftLschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonMannschaftLschenActionPerformed(e);
				}
			});
			buttonMannschaftLschen.setBounds(10, 263, 276, 23);
			contentPane.add(buttonMannschaftLschen);
		}
		{
			buttonMannschaftHinzufgen = new JButton("Mannschaft hinzuf\u00FCgen");
			buttonMannschaftHinzufgen.setMnemonic('h');
			buttonMannschaftHinzufgen.setToolTipText("Neue Mannschaft hinzuf\u00FCgen (Alt + H)");
			buttonMannschaftHinzufgen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonMannschaftHinzufgenActionPerformed(e);
				}
			});
			buttonMannschaftHinzufgen.setBounds(10, 297, 276, 23);
			contentPane.add(buttonMannschaftHinzufgen);
		}
		{
			textFieldSportartId = new JTextField();
			textFieldSportartId.setToolTipText("Id der Sportart der Mannschaft");
			textFieldSportartId.setEditable(false);
			textFieldSportartId.setBounds(219, 137, 67, 20);
			contentPane.add(textFieldSportartId);
			textFieldSportartId.setColumns(10);
		}
		{
			labelSportartId = new JLabel("ID");
			labelSportartId.setBounds(197, 137, 24, 20);
			contentPane.add(labelSportartId);
		}
		{
			buttonFirst = new JButton("|<");
			buttonFirst.setToolTipText("Erste Mannschaft");
			buttonFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonFirstActionPerformed(e);
				}
			});
			buttonFirst.setBounds(10, 177, 67, 23);
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
			buttonPrevious.setBounds(79, 177, 67, 23);
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
			buttonNext.setBounds(151, 177, 67, 23);
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
			buttonLast.setBounds(219, 177, 67, 23);
			contentPane.add(buttonLast);
		}
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sportart ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(308, 124, 200, 218);
			contentPane.add(panel);
			panel.setLayout(null);
			{
				textFieldSportartSuchen = new JTextField();
				textFieldSportartSuchen.setToolTipText("Geben Sie hier den Namen oder die Id der Sportart ein, die Sie suchen wollen");
				textFieldSportartSuchen.setBounds(6, 16, 158, 20);
				panel.add(textFieldSportartSuchen);
				textFieldSportartSuchen.setColumns(10);
			}
			{
				buttonSportartSuchen = new JButton("Suchen");
				buttonSportartSuchen.setMnemonic('u');
				buttonSportartSuchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSportartSuchenActionPerformed(e);
					}
				});
				buttonSportartSuchen.setBounds(6, 47, 89, 23);
				panel.add(buttonSportartSuchen);
				buttonSportartSuchen.setToolTipText("Sportart nach Namen oder nach Id suchen (Alt + U)");
			}
			{
				labelSportartSucheName = new JLabel("Sportart");
				labelSportartSucheName.setBounds(6, 81, 86, 29);
				panel.add(labelSportartSucheName);
			}
			{
				textFieldSportartSucheName = new JTextField();
				textFieldSportartSucheName.setBounds(63, 85, 118, 20);
				panel.add(textFieldSportartSucheName);
				textFieldSportartSucheName.setToolTipText("Name der Sportart der Mannschaft");
				textFieldSportartSucheName.setEditable(false);
				textFieldSportartSucheName.setColumns(10);
			}
			{
				labelSportartSucheId = new JLabel("ID");
				labelSportartSucheId.setBounds(6, 121, 24, 20);
				panel.add(labelSportartSucheId);
			}
			{
				textFieldSportartSucheId = new JTextField();
				textFieldSportartSucheId.setBounds(63, 121, 67, 20);
				panel.add(textFieldSportartSucheId);
				textFieldSportartSucheId.setToolTipText("Id der Sportart der Mannschaft");
				textFieldSportartSucheId.setEditable(false);
				textFieldSportartSucheId.setColumns(10);
			}
			{
				buttonSportartFirst = new JButton("|<");
				buttonSportartFirst.setToolTipText("Erste Sportart");
				buttonSportartFirst.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSportartFirstActionPerformed(e);
					}
				});
				buttonSportartFirst.setBounds(6, 152, 45, 23);
				panel.add(buttonSportartFirst);
			}
			{
				buttonSportartPrevious = new JButton("<<");
				buttonSportartPrevious.setToolTipText("Vorherige Sportart");
				buttonSportartPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSportartPreviousActionPerformed(e);
					}
				});
				buttonSportartPrevious.setBounds(54, 152, 45, 23);
				panel.add(buttonSportartPrevious);
			}
			{
				buttonSportartNext = new JButton(">>");
				buttonSportartNext.setToolTipText("N\u00E4chste Sportart");
				buttonSportartNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSportartNextActionPerformed(e);
					}
				});
				buttonSportartNext.setBounds(101, 152, 45, 23);
				panel.add(buttonSportartNext);
			}
			{
				buttonSportartLast = new JButton(">|");
				buttonSportartLast.setToolTipText("Letzte Sportart");
				buttonSportartLast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSportartLastActionPerformed(e);
					}
				});
				buttonSportartLast.setBounds(145, 152, 45, 23);
				panel.add(buttonSportartLast);
			}
			{
				buttonSportartbernehmen = new JButton("Sportart \u00FCbernehmen");
				buttonSportartbernehmen.setMnemonic('�');
				buttonSportartbernehmen.setToolTipText("Die ausgew\u00E4hlte Sportart wird zu der Mannschaft hinzugef\u00FCgt (Alt + \u00DC)");
				buttonSportartbernehmen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSportartbernehmenActionPerformed(e);
					}
				});
				buttonSportartbernehmen.setBounds(6, 184, 184, 23);
				panel.add(buttonSportartbernehmen);
			}
		}
	}
	protected void buttonSuchenActionPerformed(ActionEvent e) {
		String eingabe = textFieldSuche.getText();
		Mannschaft mannschaftAktiv;
		boolean pr�fen;
		int id;
		try {
			pr�fen = mannschaftDao.eingabePruefen(eingabe);
			mannschaftAktiv = new Mannschaft();
			if(pr�fen==true) {
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
	protected void buttonnderungenSpeichernActionPerformed(ActionEvent e) {
		String name;
		String sportartName;
		String sportartId;
		Mannschaft mannschaftAktiv;
		Sportart sportart;
		int id;

		name = textFieldName.getText();
		sportartName = textFieldSportartName.getText();
		sportartId = textFieldSportartId.getText();
		mannschaftAktiv = new Mannschaft();
		sportart = new Sportart();
		
		id = Integer.parseInt(sportartId);
		sportart.setId(id);
		sportart.setName(sportartName);
		mannschaftAktiv.setName(name);
		mannschaftAktiv.setSportart(sportart);
		mannschaftDao.insert(mannschaftAktiv);
	}
	protected void buttonMannschaftLschenActionPerformed(ActionEvent e) {
		String mannschaftId;
		int id;
		
		try {
			mannschaftId = textFieldID.getText();
			id = Integer.parseInt(mannschaftId);
			mannschaftDao.delete(id);
			textFieldID.setText("");
			textFieldName.setText("");
			textFieldSportartName.setText("");
			textFieldSportartId.setText("");
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonMannschaftHinzufgenActionPerformed(ActionEvent e) {
		Mannschaft mannschaftAktiv;
		try {
			mannschaftAktiv = create();
			mannschaftDao.insert(mannschaftAktiv);
			showMannschaft(mannschaftAktiv);
			showInfoPane("Mannschaft", mannschaftAktiv.getId());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}

	}
	protected void buttonFirstActionPerformed(ActionEvent e) {
		try {
			Mannschaft mannschaftFirst = new Mannschaft();
			mannschaftFirst = mannschaftDao.first();
			showMannschaft(mannschaftFirst);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

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

	protected void buttonLastActionPerformed(ActionEvent e) {
		try {
			Mannschaft mannschaftLast = mannschaftDao.last();
			showMannschaft(mannschaftLast);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonSportartFirstActionPerformed(ActionEvent e) {
		try {
			Sportart sportartFirst = sportartDao.first();
			showSportart(sportartFirst);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void buttonSportartPreviousActionPerformed(ActionEvent e) {
		Sportart sportartAktiv = new Sportart();
		sportartAktiv.setId(Integer.parseInt(textFieldSportartSucheId.getText()));
		sportartAktiv.setName(textFieldSportartSucheName.getText());
		try {
			Sportart sportartPrevious = sportartDao.previous(sportartAktiv);
			showSportart(sportartPrevious);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void buttonSportartNextActionPerformed(ActionEvent e) {
		Sportart sportartAktiv = new Sportart();
		sportartAktiv.setId(Integer.parseInt(textFieldSportartSucheId.getText()));
		sportartAktiv.setName(textFieldSportartSucheName.getText());
		try {
			Sportart sportartNext = sportartDao.next(sportartAktiv);
			showSportart(sportartNext);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void buttonSportartLastActionPerformed(ActionEvent e) {
		try {
			Sportart sportartLast = sportartDao.last();
			showSportart(sportartLast);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void buttonSportartSuchenActionPerformed(ActionEvent e) {
		String name = textFieldSportartSuchen.getText(); 
		Sportart sportart;
		try {
			sportart = new Sportart();
			sportart = sportartDao.select(name);
			showSportart(sportart);
		} catch (NoSportartFoundException e1) {
			showErrorPane(e1);
		}
	}
	protected void buttonSportartbernehmenActionPerformed(ActionEvent e) {
		textFieldSportartName.setText(textFieldSportartSucheName.getText());
		textFieldSportartId.setText(textFieldSportartSucheId.getText());
		textFieldSportartSucheName.setText("");
		textFieldSportartSucheId.setText("");
		textFieldSportartSuchen.setText("");
	}
	
	private Mannschaft create() {
		Mannschaft mannschaftAktiv = new Mannschaft();
		Sportart sportartAktiv = new Sportart();
		sportartAktiv.setId(Integer.parseInt(textFieldSportartId.getText()));
		sportartAktiv.setName(textFieldSportartName.getText());
		mannschaftAktiv.setSportart(sportartAktiv);
		mannschaftAktiv.setId(Integer.parseInt(textFieldID.getText()));
		mannschaftAktiv.setName(textFieldName.getText());
		return mannschaftAktiv;
	}

	private void showMannschaft(Mannschaft mannschaft) {
		textFieldID.setText(Integer.toString(mannschaft.getId()));
		textFieldName.setText(mannschaft.getName());
		textFieldSportartId.setText(Integer.toString(mannschaft.getSportart().getId()));
		textFieldSportartName.setText(mannschaft.getSportart().getName());
	}

	private void showSportart(Sportart sportart) {
		textFieldSportartSucheId.setText(Integer.toString(sportart.getId()));
		textFieldSportartSucheName.setText(sportart.getName());

	}
	private void showErrorPane(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}
	private void showInfoPane(String text, int id) {
		JOptionPane.showMessageDialog(this, text +" mit der ID " +id +" wurde hinzugef�gt", "Informationsmeldung", JOptionPane.INFORMATION_MESSAGE);
	}
}