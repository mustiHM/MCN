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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.common.exceptions.ValidationException;
import cs.hm.edu.cpvm.common.models.CalculationLogging;
import cs.hm.edu.cpvm.common.models.Customervalues;
import cs.hm.edu.cpvm.graphicalLayer.Controller;
import cs.hm.edu.cpvm.logicalLayer.workflow.WorkflowManager;
import cs.hm.edu.cpvm.logicalLayer.workflow.impl.WorkflowManagerImpl;

public class ProtocolView extends JFrame implements Controller {

	private WorkflowManager workflow;
	private JPanel contentPane;
	private JTextArea protocollField;
	private CalculationLogging protocol;
	private JLabel title;

	/**
	 * Dient nur zu Testzwecken.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProtocolView frame = new ProtocolView();
					frame.initialize();
					frame.display();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Lädt die Inhalte nochmal neu vom Workflow Manager und aktualisiert die Anzeige
	 */
	private void synchronizeData(){
		try {
			protocol = workflow.getLastCalculationLogging();
			title.setText("Protokoll vom " + protocol.getLoggingDate());
			protocollField.setText(protocol.getLogMessage());
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this,
				    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
				    "Datenbank-Fehler!",
				    JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
	
	
	public void initialize() {
		
		try {
			workflow = new WorkflowManagerImpl();
		}catch (DBException e) {
				JOptionPane.showMessageDialog(contentPane,
					    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
					    "Datenbank-Fehler!",
					    JOptionPane.ERROR_MESSAGE);
			
		}
		
		setTitle("Protokoll anzeigen - CPVM");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(400, 200, 975, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title = new JLabel("Protokoll");
		title.setBounds(10, 10, 250, 20);
		contentPane.add(title);
		
		protocollField = new JTextArea();
		protocollField.setEditable(false);
		
		JScrollPane scroller = new JScrollPane(protocollField);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		scroller.setBounds(10, 30, 950, 500);
		contentPane.add(scroller);
		
		
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
