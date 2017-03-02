package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JFrameBenutzer extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSuchen;
	private JButton buttonSuchen;
	private JButton buttonNewButton;
	private JButton buttonHinzufuegen;
	private JButton buttonSportartLschen;
	private JLabel labelId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameBenutzer frame = new JFrameBenutzer();
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
	public JFrameBenutzer() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			textFieldSuchen = new JTextField();
			textFieldSuchen.setBounds(10, 11, 170, 20);
			contentPane.add(textFieldSuchen);
			textFieldSuchen.setColumns(10);
		}
		{
			buttonSuchen = new JButton("Suchen");
			buttonSuchen.setBounds(190, 10, 89, 23);
			contentPane.add(buttonSuchen);
		}
		{
			buttonNewButton = new JButton("\u00C4nderung speichern");
			buttonNewButton.setBounds(289, 10, 135, 23);
			contentPane.add(buttonNewButton);
		}
		{
			buttonHinzufuegen = new JButton("Sportart hinzuf\u00FCgen");
			buttonHinzufuegen.setBounds(289, 44, 135, 23);
			contentPane.add(buttonHinzufuegen);
		}
		{
			buttonSportartLschen = new JButton("Sportart l\u00F6schen");
			buttonSportartLschen.setBounds(289, 78, 135, 23);
			contentPane.add(buttonSportartLschen);
		}
		{
			labelId = new JLabel("ID");
			labelId.setBounds(10, 48, 46, 14);
			contentPane.add(labelId);
		}
	}
}
