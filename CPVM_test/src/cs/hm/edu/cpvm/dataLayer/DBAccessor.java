package cs.hm.edu.cpvm.dataLayer;

import java.util.ArrayList;

import cs.hm.edu.cpvm.common.exceptions.DBException;
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
	 * Liest jeden Gewichtungsfaktor aus der Datenbank aus.
	 * @return alle Gewichtungsfaktoren
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public ArrayList<CustomervaluesConfiguration> getAllCustomervaluesConfigurations() throws DBException;
	
	/**
	 * Speichert die ver�nderten Gewichtungen in der Datenbank.
	 * @param configurations alle ver�nderten Gewichtungen
	 * @throws DBException Exception, falls Datenbank nicht verf�gbar.
	 */
	public void updateCustomervaluesConfiguration(ArrayList<CustomervaluesConfiguration> configurations) throws DBException;
	
	
}
