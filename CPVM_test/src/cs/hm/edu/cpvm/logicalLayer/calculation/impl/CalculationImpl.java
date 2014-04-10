package cs.hm.edu.cpvm.logicalLayer.calculation.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.common.models.CustomervaluesConfiguration;
import cs.hm.edu.cpvm.graphicalLayer.Test;
import cs.hm.edu.cpvm.logicalLayer.calculation.Calculation;

/**
 * Die Implementierung der Kalkulationskomponente des Prototyps.
 * Unterst�tzt keine generische Berechnung und hat deshalb private Methoden f�r die Berechnungen der einzelnen Kennzahlen.
 * @author Mustafa
 *
 */
public class CalculationImpl extends Thread implements Calculation{

	// generelle Attribute / Objekte f�r die Berechnung
	private static Log log = LogFactory.getLog(CalculationImpl.class);
	private static ArrayList<Customervalues> allCustomerValues;
	
	private static double meanRenta; // Mittelwert der Rentabilit�t
	private static double meanROI; // Mittelwert des ROI
	private static double meanDB; // Mittelwert des DB
	private static double meanSke; // Mittelwert des Skaleneffekts
	private static double meanIW; // Mittelwert des Informationswerts
	private static double meanCUP; // Mittelwert des Cross-/Up-Buying Potenzials
	private static double meanLP; // Mittelwert des Loyalit�tspotenzial
	
	// Attribute / Objekte f�r einen einzelnen Kunden:
	private Customervalues values; // die einzelnen Kundenwerte eines Kundens.
	private HashMap<String, Double> config; // die Gewichtungsfaktoren
	private boolean isCalculationDone; // Flag zum Status der Berechnung eines Kundens (f�r die gesamten Berechnungen)
	
	private double stabwRenta; // Standardabweichung der Rentabilit�t
	private double stabwROI; // Standardabweichung des ROI
	private double stabwDB; // Standardabweichung des DB
	private double stabwSkE; // Standardabweichung des Skaleneffekts
	private double stabwIW; // Standardabweichung des Informationswerts
	private double stabwCUP; // Standardabweichung des Cross-/Up-Buying Potenzials
	private double stabwLP; // Standardabweichung des Loyalit�tspotenzial
	
	
	/**
	 * Soeichert alle Kundenwerte ab, damit sie f�r die Mittelwert-Berechnungen genutzt werden k�nnen.
	 * @param allValues alle Kundenwerte
	 */
	public static void setAllCustomervalues(ArrayList<Customervalues> allValues){
		allCustomerValues = allValues;
	}
	
	
	
	public void setCustomervalues(Customervalues values) {
		this.values = values;

	}
	
	public void setCustomervaluesConfigurations(HashMap<String, Double> config){
		this.config = config;
	}

	public boolean isCalculationDone() throws DBException {
		return isCalculationDone;
	}
	
	public void run(){
		
		isCalculationDone = false;
		
		this.setName("Berechnung_" + values.getCustomerdata().getFirstName() +"_" + values.getCustomerdata().getLastName());
		log.debug("Berechnung f�r Kunden " + values.getCustomerdata().getFirstName() + " " + values.getCustomerdata().getLastName() + " gestartet...");
		
		// alle Kennzahlen werden jetzt einzeln berechnet und gespeichert
		double investition = calculateInvestment();
		values.setInvestment(investition);
		
		double profitability = calculateProfitability();
		values.setProfitability(profitability);
		
		double roi = calculateROI();
		values.setRoi(roi);
		
		double db = calculateProfitMargin();
		values.setProfitMargin(db);
		
		double scalefactor = calculateScalefactor();
		values.setScalefactor(scalefactor);
		
		double customerValueResult1 = calculateCustomerValueResult1();
		values.setCustomerValueResult1(customerValueResult1);
		
		isCalculationDone = true;
		
	}
	
	/**
	 * Berechnet die Investition
	 * @return die Investition eines Kunden.
	 */
	private double calculateInvestment(){
		// Formel: (Umsatz � Gewinn) * Investitionssatz
		double investitionsSatz = config.get("Investitionssatz");
		log.debug("Berechne Investition in Euro mit den Parametern: Gewinn = " + values.getProfit() + "; Umsatz = " + values.getSales() + "; Investitionssatz = " + investitionsSatz);
		double investition = (values.getSales()-values.getProfit())*investitionsSatz;
		log.debug("Investition in Euro betr�gt: " + investition);
		return investition;
		
	}
	
