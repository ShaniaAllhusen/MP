package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Dao.VereinDao;

public class HallenPlan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldUser;
	private JPasswordField textFieldpassword;
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
					frame.setLocationRelativeTo(null);
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

		textFieldUser = new JTextField();
		textFieldUser.setBounds(6, 40, 152, 28);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);

		textFieldpassword = new JPasswordField();
		textFieldpassword.setBounds(6, 108, 152, 28);
		panel.add(textFieldpassword);
		textFieldpassword.setColumns(10);

		lblUser = new JLabel("User :");
		lblUser.setBounds(6, 16, 100, 28);
		panel.add(lblUser);

		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(6, 84, 152, 28);
		panel.add(lblPassword);

		btnLogin = new JButton("Login");
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean strFrame = false;
				try{
					VereinDao vereinDao = new VereinDao();
					strFrame =	vereinDao.login(textFieldUser.getText(), new String(textFieldpassword.getPassword()));
					System.out.println(strFrame);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				if(strFrame) {
					Angemeldet wrkframe = new Angemeldet();
					wrkframe.setVisible(true);
					HallenPlan frame2 = new HallenPlan();
					dispose();
		
				}
			}
		});
		btnLogin.setBounds(28, 145, 113, 28);
		panel.add(btnLogin);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Halle 1", "Halle 2", "Halle 3"}));
		comboBox.setBounds(0, 0, 576, 20);
		contentPane.add(comboBox);
	}
}
