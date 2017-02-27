package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initGUI() {
		setTitle("Sportarten verwalten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 324, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(10, 11, 196, 20);
			contentPane.add(textField);
			textField.setColumns(10);
		}
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textField.getText(); 
					try {
						sportart = dao.select(name);
						showSportart(sportart);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
			textFieldName.setBounds(46, 102, 251, 20);
			contentPane.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			buttonAendern = new JButton("\u00C4nderung speichern");
			buttonAendern.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonAendernActionPerformed(e);
					sportart.setName(textFieldName.getText());
					try {
						dao.update(sportart);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonAendern.setBounds(10, 146, 141, 23);
			contentPane.add(buttonAendern);
		}
		{
			buttonHinzufuegen = new JButton("Sportart hinzuf\u00FCgen");
			buttonHinzufuegen.setBounds(153, 146, 144, 23);
			buttonHinzufuegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textFieldName.getText();
					sportart = new Sportart();
					sportart.setName(name);

					try {
						dao.insert(sportart);
						textFieldId.setText(Integer.toString(sportart.getId()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			contentPane.add(buttonHinzufuegen);
		}
		{
			buttonLoeschen = new JButton("Sportart l\u00F6schen");
			buttonLoeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sportart.setName((textFieldName.getText()));
					try {
						dao.delete(sportart);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonLoeschen.setBounds(153, 180, 144, 23);
			contentPane.add(buttonLoeschen);
		}
	}
	protected void buttonAendernActionPerformed(ActionEvent e) {
	}
	
	private void showSportart(Sportart sportart) {
		textFieldId.setText(Integer.toString(sportart.getId()));
		textFieldName.setText(sportart.getName());
		
	}
}

