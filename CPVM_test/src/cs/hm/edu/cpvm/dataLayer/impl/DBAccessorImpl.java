package cs.hm.edu.cpvm.dataLayer.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
				 customervalues.setSales(rset.getDouble("Sales"));
				 customervalues.setContracts(rset.getInt("Contracts"));
				 customervalues.setSalesDeduction(rset.getDouble("SalesDeduction"));
				 customervalues.setInformationValue(rset.getDouble("InformationValue"));
				 customervalues.setCup(rset.getInt("CUP"));
				 customervalues.setRoi(rset.getDouble("Roi"));
				 customervalues.setProfitability(rset.getDouble("Profitability"));
				 customervalues.setProfitMargin(rset.getDouble("ProfitMargin"));
				 customervalues.setInvestment(rset.getDouble("Investment"));
				 customervalues.setScalefactor(rset.getDouble("Scalefactor"));
				 customervalues.setLoyality(rset.getInt("Loyality"));
				 customervalues.setCalculationDate(""+rset.getTimestamp("CalculationDate"));
				 customervalues.setCustomerValueResult1(rset.getDouble("CustomerValueResult1"));
				 customervalues.setCustomerValueResult1(rset.getDouble("CustomerValueResult2"));
				 
			 }
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		return allCustomervalues;
	}

	public void updateAllCustomervalues(ArrayList<Customervalues> values)
			throws DBException {
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("UPDATE CUSTOMERVALUES SET PROFIT = ?, SALES = ?, CONTRACTS = ?, SALESDEDUCTION = ?, INFORMATIONVALUE = ?, CUP = ?, ROI = ?, PROFITABILITY = ?, PROFITMARGIN = ?, INVESTMENT = ?"
					+ ", SCALEFACTOR = ?, LOYALITY = ?, CALCULATIONDATE = ?, CUSTOMERVALUERESULT1 = ?, CUSTOMERVALUERESULT2 = ? WHERE ID = ?");
			
			for(int i=0; i<values.size(); i++){
				Customervalues customer = values.get(i);
				pstmt.setDouble(1, customer.getProfit());
				pstmt.setDouble(2, customer.getSales());
				pstmt.setInt(3, customer.getContracts());
				pstmt.setDouble(4, customer.getSalesDeduction());
				pstmt.setDouble(5, customer.getInformationValue());
				pstmt.setDouble(6, customer.getCup());
				pstmt.setDouble(7, customer.getRoi());
				pstmt.setDouble(8, customer.getProfitability());
				pstmt.setDouble(9, customer.getProfitMargin());
				pstmt.setDouble(10, customer.getInvestment());
				pstmt.setDouble(11, customer.getScalefactor());
				pstmt.setDouble(12, customer.getLoyality());
				pstmt.setTimestamp(13, new Timestamp(Long.parseLong(customer.getCalculationDate())));
				pstmt.setDouble(14, customer.getCustomerValueResult1());
				pstmt.setDouble(15, customer.getCustomerValueResult2());
				pstmt.setInt(16, customer.getId());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
	}

	public void updateCustomervalues(Customervalues values) throws DBException {
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("UPDATE CUSTOMERVALUES SET PROFIT = ?, SALES = ?, CONTRACTS = ?, SALESDEDUCTION = ?, INFORMATIONVALUE = ?, CUP = ?, ROI = ?, PROFITABILITY = ?, PROFITMARGIN = ?, INVESTMENT = ?"
					+ ", SCALEFACTOR = ?, LOYALITY = ?, CALCULATIONDATE = ?, CUSTOMERVALUERESULT1 = ?, CUSTOMERVALUERESULT2 = ? WHERE ID = ?");
			
				pstmt.setDouble(1, values.getProfit());
				pstmt.setDouble(2, values.getSales());
				pstmt.setInt(3, values.getContracts());
				pstmt.setDouble(4, values.getSalesDeduction());
				pstmt.setDouble(5, values.getInformationValue());
				pstmt.setDouble(6, values.getCup());
				pstmt.setDouble(7, values.getRoi());
				pstmt.setDouble(8, values.getProfitability());
				pstmt.setDouble(9, values.getProfitMargin());
				pstmt.setDouble(10, values.getInvestment());
				pstmt.setDouble(11, values.getScalefactor());
				pstmt.setDouble(12, values.getLoyality());
				pstmt.setTimestamp(13, new Timestamp(Long.parseLong(values.getCalculationDate())));
				pstmt.setDouble(14, values.getCustomerValueResult1());
				pstmt.setDouble(15, values.getCustomerValueResult2());
				pstmt.setInt(16, values.getId());
				pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	@Override
	public ArrayList<Customervalues> getCustomervaluesForCustomerdata(
			ArrayList<Customerdata> customers) throws DBException {
		ArrayList<Customervalues> values = new ArrayList<Customervalues>();
		for(int i=0; i<customers.size(); i++){
			try {
				PreparedStatement pstmt = dbConnection.prepareStatement("SELECT * FROM CUSTOMERVALUES WHERE CUSTOMERDATA_ID = ?");
				pstmt.setInt(1, customers.get(i).getId());
				
				ResultSet rset = pstmt.executeQuery();
				 while (rset.next()) {
					 Customervalues customervalues = new Customervalues();
					 customervalues.setId(rset.getInt("id"));
					 customervalues.setCustomerdata(getCustomerdata(rset.getInt("id")));
					 customervalues.setProfit(rset.getDouble("Profit"));
					 customervalues.setSales(rset.getDouble("Sales"));
					 customervalues.setContracts(rset.getInt("Contracts"));
					 customervalues.setSalesDeduction(rset.getDouble("SalesDeduction"));
					 customervalues.setInformationValue(rset.getDouble("InformationValue"));
					 customervalues.setCup(rset.getInt("CUP"));
					 customervalues.setRoi(rset.getDouble("Roi"));
					 customervalues.setProfitability(rset.getDouble("Profitability"));
					 customervalues.setProfitMargin(rset.getDouble("ProfitMargin"));
					 customervalues.setInvestment(rset.getDouble("Investment"));
					 customervalues.setScalefactor(rset.getDouble("Scalefactor"));
					 customervalues.setLoyality(rset.getInt("Loyality"));
					 customervalues.setCalculationDate(""+rset.getTimestamp("CalculationDate"));
					 customervalues.setCustomerValueResult1(rset.getDouble("CustomerValueResult1"));
					 customervalues.setCustomerValueResult1(rset.getDouble("CustomerValueResult2"));
					 values.add(customervalues);
				 }
				
			} catch (SQLException e) {
				throw new DBException(e);
			}
		}
		
		return values;
	}

	public ArrayList<Customerdata> getAllCustomerdata() throws DBException {
		ArrayList<Customerdata> customers = new ArrayList<Customerdata>();
		
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("SELECT * FROM CUSTOMERDATA");
			ResultSet rset = pstmt.executeQuery();
			 while (rset.next()) {
				 Customerdata customer = new Customerdata();
				 customer.setId(rset.getInt("id"));
				 customer.setFirstName(rset.getString("FirstName"));
				 customer.setLastName(rset.getString("LastName"));
				 customer.setStreet(rset.getString("Street"));
				 customer.setNumber(rset.getString("Number"));
				 customer.setZip(rset.getInt("Zip"));
				 customer.setCity(rset.getString("City"));
				 customer.setBirthday(""+rset.getTimestamp("Birthday"));
				 customers.add(customer);
			 }
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		return customers;
	}

	
	public void updateCustomerdata(Customerdata customer) throws DBException {
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement("UPDATE CUSTOMERDATA SET FirstName = ?, LastName = ?, Street = ?, Number = ?, ZIP = ?, City = ?, Birthday = ? WHERE ID = ?");
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getStreet());
			pstmt.setString(4, customer.getNumber());
			pstmt.setInt(5, customer.getZip());
			pstmt.setString(6, customer.getCity());
			pstmt.setTimestamp(7, new Timestamp(Long.parseLong(customer.getBirthday())));
			pstmt.setInt(8, customer.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	
	public HashMap<String, Double> getAllCustomervaluesConfigurations()
			throws DBException {
		HashMap<String, Double> configs = new HashMap<String, Double>();
		
		try{
			PreparedStatement pstmt = dbConnection.prepareStatement("SELECT * FROM Customervalues_Configurations");
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				configs.put(rset.getString("CustomerValueName"), rset.getDouble("WeightingFactor"));
			}
		}catch (SQLException e) {
			throw new DBException(e);
		}
		
		return configs;
	}

	
	public void updateCustomervaluesConfiguration(
			HashMap<String, Double> configurations) throws DBException {
		try {
			
			for (Map.Entry<String, Double> entry : configurations.entrySet()) {
			    String key = entry.getKey();
			    Double value = entry.getValue();
			    PreparedStatement pstmt = dbConnection.prepareStatement("UPDATE Customervalues_Configurations SET WeightingFactor = ? WHERE CustomerValueName = ?");
				pstmt.setDouble(1, value);
				pstmt.setString(2, key);
				pstmt.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public CalculationLogging getLastCalculationLogging() throws DBException {
		CalculationLogging log = new CalculationLogging();
		
		//SELECT * FROM <table> ORDER BY id DESC LIMIT 1; 
		try{
			PreparedStatement pstmt = dbConnection.prepareStatement("SELECT * FROM Calculation_Logging ORDER BY id DESC LIMIT 1");
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				log.setId(rset.getInt("id"));
				Blob blob = rset.getBlob("LogMessage");
				byte[] bdata = blob.getBytes(1, (int) blob.length());
				String s = new String(bdata);
				log.setLogMessage(s);
				log.setLoggingDate(rset.getTimestamp("LoggingDate"));
			}
		}catch (SQLException e) {
			throw new DBException(e);
		}
		
		
		return log;
	}

	
	public void saveCalculationLogs(ArrayList<String> logs) throws DBException {
		try{
			StringBuilder totalText = new StringBuilder();
			for(int i=0; i<logs.size(); i++){
				totalText.append("\n" + logs.get(i));
			}
			PreparedStatement pstmt = dbConnection.prepareStatement("INSERT INTO Calculation_Logging (LogMessage) values (?)");
			Blob blob = dbConnection.createBlob();
			blob.setBytes(1, totalText.toString().getBytes());
			pstmt.setBlob(1, blob);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new DBException(e);
		}
	}

}
