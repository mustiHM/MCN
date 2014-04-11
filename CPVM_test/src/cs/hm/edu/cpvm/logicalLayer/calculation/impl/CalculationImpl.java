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
 * Unterstützt keine generische Berechnung und hat deshalb private Methoden für die Berechnungen der einzelnen Kennzahlen.
 * @author Mustafa
 *
 */
public class CalculationImpl extends Thread implements Calculation{

	// generelle Attribute / Objekte für die Berechnung
	private static Log log = LogFactory.getLog(CalculationImpl.class);
	private static ArrayList<Customervalues> allCustomerValues;
	private static boolean isStatisticalCalculationDone;
	
	private static double meanRenta; // Mittelwert der Rentabilität
	private static double meanROI; // Mittelwert des ROI
	private static double meanDB; // Mittelwert des DB
	private static double meanSke; // Mittelwert des Skaleneffekts
	private static double meanIW; // Mittelwert des Informationswerts
	private static double meanCUP; // Mittelwert des Cross-/Up-Buying Potenzials
	private static double meanLP; // Mittelwert des Loyalitätspotenzial
	private static double stabwRenta; // Standardabweichung der Rentabilität
	private static double stabwROI; // Standardabweichung des ROI
	private static double stabwDB; // Standardabweichung des DB
	private static double stabwSkE; // Standardabweichung des Skaleneffekts
	private static double stabwIW; // Standardabweichung des Informationswerts
	private static double stabwCUP; // Standardabweichung des Cross-/Up-Buying Potenzials
	private static double stabwLP; // Standardabweichung des Loyalitätspotenzial
	private static double varRenta; // Varianz der Rentabilität
	private static double varROI; // Varianz des ROI
	private static double varDB; // Varianz des DB
	private static double varSkE; // Varianz des Skaleneffekts
	private static double varIW; // Varianz des Informationswerts
	private static double varCUP; // Varianz des Cross-/Up-Buying Potenzials
	private static double varLP; // Varianz des Loyalitätspotenzials
	
	
	
	// Attribute / Objekte für einen einzelnen Kunden:
	private Customervalues values; // die einzelnen Kundenwerte eines Kundens.
	private HashMap<String, Double> config; // die Gewichtungsfaktoren
	private boolean isCalculationDone; // Flag zum Status der Berechnung eines Kundens (für die gesamten Berechnungen)
	
	
	
	
	/**
	 * Soeichert alle Kundenwerte ab, damit sie für die Mittelwert-Berechnungen genutzt werden können.
	 * @param allValues alle Kundenwerte
	 */
	public static void setAllCustomervalues(ArrayList<Customervalues> allValues){
		allCustomerValues = allValues;
	}
	
	/**
	 * Startet die Berechnung der statistischen Werte wie Mittelwert, Varianz und Standardabweichung für jede Kennzahl.
	 */
	public static void startStatisticalCalculation(){
		isStatisticalCalculationDone = false;
		
		// Berechnung der Mittelwerte
		calculateMeanRenta();
		calculateMeanROI();
		calculateMeanDB();
		calculateMeanSkE();
		calculateMeanIW();
		calculateMeanCUP();
		calculateMeanLP();
		// Berechnung der Varianz
		calculateVarRenta();
		calculateVarRoi();
		calculateVarDB();
		calculateVarSkE();
		calculateVarIW();
		calculateVarCUP();
		calculateVarLP();
		// Berechnung der Standardabweichungen
		stabwRenta = calculateStandardDeviation(varRenta);
		stabwROI = calculateStandardDeviation(varROI);
		stabwDB = calculateStandardDeviation(varDB);
		stabwSkE = calculateStandardDeviation(varSkE);
		stabwIW = calculateStandardDeviation(varIW);
		stabwCUP = calculateStandardDeviation(varCUP);
		stabwLP = calculateStandardDeviation(varLP);
		
		isStatisticalCalculationDone = true;
	}
	
	/**
	 * Berechnet den Mittelwert der Rentabilität
	 */
	private static void calculateMeanRenta(){
		double totalRenta = 0;
		
		for(int i=0; i<allCustomerValues.size();i++){
			totalRenta = totalRenta + allCustomerValues.get(i).getProfitability();
		}
		
		meanRenta = totalRenta/allCustomerValues.size();
	}
	
	/**
	 * Berechnet den Mittelwert des DB.
	 */
	private static void calculateMeanROI(){
		double totalROI = 0;
		
		for(int i=0; i<allCustomerValues.size();i++){
			totalROI = totalROI + allCustomerValues.get(i).getRoi();
		}
		
		meanROI = totalROI/allCustomerValues.size();
	}
	
