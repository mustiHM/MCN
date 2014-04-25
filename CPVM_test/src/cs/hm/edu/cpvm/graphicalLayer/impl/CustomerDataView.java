package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
				model.setValueAt(value.getProfit(), i, 2);
				model.setValueAt(value.getSales(), i, 3);
				model.setValueAt(value.getContracts(), i, 4);
				model.setValueAt(value.getSalesDeduction(), i, 5);
				model.setValueAt(value.getInformationValue(), i, 6);
				model.setValueAt(value.getCup(), i, 7);
				model.setValueAt(value.getLoyality(), i, 8);
				model.setValueAt(value.getInvestment(), i, 9);
				model.setValueAt(value.getProfitability(), i, 10);
				model.setValueAt(value.getRoi(), i, 11);
				model.setValueAt(value.getProfitMargin(), i, 12);
				model.setValueAt(value.getScalefactor(), i, 13);
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
	 */
	private void updateValues(){
		if(values!=null){
			for(int i=0; i<model.getRowCount(); i++){
				Customervalues value = values.get(i);
				Object tableObj;
				
				for(int columnCount=2; columnCount<=8; columnCount++){
					tableObj = (Object) model.getValueAt(i, columnCount);
					if (tableObj instanceof String){
						// geänderte Werte haben den Datentyp String.
						switch(columnCount){
							case 2: value.setProfit(Double.parseDouble((String) tableObj));
									break;
							case 3: value.setSales(Double.parseDouble((String) tableObj));
									break;
							case 4: value.setContracts(Integer.parseInt((String) tableObj));
									break;
							case 5: value.setSalesDeduction(Double.parseDouble((String) tableObj));
									break;
							case 6: value.setInformationValue(Double.parseDouble((String) tableObj));
									break;
							case 7: value.setCup(Integer.parseInt((String) tableObj));
									break;
							case 8: value.setLoyality(Integer.parseInt((String) tableObj));
									break;
							case 9: value.setInvestment(Double.parseDouble((String) tableObj));
									break;
							case 10: value.setProfitability(Double.parseDouble((String) tableObj));
									break;
							case 11: value.setRoi(Double.parseDouble((String) tableObj));
									break;
							case 12: value.setProfitMargin(Double.parseDouble((String) tableObj));
									break;
							case 13: value.setScalefactor(Double.parseDouble((String) tableObj));
									break;
						}
					}
				}
				
			}
		}
		
	}
	
	public void initialize() {
		
		workflow = new WorkflowManagerImpl();
		listener = new ActionListenerImpl();
		
		setTitle("Kundendaten - CPVM");
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
						    "Es ist ein Validierungs-Fehler aufgetreten: " + e.getMessage(),
						    "Validierungsfehler!",
						    JOptionPane.ERROR_MESSAGE);
				}
			} 
		}
		
	}
	
}
