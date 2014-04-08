package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import cs.hm.edu.cpvm.graphicalLayer.Controller;

public class CustomerDataView extends JFrame implements Controller {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Dient nur zu Testzwecken.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDataView frame = new CustomerDataView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void initialize() {
			
		setTitle("Kundendaten - CPVM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		String[] titles = new String[] {
				"Kundennr.", "Name", "Gewinn", "Umsatz", "Verträge", "Erlösschmälerung", "Informationswert", "Cross-/Up-Buying", "Loyalitätspotenzial", "Investitionswert", "Rentabilität", "ROI", "Deckungsbeitrag", "Skaleneffekt"
			};
		DefaultTableModel model = new DefaultTableModel(titles,50);
		
		
		table = new JTable(model);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
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
		
		
		JScrollPane scroller = new JScrollPane(table);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		scroller.setBounds(79, 62, 800, 500);
		contentPane.add(scroller);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(450, 600, 95, 23);
		contentPane.add(btnSpeichern);

	}


	@Override
	public void display() {
		this.setVisible(true);
	}


	@Override
	public void close() {
		this.setVisible(false);
	}
}