	/**
	 * Berechnet den Mittelwert des Deckungsbeitrags.
	 */
	private static void calculateMeanDB(){
		double totalDB = 0;
		
		for(int i=0; i<allCustomerValues.size();i++){
			totalDB = totalDB + allCustomerValues.get(i).getProfitMargin();
		}
		
		meanDB = totalDB/allCustomerValues.size();
		
	}
	
	/**
	 * Berechnet den Mittelwert für den Skaleneffekt
	 */
	private static void calculateMeanSkE(){
		double totalSkE = 0;
		for(int i=0; i<allCustomerValues.size();i++){
			totalSkE = totalSkE + allCustomerValues.get(i).getScalefactor();
		}
		
		meanSke = totalSkE/allCustomerValues.size();
	}
	
	/**
	 * Berechnet den Mittelwert für den Informationswert.
	 */
	private static void calculateMeanIW(){
		double totalIW = 0;
		for(int i=0; i<allCustomerValues.size();i++){
			totalIW = totalIW + allCustomerValues.get(i).getInformationValue();
		}
		
		meanIW = totalIW/allCustomerValues.size();
	}
	
	/**
	 * Berechnet den Mittelwert des Cross-/Up-Buying Potenzials
	 */
	private static void calculateMeanCUP(){
		double totalCUP = 0;
		for(int i=0; i<allCustomerValues.size();i++){
			totalCUP = totalCUP + allCustomerValues.get(i).getCup();
		}
		
		meanCUP = totalCUP/allCustomerValues.size();
	}
	
	/**
	 * Berechnet den Mittelwert des Loyalitätspotenzials.
	 */
	private static void calculateMeanLP(){
		double totalLP = 0;
		for(int i=0; i<allCustomerValues.size();i++){
			totalLP = totalLP + allCustomerValues.get(i).getLoyality();
		}
		
		meanLP = totalLP/allCustomerValues.size();
	}
	/**
	 * Berechnet die Varianz der Rentabilität
	 */
	private static void calculateVarRenta(){
		double firstResult=0;
		for(int i=0; i<allCustomerValues.size(); i++){
			firstResult = firstResult + Math.pow(allCustomerValues.get(i).getProfitability()-meanRenta, 2);
		}
		
		varRenta = (firstResult)/(allCustomerValues.size()-1);
	}
	
	/**
	 * Berechnet die Varianz des ROI
	 */
	private static void calculateVarRoi(){
		double firstResult=0;
		for(int i=0; i<allCustomerValues.size(); i++){
			firstResult = firstResult + Math.pow(allCustomerValues.get(i).getRoi()-meanROI, 2);
		}
		
		varROI = (firstResult)/(allCustomerValues.size()-1);
	}
	
	/**
	 * Berechnet die Varianz des DB
	 */
	private static void calculateVarDB(){
		double firstResult=0;
		for(int i=0; i<allCustomerValues.size(); i++){
			firstResult = firstResult + Math.pow(allCustomerValues.get(i).getProfitMargin()-meanDB, 2);
		}
		
		varDB = (firstResult)/(allCustomerValues.size()-1);
	}
	
	/**
	 * Berechnet die Varianz des Skaleneffekts
	 */
	private static void calculateVarSkE(){
		double firstResult=0;
		for(int i=0; i<allCustomerValues.size(); i++){
			firstResult = firstResult + Math.pow(allCustomerValues.get(i).getScalefactor()-meanSke, 2);
		}
		
		varSkE = (firstResult)/(allCustomerValues.size()-1);
	}
	
	/**
	 * Berechnet die Varianz des Informationswerts
	 */
	private static void calculateVarIW(){
		double firstResult=0;
		for(int i=0; i<allCustomerValues.size(); i++){
			firstResult = firstResult + Math.pow(allCustomerValues.get(i).getInformationValue()-meanIW, 2);
		}
		
		varIW = (firstResult)/(allCustomerValues.size()-1);
	}
	
	
	/**
	 * Berechnet die Varianz des Cross-/Up-Buying Potenzials
	 */
	private static void calculateVarCUP(){
		double firstResult=0;
		for(int i=0; i<allCustomerValues.size(); i++){
			firstResult = firstResult + Math.pow(allCustomerValues.get(i).getCup()-meanCUP, 2);
		}
		
		varCUP = (firstResult)/(allCustomerValues.size()-1);
	}
	
	/**
	 * Berechnet die Varianz des Loyalitätspotenzials
	 */
	private static void calculateVarLP(){
		double firstResult=0;
		for(int i=0; i<allCustomerValues.size(); i++){
			firstResult = firstResult + Math.pow(allCustomerValues.get(i).getLoyality()-meanLP, 2);
		}
		
		varLP = (firstResult)/(allCustomerValues.size()-1);
	}
	
