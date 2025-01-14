import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PharmacyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NameText;
	private JTextField AddressText;
	private JTextField CityText;
	private JTextField IDText;
	private SQL SqlExecute = new SQL();
	private JScrollPane Table1;
	private JTable table;
	private JTextField StoreIDText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PharmacyFrame frame = new PharmacyFrame();
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
			PS = connection.prepareStatement("select * from Pharmacy");
			RS = PS.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(RS));

		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	/**
	 * Create the frame.
	 */
	public PharmacyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPharmacy = new JLabel("");
		lblPharmacy.setBounds(0, 0, 157, 155);
		lblPharmacy.setHorizontalAlignment(SwingConstants.CENTER);
		lblPharmacy.setForeground(new Color(64, 0, 64));
		lblPharmacy.setFont(new Font("Tempus Sans ITC", Font.BOLD, 68));
		contentPane.add(lblPharmacy);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Add/Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(34, 219, 423, 215);
		contentPane.add(panel);
		panel.setLayout(null);
		NameText = new JTextField();
		NameText.setBounds(104, 130, 211, 20);
		panel.add(NameText);
		NameText.setColumns(10);
		JLabel lblName = new JLabel("Name ");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName.setBounds(10, 126, 97, 20);
		panel.add(lblName);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(10, 153, 97, 20);
		panel.add(lblAddress);

		AddressText = new JTextField();
		AddressText.setColumns(10);
		AddressText.setBounds(104, 157, 211, 20);
		panel.add(AddressText);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCity.setBounds(10, 184, 97, 20);
		panel.add(lblCity);

		CityText = new JTextField();
		CityText.setColumns(10);
		CityText.setBounds(104, 184, 211, 20);
		panel.add(CityText);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Checking if the store ID is not empty nor its a string (since its p.k)
				if (!StoreIDText.getText().isEmpty() && StoreIDText.getText().matches("\\d+")) {

					int StoreID = Integer.parseInt(StoreIDText.getText());
					String Name = NameText.getText();
					String Address = AddressText.getText();
					String City = CityText.getText();
					SqlExecute.PharmacyInsert(StoreID, Name, Address, City);
					table_load();
					CityText.setText("");
					AddressText.setText("");
					NameText.setText("");
					StoreIDText.setText("");
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "The Store ID is wrong!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAdd.setBounds(325, 48, 89, 59);
		panel.add(btnAdd);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblStoreID = new JLabel("Store ID");
		lblStoreID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStoreID.setBounds(10, 63, 97, 20);
		panel.add(lblStoreID);

		StoreIDText = new JTextField();
		StoreIDText.setColumns(10);
		StoreIDText.setBounds(104, 67, 211, 20);
		panel.add(StoreIDText);

		JLabel lblRequ = new JLabel("*Store ID should contain numbers only and not empty");
		lblRequ.setForeground(new Color(255, 0, 0));
		lblRequ.setBounds(20, 85, 295, 14);
		panel.add(lblRequ);

		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(89, 52, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("(*)Required");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(346, 191, 89, 14);
		panel.add(lblNewLabel_1);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(325, 111, 89, 59);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!StoreIDText.getText().isEmpty() && StoreIDText.getText().matches("\\d+")) {

					int StoreID = Integer.parseInt(StoreIDText.getText());
					String Name = NameText.getText();
					String Address = AddressText.getText();
					String City = CityText.getText();
					SqlExecute.PharmacyUpdate(StoreID, Name, Address, City);
					table_load();
					CityText.setText("");
					AddressText.setText("");
					NameText.setText("");
					StoreIDText.setText("");
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "The Store ID is wrong!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));

		JPanel UpdatePanel = new JPanel();
		UpdatePanel.setLayout(null);
		UpdatePanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Delete/Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		UpdatePanel.setBounds(34, 457, 423, 81);
		contentPane.add(UpdatePanel);

		JLabel lblNewLabel_1_1_1 = new JLabel("ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(24, 30, 97, 20);
		UpdatePanel.add(lblNewLabel_1_1_1);

		IDText = new JTextField();
		IDText.setColumns(10);
		IDText.setBounds(100, 30, 211, 20);
		UpdatePanel.add(IDText);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!IDText.getText().isEmpty() && IDText.getText().matches("\\d+")) {
					int StoreID = Integer.parseInt(IDText.getText());
					String[] info = new String[3];
					SqlExecute.PharmacySearch(StoreID, info);
					if (info[0] != null) {
						StoreIDText.setText(IDText.getText());
						NameText.setText(info[0]);
						AddressText.setText(info[1]);
						CityText.setText(info[2]);
						IDText.setText("");

					}

				} else {
					JOptionPane.showMessageDialog(new JFrame(), "The Store ID is wrong!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnSearch.setBounds(68, 549, 89, 59);
		contentPane.add(btnSearch);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(167, 549, 89, 59);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!IDText.getText().isEmpty() && IDText.getText().matches("\\d+")) {
					int StoreID = Integer.parseInt(IDText.getText());
					SqlExecute.PharmacyDelete(StoreID);
					IDText.setText("");
					table_load();
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "The Store ID is wrong!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));

		JScrollPane PharmacyTable = new JScrollPane();
		PharmacyTable.setBounds(462, 218, 413, 393);
		contentPane.add(PharmacyTable);

		table = new JTable();
		PharmacyTable.setViewportView(table);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = new MainFrame();
				mainFrame.getFrmMain().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(10, 623, 101, 23);
		contentPane.add(btnBack);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2
				.setIcon(new ImageIcon(PharmacyFrame.class.getResource("/images/Screenshot 2023-11-28 024217.jpg")));
		lblNewLabel_2.setBounds(0, 0, 885, 155);
		contentPane.add(lblNewLabel_2);
		table_load();
	}
}
