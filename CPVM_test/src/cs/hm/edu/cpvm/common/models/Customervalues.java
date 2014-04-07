package cs.hm.edu.cpvm.common.models;

/**
 * Stellt die Kundenwerte eines Kunden dar.
 * @author Mustafa
 *
 */
public class Customervalues {

	private int id;
	private int customerId;
	private double profit; // Gewinn
	private double sales; // Umsatz
	private int contracts; // Anzahl an Verträgen
	private double informationValue; // Informationswert
	private int cup; // Cross-Up-Buying Potenzial
	private double roi; // Return on Invenstment
	private double profitability; // Profitabilität
	private double profitMargin;
	private double investment;
	private double scalefactor; 
	private int loyality;
	private String calculationDate;
	private double customerValueResult1;
	private double customerValueResult2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public int getContracts() {
		return contracts;
	}
	public void setContracts(int contracts) {
		this.contracts = contracts;
	}
	public double getInformationValue() {
		return informationValue;
	}
	public void setInformationValue(double informationValue) {
		this.informationValue = informationValue;
	}
	public int getCup() {
		return cup;
	}
	public void setCup(int cup) {
		this.cup = cup;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public double getProfitability() {
		return profitability;
	}
	public void setProfitability(double profitability) {
		this.profitability = profitability;
	}
	public double getProfitMargin() {
		return profitMargin;
	}
	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}
	public double getScalefactor() {
		return scalefactor;
	}
	public void setScalefactor(double scalefactor) {
		this.scalefactor = scalefactor;
	}
	public int getLoyality() {
		return loyality;
	}
	public void setLoyality(int loyality) {
		this.loyality = loyality;
	}
	public String getCalculationDate() {
		return calculationDate;
	}
	public void setCalculationDate(String calculationDate) {
		this.calculationDate = calculationDate;
	}
	public double getCustomerValueResult1() {
		return customerValueResult1;
	}
	public void setCustomerValueResult1(double customerValueResult1) {
		this.customerValueResult1 = customerValueResult1;
	}
	public double getCustomerValueResult2() {
		return customerValueResult2;
	}
	public void setCustomerValueResult2(double customerValueResult2) {
		this.customerValueResult2 = customerValueResult2;
	}
	
}
