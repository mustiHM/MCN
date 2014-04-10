package cs.hm.edu.cpvm.logicalLayer.calculation.tests;

import java.util.HashMap;

import cs.hm.edu.cpvm.common.models.Customerdata;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.common.models.CustomervaluesConfiguration;
import cs.hm.edu.cpvm.logicalLayer.calculation.Calculation;
import cs.hm.edu.cpvm.logicalLayer.calculation.impl.CalculationImpl;

/**
 * Testklasse zum Testen der Kalkulationskomponente.
 * Dient als Ersatz zu JUNIT-Tests (mit dem Kunden vereinbart!)
 * Eine manuelle Überprüfung mit dem Excelsheet ist nötig.
 * @author Mustafa
 *
 */
public class CalculationTests {

	public static void main(String[] args) {
		CalculationImpl calculation = new CalculationImpl();
		
		Customerdata customer = new Customerdata();
		customer.setFirstName("TestVorname");
		customer.setLastName("TestNachname");
		
		// Übernehmen der Kundendaten von "Kunde 1" aus dem Excelsheet.
		Customervalues values = new Customervalues();
		values.setCustomerdata(customer);
		values.setProfit(150);
		values.setSales(1800);
		values.setContracts(2);
		values.setInformationValue(1.2);
		values.setCup(2);
		values.setLoyality(1);
		

		HashMap<String, Double> configs = new HashMap<String, Double>();
		configs.put("Investitionssatz", 0.9);
		configs.put("DBU-Faktor", 0.4);
		configs.put("GewRenta", 0.1);
		configs.put("GewROI", 0.09);
		configs.put("GewDB", 0.0008);
		configs.put("GewCUP", 0.07);
		configs.put("GewLP", 0.06);
		configs.put("GewIW", 0.05);
		configs.put("GewSkE", 0.04);
		
		calculation.setCustomervalues(values);
		calculation.setCustomervaluesConfigurations(configs);
		
		calculation.start();
		
	}

}
