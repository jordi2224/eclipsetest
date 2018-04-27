package stega.ui;


import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.DefaultMutableTreeNode;

import stega.core.Core;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;

import stega.core.res.Password;
import stega.core.res.PasswordTree;


/* Jorge Huete
 * 
 * Interfaz grafica del programa
 * 
 * Las variables de este codigo son un laberinto solo para los duros de mente
 * 
 * Dios tenga es su gloria a aquellos valientes que se adentran en esta clase...
 * 
 */

public class Ventana extends JFrame implements ActionListener , ComponentListener {
    private JFileChooser fc1;
    private JFileChooser fc2;
    private JFileChooser fc3;
    
    
    //private static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private JPanel panelOcultar;
    private JPanel panelBuscar;
    private JPanel panelAdmin;
    private JPanel inputFilePanel;
    private JLabel inFileLabel;
    private JTextField inputFileField;
    private JButton fcButton1;
    private JPanel inputImagePanel;
    private JPanel outputImagePanel;
    private JLabel inImageLabel;
    private JTextField inImageField;
    private JButton fcButton2;
    private JPanel log_panel;
    public JTextArea log;
    private JLabel outImageLabel;
    private JTextField outImageField;
    private Component horizontalStrut;
    private Component horizontalStrut_1;
    private Component verticalStrut;
    private JPanel passwordPanel2;
    private JPasswordField passwordField2;
    private JPasswordField passwordField1;
    private JPanel inputImagePanel2;
    private JLabel inFileLabel2;
    private JTextField inputFileField2;
    private JButton fcButton3;
    private Component horizontalStrut_5;
    private JPanel outputFilepanel;
    private JLabel outputFileLabel;
    private JTextField outputFileField;
    private Component verticalStrut_1;
    private Component horizontalStrut_6;
    private JPanel passwordPanel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private Component horizontalStrut_7;
    private JButton findFileButton;
    private JPanel openSavePanel;
    private JButton openLibraryButton;
    private JButton saveLibraryButton;
    private JPanel JTreePanel;
    private JTree tree;
    private Component horizontalStrut_4;
    private JPanel newPasswordPanel;
    private JLabel folderLabel;
    private JTextField folderField;
    private Component horizontalStrut_8;
    private JLabel domainLabel;
    private JTextField domainField;
    private Component horizontalStrut_9;
    private JLabel passwordLabel3;
    private Component horizontalStrut_11;
    private JPasswordField passwordField3;
    private JLabel passwordLabel4;
    private JPasswordField passwordField4;
    private Component horizontalStrut_12;
    private Component horizontalStrut_13;
    private Component verticalStrut_2;
    private JButton savePasswordButton;
    private JScrollPane scrollPane;
    
    private PasswordTree passwords;
    
