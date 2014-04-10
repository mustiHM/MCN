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
	 * Lädt alle Kundenwerte aus der Datenbank. (Zuerst DBA.getAllCustomervalues und mit der CustomerId dann DBA.getCustomerdata)
	 * @return alle Kundenwerte
	 * @throws DBException Exception falls DB nicht verfügbar.
	 */
	public ArrayList<Customervalues> getCustomervalues() throws DBException;
	
	/**
	 * Validiert und speichert die Kundenwerte in die Datenbank.
	 * @param values zu speichernde Kundenwerte.
	 * @throws DBException falls DB nicht verfügbar.
	 * @throws ValidationException falls Daten nicht valide sind.
	 */
	public void updateCustomervalues(ArrayList<Customervalues> values) throws DBException, ValidationException;
	
	
	/**
	 * Startet die Berechnung der Kundenwerte und erstellt für jeden Kundendatensatz einen Berechnungs-Thread aus der Calculation-Komponente.
	 */
	public void startCalculation();
	
	/**
	 * Überwacht die Berechnungen und gibt zurück, ob diese fertiggelaufen ist.
	 * @return true, falls Berechnungen fertig sind. Andernfalls false.
	 * @throws CalculationTimeoutException 
	 */
	public boolean isCalculationDone();
}
