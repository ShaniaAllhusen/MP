package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;

public class HallenPlan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JPasswordField textField_1;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JPanel panel;
	private JComboBox comboBox;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HallenPlan frame = new HallenPlan();
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
	public HallenPlan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 23, 576, 399);
		contentPane.add(table);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Anmeldung", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(591, 23, 164, 184);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 40, 152, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(6, 108, 152, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		lblUser = new JLabel("User :");
		lblUser.setBounds(6, 16, 100, 28);
		panel.add(lblUser);
		
		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(6, 84, 152, 28);
		panel.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(28, 145, 113, 28);
		panel.add(btnLogin);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Halle 1", "Halle 2", "Halle 3"}));
		comboBox.setBounds(0, 0, 576, 20);
		contentPane.add(comboBox);
	}
}
