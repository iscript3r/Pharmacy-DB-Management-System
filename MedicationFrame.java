import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class MedicationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField MedicationIDText;
	private JTextField NameText;
	private JTextField ExpiryDateText;
	private JTextField IDText;
	private SQL SqlExecute = new SQL();
	private JTable table;
	public String [] cb=new String[100];
	public int count=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicationFrame frame = new MedicationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}public void table_load() { 
	       try {
	 		  Connection connection = null;
	    	   Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection( 
	            		"jdbc:mysql://localhost:3306/pharmacydb","root", "");
	    	   PreparedStatement PS ; 
	    	   ResultSet RS ; 
	    	   PS = connection.prepareStatement("select * from Medication"  ) ;
	    	   RS = PS.executeQuery() ; 
	    	   table.setModel(DbUtils.resultSetToTableModel(RS));
	       }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	public void fillCb() {
		try {
	 		  Connection connection = null;
	    	   Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(
 "jdbc:mysql://localhost:3306/pharmacydb","root", "");
	    	   PreparedStatement PS ; 
	    	   ResultSet RS ; 
	    	   PS = connection.prepareStatement("select Storage_ID from Storage"  ) ;
	    	   RS = PS.executeQuery() ; 
	   
	    	   while(RS.next()){
	               //shows topic data in combobox
	                cb[count++]=RS.getString("Storage_ID");
	            }
	    	   
	       }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public MedicationFrame() {
		  
		fillCb();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel ADDPanel = new JPanel();
		ADDPanel.setLayout(null);
		ADDPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add/Update", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ADDPanel.setBounds(10, 141, 503, 237);
		contentPane.add(ADDPanel);
		
		JLabel MedicationIDTextLb = new JLabel("Medication ID");
		MedicationIDTextLb.setHorizontalAlignment(SwingConstants.CENTER);
		MedicationIDTextLb.setFont(new Font("Tahoma", Font.BOLD, 20));
		MedicationIDTextLb.setBounds(20, 34, 175, 37);
		ADDPanel.add(MedicationIDTextLb);
		
		MedicationIDText = new JTextField();
		MedicationIDText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MedicationIDText.setColumns(10);
		MedicationIDText.setBounds(205, 46, 175, 20);
		ADDPanel.add(MedicationIDText);
		
		JLabel NameLb = new JLabel("Name");
		NameLb.setHorizontalAlignment(SwingConstants.CENTER);
		NameLb.setFont(new Font("Tahoma", Font.BOLD, 20));
		NameLb.setBounds(20, 95, 175, 37);
		ADDPanel.add(NameLb);
		
		NameText = new JTextField();
		NameText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NameText.setColumns(10);
		NameText.setBounds(205, 112, 175, 20);
		ADDPanel.add(NameText);
		
		JLabel ExpiryDateLb = new JLabel("Expiry_Date");
		ExpiryDateLb.setHorizontalAlignment(SwingConstants.CENTER);
		ExpiryDateLb.setFont(new Font("Tahoma", Font.BOLD, 20));
		ExpiryDateLb.setBounds(20, 131, 175, 37);
		ADDPanel.add(ExpiryDateLb);
		
		
		ExpiryDateText = new JTextField();
		ExpiryDateText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ExpiryDateText.setColumns(10);
		ExpiryDateText.setBounds(205, 143, 175, 20);
		ADDPanel.add(ExpiryDateText);
		
		JLabel MEIDTF = new JLabel("*Medication ID should contain numbers only and not empty");
		MEIDTF.setForeground(Color.RED);
		MEIDTF.setBounds(30, 64, 350, 20);
		ADDPanel.add(MEIDTF);

		JComboBox StorageIDcomboBox = new JComboBox(cb);
		StorageIDcomboBox.setEditable(true);
		StorageIDcomboBox.setBounds(205, 174, 175, 20);
		ADDPanel.add(StorageIDcomboBox);
		fillCb();
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				//Checking if the Mediation ID is not empty nor its a string (since its p.k)
				if(!MedicationIDText.getText().isEmpty() && MedicationIDText.getText().matches("\\d+")) {
					
				int MEID = Integer.parseInt(MedicationIDText.getText());
				String Name = NameText.getText() ; 
				String EXD= ExpiryDateText.getText();
				int SID = -1 ; 
				if(StorageIDcomboBox.getSelectedItem() != null) {
				SID=Integer.parseInt(StorageIDcomboBox.getSelectedItem().toString());
				}
				SqlExecute.MedicationInsert(MEID, Name,EXD,SID);
				table_load();  
				NameText.setText("");
				ExpiryDateText.setText("");
				MedicationIDText.setText("");
			
				
		}		else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Medication Or/And Storage ID is wrong!", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBounds(390, 76, 103, 56);
		ADDPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!MedicationIDText.getText().isEmpty() && MedicationIDText.getText().matches("\\d+")) {
					
				int ID = Integer.parseInt(MedicationIDText.getText());
				String Name = NameText.getText() ; 
				String Address = ExpiryDateText.getText() ; 
				int SID = -1 ; 
				if(StorageIDcomboBox.getSelectedItem() != null) {
				SID=Integer.parseInt(StorageIDcomboBox.getSelectedItem().toString());
				}
				SqlExecute.MedicationUpdate(ID, Name, Address ,SID );
				table_load() ; 
				
				ExpiryDateText.setText("");
				NameText.setText("");
				MedicationIDText.setText("");
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Medication ID is wrong!", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(390, 138, 103, 56);
		ADDPanel.add(btnUpdate);
		
		JLabel StorageIDLb = new JLabel("Storage ID");
		StorageIDLb.setHorizontalAlignment(SwingConstants.CENTER);
		StorageIDLb.setFont(new Font("Tahoma", Font.BOLD, 20));
		StorageIDLb.setBounds(20, 162, 175, 37);
		ADDPanel.add(StorageIDLb);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(170, 34, 46, 14);
		ADDPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("(*)Required");
		lblNewLabel_1_1.setBounds(425, 199, 89, 14);
		ADDPanel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(Color.RED);
		
	
		JPanel UpdatePanel = new JPanel();
		UpdatePanel.setLayout(null);
		UpdatePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Delete/Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		UpdatePanel.setBounds(10, 389, 503, 72);
		contentPane.add(UpdatePanel);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(24, 30, 97, 20);
		UpdatePanel.add(lblNewLabel_1_1_1);
		
		IDText = new JTextField();
		IDText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		IDText.setColumns(10);
		IDText.setBounds(90, 30, 211, 20);
		UpdatePanel.add(IDText);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!IDText.getText().isEmpty()&& IDText.getText().matches("\\d+")) {
					int MedicationID = Integer.parseInt(IDText.getText());
					String[] info = new String[3];
					SqlExecute.MedicationSearch(MedicationID, info);
					if (info[0] != null) {
						MedicationIDText.setText(IDText.getText());
						NameText.setText(info[0]);
						ExpiryDateText.setText(info[1]);
						StorageIDcomboBox.setSelectedItem(info[2]);
						IDText.setText("");
					}
				
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Medication ID is wrong!", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(160, 472, 89, 59);
		contentPane.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!IDText.getText().isEmpty() && IDText.getText().matches("\\d+")) {
					int MID = Integer.parseInt(IDText.getText());
					SqlExecute.MedicationDelete(MID);
					IDText.setText("");
					table_load() ; 
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Medication ID is wrong!", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(259, 472, 89, 59);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(518, 144, 389, 425);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 632, 109, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = new MainFrame();
		        mainFrame.getFrmMain().setVisible(true);
		        dispose(); 
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MedicationFrame.class.getResource("/images/Screenshot 2023-11-28 024217.jpg")));
		lblNewLabel.setBounds(27, -103, 1306, 332);
		contentPane.add(lblNewLabel);
		table_load();
	}
}
