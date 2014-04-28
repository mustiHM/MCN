package cs.hm.edu.cpvm.dataLayer.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	private void connect() throws FileNotFoundException, IOException{
		Properties p = new Properties();
		p.load(new FileInputStream("conf/db.properties"));
		dbUrl = p.getProperty("dbAddress");
		dbName = p.getProperty("dbName");
		dbUser = p.getProperty("dbUser");
		dbPassword = p.getProperty("dbPassword");
		
		
	}
	
	@Override
	public ArrayList<Customervalues> getAllCustomervalues() throws DBException {
		// TODO Auto-generated method stub
		return null;
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
