package cs.hm.edu.cpvm.logicalLayer.validation;

import java.util.ArrayList;

import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.Customervalues;

/**
 * Bietet Dienste zum Validieren von Datenfeldern an.
 * Im Prototyp wird nur das Datenformat validiert, deshalb werden nur die Methoden validateDouble und validateInt implementiert.
 * Zukünftig könnten hier noch weitere Methoden wie validateProfit etc. integriert werden.
 * @author Mustafa
 *
 */
public interface Validation {

	/**
	 * Prüft ob der übergebene Wert ein korrekter Betrag ist.
	 * @param value zu prüfender Wert
	 * @return true, wenn die Prüfung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateAmount(String value) throws ValidationException;
	
	/**
	 * Prüft ob der übergebene Wert eine korrekte Zahl ist.
	 * @param value zu prüfender Wert
	 * @return true, wenn die Prüfung erfolgreich war, andernfalls false.
	 * @throws ValidationException falls ein Fehler auftritt zb falsches Datenformat.
	 */
	public boolean validateNumber(String value) throws ValidationException;
	
	
	/**
	 * Validiert alle Kundenwerte die vom Benutzer eingegeben werden und fehlerhaft sein können.
	 * Diese sind: Gewinn, Umsatz, Verträge, Erlösschmälerung, Informationswert, Cross-/Up-Buying, Loyalitätspotenzial
	 * @param values alle Kundenwerte.
	 * @return true, wenn die Prüfung erfolgreich war, andernfalls false.
	 * @throws ValidationException fall ein Wert nicht korrekt ist.
	 */
	public boolean validateCustomervalues(ArrayList<Customervalues> values) throws ValidationException;
	
}
