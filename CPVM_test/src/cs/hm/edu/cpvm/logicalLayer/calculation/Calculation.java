package cs.hm.edu.cpvm.logicalLayer.calculation;

import cs.hm.edu.cpvm.common.exceptions.DBException;

/**
 * Stellt die  Berechnungskomponente dar und bietet Methoden zum Berechnen einzelner Kennzahlen.
 * @author Mustafa
 *
 */
public interface Calculation {

	/**
	 * Startet die Berechnung. Welche konkreten Kennzahlen berechnet werden, kann je nach Implementierung unterschiedlich aussehen.
	 */
	public void startCalculation();
	
	/**
	 * Gibt zurück, ob die Berechnung durchgelaufen ist.
	 * @return true, wenn die Berechnung erfolgreich durchgelaufen ist, andernfalls false.
	 * @throws DBException Fehler, falls die Datenbank nicht verfügbar ist.
	 */
	public boolean isCalculationDone() throws DBException;
	
	
	
}
