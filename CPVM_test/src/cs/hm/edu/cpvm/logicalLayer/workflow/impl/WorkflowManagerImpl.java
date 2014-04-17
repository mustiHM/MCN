package cs.hm.edu.cpvm.logicalLayer.workflow.impl;

import java.util.ArrayList;
import java.util.HashMap;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.common.models.CustomervaluesConfiguration;
import cs.hm.edu.cpvm.dataLayer.DBAccessor;
import cs.hm.edu.cpvm.dataLayer.impl.DBAccessorMock;
import cs.hm.edu.cpvm.logicalLayer.calculation.Calculation;
import cs.hm.edu.cpvm.logicalLayer.calculation.impl.CalculationImpl;
import cs.hm.edu.cpvm.logicalLayer.workflow.WorkflowManager;

/**
 * Implementiert den Workflow-Manager speziell angepasst an die Bedürfnisse und den Komponenten des Prototyps.
 * @author Mustafa
 *
 */
public class WorkflowManagerImpl implements WorkflowManager {

	private Calculation calculation;
	private DBAccessor db;
	private ArrayList<Customervalues> allValues;
	
	private static boolean isCalculationDone;
	
	public WorkflowManagerImpl(){
		//TODO DBAccessor und Validation initialisieren
		db = new DBAccessorMock();
	}
	
	public ArrayList<Customervalues> getCustomervalues() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomervalues(ArrayList<Customervalues> values)
			throws DBException, ValidationException {
		// TODO Auto-generated method stub

	}

	public void startCalculation() throws DBException {
		isCalculationDone = false;
		
		allValues = db.getAllCustomervalues();
		HashMap<String, Double> config = db.getAllCustomervaluesConfigurations();
		CalculationImpl.setAllCustomervalues(allValues);
		CalculationImpl.prepareCalculations();
		
		for(int i=0; i<allValues.size();i++){
			CalculationImpl calculation = new CalculationImpl();
			calculation.setCustomervalues(allValues.get(i));
			calculation.setCustomervaluesConfigurations(config);
			calculation.start();
		}
		
		while(CalculationImpl.getNumberOfWaitingThreads()!=allValues.size()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		CalculationImpl.startStatisticalCalculation();
		
		// speichern in DB
		db.updateCustomervalues(allValues);
		
		isCalculationDone = true;
		
		

	}

	public boolean isCalculationDone() {
		return isCalculationDone;
	}

	public ArrayList<CustomervaluesConfiguration> getCustomervaluesConfigurations()
			throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomervaluesConfigurations(
			ArrayList<CustomervaluesConfiguration> configs) throws DBException {
		// TODO Auto-generated method stub

	}

}
