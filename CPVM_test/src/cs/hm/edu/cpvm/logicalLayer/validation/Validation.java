package cs.hm.edu.cpvm.logicalLayer.validation;

import java.util.ArrayList;

import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.Customervalues;

/**
 * Bietet Dienste zum Validieren von Datenfeldern an.
 * Im Prototyp wird nur das Datenformat validiert, deshalb werden nur die Methoden validateDouble und validateInt implementiert.
 * Zuk�nftig k�nnten hier noch weitere Methoden wie validateProfit etc. integriert werden.
 * @author Mustafa
 *
 */
public interface Validation {

	/**
	 * Pr�ft ob der �bergebene Wert ein korrekter Betrag ist.
	 * @param value zu pr�fender Wert
	 * @return true, wenn die Pr�fung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateAmount(String value) throws ValidationException;
	
	/**
	 * Pr�ft ob der �bergebene Wert eine korrekte Zahl ist.
	 * @param value zu pr�fender Wert
	 * @return true, wenn die Pr�fung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateNumber(String value) throws ValidationException;
	
	
	/**
	 * Validiert alle Kundenwerte die vom Benutzer eingegeben werden und fehlerhaft sein k�nnen.
	 * Diese sind: Gewinn, Umsatz, Vertr�ge, Erl�sschm�lerung, Informationswert, Cross-/Up-Buying, Loyalit�tspotenzial
	 * @param values alle Kundenwerte.
	 * @return true, wenn die Pr�fung erfolgreich war, andernfalls false.
	 * @throws ValidationException fall ein Wert nicht korrekt ist.
	 */
	public boolean validateCustomervalues(ArrayList<Customervalues> values) throws ValidationException;
	
}
