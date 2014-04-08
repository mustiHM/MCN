package cs.hm.edu.cpvm.logicalLayer.validation;

import cs.hm.edu.cpvm.common.exceptions.ValidationException;

/**
 * Bietet Dienste zum Validieren von Datenfeldern an.
 * Im Prototyp wird nur das Datenformat validiert, deshalb werden nur die Methoden validateDouble und validateInt implementiert.
 * Zukünftig könnten hier noch weitere Methoden wie validateProfit etc. integriert werden.
 * @author Mustafa
 *
 */
public interface Validation {

	/**
	 * Prüft ob der übergebene Wert valide ist.
	 * @param value zu prüfender Wert
	 * @return true, wenn die Prüfung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateDouble(String value) throws ValidationException;
	
	/**
	 * Prüft ob der übergebene Wert valide ist.
	 * @param value zu prüfender Wert
	 * @return true, wenn die Prüfung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateInt(String value) throws ValidationException;
	
}
