package cs.hm.edu.cpvm.common.models;

/**
 * Repräsentiert eine Kundenwert-Konfiguration.
 * @author Mustafa
 *
 */
public class CustomervaluesConfiguration {

	private int id;
	private String customervalueName; // Name des Kundenwerts
	private double weightingFactor; // Gewichtungsfaktor
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomervalueName() {
		return customervalueName;
	}
	public void setCustomervalueName(String customervalueName) {
		this.customervalueName = customervalueName;
	}
	public double getWeightingFactor() {
		return weightingFactor;
	}
	public void setWeightingFactor(double weightingFactor) {
		this.weightingFactor = weightingFactor;
	}
	
}
