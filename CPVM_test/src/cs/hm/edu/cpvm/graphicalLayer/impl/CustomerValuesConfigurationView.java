package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.JButton;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.graphicalLayer.Controller;
import cs.hm.edu.cpvm.logicalLayer.workflow.WorkflowManager;
import cs.hm.edu.cpvm.logicalLayer.workflow.impl.WorkflowManagerImpl;

public class CustomerValuesConfigurationView extends JFrame implements Controller{

	private JPanel contentPane;
	private JTextField txtRenta;
	private JTextField txtRoi;
	private JTextField txtDeckungsbeitrag;
	private JTextField txtSkaleneffekt;
	private JTextField txtInformationswert;
	private JTextField txtCup;
	private JTextField txtLoyalitaet;
	private JTextField txtTotal;
	private WorkflowManager workflow;
	private HashMap<String, Double> configs;
	private ActionListener clickListener;
	private DocumentListener docListener;
	private JButton btnSpeichern;
	private Locale fmtLocale;
	private NumberFormat formatter;
	private boolean isValid; // gibt an, ob der Gesamtwert gültig ist.
	
	/**
	 * Die Werte aller Gewichtungen als Attribute zur schnellen Zwischenrechnung für das Feld "Gesamt"
	 */
	private double renta;
	private double roi;
	private double db;
	private double ske;
	private double iw;
	private double cup;
	private double loyal;
	private double total;

