package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Dao.VereinDao;

public class HallenPlan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldUser;
	private JPasswordField textFieldpassword;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JPanel panel;
	private JComboBox comboBox_Halle;
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setToolTipText("Dies ist der Wochenplan, der oben ausgewaehlten Halle");
		scrollPane.setBounds(10, 54, 522, 299);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setEnabled(false);
        table.setDefaultRenderer(String.class, new TestRenderer());  // TODO
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);
		String[] columns = new String[] {
				 "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
		};
		try {
			
			table.setModel(new DefaultTableModel(new VereinDao().getTable(), columns));
			for (String columnIdentifier : columns) {
				TableColumn column = table.getColumn(columnIdentifier);
				column.setCellRenderer(new WordWrapCellRenderer());
			}
			
			table.updateUI();
			repaint();
			repaint();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel = new JPanel();
		panel.setToolTipText("Bitte geben Sie Ihren Benutzernamen und Ihr Passwort ein und druecken Sie dann auf den Button \"Login\", fuer die Anmeldung. Wenn Sie angemeldet sind, koennen Sie  die Tabelle veraendern.");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Anmeldung", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(591, 23, 164, 184);
		contentPane.add(panel);
		panel.setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setToolTipText("Bitte geben Sie hier Ihren Benutzernamen ein");
		textFieldUser.setBounds(6, 40, 152, 28);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);

		textFieldpassword = new JPasswordField();
		textFieldpassword.setToolTipText("Bitte geben Sie hier Ihr Passwort ein");
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
		btnLogin.setToolTipText("Betaetigen Sie diesen Button um sich anzumelden.");
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

		comboBox_Halle = new JComboBox();
		comboBox_Halle.setToolTipText("Hier koennen Sie die Halle auswaehlen, von welcher Sie den Plan angezeigt bekommen wollen");
		comboBox_Halle.setModel(new DefaultComboBoxModel(new String[] {"Halle 1", "Halle 2", "Halle 3"}));
		comboBox_Halle.setBounds(10, 20, 522, 20);
		contentPane.add(comboBox_Halle);
	}
	
	public JTable getTable() {
		return table;
	}
	
	static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
	    WordWrapCellRenderer() {
	        setLineWrap(true);
	        setWrapStyleWord(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        setText((value == null) ? "" : value.toString());
	        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
	        if (table.getRowHeight(row) != getPreferredSize().height) {
	            table.setRowHeight(row, getPreferredSize().height);
	        }
	        return this;
	    }
	}
}
