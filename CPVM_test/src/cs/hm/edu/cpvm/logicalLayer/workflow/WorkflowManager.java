package cs.hm.edu.cpvm.logicalLayer.workflow;

import java.util.ArrayList;

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
	 * @throws DBException falls Datenbank nicht verfügbar.
	 */
	public void startCalculation() throws DBException;
	
	/**
	 * Überwacht die Berechnungen und gibt zurück, ob diese fertiggelaufen ist.
	 * @return true, falls Berechnungen fertig sind. Andernfalls false. 
	 */
	public boolean isCalculationDone();
	
	/**
	 * Lädt alle Konfigurationen der Kennzahlen (z.B. Gewichtungen) aus der Datenbank.
	 * @return Liste aller Kennzahl-Konfigurationen
	 * @throws DBException falls Datenbank nicht verfügbar.
	 */
	public ArrayList<CustomervaluesConfiguration> getCustomervaluesConfigurations() throws DBException;
	
	/**
	 * Speichert die Gewichtungen der Kennzahlen in der Datenbank.
	 * @param configs zu speichernden Gewichtungen
	 * @throws DBException dalls Datenbank nicht verfügbar.
	 */
	public void updateCustomervaluesConfigurations(ArrayList<CustomervaluesConfiguration> configs) throws DBException;
}
