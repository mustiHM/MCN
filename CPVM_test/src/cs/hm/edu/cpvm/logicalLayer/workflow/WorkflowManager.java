package cs.hm.edu.cpvm.logicalLayer.workflow;

import java.util.ArrayList;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.Customerdata;
import cs.hm.edu.cpvm.common.models.Customervalues;

/**
 * Steuert die einzelnen Komponenten und bietet der GUI Dienste der Komponenten an.
 * @author Mustafa
 *
 */
public interface WorkflowManager {

	/**
	 * L�dt alle Kundenwerte aus der Datenbank. (Zuerst DBA.getAllCustomervalues und mit der CustomerId dann DBA.getCustomerdata)
	 * @return alle Kundenwerte
	 * @throws DBException Exception falls DB nicht verf�gbar.
	 */
	public ArrayList<Customervalues> getCustomervalues() throws DBException;
	
	/**
	 * Validiert und speichert die Kundenwerte in die Datenbank.
	 * @param values zu speichernde Kundenwerte.
	 * @throws DBException falls DB nicht verf�gbar.
	 * @throws ValidationException falls Daten nicht valide sind.
	 */
	public void updateCustomervalues(ArrayList<Customervalues> values) throws DBException, ValidationException;
	
	
	/**
	 * Startet die Berechnung der Kundenwerte und erstellt f�r jeden Kundendatensatz einen Berechnungs-Thread aus der Calculation-Komponente.
	 */
	public void startCalculation();
	
	/**
	 * �berwacht die Berechnungen und gibt zur�ck, ob diese fertiggelaufen ist.
	 * @return true, falls Berechnungen fertig sind. Andernfalls false.
	 * @throws CalculationTimeoutException 
	 */
	public boolean isCalculationDone();
}
