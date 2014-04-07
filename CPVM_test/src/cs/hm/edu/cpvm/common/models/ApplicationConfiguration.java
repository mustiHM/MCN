package cs.hm.edu.cpvm.common.models;

/**
 * Repräsentiert die einzelnen Konfigurationen der Anwendung.
 * @author Mustafa
 *
 */
public class ApplicationConfiguration {

	private int id;
	private String parameter;
	private String value;
	private String datatype;
	private String describtion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	
	
	
}
