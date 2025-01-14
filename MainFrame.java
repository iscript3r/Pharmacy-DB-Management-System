import java.awt.* ;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class MainFrame {

	private JFrame frmMain;

	public JFrame getFrmMain() {
		return frmMain;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("PharmacyDB");
		frmMain.setBounds(100, 100, 895, 686);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 154, 883, 497);
		frmMain.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_3_1 = new JButton("Manufacturer Produce Medication");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManufacturerProduceMedication Mpm = new ManufacturerProduceMedication() ; 
				Mpm.show(); 
				frmMain.dispose(); 
			}
		});
		btnNewButton_3_1.setForeground(Color.BLACK);
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3_1.setBounds(437, 346, 370, 60);
		panel.add(btnNewButton_3_1);
		
		JButton btnNewButton_3 = new JButton("Storage");
		btnNewButton_3.setBounds(166, 346, 239, 60);
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StorageFrame StorageFrame1 = new StorageFrame() ; 
				StorageFrame1.show(); 
				frmMain.dispose(); 
			}
		});
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton_2 = new JButton("Medication");
		btnNewButton_2.setBounds(605, 255, 239, 60);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicationFrame MedicationFrame = new MedicationFrame() ; 
				MedicationFrame.show(); 
				frmMain.dispose(); 
			}
		});
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("Manufacturer");
		btnNewButton_1.setBounds(330, 255, 239, 60);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManufacturerFrame ManufacturerFrame = new ManufacturerFrame() ; 
				ManufacturerFrame.show(); 
				frmMain.dispose(); 
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("Pharmacy");
		btnNewButton.setBounds(36, 255, 239, 60);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PharmacyFrame PharmacyFrame = new PharmacyFrame() ; 
				PharmacyFrame.show(); 
				frmMain.dispose(); 
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton_3_1_1 = new JButton("EXIT");
		btnNewButton_3_1_1.setBounds(772, 466, 101, 22);
		panel.add(btnNewButton_3_1_1);
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.dispose(); 
			}
		});
		btnNewButton_3_1_1.setForeground(Color.BLACK);
		btnNewButton_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Please Choose An Option!");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(216, 58, 513, 60);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 883, 155);
		frmMain.getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/images/Screenshot 2023-11-28 024217.jpg")));
		panel_1.add(lblNewLabel);
	}
}
