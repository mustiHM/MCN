package cs.hm.edu.cpvm.logicalLayer.calculation;

import java.util.ArrayList;
import java.util.HashMap;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.common.models.CustomervaluesConfiguration;

/**
 * Stellt die  Berechnungskomponente dar und bietet Methoden zum Berechnen einzelner sowie statischer Kennzahlen.
 * @author Mustafa
 *
 */
public interface Calculation {

	/**
	 * Speichert die Kundenwerte eines Kundens für die Berechnung ab.
	 * @param values Kundenwerte eines Kundens
	 */
	public void setCustomervalues(Customervalues values);
	
	
	/**
	 * Speichert alle Gewichtungsfaktoren und sonstige Variablen und stellt sie für die Berechnung zur Verfügung.
	 * @param configs Liste aller zu nutzenden Variablen, Gewichtungsfaktoren etc.
	 */
	public void setCustomervaluesConfigurations(HashMap<String, Double> configs);
	
	/**
	 * Gibt zurück, ob die Berechnung eines Kundendatensatzes durchgelaufen ist.
	 * @return true, wenn die Berechnung erfolgreich durchgelaufen ist, andernfalls false.
	 * @throws DBException Fehler, falls die Datenbank nicht verfügbar ist.
	 */
	public boolean isCalculationDone() throws DBException;
	
	
	
}
