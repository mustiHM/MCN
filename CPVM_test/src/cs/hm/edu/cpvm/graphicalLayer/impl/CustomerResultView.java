package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.graphicalLayer.Controller;
import cs.hm.edu.cpvm.logicalLayer.workflow.WorkflowManager;
import cs.hm.edu.cpvm.logicalLayer.workflow.impl.WorkflowManagerImpl;

public class CustomerResultView extends JFrame implements Controller {

	private JPanel contentPane;
	private JTable table;
	private WorkflowManager workflow;
	private ArrayList<Customervalues> values;
	private DefaultTableModel model;
	private String[] titles;

	/**
	 * Nur zu Testzwecken
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerResultView frame = new CustomerResultView();
					frame.initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CustomerResultView() {
		
	}

	/**
	 * Synchronisiert den Inhalt der Tabelle mit den Inhalten aus der DB.
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
				model.setValueAt(value.getCustomerdata().getFirstName() + " " + value.getCustomerdata().getLastName(), i, 0);
				model.setValueAt(value.getCustomerValueResult1(), i, 1);
				model.setValueAt(value.getCustomerValueResult2(), i, 2);
			}
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		/*
		 * Einzelne Spalten bekommen eine bestimmte vordefinierte Breite
		 */
		table.getColumn("Kunde").setPreferredWidth(170);
		table.getColumn("Kundenwert 1").setPreferredWidth(120);
		table.getColumn("Kundenwert 2").setPreferredWidth(120);		
		
		
		
	}
	

	@Override
	public void initialize() {
		workflow = new WorkflowManagerImpl();
		
		setTitle("Kundenergebnisse - CPVM");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(705, 200, 435, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titles = new String[] {
				"Kunde", "Kundenwert 1", "Kundenwert 2"
			};
		
		
		model = new DefaultTableModel(titles,10);
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scroller = new JScrollPane(table);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		scroller.setBounds(10, 10, 410, 500);
		contentPane.add(scroller);
		
		
		JButton btnSpeichern = new JButton("Formeln anzeigen");
		btnSpeichern.setBounds(40, 530, 150, 23);
		contentPane.add(btnSpeichern);
		

		JButton btnProtokoll = new JButton("Protokoll anzeigen");
		btnProtokoll.setBounds(210, 530, 150, 23);
		contentPane.add(btnProtokoll);
		
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
