package cs.hm.edu.cpvm.graphicalLayer.impl;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import cs.hm.edu.cpvm.graphicalLayer.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView extends JFrame implements Controller{

	private JPanel contentPane;
	private JButton btnKundendatenAnzeigen;
	private JButton btnKundenwerteBerechnen;
	private JButton btnKundenergebnisseAnzeigen;
	private JButton btnGewichtungsfaktorenEditieren;
	private ActionListener listener;
	
	private Controller viewKundendatenAnzeigen;
	private Controller viewKundenErgebnisseAnzeigen;
	private Controller viewKonfigAnzeigen;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	
	public void initialize() {
	
		viewKundendatenAnzeigen = new CustomerDataView();
		viewKundendatenAnzeigen.initialize();
		viewKundenErgebnisseAnzeigen = new CustomerResultView();
		viewKundenErgebnisseAnzeigen.initialize();
		viewKonfigAnzeigen = new CustomerValuesConfigurationView();
		viewKonfigAnzeigen.initialize();
		
		listener = new ActionListenerImpl();
		
		setTitle("Hauptmen\u00FC - CPVM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnKundendatenAnzeigen = new JButton("Kundendaten anzeigen/bearbeiten");
		btnKundendatenAnzeigen.setBounds(115, 102, 240, 60);
		btnKundendatenAnzeigen.addActionListener(listener);
		contentPane.add(btnKundendatenAnzeigen);
		
		btnKundenwerteBerechnen = new JButton("Kundenwerte berechnen");
		btnKundenwerteBerechnen.setBounds(115, 168, 240, 60);
		btnKundenwerteBerechnen.addActionListener(listener);
		contentPane.add(btnKundenwerteBerechnen);
		
		btnKundenergebnisseAnzeigen = new JButton("Kundenergebnisse anzeigen");
		btnKundenergebnisseAnzeigen.addActionListener(listener);
		btnKundenergebnisseAnzeigen.setBounds(115, 234, 240, 60);
		contentPane.add(btnKundenergebnisseAnzeigen);
		
		btnGewichtungsfaktorenEditieren = new JButton("Gewichtungsfaktoren editieren");
		btnGewichtungsfaktorenEditieren.setBounds(115, 300, 240, 60);
		btnGewichtungsfaktorenEditieren.addActionListener(listener);
		contentPane.add(btnGewichtungsfaktorenEditieren);
		
		
	}

	
	public void close() {
		// kann für das Hauptmenü ignoriert werden, da es auf JFrame.EXIT_ON_CLOSE hört.
		
	}
	
	public void display(){
		// kann für das Hauptmenü ignoriert werden, da es dauerhaft angezeigt wird.
	}
	
	/**
	 * Private ActionListener-Klasse
	 * @author Mustafa
	 *
	 */
	private class ActionListenerImpl implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(btnKundendatenAnzeigen)) {
				viewKundendatenAnzeigen.display();
			} else if (arg0.getSource().equals(btnKundenwerteBerechnen)) {

			} else if (arg0.getSource().equals(btnKundenergebnisseAnzeigen)) {
				viewKundenErgebnisseAnzeigen.display();
			} else if (arg0.getSource().equals(btnGewichtungsfaktorenEditieren)) {
				viewKonfigAnzeigen.display();
			}
		}
		
	}

	
}
