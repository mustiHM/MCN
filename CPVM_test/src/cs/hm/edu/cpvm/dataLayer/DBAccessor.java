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
	 * Liest alle Kundenwerte aus der Datenbank aus und gibt diese zur�ck.
	 * @return alle Kundenwerte aus der Datenbank
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public ArrayList<Customervalues> getAllCustomervalues() throws DBException;
	
	/**
	 * Speichert die ver�nderten Kundenwerte in der Datenbank.
	 * @param values nur die ver�nderten Kundenwerte.
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public void updateCustomervalues(ArrayList<Customervalues> values) throws DBException;
	
	/**
	 * Gibt alle Kundenwerte f�r eine Liste von Kundendaten zur�ck.
	 * @param customers Liste von Kundendaten
	 * @return Kennzahlen zu den Kunden aus der �bergebenen Liste.
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public ArrayList<Customervalues> getCustomervaluesForCustomerdata(ArrayList<Customerdata> customers) throws DBException;
	
	/**
	 * Gibt alle Kundendaten aus der Datenbank zur�ck.
	 * @return alle Kundendaten
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public ArrayList<Customerdata> getAllCustomerdata() throws DBException;
	
	
	/**
	 * Liest jeden Gewichtungsfaktor aus der Datenbank aus.
	 * @return alle Gewichtungsfaktoren
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public HashMap<String, Double> getAllCustomervaluesConfigurations() throws DBException;
	
	/**
	 * Speichert die ver�nderten Gewichtungen in der Datenbank.
	 * @param configurations alle ver�nderten Gewichtungen
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public void updateCustomervaluesConfiguration(HashMap<String, Double> configurations) throws DBException;
	
	
}
