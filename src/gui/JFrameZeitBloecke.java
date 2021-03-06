package gui;

//Imports
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import tabellenklassen.Zeitblock;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameZeitBloecke extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldStandart;
	private JTextField textFieldAusgabeAuswahl;
	private int zeitblock;
	private int von;
	private int bis; 
	public static int vonbisdif = 24;
	static Zeitblock zb = new Zeitblock();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameZeitBloecke frame = new JFrameZeitBloecke();
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
	
	//Konstruktor
	public JFrameZeitBloecke() {
		setTitle("ZeitBl\u00F6cke_Ausw\u00E4hlen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 473, 156);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnZeitBlock_60min = new JButton("ZeitBlock_60min");
		btnZeitBlock_60min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeitblock = 60;
				zeitBlockAuswahl();
			}
		});
		btnZeitBlock_60min.setBounds(0, 0, 183, 23);
		panel.add(btnZeitBlock_60min);

		JButton btnZeitblock45min = new JButton("ZeitBlock_45min");
		btnZeitblock45min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeitblock = 45;
				zeitBlockAuswahl();
			}
		});
		btnZeitblock45min.setBounds(0, 34, 183, 23);
		panel.add(btnZeitblock45min);

		JButton btnZeitblock30min = new JButton("ZeitBlock_30min");
		btnZeitblock30min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeitblock = 30;
				zeitBlockAuswahl();
			}
		});
		btnZeitblock30min.setBounds(0, 68, 183, 23);
		panel.add(btnZeitblock30min);

		textFieldStandart = new JTextField();
		textFieldStandart.setBounds(193, 1, 270, 20);
		panel.add(textFieldStandart);
		textFieldStandart.setText("Standart auswahl \"ZeitBlock_60min\"");
		textFieldStandart.setEditable(false);
		textFieldStandart.setColumns(10);

		textFieldAusgabeAuswahl = new JTextField();
		textFieldAusgabeAuswahl.setBounds(193, 35, 270, 20);
		panel.add(textFieldAusgabeAuswahl);
		textFieldAusgabeAuswahl.setEditable(false);
		textFieldAusgabeAuswahl.setColumns(10);

		JSpinner spinnerUhrzeitVon = new JSpinner();
		spinnerUhrzeitVon.setBounds(238, 69, 73, 20);
		panel.add(spinnerUhrzeitVon);
		spinnerUhrzeitVon.setModel(new SpinnerNumberModel(0, 0, 24, 1));


		JLabel lblVon = new JLabel("Von:");
		lblVon.setBounds(203, 72, 46, 14);
		panel.add(lblVon);

		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAktualisieren.setBounds(0, 102, 463, 43);
		panel.add(btnAktualisieren);

		JLabel lblBis = new JLabel("Bis :");
		lblBis.setBounds(321, 72, 46, 14);
		panel.add(lblBis);

		JSpinner spinnerUhrzeitBis = new JSpinner();
		spinnerUhrzeitBis.setModel(new SpinnerNumberModel(22, 0, 24, 1));
		spinnerUhrzeitBis.setBounds(355, 69, 73, 20);
		panel.add(spinnerUhrzeitBis);


		//Wenn Button aktualisieren gedr�ckt -> Aktualisiere Uhrzeiten von und bis
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bis = (int) spinnerUhrzeitBis.getValue();
				von = (int) spinnerUhrzeitVon.getValue();
				vonbisdif = bis-von;
				zb.setDauer(zeitblock);
			}
		});
	}
	// Methode zeitberechnen()
	public static String zeitberechnen(int z) throws ArithmeticException{
		
		int dauer = 60;
		//int dauer = zb.getDauer();
		int min = 0; 
		int stunde = 0;
		int gesamt= 0;
		int dif = 0;
		String[] uhrzeit = new String[(vonbisdif*2)/dauer];
		
		for (int i = 0; i < vonbisdif*2; i++) {
			dif = min /dauer;
			System.out.println(dif);
			gesamt = gesamt + dauer;
			min = min + dauer; 
			if(min%dauer == 0) {
				stunde++;
				min = 0;
			}else if(min%dauer >= 0){
				min = min%dauer;
						stunde++;
			}
			uhrzeit[i] = String.valueOf(stunde)+":"+String.valueOf(min);
		
		}
		System.out.println(uhrzeit[z]);
			return uhrzeit[z];
}


	//Methode zeitBlockAuswahl()
	public void zeitBlockAuswahl() {
		if(zeitblock==60) {
			textFieldAusgabeAuswahl.setText("Aktuell ZeitBlock_60min");
		}else if(zeitblock==45) {
			textFieldAusgabeAuswahl.setText("Aktuell ZeitBlock_45min");
		}else if(zeitblock==30) {
			textFieldAusgabeAuswahl.setText("Aktuell ZeitBlock_30min");
		}else{
			textFieldAusgabeAuswahl.setText("Standart ausgew�hlt");
		}
	}
}
