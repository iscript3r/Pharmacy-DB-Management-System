import java.awt.EventQueue;
import java.sql.*;

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
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class ManufacturerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField ManufacturerIDText;
	private JTextField NameText;
	private JTextField LocationText;
	private JTextField IDText;
	private SQL SqlExecute = new SQL();
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManufacturerFrame frame = new ManufacturerFrame();
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
	            connection = DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/pharmacydb","root", "");
	    	   PreparedStatement PS ; 
	    	   ResultSet RS ; 
	    	   PS = connection.prepareStatement("select * from Manufacturer"  ) ;
	    	   RS = PS.executeQuery() ; 
	    	   table.setModel(DbUtils.resultSetToTableModel(RS)) ;
	    	   
	       }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}

	/**
	 * Create the frame.
	 */
	public ManufacturerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1267, 613);
		getContentPane().setLayout(null);
		
		JPanel ADDPanel = new JPanel();
		ADDPanel.setLayout(null);
		ADDPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add/Update", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ADDPanel.setBounds(0, 201, 617, 221);
		getContentPane().add(ADDPanel);
		
		JLabel ManufacturerIDLb = new JLabel("Manufacturer ID");
		ManufacturerIDLb.setHorizontalAlignment(SwingConstants.CENTER);
		ManufacturerIDLb.setFont(new Font("Tahoma", Font.BOLD, 20));
		ManufacturerIDLb.setBounds(20, 34, 175, 37);
		ADDPanel.add(ManufacturerIDLb);
		
		ManufacturerIDText = new JTextField();
		ManufacturerIDText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ManufacturerIDText.setColumns(10);
		ManufacturerIDText.setBounds(205, 34, 175, 26);
		ADDPanel.add(ManufacturerIDText);
		
		JLabel NameLbl = new JLabel("Name");
		NameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		NameLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		NameLbl.setBounds(20, 95, 154, 37);
		ADDPanel.add(NameLbl);
		
		NameText = new JTextField();
		NameText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NameText.setColumns(10);
		NameText.setBounds(205, 104, 175, 26);
		ADDPanel.add(NameText);
		
		JLabel locationLbl = new JLabel("Location");
		locationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		locationLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		locationLbl.setBounds(20, 156, 175, 37);
		ADDPanel.add(locationLbl);
		
		LocationText = new JTextField();
		LocationText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LocationText.setColumns(10);
		LocationText.setBounds(205, 165, 175, 26);
		ADDPanel.add(LocationText);
		
		JLabel lblmanufacturerIdShould = new JLabel("*Manufacturer ID should contain numbers only and not empty");
		lblmanufacturerIdShould.setForeground(Color.RED);
		lblmanufacturerIdShould.setBounds(30, 64, 350, 14);
		ADDPanel.add(lblmanufacturerIdShould);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				
				//Checking if the store ID is not empty nor its a string (since its p.k)
				if(!ManufacturerIDText.getText().isEmpty() && ManufacturerIDText.getText().matches("\\d+")){
					
				int MID = Integer.parseInt(ManufacturerIDText.getText());
				String Name = NameText.getText() ; 
				String Location = LocationText.getText() ; 
				SqlExecute.ManufacturerInsert(MID, Name, Location);
				table_load();  
				NameText.setText("");
				LocationText.setText("");
				ManufacturerIDText.setText("");
				
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Manufucaturer ID is wrong!", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBounds(497, 16, 90, 81);
		ADDPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!ManufacturerIDText.getText().isEmpty() && ManufacturerIDText.getText().matches("\\d+")) {
				
			int ID = Integer.parseInt(ManufacturerIDText.getText());
			String Name = NameText.getText() ; 
			String Location = LocationText.getText() ; 

			SqlExecute.ManufacturerUpdate(ID, Name, Location);
			table_load() ; 
			
			LocationText.setText("");
			NameText.setText("");
			ManufacturerIDText.setText("");
			}else {
			    JOptionPane.showMessageDialog(new JFrame(), "The Manufaturer ID is wrong!", "ERROR",
			            JOptionPane.ERROR_MESSAGE);
			}
		}
	});
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(497, 116, 90, 69);
		ADDPanel.add(btnUpdate);
		
		JLabel lblNewLabel_1_1 = new JLabel("(*)Required");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(457, 196, 89, 14);
		ADDPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("*");
		lblNewLabel_1_1_2.setForeground(Color.RED);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_2.setBounds(179, 34, 46, 14);
		ADDPanel.add(lblNewLabel_1_1_2);
		
		JPanel UpdatePanel = new JPanel();
		UpdatePanel.setLayout(null);
		UpdatePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Delete/Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		UpdatePanel.setBounds(10, 433, 607, 78);
		getContentPane().add(UpdatePanel);
		
		JLabel IDlbl = new JLabel("ID");
		IDlbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		IDlbl.setBounds(24, 30, 97, 20);
		UpdatePanel.add(IDlbl);
		
		IDText = new JTextField();
		IDText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		IDText.setColumns(10);
		IDText.setBounds(81, 30, 249, 25);
		UpdatePanel.add(IDText);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !IDText.getText().isEmpty()&& IDText.getText().matches("\\d+")) {
					int StorageID = Integer.parseInt(IDText.getText());
					String[] info = new String[2];
					SqlExecute.ManufacturerSearch(StorageID, info);
					if (info[0] != null) {
						ManufacturerIDText.setText(IDText.getText());
						NameText.setText(info[0]);
						LocationText.setText(info[1]);
						IDText.setText("");
					}
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Manufucaturer ID is wrong!", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(215, 522, 89, 45);
		getContentPane().add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!IDText.getText().isEmpty() && IDText.getText().matches("\\d+")) {
					int MID = Integer.parseInt(IDText.getText());
					SqlExecute.ManufacturerDelete(MID);
					IDText.setText("");
					table_load() ; 
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Manufacture ID is wrong!", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(314, 522, 89, 45);
		getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(642, 204, 470, 359);
		getContentPane().add(scrollPane);
		
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
		btnBack.setBounds(10, 544, 103, 23);
		getContentPane().add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, -28, 1255, 224);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ManufacturerFrame.class.getResource("/images/the-manufacturer-logo.jpg")));
		panel.add(lblNewLabel);
		table_load();
	}

}