	/**
	 * Dient nur zu Testzwecken
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerValuesConfigurationView frame = new CustomerValuesConfigurationView();
					frame.initialize();
					frame.display();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Lädt die Inhalte nochmal neu vom Workflow Manager und aktualisiert die Tabelle
	 * @throws ParseException  Fehler bei falschem Format
	 * @throws NumberFormatException Fehler bei falschem Format
	 */
	private void synchronizeData() throws NumberFormatException , ParseException{
		try {
			configs = workflow.getCustomervaluesConfigurations();
			txtRenta.setText(formatter.format(configs.get("VertRenta")));
			txtRoi.setText(formatter.format(configs.get("VertROI")));
			txtDeckungsbeitrag.setText(formatter.format(configs.get("VertDB")));
			txtSkaleneffekt.setText(formatter.format(configs.get("VertSkE")));
			txtInformationswert.setText(formatter.format(configs.get("VertIW")));
			txtCup.setText(formatter.format(configs.get("VertCUP")));
			txtLoyalitaet.setText(formatter.format(configs.get("VertLP")));
			txtTotal.setText(formatter.format(calculateTotalValue()));
			
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this,
				    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
				    "Datenbank-Fehler!",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void initialize() {
		fmtLocale = Locale.getDefault();
		formatter = NumberFormat.getInstance(fmtLocale);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		try {
			workflow = new WorkflowManagerImpl();
		}catch (DBException e) {
				JOptionPane.showMessageDialog(contentPane,
					    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
					    "Datenbank-Fehler!",
					    JOptionPane.ERROR_MESSAGE);
			
		}
		clickListener = new ActionListenerImpl();
		docListener = new DocumentListenerImpl();
		
		setTitle("Gewichtungsfaktoren editieren - CPVM");
		setBounds(700, 400, 450, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKonfiguration = new JLabel("Gewichtungsfaktoren");
		lblKonfiguration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKonfiguration.setBounds(43, 47, 160, 25);
		contentPane.add(lblKonfiguration);
		
		JLabel lblRentabilitt = new JLabel("Rentabilit\u00E4t:");
		lblRentabilitt.setBounds(43, 100, 132, 14);
		contentPane.add(lblRentabilitt);
		
		txtRenta = new JTextField();
		txtRenta.setBounds(242, 97, 86, 20);
		txtRenta.getDocument().addDocumentListener(docListener);
		contentPane.add(txtRenta);
		txtRenta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ROI:");
		lblNewLabel.setBounds(43, 125, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtRoi = new JTextField();
		txtRoi.setBounds(242, 122, 86, 20);
		txtRoi.getDocument().addDocumentListener(docListener);
		contentPane.add(txtRoi);
		txtRoi.setColumns(10);
		
		JLabel lblDeckungsbeitrag = new JLabel("Deckungsbeitrag:");
		lblDeckungsbeitrag.setBounds(43, 150, 132, 14);
		contentPane.add(lblDeckungsbeitrag);
		
		txtDeckungsbeitrag = new JTextField();
		txtDeckungsbeitrag.setBounds(242, 147, 86, 20);
		txtDeckungsbeitrag.getDocument().addDocumentListener(docListener);
		contentPane.add(txtDeckungsbeitrag);
		txtDeckungsbeitrag.setColumns(10);
		
		JLabel lblSkaleneffekt = new JLabel("Skaleneffekt:");
		lblSkaleneffekt.setBounds(43, 175, 145, 14);
		contentPane.add(lblSkaleneffekt);
		
		txtSkaleneffekt = new JTextField();
		txtSkaleneffekt.setBounds(242, 172, 86, 20);
		txtSkaleneffekt.getDocument().addDocumentListener(docListener);
		contentPane.add(txtSkaleneffekt);
		txtSkaleneffekt.setColumns(10);
		
		JLabel lblInformationswert = new JLabel("Informationswert:");
		lblInformationswert.setBounds(43, 205, 132, 14);
		contentPane.add(lblInformationswert);
		
		txtInformationswert = new JTextField();
		txtInformationswert.setBounds(242, 202, 86, 20);
		txtInformationswert.getDocument().addDocumentListener(docListener);
		contentPane.add(txtInformationswert);
		txtInformationswert.setColumns(10);
		
		JLabel lblCrossupbuyingPotenzial = new JLabel("Cross-/Up-Buying Potenzial:");
		lblCrossupbuyingPotenzial.setBounds(43, 236, 189, 14);
		contentPane.add(lblCrossupbuyingPotenzial);
		
		txtCup = new JTextField();
		txtCup.setBounds(242, 233, 86, 20);
		txtCup.getDocument().addDocumentListener(docListener);
		contentPane.add(txtCup);
		txtCup.setColumns(10);
		
		JLabel lblLoyalittspotenzial = new JLabel("Loyalit\u00E4tspotenzial:");
		lblLoyalittspotenzial.setBounds(43, 267, 160, 14);
		contentPane.add(lblLoyalittspotenzial);
		
		txtLoyalitaet = new JTextField();
		txtLoyalitaet.setBounds(242, 264, 86, 20);
		txtLoyalitaet.getDocument().addDocumentListener(docListener);
		contentPane.add(txtLoyalitaet);
		txtLoyalitaet.setColumns(10);
		
		JLabel lblGesamtwert = new JLabel("Gesamtwert:");
		lblGesamtwert.setBounds(43, 333, 132, 14);
		contentPane.add(lblGesamtwert);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(242, 330, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(146, 367, 122, 23);
		btnSpeichern.addActionListener(clickListener);
		contentPane.add(btnSpeichern);
	}


	@Override
	public void display() {
		try {
			synchronizeData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					contentPane,
					"Die Daten aus der Datenbank sind nicht gültig bzw. haben ein falsches Format!", "Format-Fehler",
					JOptionPane.ERROR_MESSAGE);
		}
		this.setVisible(true);
	}


	@Override
	public void close() {
		this.setVisible(false);
	}
	
	/**
	 * Berechnet den Gesamtwert der eingetragenen Gewichtungen.
	 * @return Gesamtwert der Gewichtungen
	 * @throws NumberFormatException Fehler falls kein Double-Wert eingegeben wird.
	 * @throws ParseException Fehler bei falschem Format
	 */
	private double calculateTotalValue() throws NumberFormatException, ParseException{
		Number number;
		number = formatter.parse(txtRenta.getText());
		renta = number.doubleValue();
		number = formatter.parse(txtRoi.getText());
		roi = number.doubleValue();
		number = formatter.parse(txtDeckungsbeitrag.getText());
		db = number.doubleValue();
		number = formatter.parse(txtSkaleneffekt.getText());
		ske = number.doubleValue();
		number = formatter.parse(txtInformationswert.getText());
		iw = number.doubleValue();
		number = formatter.parse(txtCup.getText());
		cup = number.doubleValue();
		number = formatter.parse(txtLoyalitaet.getText());
		loyal = number.doubleValue();
		
		return renta+roi+db+ske+iw+cup+loyal;
	}
	
	/**
	 * Private ActionListener-Klasse
	 * @author Mustafa
	 *
	 */
	private class ActionListenerImpl implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource() == btnSpeichern){
				if(total<100){
					// laut Anforderung: Unter 100 soll ein Hinweis erscheinen
					Object[] options = {"Ja", "Nein"};
					int response = JOptionPane.showOptionDialog(contentPane,
					    "Der Gesamtwert der Gewichtungen liegt unter 100. \nMöchten Sie die Änderungen trotzdem speichern?",
					    "Warnung",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.WARNING_MESSAGE,
					    null,
					    options,
					    options[0]);
					
					if(response == 0){
						// Klick auf "Ja"
						saveChangings();
					}
						
				}
				else{
					saveChangings();
				}
			}
			
		}
		
		private void saveChangings(){
			if (isValid) {
				try {

					configs.put("VertRenta", renta);
					configs.put("VertROI", roi);
					configs.put("VertDB", db);
					configs.put("VertSkE", ske);
					configs.put("VertIW", iw);
					configs.put("VertCUP", cup);
					configs.put("VertLP", loyal);

					boolean success = false;
					success = workflow
							.updateCustomervaluesConfigurations(configs);
					if (success) {
						JOptionPane.showMessageDialog(contentPane,
								"Die Daten wurden erfolgreich aktualisiert.",
								"Hinweis", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane
								.showMessageDialog(
										contentPane,
										"Der Gesamtwert 100 wurde überschritten oder die Eingabedaten sind nicht korrekt. \nBitte überprüfen!",
										"Fehler", JOptionPane.ERROR_MESSAGE);
					}
				} catch (DBException e) {
					JOptionPane.showMessageDialog(
							contentPane,
							"Es ist ein Datenbank-Fehler aufgetreten: "
									+ e.getMessage(), "Datenbank-Fehler",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(
						contentPane,
						"Die Eingabe war nicht gültig. Bitte verwenden Sie keine Buchstaben!", "Format-Fehler",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	
	
	/**
	 * Private DocumentListener-Klasse zum Reagieren auf Veränderungen bei den Textfeldern.
	 * @author Mustafa
	 *
	 */
	private class DocumentListenerImpl implements DocumentListener{

		public void changedUpdate(DocumentEvent arg0) {
			updateTotalValue();
		}

		public void insertUpdate(DocumentEvent arg0) {
			updateTotalValue();
		}

		public void removeUpdate(DocumentEvent arg0) {
			updateTotalValue();
		}

		private void updateTotalValue(){
			try{
				total = calculateTotalValue();
				txtTotal.setText(formatter.format(total));
				isValid = true;
			}catch (NumberFormatException e){
				// Exceptions treten sicher auf, da während Änderungen der String-Wert nicht immer ein korrekter Double-Wert sein kann.
				txtTotal.setText("...");
				isValid = false;
			}
			catch (ParseException e){
				// Exceptions treten sicher auf, da während Änderungen der String-Wert nicht immer ein korrekter Double-Wert sein kann.
				txtTotal.setText("...");
				isValid = false;
			}
		}
				
	}
	
}
