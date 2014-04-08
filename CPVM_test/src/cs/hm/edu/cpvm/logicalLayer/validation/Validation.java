package cs.hm.edu.cpvm.logicalLayer.validation;

import cs.hm.edu.cpvm.common.exceptions.ValidationException;

/**
 * Bietet Dienste zum Validieren von Datenfeldern an.
 * Im Prototyp wird nur das Datenformat validiert, deshalb werden nur die Methoden validateDouble und validateInt implementiert.
 * Zuk�nftig k�nnten hier noch weitere Methoden wie validateProfit etc. integriert werden.
 * @author Mustafa
 *
 */
public interface Validation {

	/**
	 * Pr�ft ob der �bergebene Wert valide ist.
	 * @param value zu pr�fender Wert
	 * @return true, wenn die Pr�fung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateDouble(String value) throws ValidationException;
	
	/**
	 * Pr�ft ob der �bergebene Wert valide ist.
	 * @param value zu pr�fender Wert
	 * @return true, wenn die Pr�fung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateInt(String value) throws ValidationException;
	
}
