package cs.hm.edu.cpvm.common.models;

import java.sql.Timestamp;

/**
 * Stellt einen Log-Eintrag aus der Datenbank Tabelle Calculation_Logging dar.
 * (Ist mit dem Change Request hinzugekommen)
 * @author Mustafa
 *
 */
public class CalculationLogging {

	private int id;
	private String logMessage;
	private Timestamp loggingDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public Timestamp getLoggingDate() {
		return loggingDate;
	}
	public void setLoggingDate(Timestamp loggingDate) {
		this.loggingDate = loggingDate;
	}
	
	
	
}
