package cs.hm.edu.cpvm.logicalLayer.workflow;

import java.util.ArrayList;
import java.util.HashMap;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.Customerdata;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.common.models.CustomervaluesConfiguration;

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
	 * @return gibt zur�ck ob  die Aktion erfolgreich war.
	 * @throws DBException falls DB nicht verf�gbar.
	 * @throws ValidationException falls Daten nicht valide sind.
	 */
	public boolean updateCustomervalues(ArrayList<Customervalues> values) throws DBException, ValidationException;
	
	
	/**
	 * Startet die Berechnung der Kundenwerte und erstellt f�r jeden Kundendatensatz einen Berechnungs-Thread aus der Calculation-Komponente.
	 * @throws DBException falls Datenbank nicht verf�gbar.
	 */
	public void startCalculation() throws DBException;
	
	/**
	 * �berwacht die Berechnungen und gibt zur�ck, ob diese fertiggelaufen ist.
	 * @return true, falls Berechnungen fertig sind. Andernfalls false. 
	 */
	public boolean isCalculationDone();
	
	/**
	 * L�dt alle Konfigurationen der Kennzahlen (z.B. Gewichtungen) aus der Datenbank.
	 * @return Liste aller Kennzahl-Konfigurationen
	 * @throws DBException falls Datenbank nicht verf�gbar.
	 */
	public HashMap<String, Double> getCustomervaluesConfigurations() throws DBException;
	
	/**
	 * Speichert die Gewichtungen der Kennzahlen in der Datenbank.
	 * @param configs zu speichernden Gewichtungen
	 * @return gibt zur�ck, ob die Aktion erfolgreich war.
	 * @throws DBException dalls Datenbank nicht verf�gbar.
	 */
	public boolean updateCustomervaluesConfigurations(ArrayList<CustomervaluesConfiguration> configs) throws DBException;
}
