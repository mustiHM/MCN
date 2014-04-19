package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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
	
	public void initialize() {
		
		workflow = new WorkflowManagerImpl();
		
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
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(427, 530, 95, 23);
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
}
