package cs.hm.edu.cpvm.dataLayer.impl;

import java.util.ArrayList;
import java.util.HashMap;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.models.Customerdata;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.dataLayer.DBAccessor;

/**
 * Stellt einen Mock-Service zur Verfügung und simuliert die Datenbank.
 * @author Mustafa
 *
 */
public class DBAccessorMock implements DBAccessor {

	private ArrayList<Customervalues> allValues;
	private HashMap<String, Double> configs;
	
	
	@Override
	public ArrayList<Customervalues> getAllCustomervalues() throws DBException {
		return allValues;
	}

	@Override
	public void updateCustomervalues(ArrayList<Customervalues> values)
			throws DBException {
		allValues = values;
	}

	@Override
	public ArrayList<Customervalues> getCustomervaluesForCustomerdata(
			ArrayList<Customerdata> customers) throws DBException {
		return allValues;
	}

	@Override
	public ArrayList<Customerdata> getAllCustomerdata() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Double> getAllCustomervaluesConfigurations()
			throws DBException {
		return configs;
	}

	@Override
	public void updateCustomervaluesConfiguration(
			HashMap<String, Double> configurations) throws DBException {
		// TODO Auto-generated method stub

	}
	
	public DBAccessorMock(){
		
		allValues = new ArrayList<Customervalues>();
		
		Customerdata customer = new Customerdata();
		customer.setFirstName("TestVorname");
		customer.setLastName("TestNachname");
		
		Customerdata customer2 = new Customerdata();
		customer2.setFirstName("VorNameZwei");
		customer2.setLastName("NachnameZwei");
		
		Customerdata customer3 = new Customerdata();
		customer3.setFirstName("VorNameDrei");
		customer3.setLastName("NachnameDrei");
		
		Customerdata customer4 = new Customerdata();
		customer4.setFirstName("VorNameVier");
		customer4.setLastName("NachnameVier");
		
		Customerdata customer5 = new Customerdata();
		customer5.setFirstName("VorNameFuenf");
		customer5.setLastName("NachnameFuenf");
		
		
		
		// Übernehmen der Kundendaten von "Kunde 1" aus dem Excelsheet.
		Customervalues values = new Customervalues();
		values.setCustomerdata(customer);
		values.setProfit(150);
		values.setSales(1800);
		values.setContracts(2);
		values.setInformationValue(1.2);
		values.setCup(2);
		values.setLoyality(1);
		
		Customervalues values2 = new Customervalues();
		values2.setCustomerdata(customer2);
		values2.setProfit(270);
		values2.setSales(44526);
		values2.setContracts(15);
		values2.setInformationValue(3.7);
		values2.setCup(4);
		values2.setLoyality(4);
		
		Customervalues values3 = new Customervalues();
		values3.setCustomerdata(customer3);
		values3.setProfit(42);
		values3.setSales(6525);
		values3.setContracts(6);
		values3.setInformationValue(0);
		values3.setCup(5);
		values3.setLoyality(3);
		

		Customervalues values4 = new Customervalues();
		values4.setCustomerdata(customer4);
		values4.setProfit(45565);
		values4.setSales(100134);
		values4.setContracts(24);
		values4.setInformationValue(0);
		values4.setCup(2);
		values4.setLoyality(4);
		

		Customervalues values5 = new Customervalues();
		values5.setCustomerdata(customer5);
		values5.setProfit(667);
		values5.setSales(6433);
		values5.setContracts(7);
		values5.setInformationValue(1.6);
		values5.setCup(3);
		values5.setLoyality(3);
		
		allValues.add(values);
		allValues.add(values2);
		allValues.add(values3);
		allValues.add(values4);
		allValues.add(values5);
		
		

		configs = new HashMap<String, Double>();
		configs.put("Investitionssatz", 0.9);
		configs.put("DBU-Faktor", 0.4);
		configs.put("GewRenta", 0.1);
		configs.put("GewROI", 0.09);
		configs.put("GewDB", 0.0008);
		configs.put("GewCUP", 0.07);
		configs.put("GewLP", 0.06);
		configs.put("GewIW", 0.05);
		configs.put("GewSkE", 0.04);
		configs.put("VertRenta", 21.0);
		configs.put("VertROI", 19.0);
		configs.put("VertDB", 16.0);
		configs.put("VertSkE", 8.0);
		configs.put("VertIW", 10.0);
		configs.put("VertCUP", 14.0);
		configs.put("VertLP", 12.0);
		
	}

}
