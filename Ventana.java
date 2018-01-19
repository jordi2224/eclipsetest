package stega;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Ventana extends JFrame implements ActionListener {

    private JLabel tPassword, tTitleEncrypt, tInputFile, tInputMat, tOutputFile;
    private JButton boton;          
    private JPasswordField passwordField;
    
    public Ventana() {
        super();
        setWindow();
        startJComponents();
    }

    private void setWindow() {
        this.setTitle("DimiX Stega");                   
        this.setSize(1200, 600);                                 
        this.setLocationRelativeTo(null);                       
        this.setLayout(null);                                  
        this.setResizable(false);                           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void startJComponents() {
    	
        tPassword = new JLabel();
        tTitleEncrypt = new JLabel();
        tInputFile = new JLabel();
        tInputMat = new JLabel();
        tOutputFile = new JLabel();
        passwordField = new JPasswordField(10);
        passwordField.addActionListener(this);
        
        boton = new JButton();
        
        tPassword.setText("Password");    
        tPassword.setBounds(50, 250, 100, 10);
        
        tTitleEncrypt.setText("Hide a file:");
        tTitleEncrypt.setBounds(50, 20, 100, 25);
        
        tInputFile.setText("Input file:");
        tInputFile.setBounds(50, 80, 100, 30);
        
        tInputMat.setText("Input image:");
        tInputMat.setBounds(50, 110, 100, 30);
        
        tOutputFile.setText("Output image:");
        tOutputFile.setBounds(50, 140, 100, 30);
        
        passwordField.setBounds(150, 245, 100, 25);
        
        boton.setText("Hide");
        boton.setBounds(50, 300, 200, 30);
        boton.addActionListener(this);
        
        this.add(tPassword);
        this.add(tTitleEncrypt);
        this.add(tInputFile);
        this.add(tInputMat);
        this.add(tOutputFile);
        this.add(passwordField);
        this.add(boton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = passwordField.getText();
        JOptionPane.showMessageDialog(this,nombre);  
        
    }

    public static void main(String[] args) {
        Ventana V = new Ventana();
        V.setVisible(true);  
    }
}