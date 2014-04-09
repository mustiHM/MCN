package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cs.hm.edu.cpvm.graphicalLayer.Controller;

public class CustomerResultView extends JFrame implements Controller {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Nur zu Testzwecken
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerResultView frame = new CustomerResultView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CustomerResultView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}


	@Override
	public void initialize() {
		setTitle("Kundenergebnisse - CPVM");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(400, 200, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		String[] titles = new String[] {
				"Kunde", "Kundenwert 1", "Kundenwert 2"
			};
		DefaultTableModel model = new DefaultTableModel(titles,50);
		
		
		table = new JTable(model);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		/*
		 * Einzelne Spalten bekommen eine bestimmte vordefinierte Breite
		 */
		table.getColumn("Kunde").setPreferredWidth(150);
		table.getColumn("Kundenwert 1").setPreferredWidth(120);
		table.getColumn("Kundenwert 2").setPreferredWidth(120);		
		
		
		JScrollPane scroller = new JScrollPane(table);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		scroller.setBounds(150, 62, 410, 500);
		contentPane.add(scroller);
		
		
		JButton btnSpeichern = new JButton("Formeln anzeigen");
		btnSpeichern.setBounds(180, 600, 150, 23);
		contentPane.add(btnSpeichern);
		

		JButton btnProtokoll = new JButton("Protokoll anzeigen");
		btnProtokoll.setBounds(350, 600, 150, 23);
		contentPane.add(btnProtokoll);
		
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
