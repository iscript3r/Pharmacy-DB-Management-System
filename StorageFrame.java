import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class StorageFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField QuantityInStockText;
	private JTextField IDText;
	private SQL SqlExecute = new SQL()  ; 
	private JTable table;
	private JTextField StorageIDText;
	JComboBox<Object> PharmacyIDCombo = new JComboBox<Object>();
	private JTextField StorageTypeText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StorageFrame frame = new StorageFrame();
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
	            connection = SqlExecute.Connect();
	    	   PreparedStatement PS ; 
	    	   ResultSet RS ; 
	    	   PS = connection.prepareStatement("select * from Storage"  ) ;
	    	   RS = PS.executeQuery() ; 
	    	   table.setModel(DbUtils.resultSetToTableModel(RS)) ;
	    	   
	    	   
	       }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void FillComboBox() {
		
		try {
	 		  Connection connection = null;
	            connection = SqlExecute.Connect();
	            
            String SearchQuery = "SELECT Store_ID  FROM pharmacy ";
            
            Statement statement = connection.createStatement();
            ResultSet RS = statement.executeQuery(SearchQuery) ; 
            while(RS.next()) {
            	PharmacyIDCombo.addItem(RS.getString("Store_ID")) ; 
            }
            
            statement.close();
            connection.close();
            
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
	}
	

	/**
	 * Create the frame.
	 */
	public StorageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add/Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 169, 473, 271);
		contentPane.add(panel);
		panel.setLayout(null);
		QuantityInStockText = new JTextField();
		QuantityInStockText.setBounds(187, 100, 211, 20);
		panel.add(QuantityInStockText);
		QuantityInStockText.setColumns(10); 		
		JLabel lblName = new JLabel("Quantity in stock");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName.setBounds(10, 100, 182, 24);
		panel.add(lblName);
		
		JLabel lblStorageID = new JLabel("Storage ID");
		lblStorageID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStorageID.setBounds(10, 36, 121, 24);
		panel.add(lblStorageID);
		
		StorageIDText = new JTextField();
		StorageIDText.setColumns(10);
		StorageIDText.setBounds(187, 42, 211, 20);
		panel.add(StorageIDText);
		
		JLabel lblRequ = new JLabel("*Storage should contain numbers only and not empty");
		lblRequ.setForeground(new Color(255, 0, 0));
		lblRequ.setBounds(0, 71, 299, 14);
		panel.add(lblRequ);
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(113, 29, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblPharmacyID = new JLabel("Pharmacy ID");
		lblPharmacyID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPharmacyID.setBounds(10, 170, 182, 20);
		panel.add(lblPharmacyID);
		
		PharmacyIDCombo.setBounds(187, 167, 211, 23);
		panel.add(PharmacyIDCombo);
		
		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(140, 168, 46, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnAdd =  new JButton("Add");
		btnAdd.setBounds(174, 201, 89, 59);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Checking if the storage ID is not empty nor its a string (since its p.k)
				if(!StorageIDText.getText().isEmpty() && StorageIDText.getText().matches("\\d+") 
						&& PharmacyIDCombo.getSelectedIndex() != -1) {
					
				int StorageID = Integer.parseInt(StorageIDText.getText());
				String QuantityInStock = QuantityInStockText.getText() ; 
				String StorageType = StorageTypeText.getText() ;
				String selectedPharmacyID = (String) PharmacyIDCombo.getSelectedItem();
				int PharmacyID = Integer.parseInt(selectedPharmacyID) ;
				SqlExecute.StorageInsert(StorageID, QuantityInStock , PharmacyID, StorageType );
				table_load() ; 
				QuantityInStockText.setText("");
				StorageIDText.setText("");
				StorageTypeText.setText("");
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "Fill all the required items correctly !", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(273, 201, 89, 59);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!StorageIDText.getText().isEmpty() && StorageIDText.getText().matches("\\d+") ) {
					
				int StorageID = Integer.parseInt(StorageIDText.getText());
				String QuantityInStock = QuantityInStockText.getText() ; 
				String StorageType = StorageTypeText.getText() ; 
				SqlExecute.StorageUpdate(StorageID, QuantityInStock ,StorageType );
				table_load() ; 
				QuantityInStockText.setText("");
				StorageIDText.setText("");
				StorageTypeText.setText("");
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "Fill all the required items correctly !", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_1 = new JLabel("(*)Required");
		lblNewLabel_1.setBounds(391, 207, 89, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		
		JLabel lblStorageType = new JLabel("Storage Type");
		lblStorageType.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStorageType.setBounds(10, 131, 182, 28);
		panel.add(lblStorageType);
		
		StorageTypeText = new JTextField();
		StorageTypeText.setColumns(10);
		StorageTypeText.setBounds(187, 139, 211, 20);
		panel.add(StorageTypeText);
		
		JPanel UpdatePanel = new JPanel();
		UpdatePanel.setLayout(null);
		UpdatePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Delete/Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		UpdatePanel.setBounds(10, 446, 473, 69);
		contentPane.add(UpdatePanel);
		
		JLabel SearchDeleteID = new JLabel("ID");
		SearchDeleteID.setFont(new Font("Tahoma", Font.BOLD, 20));
		SearchDeleteID.setBounds(24, 30, 97, 20);
		UpdatePanel.add(SearchDeleteID);
		
		IDText = new JTextField();
		IDText.setColumns(10);
		IDText.setBounds(81, 30, 211, 20);
		UpdatePanel.add(IDText);
		
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !IDText.getText().isEmpty()&& IDText.getText().matches("\\d+")) {
					int StorageID = Integer.parseInt(IDText.getText());
					String[] info = new String[3];
					SqlExecute.StorageSearch(StorageID, info);
					if (info[0] != null) {
						StorageIDText.setText(IDText.getText());
						QuantityInStockText.setText(info[0]);
						PharmacyIDCombo.setSelectedItem(info[1]);
						StorageTypeText.setText(info[2]);
						IDText.setText("");
					}
					
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "The Storage ID is wrong!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
					
				    
				
			}
		});
		
		
		btnSearch.setBounds(77, 515, 89, 59);
		contentPane.add(btnSearch);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(176, 515, 89, 59);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!IDText.getText().isEmpty() && IDText.getText().matches("\\d+")) {
					int StorageID = Integer.parseInt(IDText.getText());
					SqlExecute.StorageDelete(StorageID);
					IDText.setText("");
					table_load() ; 
					FillComboBox() ;
				}else {
				    JOptionPane.showMessageDialog(new JFrame(), "The Storage ID is wrong!", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane StorageTable = new JScrollPane();
		StorageTable.setBounds(486, 174, 399, 447);
		contentPane.add(StorageTable);
		
		table = new JTable();
		StorageTable.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 885, 150);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(StorageFrame.class.getResource("/images/th.jpg")));
		panel_1.add(lblNewLabel_3);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 611, 113, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = new MainFrame();
		        mainFrame.getFrmMain().setVisible(true);
		        dispose(); 
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		table_load() ;
		FillComboBox() ;
	}
	}