    Ventana(){ //Constructor
        super();
        /**/this.setLayout(new BorderLayout(0, 0));
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        /**/this.add(tabbedPane, BorderLayout.CENTER);
        
        panelOcultar = new JPanel();
        tabbedPane.addTab("Encrypt", null, panelOcultar, null);
        panelOcultar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        inputFilePanel = new JPanel();
        panelOcultar.add(inputFilePanel);
        inputFilePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        inFileLabel = new JLabel("Input File");
        inputFilePanel.add(inFileLabel);
        
        inputFileField = new JTextField();
        inputFilePanel.add(inputFileField);
        inputFileField.setColumns(20);
        
        fcButton1 = new JButton("...");
        fcButton1.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = Ventana.this.fc1.showOpenDialog(Ventana.this);
	        	
	        	if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		File selectedFile = fc1.getSelectedFile();
	        		inputFileField.setText(selectedFile.getAbsolutePath());
	        	}
				
			}
        	
        });
        inputFilePanel.add(fcButton1);
        
        horizontalStrut = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut);
        
        inputImagePanel = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) inputImagePanel.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panelOcultar.add(inputImagePanel);
        
        inImageLabel = new JLabel("Input Image");
        inputImagePanel.add(inImageLabel);
        
        inImageField = new JTextField();
        inputImagePanel.add(inImageField);
        inImageField.setColumns(20);
        
        fcButton2 = new JButton("...");
        fcButton2.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = Ventana.this.fc2.showOpenDialog(Ventana.this);
	        	
	        	if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		File selectedFile = fc2.getSelectedFile();
	        		inImageField.setText(selectedFile.getAbsolutePath());
	        		outImageField.setText(selectedFile.getParent()+Core.selSlash(selectedFile.getParent()));
	        	}
				
			}
        	
        });
        inputImagePanel.add(fcButton2);
        
        horizontalStrut_1 = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut_1);
        
        outputImagePanel = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) outputImagePanel.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panelOcultar.add(outputImagePanel);
        
        outImageLabel = new JLabel("Output Image");
        outputImagePanel.add(outImageLabel);
        
        outImageField = new JTextField();
        outputImagePanel.add(outImageField);
        outImageField.setColumns(20);
        
        verticalStrut = Box.createVerticalStrut(50);
        panelOcultar.add(verticalStrut);
        
        Component horizontalStrut_2 = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut_2);
        
        JPanel passwordPanel1 = new JPanel();
        panelOcultar.add(passwordPanel1);
        
        JLabel passwordLabel1 = new JLabel("Password");
        passwordPanel1.add(passwordLabel1);
        
        passwordField1 = new JPasswordField();
        passwordField1.setColumns(10);
        passwordPanel1.add(passwordField1);
        
        passwordPanel2 = new JPanel();
        panelOcultar.add(passwordPanel2);
        
        JLabel passwordLabel2 = new JLabel("Repeat Password");
        passwordPanel2.add(passwordLabel2);
        
        passwordField2 = new JPasswordField();
        passwordField2.setColumns(10);
        passwordPanel2.add(passwordField2);
        
        Component horizontalStrut_3 = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut_3);
        
        JButton hideFileButton = new JButton("Hide File");
        hideFileButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				char[] password = passwordField1.getPassword();
	            char[] checkpass = passwordField2.getPassword();
	            
	        	if (Arrays.equals(password, checkpass) && password.length != 0) {
	    	        if(inputFileField.getText().length() == 0 || inImageField.getText().length() == 0 || outImageField.getText().length() == 0 ) {
	    	        	JOptionPane.showMessageDialog(Ventana.this, "Missing Inputs for Encryption!");
	    	        }else {
	    	        	log.append("\nEncrypting:\n");
		    	        log.append(inputFileField.getText() + "\n");
		    	        log.append(inImageField.getText() + "\n");
		    	        log.append(outImageField.getText() + "\n");
		    	        
		    	        try {
							Core.encrypt(Ventana.this, inputFileField.getText(), inImageField.getText(), outImageField.getText(), password);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
		    	        
	    	        }
	            }else if(password.length != 0){
	    	        JOptionPane.showMessageDialog(Ventana.this, "Password Mismatch!");
	            }else {
	            	JOptionPane.showMessageDialog(Ventana.this, "Password Field Empty!!");
	            }
			}
		});
        panelOcultar.add(hideFileButton);
        
        panelBuscar = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panelBuscar.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        tabbedPane.addTab("Decrypt", null, panelBuscar, null);
        
        inputImagePanel2 = new JPanel();
        panelBuscar.add(inputImagePanel2);
        
        inFileLabel2 = new JLabel("Input Image");
        inputImagePanel2.add(inFileLabel2);
        
        inputFileField2 = new JTextField();
        inputFileField2.setColumns(20);
        inputImagePanel2.add(inputFileField2);
        
        fcButton3 = new JButton("...");
        fcButton3.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e){
        		int returnVal = Ventana.this.fc3.showOpenDialog(Ventana.this);
            	
            	if (returnVal == JFileChooser.APPROVE_OPTION) {
            		File selectedFile = fc3.getSelectedFile();
            		inputFileField2.setText(selectedFile.getAbsolutePath());
            		outputFileField.setText(selectedFile.getParent()+Core.selSlash(selectedFile.getParent()));        	}
        	}
        });
        inputImagePanel2.add(fcButton3);
        
        horizontalStrut_5 = Box.createHorizontalStrut(2000);
        panelBuscar.add(horizontalStrut_5);
        
        outputFilepanel = new JPanel();
        panelBuscar.add(outputFilepanel);
        
        outputFileLabel = new JLabel("Output File");
        outputFilepanel.add(outputFileLabel);
        
        outputFileField = new JTextField();
        outputFileField.setColumns(20);
        outputFilepanel.add(outputFileField);
        
        verticalStrut_1 = Box.createVerticalStrut(50);
        panelBuscar.add(verticalStrut_1);
        
        horizontalStrut_6 = Box.createHorizontalStrut(2000);
        panelBuscar.add(horizontalStrut_6);
        
        passwordPanel = new JPanel();
        panelBuscar.add(passwordPanel);
        
        passwordLabel = new JLabel("Password");
        passwordPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordPanel.add(passwordField);
        
        horizontalStrut_7 = Box.createHorizontalStrut(2000);
        panelBuscar.add(horizontalStrut_7);
        
        findFileButton = new JButton("Find File");
        findFileButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		char[] password = passwordField.getPassword();
            	if(password.length == 0) {
            		JOptionPane.showMessageDialog(Ventana.this, "Password Field Empty!!");
            	}else if(inputFileField2.getText().length() == 0 || outputFileField.getText().length() == 0){
            		JOptionPane.showMessageDialog(Ventana.this, "Missing Inputs for Decryption!");
            	}else {
            		log.append("\nDecrypting:\n");
            		log.append(inputFileField2.getText() + "\n");
        	        log.append(outputFileField.getText() + "\n");
        	        try {
    					Core.decrypt(Ventana.this, inputFileField2.getText(), outputFileField.getText(), password);
    				} catch (Exception e1) {
    					e1.printStackTrace();
    				}
            	}
        	}
        });
        panelBuscar.add(findFileButton);
        
        panelAdmin = new JPanel();
        tabbedPane.addTab("Password Manager", null, panelAdmin, null);
        panelAdmin.setLayout(new BorderLayout(0, 0));
        
        openSavePanel = new JPanel();
        FlowLayout fl_openSavePanel = (FlowLayout) openSavePanel.getLayout();
        fl_openSavePanel.setAlignment(FlowLayout.LEFT);
        panelAdmin.add(openSavePanel, BorderLayout.SOUTH);
        
        
        openLibraryButton = new JButton("Open Password Library");
        openLibraryButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
    			Ventana.this.print("Attempting to load tree\n");
        		try {
					Ventana.this.passwords = Core.leerPass(Ventana.this);
	        		Ventana.this.rebuildTree("MASTER");
	        		Ventana.this.print("Password file loaded!\n");
	        		Ventana.this.print(Ventana.this.passwords.getName());

				}catch (IOException e1) {
					Ventana.this.print("Error reading file!\n");
				} catch (ClassNotFoundException e1) {
					print("Clase no encontrada\n");
					e1.printStackTrace();
				}
        	}
        });
        openSavePanel.add(openLibraryButton);
        
        saveLibraryButton = new JButton("Save Password Library");
        saveLibraryButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        		Core.guardarPass(Ventana.this, Ventana.this.passwords);
        		}catch(Exception e1) {
        			
        		}
        	}
        });
        openSavePanel.add(saveLibraryButton);
        
        JTreePanel = new JPanel();
        panelAdmin.add(JTreePanel, BorderLayout.WEST);
        JTreePanel.setLayout(new BorderLayout(0, 0));
        
        
        //TREE{
        
        tree = new JTree(new DefaultMutableTreeNode("-- NO PASSWORD FILE LOADED --"));
        
        JTreePanel.add(tree, BorderLayout.CENTER);
        
        passwords = new PasswordTree("--VACIO--");
        rebuildTree("MASTER");
        
        //}TREE
        
        horizontalStrut_4 = Box.createHorizontalStrut(300);
        JTreePanel.add(horizontalStrut_4, BorderLayout.NORTH);
        
        newPasswordPanel = new JPanel();
        FlowLayout fl_newPasswordPanel = (FlowLayout) newPasswordPanel.getLayout();
        fl_newPasswordPanel.setAlignment(FlowLayout.LEFT);
        panelAdmin.add(newPasswordPanel, BorderLayout.CENTER);
        
        folderLabel = new JLabel("Folder ");
        newPasswordPanel.add(folderLabel);
        
        folderField = new JTextField();
        newPasswordPanel.add(folderField);
        folderField.setColumns(10);
        
        horizontalStrut_8 = Box.createHorizontalStrut(2000);
        newPasswordPanel.add(horizontalStrut_8);
        
        domainLabel = new JLabel("Domain");
        newPasswordPanel.add(domainLabel);
        
        horizontalStrut_11 = Box.createHorizontalStrut(0);
        newPasswordPanel.add(horizontalStrut_11);
        
        domainField = new JTextField();
        newPasswordPanel.add(domainField);
        domainField.setColumns(10);
        
        horizontalStrut_9 = Box.createHorizontalStrut(2000);
        newPasswordPanel.add(horizontalStrut_9);
        
        passwordLabel3 = new JLabel("Password");
        newPasswordPanel.add(passwordLabel3);
        
        passwordField3 = new JPasswordField();
        passwordField3.setColumns(15);
        newPasswordPanel.add(passwordField3);
        
        horizontalStrut_12 = Box.createHorizontalStrut(2000);
        newPasswordPanel.add(horizontalStrut_12);
        
        passwordLabel4 = new JLabel("Repeat Password");
        newPasswordPanel.add(passwordLabel4);
        
        passwordField4 = new JPasswordField();
        passwordField4.setColumns(15);
        newPasswordPanel.add(passwordField4);
        
        horizontalStrut_13 = Box.createHorizontalStrut(2000);
        newPasswordPanel.add(horizontalStrut_13);
        
        verticalStrut_2 = Box.createVerticalStrut(50);
        newPasswordPanel.add(verticalStrut_2);
        
        savePasswordButton = new JButton("Save Password");
        newPasswordPanel.add(savePasswordButton);
        
        log_panel = new JPanel();
        this.setWindow();
        this.startLeftJComponents();
        this.startRightJComponents();
        this.startLog();
        this.setVisible(true);
        
        
        
    }

    private void setWindow() {
    	
        this.setTitle("DimiX Stega");                   
        this.setSize(800, 450);                                 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    private void startLog() {
    	//LOG{
    	log = new JTextArea(6,80);
        log.setEditable(false);
        log.setMargin(new Insets(5, 5, 5, 5));
    	log.setLineWrap(true);
    	DefaultCaret caret = (DefaultCaret)log.getCaret();
    	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    	log.setMaximumSize(new Dimension(100,200));
    	log_panel.setLayout(new BorderLayout(0, 0));
    	log_panel.add(log,  BorderLayout.CENTER);
    	// }LOG
        
    	//RESIZE{
    	panelOcultar.addComponentListener(this);
    	//}RESIZE
    	
        //SCROLL{
    	scrollPane = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	log_panel.add(scrollPane, BorderLayout.CENTER);
    	// }SCROLL
    	
    	log_panel.setMaximumSize(new Dimension(10000,200));
    	this.add(log_panel, BorderLayout.SOUTH);

    }
    
    public void componentResized(ComponentEvent e) {
		log.setBounds(0, 0, (int) this.getSize().getWidth(), log_panel.getSize().height);
		scrollPane.setBounds(0, 0, (int) this.getSize().getWidth(), log_panel.getSize().height);
		
		//this.print(""+this.getSize()+"\n");
	}

    private void startRightJComponents() {
        fc3 = new JFileChooser();
        fc3.setCurrentDirectory(new File(System.getProperty("user.home")));
    }
    
    private void startLeftJComponents() {
    	
        //Botones de archivo
        fc1 = new JFileChooser();
        fc1.setCurrentDirectory(new File(System.getProperty("user.home"))); //TODO esto tira en windows?
        fc2 = new JFileChooser();
        fc2.setCurrentDirectory(new File(System.getProperty("user.home")));
    }

    public void actionPerformed(ActionEvent e) {
        
	        
    }

    public void print(String in) {
    	log.append(in);
    }
    
    public static void main(String[] args) throws IOException {
    	
    	Ventana v = new Ventana();
    	
    	PasswordTree contrasprueba = new PasswordTree("Jorge");
    	contrasprueba.addPassword("Social", new Password("google.es", "holahola".getBytes(), "MASTER"));
    	Core.guardarPass(v, contrasprueba);
    	
    	/*
    	v.log.append("Tloc, Nice! \n");
    	v.log.append("Made by Jorge Huete: jorgehuetes@gmail.com \n");
    	v.log.append("All permission for use, modification and distribution is hereby granted to all public"
    			+ " as long as it is for good and not evil. \n\n\n");
    	*/
    	v.setSize(801, 451); //Shhhhhh nuestro secreto
    	
    }
    
    public void rebuildTree(String clave) {
    	
    	
    	JTreePanel.remove(tree);
    	
    	tree = passwords.toJTree(clave); //TODO usar clave del usuario
        
        JTreePanel.add(tree, BorderLayout.CENTER);
        Ventana.this.tree.revalidate();
		Ventana.this.tree.repaint();

    }


	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		log.setBounds(0, 0, (int) this.getSize().getWidth(), log_panel.getSize().height);
		scrollPane.setBounds(0, 0, (int) this.getSize().getWidth(), log_panel.getSize().height);
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		log.setBounds(0, 0, (int) this.getSize().getWidth(), log_panel.getSize().height);
		scrollPane.setBounds(0, 0, (int) this.getSize().getWidth(), log_panel.getSize().height);
		
	}
}
