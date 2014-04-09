package cs.hm.edu.cpvm.graphicalLayer.impl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import cs.hm.edu.cpvm.graphicalLayer.Controller;

public class CustomerValuesConfigurationView extends JFrame implements Controller{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Dient nur zu Testzwecken
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerValuesConfigurationView frame = new CustomerValuesConfigurationView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	


	@Override
	public void initialize() {
		setTitle("Konfiguration - CPVM");
		setBounds(400, 200, 450, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKonfiguration = new JLabel("Gewichtungsfaktoren");
		lblKonfiguration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKonfiguration.setBounds(43, 47, 160, 25);
		contentPane.add(lblKonfiguration);
		
		JLabel lblRentabilitt = new JLabel("Rentabilit\u00E4t:");
		lblRentabilitt.setBounds(43, 100, 132, 14);
		contentPane.add(lblRentabilitt);
		
		textField = new JTextField();
		textField.setBounds(242, 97, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ROI:");
		lblNewLabel.setBounds(43, 125, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(242, 122, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDeckungsbeitrag = new JLabel("Deckungsbeitrag:");
		lblDeckungsbeitrag.setBounds(43, 150, 132, 14);
		contentPane.add(lblDeckungsbeitrag);
		
		textField_2 = new JTextField();
		textField_2.setBounds(242, 147, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSkaleneffekt = new JLabel("Skaleneffekt:");
		lblSkaleneffekt.setBounds(43, 175, 145, 14);
		contentPane.add(lblSkaleneffekt);
		
		textField_3 = new JTextField();
		textField_3.setBounds(242, 172, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblInformationswert = new JLabel("Informationswert:");
		lblInformationswert.setBounds(43, 205, 132, 14);
		contentPane.add(lblInformationswert);
		
		textField_4 = new JTextField();
		textField_4.setBounds(242, 202, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblCrossupbuyingPotenzial = new JLabel("Cross-/Up-Buying Potenzial:");
		lblCrossupbuyingPotenzial.setBounds(43, 236, 189, 14);
		contentPane.add(lblCrossupbuyingPotenzial);
		
		textField_5 = new JTextField();
		textField_5.setBounds(242, 233, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblLoyalittspotenzial = new JLabel("Loyalit\u00E4tspotenzial:");
		lblLoyalittspotenzial.setBounds(43, 267, 160, 14);
		contentPane.add(lblLoyalittspotenzial);
		
		textField_6 = new JTextField();
		textField_6.setBounds(242, 264, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblGesamtwert = new JLabel("Gesamtwert:");
		lblGesamtwert.setBounds(43, 333, 132, 14);
		contentPane.add(lblGesamtwert);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(242, 330, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(146, 367, 122, 23);
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
