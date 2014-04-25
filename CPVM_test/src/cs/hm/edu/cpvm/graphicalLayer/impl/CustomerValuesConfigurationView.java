package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

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
	private KeyListener keyListener;
	private JButton btnSpeichern;
	
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

	/**
	 * Dient nur zu Testzwecken
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerValuesConfigurationView frame = new CustomerValuesConfigurationView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Lädt die Inhalte nochmal neu vom Workflow Manager und aktualisiert die Tabelle
	 */
	private void synchronizeData(){
		try {
			configs = workflow.getCustomervaluesConfigurations();
			txtRenta.setText("" + configs.get("VertRenta"));
			txtRoi.setText("" + configs.get("VertROI"));
			txtDeckungsbeitrag.setText("" + configs.get("VertDB"));
			txtSkaleneffekt.setText("" + configs.get("VertSkE"));
			txtInformationswert.setText("" + configs.get("VertIW"));
			txtCup.setText("" + configs.get("VertCUP"));
			txtLoyalitaet.setText("" + configs.get("VertLP"));
			txtTotal.setText("" + calculateTotalValue());
			
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this,
				    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
				    "Datenbank-Fehler!",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void initialize() {
		workflow = new WorkflowManagerImpl();
		clickListener = new ActionListenerImpl();
		keyListener = new KeyListenerImpl();
		
		setTitle("Konfiguration - CPVM");
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
		txtRenta.addKeyListener(keyListener);
		contentPane.add(txtRenta);
		txtRenta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ROI:");
		lblNewLabel.setBounds(43, 125, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtRoi = new JTextField();
		txtRoi.setBounds(242, 122, 86, 20);
		txtRoi.addKeyListener(keyListener);
		contentPane.add(txtRoi);
		txtRoi.setColumns(10);
		
		JLabel lblDeckungsbeitrag = new JLabel("Deckungsbeitrag:");
		lblDeckungsbeitrag.setBounds(43, 150, 132, 14);
		contentPane.add(lblDeckungsbeitrag);
		
		txtDeckungsbeitrag = new JTextField();
		txtDeckungsbeitrag.setBounds(242, 147, 86, 20);
		txtDeckungsbeitrag.addKeyListener(keyListener);
		contentPane.add(txtDeckungsbeitrag);
		txtDeckungsbeitrag.setColumns(10);
		
		JLabel lblSkaleneffekt = new JLabel("Skaleneffekt:");
		lblSkaleneffekt.setBounds(43, 175, 145, 14);
		contentPane.add(lblSkaleneffekt);
		
		txtSkaleneffekt = new JTextField();
		txtSkaleneffekt.setBounds(242, 172, 86, 20);
		txtSkaleneffekt.addKeyListener(keyListener);
		contentPane.add(txtSkaleneffekt);
		txtSkaleneffekt.setColumns(10);
		
		JLabel lblInformationswert = new JLabel("Informationswert:");
		lblInformationswert.setBounds(43, 205, 132, 14);
		contentPane.add(lblInformationswert);
		
		txtInformationswert = new JTextField();
		txtInformationswert.setBounds(242, 202, 86, 20);
		txtInformationswert.addKeyListener(keyListener);
		contentPane.add(txtInformationswert);
		txtInformationswert.setColumns(10);
		
		JLabel lblCrossupbuyingPotenzial = new JLabel("Cross-/Up-Buying Potenzial:");
		lblCrossupbuyingPotenzial.setBounds(43, 236, 189, 14);
		contentPane.add(lblCrossupbuyingPotenzial);
		
		txtCup = new JTextField();
		txtCup.setBounds(242, 233, 86, 20);
		txtCup.addKeyListener(keyListener);
		contentPane.add(txtCup);
		txtCup.setColumns(10);
		
		JLabel lblLoyalittspotenzial = new JLabel("Loyalit\u00E4tspotenzial:");
		lblLoyalittspotenzial.setBounds(43, 267, 160, 14);
		contentPane.add(lblLoyalittspotenzial);
		
		txtLoyalitaet = new JTextField();
		txtLoyalitaet.setBounds(242, 264, 86, 20);
		txtLoyalitaet.addKeyListener(keyListener);
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
		synchronizeData();
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
	 */
	private double calculateTotalValue() throws NumberFormatException{
		
		renta = Double.parseDouble(txtRenta.getText());
		roi = Double.parseDouble(txtRoi.getText());
		db = Double.parseDouble(txtDeckungsbeitrag.getText());
		ske = Double.parseDouble(txtSkaleneffekt.getText());
		iw = Double.parseDouble(txtInformationswert.getText());
		cup = Double.parseDouble(txtCup.getText());
		loyal = Double.parseDouble(txtLoyalitaet.getText());
		
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
				
			}
			
		}

	}
	
	/**
	 * Private KeyListener-Klasse
	 * @author Mustafa
	 *
	 */
	private class KeyListenerImpl implements KeyListener{

		public void keyPressed(KeyEvent arg0) {
			// unwichtig
		}

		public void keyReleased(KeyEvent arg0) {
			// unwichtig
		}

		public void keyTyped(KeyEvent arg0) {
			try{
				double total = calculateTotalValue();
				txtTotal.setText("" + total);
				System.out.println("Eine Taste wurde gedrückt.");
			}catch (NumberFormatException e){
				// Exceptions treten sicher auf, da während Änderungen der String-Wert nicht immer ein korrekter Double-Wert sein kann.
				txtTotal.setText("...");
			}
		}
		
	}
	
}
