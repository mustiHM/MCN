package cs.hm.edu.cpvm.logicalLayer.validation;

/**
 * Bietet Dienste zum Validieren von Datenfeldern an.
 * @author Mustafa
 *
 */
public interface Validation {

	/**
	 * Prüft ob der übergebene Wert ein korrekter Double Wert ist.
	 * @param value zu prüfender Wert
	 * @return true, wenn die Prüfung erfolgreich war, andernfalls false.
	 */
	public boolean validateDouble(String value);
	
	/**
	 * Prüft, ob der übergebene Wert ein korrekter Int Wert ist.
	 * @param value zu prüfender Wert
	 * @return true, wenn die Prüfung erfolgreich war, andernfalls false.
	 */
	public boolean validateInt(String value);
	
}
