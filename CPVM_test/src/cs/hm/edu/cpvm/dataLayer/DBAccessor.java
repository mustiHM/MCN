package cs.hm.edu.cpvm.dataLayer;

import java.util.ArrayList;
import java.util.HashMap;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.models.Customerdata;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.common.models.CustomervaluesConfiguration;

/**
 * Bietet Zugriff auf eine Datenbank. Die konkrete Implementierung kann sich auf eine bestimmte Datenbank spezialisieren.
 * @author Mustafa
 *
 */
public interface DBAccessor {

	/**
	 * Liest alle Kundenwerte aus der Datenbank aus und gibt diese zurück.
	 * @return alle Kundenwerte aus der Datenbank
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public ArrayList<Customervalues> getAllCustomervalues() throws DBException;
	
	/**
	 * Speichert die veränderten Kundenwerte in der Datenbank.
	 * @param values nur die veränderten Kundenwerte.
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public void updateCustomervalues(ArrayList<Customervalues> values) throws DBException;
	
	/**
	 * Gibt alle Kundenwerte für eine Liste von Kundendaten zurück.
	 * @param customers Liste von Kundendaten
	 * @return Kennzahlen zu den Kunden aus der übergebenen Liste.
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public ArrayList<Customervalues> getCustomervaluesForCustomerdata(ArrayList<Customerdata> customers) throws DBException;
	
	/**
	 * Gibt alle Kundendaten aus der Datenbank zurück.
	 * @return alle Kundendaten
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public ArrayList<Customerdata> getAllCustomerdata() throws DBException;
	
	
	/**
	 * Liest jeden Gewichtungsfaktor aus der Datenbank aus.
	 * @return alle Gewichtungsfaktoren
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public HashMap<String, Double> getAllCustomervaluesConfigurations() throws DBException;
	
	/**
	 * Speichert die veränderten Gewichtungen in der Datenbank.
	 * @param configurations alle veränderten Gewichtungen
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public void updateCustomervaluesConfiguration(HashMap<String, Double> configurations) throws DBException;
	
	
}
