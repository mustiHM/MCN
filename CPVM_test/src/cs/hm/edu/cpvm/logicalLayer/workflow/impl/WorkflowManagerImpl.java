package cs.hm.edu.cpvm.logicalLayer.workflow.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.CalculationLogging;
import cs.hm.edu.cpvm.common.models.Customerdata;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.common.models.CustomervaluesConfiguration;
import cs.hm.edu.cpvm.dataLayer.DBAccessor;
import cs.hm.edu.cpvm.dataLayer.impl.DBAccessorMock;
import cs.hm.edu.cpvm.logicalLayer.calculation.Calculation;
import cs.hm.edu.cpvm.logicalLayer.calculation.impl.CalculationImpl;
import cs.hm.edu.cpvm.logicalLayer.validation.Validation;
import cs.hm.edu.cpvm.logicalLayer.workflow.WorkflowManager;

/**
 * Implementiert den Workflow-Manager speziell angepasst an die Bedürfnisse und den Komponenten des Prototyps.
 * @author Mustafa
 *
 */
public class WorkflowManagerImpl implements WorkflowManager {

	private Calculation calculation;
	private DBAccessor db;
	private Validation validation;
	private ArrayList<Customervalues> allValues;
	private HashMap<String, Double> configurations;
	private ArrayList<String> protocol;
	private ArrayList<CalculationImpl> calculationThreads;
	
	private static boolean isCalculationDone;
	
	public WorkflowManagerImpl(){
		//TODO DBAccessor und Validation initialisieren
		db = new DBAccessorMock();
	}
	
	public ArrayList<Customervalues> getCustomervalues() throws DBException {
		ArrayList<Customerdata> customers = db.getAllCustomerdata();
		allValues = db.getCustomervaluesForCustomerdata(customers);
		
		return allValues;
	}

	public boolean updateCustomervalues(ArrayList<Customervalues> allValues)
			throws DBException, ValidationException {
		for(int i=0; i<allValues.size();i++){
			Customervalues values = allValues.get(i);
			// Kundendaten speichern
			db.updateCustomerdata(values.getCustomerdata());
			
			// Kundenwerte validieren
			// momentan nicht angefordert!
			// TODO mit echter DB nochmal prüfen: entweder unterer Befehl updateAll oder einzeln via updateCustomer..
			//db.updateCustomervalues(values);
		}
		
		db.updateAllCustomervalues(allValues);
		return true;
	}

	public void startCalculation() throws DBException {
		isCalculationDone = false;
		protocol = new ArrayList<String>();
		calculationThreads = new ArrayList<CalculationImpl>();
		allValues = db.getAllCustomervalues();
		HashMap<String, Double> config = db.getAllCustomervaluesConfigurations();
		CalculationImpl.setAllCustomervalues(allValues);
		CalculationImpl.prepareCalculations();
		
		for(int i=0; i<allValues.size();i++){
			CalculationImpl calculation = new CalculationImpl();
			calculation.setCustomervalues(allValues.get(i));
			calculation.setCustomervaluesConfigurations(config);
			calculation.start();
			calculationThreads.add(calculation);
		}
		
		while(CalculationImpl.getNumberOfWaitingThreads()!=allValues.size()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		CalculationImpl.startStatisticalCalculation();
		
		while(CalculationImpl.getNumberOfFinishedThreads()!=allValues.size()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// speichern in DB
		db.updateAllCustomervalues(allValues);
		
		// logs holen
		for(int i=0; i<calculationThreads.size();i++){
			protocol.add(calculationThreads.get(i).getProtocol());
		}
		
		db.saveCalculationLogs(protocol);
		
		isCalculationDone = true;
		
		

	}

	public boolean isCalculationDone() {
		return isCalculationDone;
	}

	public HashMap<String, Double> getCustomervaluesConfigurations()
			throws DBException {
		configurations =  db.getAllCustomervaluesConfigurations();
		return configurations;
	}

	public boolean updateCustomervaluesConfigurations(
			HashMap<String, Double> configs) throws DBException {
		double totalValue = 0;
		
		totalValue = configs.get("VertRenta")+configs.get("VertROI")+configs.get("VertDB")
				+configs.get("VertSkE")+configs.get("VertIW")+configs.get("VertCUP")+configs.get("VertLP");
		if(totalValue>100.00){
			return false;
		}
		else{
			configurations.putAll(configs);
			
			// neue Werte in DB speichern
			db.updateCustomervaluesConfiguration(configurations);
			return true;	
		}
		
	}

	@Override
	public CalculationLogging getLastCalculationLogging() throws DBException {
		return db.getLastCalculationLogging();
	}

}
