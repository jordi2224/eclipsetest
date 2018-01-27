package stega;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.filechooser.*;



public class Ventana extends JFrame implements ActionListener {

    private JLabel tPassword, tTitleEncrypt, tInputFile, tInputMat, tOutputFile;
    private JLabel tCheckPass;
    private JButton boton, selFichero1, selFichero2;          
    private JPasswordField passwordField, checkPassField;
    private JTextField file1, file2, file3;
    private JTextArea log;
    private JFileChooser fc1;
    private JFileChooser fc2;
    
    private static final long serialVersionUID = 1L;    //No estoy seguro de por que esto es necesario
    													//pero sin ello da warning
    
    Ventana(){ //Constructor
        //super();
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

    private void startJComponents() { //TODO El log no tira, hacer su propia clase?
    	
    	//LOG{
    	
    	log = new JTextArea(5,2);
        log.setBounds(50, 360, 1100, 180);
        log.setEditable(false);
        log.setMargin(new Insets(5, 5, 5, 5)); //TODO Esto no va. REEEEEEEEEEEEEEE

    	
    	// }LOG
        
        fc1 = new JFileChooser();
        fc1.setCurrentDirectory(new File(System.getProperty("user.home"))); //TODO esto tira en windows?
        fc2 = new JFileChooser();
        fc2.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        tPassword = new JLabel();
        tCheckPass = new JLabel();
        tTitleEncrypt = new JLabel();
        tInputFile = new JLabel();
        tInputMat = new JLabel();
        tOutputFile = new JLabel();
        file1 = new JTextField();
        file2 = new JTextField();
        file3 = new JTextField();
        passwordField = new JPasswordField(10);
        passwordField.addActionListener(this);
        checkPassField = new JPasswordField(10);
        checkPassField.addActionListener(this);
        
        boton = new JButton();
        selFichero1 = new JButton();
        selFichero2 = new JButton();
        
        tPassword.setText("Password:");    
        tPassword.setBounds(50, 215, 100, 10);
        
        tCheckPass.setText("Verify Password:");    
        tCheckPass.setBounds(50, 230, 200, 40);
        
        tTitleEncrypt.setText("Hide a file");
        tTitleEncrypt.setBounds(50, 20, 100, 25);
        
        tInputFile.setText("Input file:");
        tInputFile.setBounds(50, 80, 100, 30);
        file1.setBounds(180, 80, 300, 30);
        selFichero1.setText("...  1");
        selFichero1.setBounds(480, 80, 30, 30);
        selFichero1.addActionListener(this);
        
        tInputMat.setText("Input image:");
        tInputMat.setBounds(50, 110, 100, 30);
        file2.setBounds(180, 110, 300, 30);
        selFichero2.setText("...  2");
        selFichero2.setBounds(480, 110, 30, 30);
        selFichero2.addActionListener(this);
        
        tOutputFile.setText("Output image:");
        tOutputFile.setBounds(50, 140, 100, 30);
        file3.setBounds(180, 140, 300, 30);

        passwordField.setBounds(175, 210, 160, 25);
        checkPassField.setBounds(175, 240, 160, 25);
        
        boton.setText("Hide");
        boton.setBounds(50, 300, 200, 30);
        boton.addActionListener(this);
        
        this.add(tPassword);
        this.add(tCheckPass);
        this.add(tTitleEncrypt);
        this.add(tInputFile);
        this.add(file1);
        this.add(selFichero1);
        this.add(tInputMat);
        this.add(file2);
        this.add(selFichero2);
        this.add(tOutputFile);
        this.add(file3);
        this.add(passwordField);
        this.add(checkPassField);
        this.add(boton);
        this.add(log);
    }

    public void actionPerformed(ActionEvent e) {  //TODO esto es mejor en un case, en vez de getaction getID
    	
        if (e.getActionCommand().equals("Hide")) { //Boton encriptacion
        	
        	char[] password = passwordField.getPassword();
            char[] checkpass = checkPassField.getPassword();
            
        	if (Arrays.equals(password, checkpass)) {
    	        log.append("Password ok!\n");
            }else {
            	
    	        JOptionPane.showMessageDialog(this, "Mismatch!");

            }
        	
        }else if(e.getActionCommand().equals("...  1")){
        	
        	int returnVal = fc1.showOpenDialog(this);
        	
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		File selectedFile = fc1.getSelectedFile();
        		file1.setText(selectedFile.getAbsolutePath());
        	}
        	
        }else if(e.getActionCommand().equals("...  2")) {
        	
        	int returnVal = fc2.showOpenDialog(this);
        	
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		File selectedFile = fc2.getSelectedFile();
        		file2.setText(selectedFile.getAbsolutePath());
        	}
        	
        }
        
	        
    }

    public static void main(String[] args) {
        Ventana V = new Ventana();
        V.setVisible(true);
        V.log.append("Tloc. Nice!\n");
    }
}