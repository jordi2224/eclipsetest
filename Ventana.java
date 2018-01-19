package stega;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;



public class Ventana extends JFrame implements ActionListener {

    private JLabel tPassword, tTitleEncrypt, tInputFile, tInputMat, tOutputFile;
    private JLabel tCheckPass;
    private JButton boton, selFichero1, selFichero2;          
    private JPasswordField passwordField, checkPassField;
    private JTextArea log;
    
    private static final long serialVersionUID = 1L;    //No estoy seguro de porque esto es necesario
    													//pero sin ello da warning
    
    Ventana(){
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
    	
    	//LOG{
    	
    	log = new JTextArea(50,200);
        log.setBounds(50, 360, 1100, 180);
        log.setEditable(false);
        log.setMargin(new Insets(5, 5, 5, 5)); //TODO Esto no va. REEEEEEEEEEEEEEE
        JScrollPane logScrollPane = new JScrollPane(log);
    	
    	// }LOG
        
        
        tPassword = new JLabel();
        tCheckPass = new JLabel();
        tTitleEncrypt = new JLabel();
        tInputFile = new JLabel();
        tInputMat = new JLabel();
        tOutputFile = new JLabel();
        passwordField = new JPasswordField(10);
        passwordField.addActionListener(this);
        checkPassField = new JPasswordField(10);
        checkPassField.addActionListener(this);
        
        boton = new JButton();
        
        tPassword.setText("Password:");    
        tPassword.setBounds(50, 215, 100, 10);
        
        tCheckPass.setText("Verify Password:");    
        tCheckPass.setBounds(50, 230, 200, 40);
        
        tTitleEncrypt.setText("Hide a file");
        tTitleEncrypt.setBounds(50, 20, 100, 25);
        
        tInputFile.setText("Input file:");
        tInputFile.setBounds(50, 80, 100, 30);
        
        tInputMat.setText("Input image:");
        tInputMat.setBounds(50, 110, 100, 30);
        
        tOutputFile.setText("Output image:");
        tOutputFile.setBounds(50, 140, 100, 30);
        
        passwordField.setBounds(175, 210, 160, 25);
        checkPassField.setBounds(175, 240, 160, 25);
        
        boton.setText("Hide");
        boton.setBounds(50, 300, 200, 30);
        boton.addActionListener(this);
        
        this.add(tPassword);
        this.add(tCheckPass);
        this.add(tTitleEncrypt);
        this.add(tInputFile);
        this.add(tInputMat);
        this.add(tOutputFile);
        this.add(passwordField);
        this.add(checkPassField);
        this.add(boton);
        this.add(log);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        char[] password = passwordField.getPassword();
        char[] checkpass = checkPassField.getPassword();
        
        
        if (Arrays.equals(password, checkpass)) {
	        log.append("Password ok!\n");
        }else {
        	
	        JOptionPane.showMessageDialog(this, "Mismatch!");

        }
	        
    }

    public static void main(String[] args) {
        Ventana V = new Ventana();
        V.setVisible(true);
        V.log.append("Tloc. Nice!\n");
    }
}