	/**
	 * Berechnet die Rentabilit�t eines Kundens
	 * @return Rentabilit�t
	 */
	private double calculateProfitability(){
		// Formel: Gewinn * 100 / Umsatz
		log.debug("Berechne Rentabilit�t in % mit den Parametern: Gewinn = " + values.getProfit() + "; Umsatz = " + values.getSales());
		double profitability = values.getProfit()*100/values.getSales();
		log.debug("Rentabilit�t in % betr�gt: " + profitability);
		return profitability;
	}
	
	/**
	 * Berechnet den ROI.
	 * @return ROI
	 */
	private double calculateROI(){
		// Formel: Gewinn * 100 / Investition
		log.debug("Berechne ROI in % mit den Parametern: Gewinn = " + values.getProfit() + "; Investition = " + values.getInvestment());
		double roi = values.getProfit()*100/values.getInvestment();
		log.debug("ROI in % betr�gt: " + roi);
		return roi;
				
	}
	
	/**
	 * Berechnet den Deckungsbeitrag.
	 * @return Deckungsbeitrag
	 */
	private double calculateProfitMargin(){
		// Formel: Umsatz * DBU-Faktor
		double dbuFaktor = config.get("DBU-Faktor");
		log.debug("Berechne Deckungsbeitrag mit den Parametern: Umsatz = " + values.getSales() + "; DBU-Faktor = " + dbuFaktor);
		double profitMargin = values.getSales()*dbuFaktor;
		log.debug("Deckungsbeitrag betr�gt: " + profitMargin);
		return profitMargin;
	}
	
	/**
	 * Berechnet den Skaleneffekt.
	 * @return Skaleneffekt
	 */
	private double calculateScalefactor(){
		// Formel: Summe (Vertr�ge in St�ck - 1) * Investitionssatz
		double investitionsSatz = config.get("Investitionssatz");
		log.debug("Berechne Skaleneffekt mit den Parametern: Vertr�ge in St�ck = " + values.getContracts() + "; Investitionssatz = " + investitionsSatz);
		double scalefactor = (values.getContracts()-1)*investitionsSatz;
		log.debug("Skaleneffekt betr�gt: " + scalefactor);
		return scalefactor;
	}
	
	/**
	 * Berechnet den gewichteten Kundenwert 1
	 * @return gewichteter Kundenwert1
	 */
	private double calculateCustomerValueResult1(){
		// Formel: Summe (Renta.% * GewRenta; ROI % * GewROI; DB * GewDB; CUP * GewCUP; LP * GewLP; IW * GewIW; SkE * GewSkE)
		double gewRenta = config.get("GewRenta");
		double gewROI = config.get("GewROI");
		double gewDB = config.get("GewDB");
		double gewCUP = config.get("GewCUP");
		double gewLP = config.get("GewLP");
		double gewIW = config.get("GewIW");
		double gewSkE = config.get("GewSkE");
		log.debug("Berechne gewichteten Kundenwert 1 mit den Parametern: Rentabilit�t = " + values.getProfitability() + "; Gewichtungsfaktor f�r Rentabilit�t = " + gewRenta
				+ "; ROI = " + values.getRoi() + "; Gewichtungsfaktor f�r ROI = " + gewROI + "; Deckungsbeitrag = " + values.getProfitMargin() + "; Gewichtungsfaktor f�r Deckungsbeitrag = " + gewDB
				+ "; Cross-/Up-Buying Potenzial = " + values.getCup() + "; Gewichtungsfaktor f�r Cross-/Up-Buying Potenzial = " + gewCUP + "; Loyalit�tspotenzial = " + values.getLoyality()
				+ "Gewichtungsfaktor f�r Loyalit�tspotenzial = " + gewLP + "; Informationswert = " + values.getInformationValue() + "; Gewichtungsfaktor f�r Informationswert = " + gewIW
				+ "; Skaleneffekt = " + values.getScalefactor() + "; Gewichtungsfaktor f�r Skaleneffekt = " + gewSkE);
		double customerresult1 = (values.getProfitability()*gewRenta)+(values.getRoi()*gewROI)+(values.getProfitMargin()*gewDB)+(values.getCup()*gewCUP)+(values.getLoyality()*gewLP)
				+(values.getInformationValue()*gewIW)+(values.getScalefactor()*gewSkE);
		log.debug("Gewichteter Kundenwert1 betr�gt: " + customerresult1);
		return customerresult1;
	}

}
