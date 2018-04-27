package stega.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ClavePrompt extends JFrame{

	
	boolean incomplete = true;
	char[] clave;
	private JPasswordField passwordField;
	private JButton botonOK;
	private JButton botonCancel;
	private JLabel titulo;
	private JPanel panelBotones;
	private JPanel panelClave;
	
	public ClavePrompt(){
		super();
		this.setLayout(new BorderLayout(0, 0));
		this.setTitle("Enter Password");                   
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		titulo = new JLabel("Enter password for selected file: ");
		this.add(titulo, BorderLayout.NORTH);
		
		panelClave = new JPanel(new FlowLayout());
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panelClave.add(passwordField);
		this.add(panelClave, BorderLayout.CENTER);
		
		
		
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		
		botonOK = new JButton("OK");
		botonOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClavePrompt.this.clave = ClavePrompt.this.passwordField.getPassword();
				ClavePrompt.this.incomplete = false;
			}
			
		});
		panelBotones.add(botonOK);
		
		botonCancel = new JButton("Cancel");
		botonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClavePrompt.this.incomplete = false;
			}
		});
		panelBotones.add(botonCancel);
        
		this.add(panelBotones, BorderLayout.SOUTH);
		
		
		this.setVisible(true);
	}
	
	
	
	char[] getClave(Ventana v) {
		while (incomplete) {
			try {
				Thread.sleep(50);
				v.print("Bip\n");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return clave;
	}
}
