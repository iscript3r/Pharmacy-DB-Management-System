import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class SQL {
	
	
	
	
	public Connection Connect() {
		Connection connection = null ; 
		try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://<host>:<port>/<database>", "<username>", "<password>");
         }
        catch (Exception exception) {
            System.out.println(exception);
        }
		 return connection ; 
	}
	
	
	public void PharmacyInsert(int StoreID,String Name , String Address , String City) {
		  Connection connection = null;
	        try {
	            connection = Connect() ; 
	 
	            String InsertQuery = "INSERT INTO pharmacy"
	            		 + " (`Store_ID`,`Pharmacy_Name`, `Address`, `City`)"
	            		 + "VALUES('"+StoreID+"','"+Name+"','"+Address+"','"+City+"')" ; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(InsertQuery) ; 
	            JOptionPane.showMessageDialog(null, "Pharmacy is Added !") ;
	            statement.close();
	            connection.close();
	        }catch(SQLIntegrityConstraintViolationException e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "The ID is duplicate !", "ERROR",
			            JOptionPane.ERROR_MESSAGE);	       
	        	}
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void PharmacySearch(int StoreID , String[] info) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;

	 
	            String SearchQuery = "SELECT Store_ID,Pharmacy_Name,Address,City "
	            		 + "FROM pharmacy "
	            		 + "WHERE Store_ID = '"+StoreID+"'";
	            
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            	 JOptionPane.showMessageDialog(null, "Pharmacy found !\n");
	            	info[0] = RS.getString(2); 
	            	info[1] = RS.getString(3) ;
	            	info[2]  = RS.getString(4) ;
	            }else {
		            JOptionPane.showMessageDialog(null, "Pharmacy not found !\n") ;
	            }
	            
	            statement.close();
	            connection.close();
	            
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}

	public void PharmacyUpdate(int StoreID,String Name , String Address , String City) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	 //Checking if the ID exists before updating
	        	String SearchQuery = "SELECT * FROM pharmacy WHERE Store_ID = '"+ StoreID +"'";
	            Statement Searchstatement = connection.createStatement();
	            ResultSet RS = Searchstatement.executeQuery(SearchQuery) ; 
	            
	            if(RS.next()) {
	            String UpdateQuery = "UPDATE pharmacy"
	            		 + " set Pharmacy_Name = '"+Name+"' ,Address = '"+Address+"' ,City = '"+City+"'" 
	            		 + "WHERE Store_ID = '" + StoreID + "'"; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Pharmacy is Updated !") ;
	            statement.close();
	            connection.close();
	            }else {
	            	JOptionPane.showMessageDialog(new JFrame(), "The ID is not found for updating !", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void PharmacyDelete(int StoreID) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	String SearchQuery = "SELECT * FROM pharmacy WHERE Store_ID = '"+ StoreID +"'";
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            
	            if(RS.next()) {
	            String UpdateQuery = "DELETE FROM pharmacy WHERE Store_ID = '" + StoreID + "'";  
	            statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Pharmacy is Deleted !") ;
	            }else {
		            JOptionPane.showMessageDialog(null, "Pharmacy not found !\n") ;
	            }
	            statement.close();
	            connection.close();
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}

	
	public void StorageInsert(int StorageID,String QuantityInStock , int PharmacyID , String StorageType) {
		  Connection connection = null;
	        try {
	            connection = Connect() ; 
	 
	            String InsertQuery = "INSERT INTO Storage"
	            		 + " (`Storage_ID`,`Quantity_In_Stock`, `Pharmacy_Store_ID` , `Storage_Type`)"
	            		 + "VALUES('"+StorageID+"','"+QuantityInStock+"','"+PharmacyID+"','"+StorageType+"')"; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(InsertQuery) ; 
	            JOptionPane.showMessageDialog(null, "Storage is Added !") ;
	            statement.close();
	            connection.close();
	        }catch(SQLIntegrityConstraintViolationException e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "The Storage/Pharamacy ID is duplicate !", "ERROR",
			            JOptionPane.ERROR_MESSAGE);	       
	        	}
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void StorageSearch(int StorageID , String[] info) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;

	 
	            String SearchQuery = "SELECT Storage_ID,Quantity_In_Stock,Pharmacy_Store_ID ,Storage_Type "
	            		 + "FROM storage "
	            		 + "WHERE Storage_ID = '"+StorageID+"'";
	            
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            	 JOptionPane.showMessageDialog(null, "Storage found !\n");
		            	info[0] = RS.getString(2); 
		            	info[1] = RS.getString(3);
		            	info[2] = RS.getString(4); 
	            }else {
		            JOptionPane.showMessageDialog(null, "Storage not found !\n") ;
	            }
	            
	            statement.close();
	            connection.close();
	            
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void StorageUpdate(int StorageID,String QuantityInStock , String StorageType) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	 //Checking if the ID exists before updating
	        	String SearchQuery = "SELECT * FROM Storage WHERE Storage_ID = '"+ StorageID +"'";
	            Statement Searchstatement = connection.createStatement();
	            ResultSet RS = Searchstatement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            String UpdateQuery = "UPDATE Storage"
	                    + " SET Quantity_In_Stock = '" + QuantityInStock 
	                    + "', Storage_Type = '" + StorageType + "'"
	                    + " WHERE Storage_ID = '" + StorageID + "'"; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Storage is Updated !") ;
	            statement.close();
	            connection.close();
	            }else {
	            	JOptionPane.showMessageDialog(new JFrame(), "The ID is not found for updating !", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void StorageDelete(int StorageID) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	String SearchQuery = "SELECT * FROM storage WHERE Storage_ID = '"+ StorageID +"'";
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            
	            if(RS.next()) {
	            String UpdateQuery = "DELETE FROM storage WHERE Storage_ID = '" + StorageID + "'";  
	            statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Storage is Deleted !") ;
	            }else {
		            JOptionPane.showMessageDialog(null, "Storage not found !\n") ;
	            }
	            statement.close();
	            connection.close();
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	
	
	public void ManufacturerInsert(int ManufacturerID,String Name , String Location) {
		  Connection connection = null;
	        try {
	            connection = Connect() ; 
	 
	            String InsertQuery = "INSERT INTO MANUFACTURER"
	            		 + " (`Manufacturer_ID`,`Name`, `Location`)"
	            		 + "VALUES('"+ManufacturerID+"','"+Name+"','"+Location+"')" ; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(InsertQuery) ; 
	            JOptionPane.showMessageDialog(null, "Manufacturer is Added !") ;
	            statement.close();
	            connection.close();
	        }catch(SQLIntegrityConstraintViolationException e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "The ID is duplicate !", "ERROR",
			            JOptionPane.ERROR_MESSAGE);	       
	        	}
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}	
	
	public void ManufacturerSearch(int ManufacturerID , String[] info) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;

	 
	            String SearchQuery = "SELECT Manufacturer_ID,Name,Location "
	            		 + "FROM Manufacturer "
	            		 + "WHERE Manufacturer_ID = '"+ManufacturerID+"'";
	            
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            	JOptionPane.showMessageDialog(null, "Manufacturer found !\n");
	            	info[0]= RS.getString(2) ;
	            	info[1] = RS.getString(3) ; 
	            }else {
		            JOptionPane.showMessageDialog(null, "Manufacturer not found !\n") ;
	            }
	            
	            statement.close();
	            connection.close();
	            
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void ManufacturerUpdate(int ManufacturerID,String Name,String location) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	 //Checking if the ID exists before updating
	        	String SearchQuery = "SELECT * FROM Manufacturer WHERE Manufacturer_ID = '"+ ManufacturerID +"'";
	            Statement Searchstatement = connection.createStatement();
	            ResultSet RS = Searchstatement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            String UpdateQuery = "UPDATE Manufacturer"
	            		 + " set Name = '"+Name 
	            		 + "' ,Location= '"+location+"'"
	            		 + "WHERE Manufacturer_ID = '" + ManufacturerID + "'"; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Manufacturer is Updated !") ;
	            statement.close();
	            connection.close();
	            }else {
	            	JOptionPane.showMessageDialog(new JFrame(), "The ID is not found for updating !", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void ManufacturerDelete(int ManufacturerID) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	String SearchQuery = "SELECT * FROM Manufacturer WHERE Manufacturer_ID = '"+ ManufacturerID +"'";
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            
	            if(RS.next()) {
	            String UpdateQuery = "DELETE FROM Manufacturer WHERE Manufacturer_ID = '" + ManufacturerID + "'";  
	             statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Manufacturer is Deleted !") ;
	            }else {
		            JOptionPane.showMessageDialog(null, "Manufacturer not found !\n") ;
	            }
	            statement.close();
	            connection.close();
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	
	public void MedicationInsert(int MedicationID,String Name , String Expirydate, int StorageID) {
		  Connection connection = null;
	        try {
	            connection = Connect() ; 
	            String InsertQuery="" ;
	            if(StorageID != -1) {      
	           InsertQuery = "INSERT INTO Medication"
	            		 + " (`Medication_ID`, `Medication_Name`, `Expiry_Date`,`Storage_Storage_ID`)"
	            		 + "VALUES('"+MedicationID+"','"+Name+"','"+Expirydate+"',"+StorageID+")" ; 
	            }else {
	            InsertQuery = "INSERT INTO Medication"
		            		 + " (`Medication_ID`, `Medication_Name`, `Expiry_Date`)"
		            		 + "VALUES('"+MedicationID+"','"+Name+"','"+Expirydate+"')" ; 
	            }
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(InsertQuery) ; 
	            JOptionPane.showMessageDialog(null, "Medication is Added !") ;
	            statement.close();
	            connection.close();
	        }catch(SQLIntegrityConstraintViolationException e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "The ID is duplicate !", "ERROR",
			            JOptionPane.ERROR_MESSAGE);	       
	        	}
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}	
	
	public void MedicationSearch(int MedicationID , String[] info) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;

	 
	            String SearchQuery = "SELECT Medication_ID,Medication_Name,Expiry_Date,Storage_Storage_ID "
	            		 + "FROM Medication "
	            		 + "WHERE Medication_ID = '"+MedicationID+"'";
	            
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            	JOptionPane.showMessageDialog(null, "Medication found !\n");
	            	info[0] = RS.getString(2) ;
	            	info[1] = RS.getString(3) ;
	            	info[2] = RS.getString(4) ;
		            
	            }else {
	            	JOptionPane.showMessageDialog(null, "Medication not found !\n") ;
	            }
	            
	            statement.close();
	            connection.close();
	            
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void MedicationUpdate(int MedicationID,String Name,String Expirydate , int StorageID) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	 //Checking if the ID exists before updating
	        	String SearchQuery = "SELECT * FROM Medication WHERE Medication_ID = '"+ MedicationID +"'";
	            Statement Searchstatement = connection.createStatement();
	            ResultSet RS = Searchstatement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            	String UpdateQuery = "UPDATE Medication"
	            		    + " SET Medication_Name = '" + Name 
	            		    + "', Expiry_Date = '" + Expirydate + "'";

	            		if (StorageID != -1) {
	            		    UpdateQuery += ", Storage_Storage_ID = '" + StorageID + "'";
	            		}

	            		UpdateQuery += " WHERE Medication_ID = '" + MedicationID + "'";
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Medication is Updated !") ;
	            statement.close();
	            connection.close();
	            }else {
	            	JOptionPane.showMessageDialog(new JFrame(), "The ID is not found for updating !", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void MedicationDelete(int MedicationID) {
		  Connection connection = null;
	        try {
	        	connection = Connect() ;
	        	String SearchQuery = "SELECT * FROM Medication WHERE Medication_ID = '"+ MedicationID +"'";
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            String UpdateQuery = "DELETE FROM Medication WHERE Medication_ID = '" + MedicationID + "'";  
	             statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Medication is Deleted !") ;
	            }else {
	            	JOptionPane.showMessageDialog(null, "Medication not found !\n") ;
	            }
	            statement.close();
	            connection.close();
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	
	/*public void MedicationSIDInsert(int MedicationID,int StorageID) {
		  Connection connection = null;
	        try {
	            connection = Connect() ; 
	 
	            String InsertQuery = "INSERT INTO Medication"
	            		 + " (`Medication_ID`,`Storage_Storage_ID`)"
	            		 + "VALUES('"+MedicationID+"',"+StorageID+")" ; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(InsertQuery) ; 
	            JOptionPane.showMessageDialog(null, "Manufacturer is Added !") ;
	            statement.close();
	            connection.close();
	        }catch(SQLIntegrityConstraintViolationException e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "The ID is duplicate !", "ERROR",
			            JOptionPane.ERROR_MESSAGE);	       
	        	}
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}*/
	
	public void Manufacturer_Produce_MedicationInsert(int MedicationID,int ManufacturerID , String PackagingDetails) {
		  Connection connection = null;
	        try {
	            connection = Connect() ; 
	 
	            String InsertQuery = "INSERT INTO manufacturer_produce_medication "
	            		 + " (`Medication_Medication_ID`,`Manufacturer_Manufacturer_ID` ,`Packaging_Details`)"
	            		 + "VALUES('"+MedicationID+"','"+ManufacturerID+"', '"+ PackagingDetails+"')"; 
	            
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(InsertQuery) ; 
	            JOptionPane.showMessageDialog(null, "Manufacturer_Produce_Medication is Added !") ;
	            statement.close();
	            connection.close();
	        }catch(SQLIntegrityConstraintViolationException e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "The ID is duplicate !", "ERROR",
			            JOptionPane.ERROR_MESSAGE);	       
	        	}
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void Manufacturer_Produce_MedicationDelete(int ManufacturerID,int MedicationID) {
		  Connection connection = null;
	        try {
	          	connection = Connect() ;
	            String UpdateQuery = "DELETE FROM manufacturer_produce_medication WHERE Medication_Medication_ID = '" + MedicationID + "'"
	            		+ "AND Manufacturer_Manufacturer_ID= '"+ ManufacturerID+"'";  
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(UpdateQuery) ; 
	            JOptionPane.showMessageDialog(null, "Manufacturer_Produce_Medication is Deleted !") ;
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
	
	public void Manufacturer_Produce_MedicationSearch( int MedicationID,int ManufacturerID, String info[]){
		  Connection connection = null;
	        try {
	        	connection = Connect() ;

	 
	            String SearchQuery = "SELECT *"
	            		 + "FROM manufacturer_produce_medication "
	            		 + "WHERE  Medication_Medication_ID = '"+MedicationID+"'"
	            		 		+ "and Manufacturer_Manufacturer_ID = '"+ManufacturerID+"'";
	            
	            Statement statement = connection.createStatement();
	            ResultSet RS = statement.executeQuery(SearchQuery) ; 
	            if(RS.next()) {
	            	JOptionPane.showMessageDialog(null, "Manufacturer_Produce_Medication found !\n");
	            	info[0] = RS.getString(1) ;
	            	info[1] = RS.getString(2) ;
	            	info[2] = RS.getString(3) ;
		            
	            }else {
	            	JOptionPane.showMessageDialog(null, "Manufacturer_Produce_Medication not found !\n") ;
	            }
	            
	            statement.close();
	            connection.close();
	            
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	}
public void Manufacturer_Produce_MedicationUpdate(int MId, int MEID,String PackagingDetails){
	  Connection connection = null;
      try {
      	connection = Connect() ;
      	 //Checking if the ID exists before updating
      	String SearchQuery = "SELECT * FROM manufacturer_produce_medication WHERE Medication_Medication_ID = '"+ MEID +"'"
      			+ "and  Manufacturer_Manufacturer_ID = '"+MId+"'";
          Statement Searchstatement = connection.createStatement();
          ResultSet RS = Searchstatement.executeQuery(SearchQuery) ; 
          if(RS.next()) {
          	String UpdateQuery = "UPDATE manufacturer_produce_medication"
          		    + " SET Manufacturer_Manufacturer_ID = '" + MId 
          		    + "', Medication_Medication_ID = '" + MEID + "'"
          		    		+ ", Packaging_Details = '"+PackagingDetails+"'"
          		    				+ "WHERE Medication_Medication_ID = '"+ MEID+ "'" 
          		    				+ "and  Manufacturer_Manufacturer_ID = '" +MId +"'";

          Statement statement = connection.createStatement();
          statement.executeUpdate(UpdateQuery) ; 
          JOptionPane.showMessageDialog(null, "Manufacturer_Produce_Medication is Updated !") ;
          statement.close();
          connection.close();
          }else {
          	JOptionPane.showMessageDialog(new JFrame(), "The ID is not found for updating !", "ERROR",
			            JOptionPane.ERROR_MESSAGE);
          }
      }
      catch (Exception exception) {
          System.out.println(exception);
      }
}
	
	
	
	
	
}






	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

