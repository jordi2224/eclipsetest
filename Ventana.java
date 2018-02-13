package stega;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;

/* Jorge Huete
 * 
 * Interfaz grafica del programa
 * 
 * Las variables de este codigo son un laberinto solo para los duros de mente
 * 
 */

public class Ventana extends JFrame implements ActionListener {

    private JLabel tPassword, tTitleEncrypt, tInputFile, tInputMat, tOutputFile; //TODO: una variable por linea
    private JLabel tCheckPass;
    private JLabel tDecryptPass;
    private JLabel tTitleDecrypt;
    private JLabel tEncryptedImage;
    private JLabel tDEcryptedFile;
    private JButton hideB, selFichero1, selFichero2;  
    private JButton selFichero3;
    private JButton decB;
    private JPasswordField passwordField, checkPassField;
    private JPasswordField decryptionPass;
    private JTextField file1, file2, file3;
    private JTextField fileD1;
    private JTextField fileD2;
    private JTextArea log;
    private JFileChooser fc1;
    private JFileChooser fc2;
    private JFileChooser fc3;
    
    
    private static final long serialVersionUID = 1L;    //No estoy seguro de por que esto es necesario
    													//pero sin ello da warning
    
    Ventana(){ //Constructor
        //super();
        this.setWindow();
        startLeftJComponents();
        startRightJComponents();
        startLog();
    }

    private void setWindow() {
    	
        this.setTitle("DimiX Stega");                   
        this.setSize(1200, 600);                                 
        this.setLocationRelativeTo(null);                       
        this.setLayout(null);                                  
        this.setResizable(false);                           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    private void startLog() { //TODO no tira, no hace scroll
    	//LOG{
    	
    	log = new JTextArea(5,2);
        log.setBounds(50, 360, 1100, 180);
        log.setEditable(false);
        log.setMargin(new Insets(5, 5, 5, 5)); //TODO Esto no va. REEEEEEEEEEEEEEE

    	
    	// }LOG
        this.add(log);
    	
    }

    private void startRightJComponents() {
    	
    	tTitleDecrypt = new JLabel();
    	tEncryptedImage = new JLabel();
    	tDEcryptedFile = new JLabel();
    	tDecryptPass = new JLabel();
    	fileD1 = new JTextField();
    	fileD2 = new JTextField();
    	selFichero3 = new JButton();
    	decB = new JButton();
    	decryptionPass = new JPasswordField();
    	
    	tTitleDecrypt.setText("Find a file");
    	tTitleDecrypt.setBounds(650, 20, 100, 25);
    	
    	tEncryptedImage.setText("Input image:");
        tEncryptedImage.setBounds(650, 80, 100, 30);
        
        tDEcryptedFile.setText("Ouput File:");
        tDEcryptedFile.setBounds(650, 110, 100, 30);
        
        tDecryptPass.setText("Password");
        tDecryptPass.setBounds(650, 215, 100, 10);
        
        fileD1.setBounds(780, 80, 300, 30);
        fileD2.setBounds(780, 110, 300, 30);
    	
        selFichero3.setText("...  3");
        selFichero3.setBounds(1080, 80, 30, 30);
        selFichero3.addActionListener(this);
        fc3 = new JFileChooser();
        fc3.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        decB.setText("Decrypt");
        decB.setBounds(650, 300, 200, 30);
        decB.addActionListener(this);
        
        decryptionPass.setBounds(780, 210, 160, 25);
        
    	this.add(tTitleDecrypt);
    	this.add(tEncryptedImage);
    	this.add(tDEcryptedFile);
    	this.add(fileD1);
    	this.add(fileD2);
    	this.add(selFichero3);
    	this.add(decryptionPass);
    	this.add(tDecryptPass);
    	this.add(decB);
    }
    
    private void startLeftJComponents() { //TODO El log no tira, hacer su propia clase?
    	
    	
        //Botones de archivo
        fc1 = new JFileChooser();
        fc1.setCurrentDirectory(new File(System.getProperty("user.home"))); //TODO esto tira en windows?
        fc2 = new JFileChooser();
        fc2.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //Etiquetas
        tPassword = new JLabel();
        tCheckPass = new JLabel();
        tTitleEncrypt = new JLabel();
        tInputFile = new JLabel();
        tInputMat = new JLabel();
        tOutputFile = new JLabel();
        
        //Campos de texto
        file1 = new JTextField();
        file2 = new JTextField();
        file3 = new JTextField();
        passwordField = new JPasswordField(10);
        passwordField.addActionListener(this);
        checkPassField = new JPasswordField(10);
        checkPassField.addActionListener(this);
        
        //Botones
        hideB = new JButton();
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
        
        hideB.setText("Hide");
        hideB.setBounds(50, 300, 200, 30);
        hideB.addActionListener(this);
        
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
        this.add(hideB);
    }

    public void actionPerformed(ActionEvent e) {  //TODO esto es mejor en un case, en vez de getaction getID
    	
        if (e.getActionCommand().equals("Hide")) { //Boton encriptacion
        	
        	char[] password = passwordField.getPassword();
            char[] checkpass = checkPassField.getPassword();
            
        	if (Arrays.equals(password, checkpass)) {
    	        log.append("Password ok!\n");
    	        log.append(file1.getText() + "\n");
    	        log.append(file2.getText() + "\n");
    	        log.append(file3.getText() + "\n");
    	        
            }else{
            	
    	        JOptionPane.showMessageDialog(this, "Mismatch!");

            }
        	
        }else if(e.getActionCommand().equals("Decrypt")){
        	
        	char[] password = decryptionPass.getPassword();
        	log.append(new String(password) + "\n");
        	
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
        	
        }else if(e.getActionCommand().equals("...  3")) {
        	
        	int returnVal = fc3.showOpenDialog(this);
        	
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		File selectedFile = fc3.getSelectedFile();
        		fileD1.setText(selectedFile.getAbsolutePath());
        	}
        }
        
	        
    }

    public static void main(String[] args) {
        Ventana V = new Ventana();
        V.setVisible(true);
        V.log.append("Tloc. Nice!\n");
    }
}