package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import cs.hm.edu.cpvm.common.exceptions.DBException;
import cs.hm.edu.cpvm.graphicalLayer.Controller;
import cs.hm.edu.cpvm.logicalLayer.workflow.WorkflowManager;
import cs.hm.edu.cpvm.logicalLayer.workflow.impl.WorkflowManagerImpl;

public class WaitingView extends JFrame implements Controller{

	private JPanel contentPane;
	private WorkflowManager workflow;
	private WorkflowHandler handler; // eigener Thread zum Beobachten des Workflows
	
	/**
	 * Dient nur zu Testzwecken
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaitingView frame = new WaitingView();
					frame.initialize();
					frame.display();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	public void initialize() {
		try {
			workflow = new WorkflowManagerImpl();
		}catch (DBException e) {
				JOptionPane.showMessageDialog(contentPane,
					    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
					    "Datenbank-Fehler!",
					    JOptionPane.ERROR_MESSAGE);
			
		}
		
		
		setTitle("Berechnungsvorgang");
		setBounds(700, 400, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProcessingText = new JLabel("Die Kundenwerte werden berechnet");
		lblProcessingText.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProcessingText.setBounds(120, 47, 300, 25);
		contentPane.add(lblProcessingText);
		
		JLabel lblWaitingText = new JLabel("Bitte haben Sie einen Moment Geduld...");
		lblWaitingText.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWaitingText.setBounds(120, 85, 300, 14);
		contentPane.add(lblWaitingText);
		
		JLabel lblSandclock = new JLabel(new ImageIcon("img/sanduhr.gif"));
		lblSandclock.setBounds(20,20,100,100);
		contentPane.add(lblSandclock);
		
		
	}


	@Override
	public void display() {
		this.setVisible(true);
		handler = new WorkflowHandler();
		startCalculation();
		handler.start();
		
	}


	@Override
	public void close() {
		this.setVisible(false);
		handler = null;
	}
	
	
	private void startCalculation(){
		try {
			workflow.startCalculation();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this,
				    "Es ist ein Datenbank-Fehler aufgetreten: " + e.getMessage(),
				    "Datenbank-Fehler!",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class WorkflowHandler extends Thread{

		
		public void run() {
			
			do{
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(contentPane,
						    "Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage(),
						    "Fehler",
						    JOptionPane.ERROR_MESSAGE);
				}
			}while(!workflow.isCalculationDone());
			
			close();
			Controller customerResultView;
			customerResultView = new CustomerResultView();
			customerResultView.initialize();
			customerResultView.display();
			//finish();
		}
		
		private void finish(){
			handler = null;
		}
		
	}
	
}
