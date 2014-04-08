package cs.hm.edu.cpvm.graphicalLayer;

/**
 * Muss von jeder GUI View implementiert werden, damit eine Basisfunktionalit�t gew�hrleistet ist.
 * @author Mustafa
 *
 */
public interface Controller {

	/**
	 * Initialisiert die GUI mit allen ben�tigten Objekten, zeigt sie jedoch noch nicht an
	 */
	public void initialize();
	
	/**
	 * Zeigt die View an.
	 */
	public void display();
	
	/**
	 * Blendet die View aus. Muss aber nicht zwangsl�ufig ein echtes schlie�en sein, kann je nach fall auch ein reines Ausblenden sein.
	 */
	public void close();
}
