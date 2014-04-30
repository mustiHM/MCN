package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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
	private JButton btnCalculationDoc;
	private JButton btnProtokoll;
	private ActionListener listener;
	private Controller protocolView;
	private Locale fmtLocale;
	private NumberFormat formatter;

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
				model.setValueAt(formatter.format(value.getCustomerValueResult1()), i, 1);
				model.setValueAt(formatter.format(value.getCustomerValueResult2()), i, 2);
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
		protocolView = new ProtocolView();
		protocolView.initialize();
		
		setTitle("Kundenergebnisse anzeigen - CPVM");
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
		table = new JTable(model) {
            /**
             * eigene Implementierung notwendig, damit bestimmte Spalten nicht editierbar sind!
             */
			public boolean isCellEditable(int x, int y) {
                return false;
            }
        };

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scroller = new JScrollPane(table);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		scroller.setBounds(10, 10, 410, 500);
		contentPane.add(scroller);
		
		
		btnCalculationDoc = new JButton("Formeln anzeigen");
		btnCalculationDoc.setBounds(40, 530, 150, 23);
		btnCalculationDoc.addActionListener(listener);
		contentPane.add(btnCalculationDoc);
		

		btnProtokoll = new JButton("Protokoll anzeigen");
		btnProtokoll.setBounds(210, 530, 150, 23);
		btnProtokoll.addActionListener(listener);
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

	
	/**
	 * Private ActionListener-Klasse
	 * @author Mustafa
	 *
	 */
	private class ActionListenerImpl implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(btnProtokoll)) {
				protocolView.display();
			}
			
			else if(arg0.getSource().equals(btnCalculationDoc)){
				try {
					Desktop.getDesktop().open(new File("files/Formelschema_mit_Beispielberechnung.pdf"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(contentPane,
						    "Es ist ein Fehler beim Öffnen des Log-Files aufgetreten: " + e1.getMessage(),
						    "Datei-Fehler!",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	
}
