package cs.hm.edu.cpvm.common.exceptions;

/**
 * Fehlerklasse falls Fehler mit der Datenbank auftreten.
 * @author Mustafa
 *
 */
public class DBException extends Exception {

	public DBException() {
		// TODO Auto-generated constructor stub
	}

	public DBException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DBException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DBException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DBException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