	/**
	 * Berechnet die Standardabweichung einer gegebenen Varianz.
	 * @param variance die Varianz zu der die Standardabw. berechnet werden soll.
	 * @return die Standardabweichung des Werts
	 */
	private static double calculateStandardDeviation(double variance){
		return Math.sqrt(variance);
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
		log.debug("Berechnung für Kunden " + values.getCustomerdata().getFirstName() + " " + values.getCustomerdata().getLastName() + " gestartet...");
		
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
		
		// TODO indikator für workflow manager implementieren, damit er weiss, wann er die statistischen berechnungen anstoßen kann.
		// warte auf statistische Berechnungen
		while(!isStatisticalCalculationDone){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				log.error(e.getStackTrace());
				e.printStackTrace();
			}
		}
		
		double customerValueResult2 = calculateCustomerValueResult2();
		values.setCustomerValueResult2(customerValueResult2);
		
		isCalculationDone = true;
		
	}
	
	/**
	 * Berechnet die Investition
	 * @return die Investition eines Kunden.
	 */
	private double calculateInvestment(){
		// Formel: (Umsatz – Gewinn) * Investitionssatz
		double investitionsSatz = config.get("Investitionssatz");
		log.debug("Berechne Investition in Euro mit den Parametern: Gewinn = " + values.getProfit() + "; Umsatz = " + values.getSales() + "; Investitionssatz = " + investitionsSatz);
		double investition = (values.getSales()-values.getProfit())*investitionsSatz;
		log.debug("Investition in Euro beträgt: " + investition);
		return investition;
		
	}
	
	/**
	 * Berechnet die Rentabilität eines Kundens
	 * @return Rentabilität
	 */
	private double calculateProfitability(){
		// Formel: Gewinn * 100 / Umsatz
		log.debug("Berechne Rentabilität in % mit den Parametern: Gewinn = " + values.getProfit() + "; Umsatz = " + values.getSales());
		double profitability = values.getProfit()*100/values.getSales();
		log.debug("Rentabilität in % beträgt: " + profitability);
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
		log.debug("ROI in % beträgt: " + roi);
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
		log.debug("Deckungsbeitrag beträgt: " + profitMargin);
		return profitMargin;
	}
	
	/**
	 * Berechnet den Skaleneffekt.
	 * @return Skaleneffekt
	 */
	private double calculateScalefactor(){
		// Formel: Summe (Verträge in Stück - 1) * Investitionssatz
		double investitionsSatz = config.get("Investitionssatz");
		log.debug("Berechne Skaleneffekt mit den Parametern: Verträge in Stück = " + values.getContracts() + "; Investitionssatz = " + investitionsSatz);
		double scalefactor = (values.getContracts()-1)*investitionsSatz;
		log.debug("Skaleneffekt beträgt: " + scalefactor);
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
		log.debug("Berechne gewichteten Kundenwert 1 mit den Parametern: Rentabilität = " + values.getProfitability() + "; Gewichtungsfaktor für Rentabilität = " + gewRenta
				+ "; ROI = " + values.getRoi() + "; Gewichtungsfaktor für ROI = " + gewROI + "; Deckungsbeitrag = " + values.getProfitMargin() + "; Gewichtungsfaktor für Deckungsbeitrag = " + gewDB
				+ "; Cross-/Up-Buying Potenzial = " + values.getCup() + "; Gewichtungsfaktor für Cross-/Up-Buying Potenzial = " + gewCUP + "; Loyalitätspotenzial = " + values.getLoyality()
				+ "Gewichtungsfaktor für Loyalitätspotenzial = " + gewLP + "; Informationswert = " + values.getInformationValue() + "; Gewichtungsfaktor für Informationswert = " + gewIW
				+ "; Skaleneffekt = " + values.getScalefactor() + "; Gewichtungsfaktor für Skaleneffekt = " + gewSkE);
		double customerresult1 = (values.getProfitability()*gewRenta)+(values.getRoi()*gewROI)+(values.getProfitMargin()*gewDB)+(values.getCup()*gewCUP)+(values.getLoyality()*gewLP)
				+(values.getInformationValue()*gewIW)+(values.getScalefactor()*gewSkE);
		log.debug("Gewichteter Kundenwert1 beträgt: " + customerresult1);
		return customerresult1;
	}
	
	/**
	 * Berechnet den gewichteten Kundenwert 2.
	 * @return gewichteten Kundenwert2.
	 */
	private double calculateCustomerValueResult2(){
		// Formel: Summe (VertRenta * Wert Renta; VertROI * Wert ROI; VertDB * Wert DB; VertSkE * Wert SkE; VertIW * Wert IW; VertCUP * Wert CUP; VertLP * Wert LP)
		// TODO Wert Renta berechnen
		
		double vertRenta = config.get("VerRenta");
		//TODO weitere configs laden
		return 0;
	}

}
