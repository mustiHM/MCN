package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.graphicalLayer.Controller;
import cs.hm.edu.cpvm.logicalLayer.workflow.WorkflowManager;
import cs.hm.edu.cpvm.logicalLayer.workflow.impl.WorkflowManagerImpl;

public class CustomerDataView extends JFrame implements Controller {

	private WorkflowManager workflow;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Customervalues> values;
	private DefaultTableModel model;
	private String[] titles;
	private JButton btnSpeichern;
	private ActionListener listener;
	private Locale fmtLocale;
	private NumberFormat formatter;


	/**
	 * Dient nur zu Testzwecken.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDataView frame = new CustomerDataView();
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
	 */
	private void synchronizeData(){
		try {
			values = workflow.getCustomervalues();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this,
				    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
				    "Datenbank-Fehler!",
				    JOptionPane.ERROR_MESSAGE);
			
		}
		
		if(values!=null){
			model = new DefaultTableModel(titles,values.size());
			for(int i=0; i<values.size(); i++){
				Customervalues value = values.get(i);
				model.setValueAt(value.getCustomerdata().getId(), i, 0);
				model.setValueAt(value.getCustomerdata().getFirstName() + " " + value.getCustomerdata().getLastName(), i, 1);
				model.setValueAt(formatter.format(value.getProfit()), i, 2);
				model.setValueAt(formatter.format(value.getSales()), i, 3);
				model.setValueAt(value.getContracts(), i, 4);
				model.setValueAt(formatter.format(value.getSalesDeduction()), i, 5);
				model.setValueAt(formatter.format(value.getInformationValue()), i, 6);
				model.setValueAt(value.getCup(), i, 7);
				model.setValueAt(value.getLoyality(), i, 8);
				model.setValueAt(formatter.format(value.getInvestment()), i, 9);
				model.setValueAt(formatter.format(value.getProfitability()), i, 10);
				model.setValueAt(formatter.format(value.getRoi()), i, 11);
				model.setValueAt(formatter.format(value.getProfitMargin()), i, 12);
				model.setValueAt(formatter.format(value.getScalefactor()), i, 13);
			}
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		/*
		 * Einzelne Spalten bekommen eine bestimmte vordefinierte Breite
		 */
		table.getColumn("Name").setPreferredWidth(150);
		table.getColumn("Erlösschmälerung").setPreferredWidth(120);
		table.getColumn("Informationswert").setPreferredWidth(120);		
		table.getColumn("Cross-/Up-Buying").setPreferredWidth(120);
		table.getColumn("Loyalitätspotenzial").setPreferredWidth(120);		
		table.getColumn("Investitionswert").setPreferredWidth(110);
		table.getColumn("Deckungsbeitrag").setPreferredWidth(110);
		
	}
	
	/**
	 * Liest die Daten aus der Tabelle (bis zur Spalte Loyalitätspotenzial) und speichert sie zurück in die ArrayList.
	 * @throws ParseException  bei falschen Datenformaten
	 */
	private void updateValues() throws ParseException{
		if(values!=null){
			for(int i=0; i<model.getRowCount(); i++){
				Customervalues value = values.get(i);
				Object tableObj;
				
				for(int columnCount=2; columnCount<=8; columnCount++){
					tableObj = (Object) model.getValueAt(i, columnCount);
					if (tableObj instanceof String){
						// geänderte Werte haben den Datentyp String.
						Number number;
						switch(columnCount){
							case 2: number = formatter.parse((String) tableObj);
									value.setProfit(number.doubleValue());
									break;
							case 3: number = formatter.parse((String) tableObj);
									value.setSales(number.doubleValue());
									break;
							case 4: value.setContracts(Integer.parseInt((String) tableObj));
									break;
							case 5: number = formatter.parse((String) tableObj);
									value.setSalesDeduction(number.doubleValue());
									break;
							case 6: number = formatter.parse((String) tableObj);
									value.setInformationValue(number.doubleValue());
									break;
							case 7: value.setCup(Integer.parseInt((String) tableObj));
									break;
							case 8: value.setLoyality(Integer.parseInt((String) tableObj));
									break;
							case 9: number = formatter.parse((String) tableObj);
									value.setInvestment(number.doubleValue());
									break;
							case 10:number = formatter.parse((String) tableObj); 
									value.setProfitability(number.doubleValue());
									break;
							case 11:number = formatter.parse((String) tableObj); 
									value.setRoi(number.doubleValue());
									break;
							case 12:number = formatter.parse((String) tableObj); 
									value.setProfitMargin(number.doubleValue());
									break;
							case 13:number = formatter.parse((String) tableObj); 
									value.setScalefactor(number.doubleValue());
									break;
						}
					}
				}
				
			}
		}
		
	}
	
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
		listener = new ActionListenerImpl();
		
		setTitle("Kundendaten anzeigen/bearbeiten - CPVM");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(400, 200, 975, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titles = new String[] {
				"Kundennr.", "Name", "Gewinn", "Umsatz", "Verträge", "Erlösschmälerung", "Informationswert", "Cross-/Up-Buying", "Loyalitätspotenzial", "Investitionswert", "Rentabilität", "ROI", "Deckungsbeitrag", "Skaleneffekt"
			};
		DefaultTableModel model = new DefaultTableModel(titles,10);		
		table = new JTable(model);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		JScrollPane scroller = new JScrollPane(table);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		scroller.setBounds(10, 10, 950, 500);
		contentPane.add(scroller);
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(427, 530, 95, 23);
		btnSpeichern.addActionListener(listener);
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
	 * Private ActionListener-Klasse
	 * @author Mustafa
	 *
	 */
	private class ActionListenerImpl implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(btnSpeichern)) {
				try {
					updateValues();
					boolean saved = workflow.updateCustomervalues(values);
					if(saved){
						JOptionPane.showMessageDialog(contentPane,
							    "Die Daten wurden erfolgreich aktualisiert.",
							    "Hinweis",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (DBException e) {
					JOptionPane.showMessageDialog(contentPane,
						    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
						    "Datenbank-Fehler!",
						    JOptionPane.ERROR_MESSAGE);
				} catch (ValidationException e) {
					JOptionPane.showMessageDialog(contentPane,
						    "Es ist ein Validierungs-Fehler aufgetreten: " + e.getMessage(),
						    "Validierungsfehler!",
						    JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(contentPane,
						    "Es ist ein Datentyp-Fehler aufgetreten: " + e.getMessage(),
						    "Datentypfehler!",
						    JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e){
					JOptionPane.showMessageDialog(contentPane,
						    "Es ist ein Datentyp-Fehler aufgetreten: " + e.getMessage(),
						    "Datentypfehler!",
						    JOptionPane.ERROR_MESSAGE);
				}
			} 
		}
		
	}
	
}
