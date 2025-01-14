import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;

public class ManufacturerProduceMedication extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public String[] cbMeID = new String[100];
	public int count = 0;
	public String[] cbMID = new String[100];
	public int countMID = 0;
	private SQL SqlExecute = new SQL();
	private JTextField PackagingDetailsText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManufacturerProduceMedication frame = new ManufacturerProduceMedication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void table_load() {
		try {
			Connection connection = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacydb", "root", "");
			PreparedStatement PS;
			ResultSet RS;
			PS = connection.prepareStatement("select * from manufacturer_produce_medication");
			RS = PS.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(RS));
			// fillCbMID();
			// fillCbMeID();

		} catch (Exception exception) {
			System.out.println(exception);

		}
	}

	public void fillCbMID() {
		try {
			Connection connection = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacydb", "root", "");
			PreparedStatement PS;
			ResultSet RS;
			PS = connection.prepareStatement("select Manufacturer_ID from Manufacturer");
			RS = PS.executeQuery();
			while (RS.next()) {
				// shows topic data in combobox
				cbMID[countMID++] = RS.getString("Manufacturer_ID");
			}

		} catch (Exception exception) {
			System.out.println(exception);
		}

	}

	public void fillCbMeID() {
		try {
			Connection connection = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacydb", "root", "");
			PreparedStatement PS;
			ResultSet RS;
			PS = connection.prepareStatement("select Medication_ID from Medication");
			RS = PS.executeQuery();
			while (RS.next()) {
				// shows topic data in combobox
				cbMeID[count++] = RS.getString("Medication_ID");
			}

		} catch (Exception exception) {
			System.out.println(exception);
		}

	}

	/**
	 * Create the frame.
	 */
	public ManufacturerProduceMedication() {
		fillCbMeID();
		fillCbMID();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add/Delete", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 199, 457, 364);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel MedicationIDlbl = new JLabel("Medication ID");
		MedicationIDlbl.setHorizontalAlignment(SwingConstants.CENTER);
		MedicationIDlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		MedicationIDlbl.setBounds(-15, 57, 186, 50);
		panel.add(MedicationIDlbl);

		JLabel ManufacturerIDlbl = new JLabel("Manufacturer ID");
		ManufacturerIDlbl.setHorizontalAlignment(SwingConstants.CENTER);
		ManufacturerIDlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		ManufacturerIDlbl.setBounds(9, 146, 175, 37);
		panel.add(ManufacturerIDlbl);

		JComboBox MedicationIDComboBox = new JComboBox(cbMeID);
		MedicationIDComboBox.setBounds(205, 68, 159, 37);
		panel.add(MedicationIDComboBox);

		JComboBox ManufacturerIDComboBox = new JComboBox(cbMID);
		ManufacturerIDComboBox.setBounds(205, 150, 159, 37);
		panel.add(ManufacturerIDComboBox);

		JLabel lblNewLabel_1_1 = new JLabel("*");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(140, 57, 46, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("*");
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(171, 143, 46, 14);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("(*)Required");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setBounds(387, 263, 89, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblPackagingDetails = new JLabel("Packaging Details ");
		lblPackagingDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackagingDetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPackagingDetails.setBounds(-1, 218, 208, 42);
		panel.add(lblPackagingDetails);
		
		PackagingDetailsText = new JTextField();
		PackagingDetailsText.setColumns(10);
		PackagingDetailsText.setBounds(205, 233, 211, 20);
		panel.add(PackagingDetailsText);
		
				JButton btnAdd = new JButton("Add");
				btnAdd.setBounds(9, 278, 108, 75);
				panel.add(btnAdd);
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Checking if the Mediation ID is not empty nor its a string (since its p.k
						if (ManufacturerIDComboBox.getSelectedIndex() != -1 && MedicationIDComboBox.getSelectedIndex() != -1) {
							int ManufacturerID = Integer.parseInt(ManufacturerIDComboBox.getSelectedItem().toString());
							int MedicationID = Integer.parseInt(MedicationIDComboBox.getSelectedItem().toString());
							String PackagingDetails = PackagingDetailsText.getText(); 
							SqlExecute.Manufacturer_Produce_MedicationInsert(MedicationID, ManufacturerID , PackagingDetails);
							PackagingDetailsText.setText("");
							table_load();

						} else {
							JOptionPane.showMessageDialog(new JFrame(), "The Manufacturer OR/And Medication ID is wrong!",
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
				
						JButton btnDelete = new JButton("Delete");
						btnDelete.setBounds(123, 278, 108, 75);
						panel.add(btnDelete);
						btnDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								// Checking if the Mediation ID is not empty nor its a string (since its p.k
								if (ManufacturerIDComboBox.getSelectedIndex() != -1 && MedicationIDComboBox.getSelectedIndex() != -1) {
									int MId = Integer.parseInt(ManufacturerIDComboBox.getSelectedItem().toString());
									int MEID = Integer.parseInt(MedicationIDComboBox.getSelectedItem().toString());
									SqlExecute.Manufacturer_Produce_MedicationDelete(MId, MEID);
									table_load();

								} else {
									JOptionPane.showMessageDialog(new JFrame(), "The Manufacturer OR/And Medication ID is wrong!",
											"ERROR", JOptionPane.ERROR_MESSAGE);
								}

							}
						});
						btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
						
						JButton Update = new JButton("Update");
						Update.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								// Checking if the Mediation ID is not empty nor its a string (since its p.k
								if (ManufacturerIDComboBox.getSelectedIndex() != -1 && MedicationIDComboBox.getSelectedIndex() != -1) {
									int MId = Integer.parseInt(ManufacturerIDComboBox.getSelectedItem().toString());
									int MEID = Integer.parseInt(MedicationIDComboBox.getSelectedItem().toString());
									String PackagingDetails = PackagingDetailsText.getText(); 
									SqlExecute.Manufacturer_Produce_MedicationUpdate(MId, MEID,PackagingDetails);
									PackagingDetailsText.setText("");
									table_load();

								} else {
									JOptionPane.showMessageDialog(new JFrame(), "The Manufacturer OR/And Medication ID is wrong!",
											"ERROR", JOptionPane.ERROR_MESSAGE);
								}

							}
						});
						Update.setFont(new Font("Tahoma", Font.BOLD, 11));
						Update.setBounds(231, 278, 108, 75);
						panel.add(Update);
						
						JButton btnSearch = new JButton("Search");
						btnSearch.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								if(ManufacturerIDComboBox.getSelectedIndex() != -1 && MedicationIDComboBox.getSelectedIndex() != -1) {
									int MId = Integer.parseInt(ManufacturerIDComboBox.getSelectedItem().toString());
									int MEID = Integer.parseInt(MedicationIDComboBox.getSelectedItem().toString());
									String[] info = new String[3];
									SqlExecute.Manufacturer_Produce_MedicationSearch(MEID,MId, info);
									if (info[0] != null) {
										ManufacturerIDComboBox.setSelectedItem(info[1]);;
										MedicationIDComboBox.setSelectedItem(info[0]);
										PackagingDetailsText.setText(info[2]);
										
									}
								
								}else {
								    JOptionPane.showMessageDialog(new JFrame(), "The Manufacturer OR/And Medication is wrong!", "ERROR",
								            JOptionPane.ERROR_MESSAGE);
								}
								
							}
						});
						btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
						btnSearch.setBounds(344, 278, 108, 75);
						panel.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(467, 208, 396, 355);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = new MainFrame();
				mainFrame.getFrmMain().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(10, 574, 118, 23);
		contentPane.add(btnBack);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				ManufacturerProduceMedication.class.getResource("/images/Screenshot 2023-11-28 024217.jpg")));
		lblNewLabel.setBounds(0, 0, 883, 112);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("   Manufacturer Produce Medication");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel_1.setBounds(193, 134, 578, 53);
		contentPane.add(lblNewLabel_1);
		table_load();
	}

}

