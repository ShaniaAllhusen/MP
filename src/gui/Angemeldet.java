package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Angemeldet extends JFrame {

	private JPanel contentPane;
	private JButton button_löschen;
	private JButton button_Mannschaft;
	private JButton buttonHierKnnenSie;
	private JButton button_speichern;
	private JComboBox comboBox;
	private JLabel labelHerzlichWillkommen;
	private JTable table;
	private JLabel labelNewLabel;

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
			button_löschen = new JButton("L\u00F6schen");
			button_löschen.setToolTipText("Mit einem Klich auf diesen Button k\u00F6nnen Sie den ausgew\u00E4hlten Datensatz l\u00F6schen");
			button_löschen.setBounds(544, 91, 205, 23);
			contentPane.add(button_löschen);
		}
		{
			button_Mannschaft = new JButton("Mannschaft hinzuf\u00FCgen/entfernen");
			button_Mannschaft.setToolTipText("Hier k\u00F6nnen Sie eine Mannschaft hinzuf\u00FCgen oder entfernen");
			button_Mannschaft.setBounds(544, 125, 205, 23);
			contentPane.add(button_Mannschaft);
		}
		{
			buttonHierKnnenSie = new JButton("Abmelden");
			buttonHierKnnenSie.setToolTipText("Hier k\u00F6nnen sie sich wieder abmelden");
			buttonHierKnnenSie.setBounds(544, 159, 205, 23);
			contentPane.add(buttonHierKnnenSie);
		}
		{
			button_speichern = new JButton("\u00C4nderungen speichern");
			button_speichern.setToolTipText("Wenn Sie hier klicken, werden Ihre \u00C4nderungen abgespeichert");
			button_speichern.setBounds(544, 57, 205, 23);
			contentPane.add(button_speichern);
		}
		{
			comboBox = new JComboBox();
			comboBox.setToolTipText("Hier k\u00F6nnen Sie die Halle ausw\u00E4hlen, von welcher Sie den Plan angezeigt bekommen wollen");
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Halle 1", "Halle 2", "Halle 3"}));
			comboBox.setBounds(10, 23, 522, 20);
			contentPane.add(comboBox);
		}
		{
			labelHerzlichWillkommen = new JLabel("Herzlich Willkommen!");
			labelHerzlichWillkommen.setHorizontalAlignment(SwingConstants.CENTER);
			labelHerzlichWillkommen.setToolTipText("Sch\u00F6n, dass sie da sind!");
			labelHerzlichWillkommen.setBounds(78, 1, 304, 23);
			contentPane.add(labelHerzlichWillkommen);
		}
		{
			table = new JTable();
			table.setToolTipText("Der Hallenplan");
			table.setBounds(10, 47, 524, 306);
			contentPane.add(table);
		}
		{
			labelNewLabel = new JLabel("New label");
			labelNewLabel.setToolTipText("Sport");
			labelNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			labelNewLabel.setIcon(new ImageIcon("F:\\Mittelstufenprojekt\\Unbenannt.png"));
			labelNewLabel.setBounds(543, 203, 206, 150);
			contentPane.add(labelNewLabel);
		}
	}
}
