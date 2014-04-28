package cs.hm.edu.cpvm.dataLayer;

import java.util.ArrayList;
import java.util.HashMap;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.models.CalculationLogging;
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
	 * Speichert mehrere veränderte Kundenwerte in die Datenbank.
	 * @param values nur die veränderten Kundenwerte.
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public void updateAllCustomervalues(ArrayList<Customervalues> values) throws DBException;
	
	/**
	 * Speichert die veränderten Kundenwerte eines Kundens in die Datenbank.
	 * @param values die Kundenwerte eines Kundens.
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public void updateCustomervalues(Customervalues values) throws DBException;
	
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
	 * Aktualisiert die Kundendaten eines Kundens.
	 * @param customers Kundendaten
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public void updateCustomerdata(Customerdata customer) throws DBException;
	
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
	
	/**
	 * Gibt das letzte Protokoll der zu letzt durchgeführten Berechnung durch.
	 * @return Protokoll der letzten Berechnung
	 * @throws DBException falls Datenbank nicht verfügbar
	 */
	public CalculationLogging getLastCalculationLogging() throws DBException;
	
	/**
	 * Speichert die Protokoll-Einträge der Berechnungs-Threads in die Datenbank. 
	 * Alle Einträge werden in einem zusammenhängenden Eintrag zusammengefasst.
	 * Hierbei wird die ID und der Timestamp von der Datenbank selbst gesetzt.
	 * @param logs Liste aller Einträge
	 * @throws DBException Exception, falls Datenbank nicht verfügbar.
	 */
	public void saveCalculationLogs(ArrayList<String> logs) throws DBException;
	
}
