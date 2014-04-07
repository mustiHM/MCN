package cs.hm.edu.cpvm.logicalLayer.validation;

/**
 * Bietet Dienste zum Validieren von Datenfeldern an.
 * @author Mustafa
 *
 */
public interface Validation {

	/**
	 * Pr�ft ob der �bergebene Wert ein korrekter Double Wert ist.
	 * @param value zu pr�fender Wert
	 * @return true, wenn die Pr�fung erfolgreich war, andernfalls false.
	 */
	public boolean validateDouble(String value);
	
	/**
	 * Pr�ft, ob der �bergebene Wert ein korrekter Int Wert ist.
	 * @param value zu pr�fender Wert
	 * @return true, wenn die Pr�fung erfolgreich war, andernfalls false.
	 */
	public boolean validateInt(String value);
	
}
