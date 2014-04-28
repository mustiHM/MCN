package cs.hm.edu.cpvm.dataLayer.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;






import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.models.CalculationLogging;
import cs.hm.edu.cpvm.common.models.Customerdata;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.dataLayer.DBAccessor;

public class DBAccessorImpl implements DBAccessor {

	private String dbUrl;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private boolean isConnected=false;
	private Connection dbConnection;
	
	/**
	 * Überprüft erst ob eine Datenbank-Verbindung besteht und verbindet sich anschließend mit dieser.
	 * Hierfür wird das Config file db.properties genutzt.
	 * @throws FileNotFoundException Fehler, falls Config file nicht verfügbar.
	 * @throws IOException Fehler beim Lesen des Config files.
	 * @throws SQLException Datenbank Fehler.
	 */
	private void connect() throws FileNotFoundException, IOException, SQLException{
		if(!isConnected){
			Properties p = new Properties();
			p.load(new FileInputStream("conf/db.properties"));
			dbUrl = p.getProperty("dbAddress");
			dbName = p.getProperty("dbName");
			dbUser = p.getProperty("dbUser");
			dbPassword = p.getProperty("dbPassword");
			
			dbConnection = DriverManager.getConnection("jdbc:mysql://"+ dbUrl + "/" + dbName  + "?"
					+ "user=" + dbUser + "&password=" + dbPassword);
			
			isConnected = true;
		}
		
	}
	
	private Customerdata getCustomerdata(int id) throws SQLException{
		Customerdata customer = new Customerdata();
		PreparedStatement pstmt = dbConnection.prepareStatement("SELECT * FROM CUSTOMERDATA WHERE ID = ?");
		pstmt.setInt(1, id);
		ResultSet rset = pstmt.executeQuery();
		 while (rset.next()) {
			 customer.setId(rset.getInt("id"));
			 customer.setFirstName(rset.getString("FirstName"));
			 customer.setLastName(rset.getString("LastName"));
			 customer.setStreet(rset.getString("Street"));
			 customer.setNumber(rset.getString("Number"));
			 customer.setZip(rset.getInt("Zip"));
			 customer.setCity(rset.getString("City"));
			 customer.setBirthday(""+rset.getTimestamp("Birthday"));
		 }
		
		return customer;
	}
	
	@Override
	public ArrayList<Customervalues> getAllCustomervalues() throws DBException {
		ArrayList<Customervalues> allCustomervalues = new ArrayList<Customervalues>();
		
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("SELECT * FROM CUSTOMERVALUES");
			ResultSet rset = pstmt.executeQuery();
			 while (rset.next()) {
				 Customervalues customervalues = new Customervalues();
				 customervalues.setId(rset.getInt("id"));
				 customervalues.setCustomerdata(getCustomerdata(rset.getInt("id")));
				 customervalues.setProfit(rset.getDouble("Profit"));
				 // TODO weitere bla
				 
			 }
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		return allCustomervalues;
	}

	@Override
	public void updateAllCustomervalues(ArrayList<Customervalues> values)
			throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomervalues(Customervalues values) throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Customervalues> getCustomervaluesForCustomerdata(
			ArrayList<Customerdata> customers) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customerdata> getAllCustomerdata() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomerdata(Customerdata customer) throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Double> getAllCustomervaluesConfigurations()
			throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomervaluesConfiguration(
			HashMap<String, Double> configurations) throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public CalculationLogging getLastCalculationLogging() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCalculationLogs(ArrayList<String> logs) throws DBException {
		// TODO Auto-generated method stub
		
	}

}
