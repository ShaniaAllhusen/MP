package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZeitBloecke extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldStandart;
	private JTextField textFieldAusgabeAuswahl;
	private int zeitblock;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public ZeitBloecke() {
		setTitle("ZeitBl\u00F6cke_Ausw\u00E4hlen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 376, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnZeitBlock_60min = new JButton("ZeitBlock_60min");
		btnZeitBlock_60min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			zeitblock = 1;
			zeitBlockAuswahl();
			}
		});
		btnZeitBlock_60min.setBounds(0, 0, 183, 23);
		panel.add(btnZeitBlock_60min);
		
		JButton btnZeitblock45min = new JButton("ZeitBlock_45min");
		btnZeitblock45min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			zeitblock = 2;
			zeitBlockAuswahl();
			}
		});
		btnZeitblock45min.setBounds(0, 34, 183, 23);
		panel.add(btnZeitblock45min);
		
		JButton btnZeitblock30min = new JButton("ZeitBlock_30min");
		btnZeitblock30min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			zeitblock = 3;
			zeitBlockAuswahl();
			}
		});
		btnZeitblock30min.setBounds(0, 68, 183, 23);
		panel.add(btnZeitblock30min);
		
		textFieldStandart = new JTextField();
		textFieldStandart.setBounds(193, 1, 183, 20);
		panel.add(textFieldStandart);
		textFieldStandart.setText("Standart auswahl \"ZeitBlock_60min\"");
		textFieldStandart.setEditable(false);
		textFieldStandart.setColumns(10);
		
		textFieldAusgabeAuswahl = new JTextField();
		textFieldAusgabeAuswahl.setBounds(193, 35, 183, 20);
		panel.add(textFieldAusgabeAuswahl);
		textFieldAusgabeAuswahl.setEditable(false);
		textFieldAusgabeAuswahl.setColumns(10);
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.setBounds(193, 68, 183, 23);
		panel.add(btnAktualisieren);
	}
	public void zeitBlockAuswahl() {
		if(zeitblock==1) {
			textFieldAusgabeAuswahl.setText("Aktuell ZeitBlock_60min");
		}else if(zeitblock==2) {
			textFieldAusgabeAuswahl.setText("Aktuell ZeitBlock_45min");
		}else if(zeitblock==3) {
			textFieldAusgabeAuswahl.setText("Aktuell ZeitBlock_30min");
		}else{
			textFieldAusgabeAuswahl.setText("Standart ausgewählt");
		}
	}
}
