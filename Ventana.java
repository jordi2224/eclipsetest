//package stega.ui;

import java.awt.Dimension;
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

import stega.core.Core;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Font;

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
    
    
    private static final long serialVersionUID = 1L; //TODO investigar esto
    private JTabbedPane tabbedPane;
    private JPanel panelOcultar;
    private JPanel panelBuscar;
    private JPanel panelAdmin;
    private JPanel inputFilePanel;
    private JLabel lblNewLabel;
    private JTextField textField;
    private JButton btnNewButton;
    private JPanel inputImagePanel;
    private JPanel outputImagePanel;
    private JLabel lblNewLabel_1;
    private JTextField textField_1;
    private JButton btnNewButton_1;
    private JPanel log_panel;
    public JTextArea log;
    private JLabel lblNewLabel_2;
    private JTextField textField_2;
    private Component horizontalStrut;
    private Component horizontalStrut_1;
    private Component verticalStrut;
    private JPanel passwordPanel;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JPanel panel_1;
    private JLabel label_2;
    private JTextField textField_4;
    private JButton button_1;
    private Component horizontalStrut_5;
    private JPanel panel_2;
    private JLabel label_3;
    private JTextField textField_5;
    private Component verticalStrut_1;
    private Component horizontalStrut_6;
    private JPanel panel_4;
    private JLabel label_4;
    private JPasswordField passwordField_2;
    private Component horizontalStrut_7;
    private JButton btnFindFile;
    private JPanel panel;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JPanel panel_5;
    private JTree tree;
    
    Ventana(){ //Constructor
        super();
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        panelOcultar = new JPanel();
        tabbedPane.addTab("Encrypt", null, panelOcultar, null);
        panelOcultar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        inputFilePanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) inputFilePanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panelOcultar.add(inputFilePanel);
        
        lblNewLabel = new JLabel("New label");
        inputFilePanel.add(lblNewLabel);
        
        textField = new JTextField();
        inputFilePanel.add(textField);
        textField.setColumns(20);
        
        btnNewButton = new JButton("...");
        inputFilePanel.add(btnNewButton);
        
        horizontalStrut = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut);
        
        inputImagePanel = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) inputImagePanel.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panelOcultar.add(inputImagePanel);
        
        lblNewLabel_1 = new JLabel("New label");
        inputImagePanel.add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        inputImagePanel.add(textField_1);
        textField_1.setColumns(20);
        
        btnNewButton_1 = new JButton("...");
        inputImagePanel.add(btnNewButton_1);
        
        horizontalStrut_1 = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut_1);
        
        outputImagePanel = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) outputImagePanel.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panelOcultar.add(outputImagePanel);
        
        lblNewLabel_2 = new JLabel("New label");
        outputImagePanel.add(lblNewLabel_2);
        
        textField_2 = new JTextField();
        outputImagePanel.add(textField_2);
        textField_2.setColumns(20);
        
        verticalStrut = Box.createVerticalStrut(50);
        panelOcultar.add(verticalStrut);
        
        Component horizontalStrut_2 = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut_2);
        
        JPanel panel_3 = new JPanel();
        panelOcultar.add(panel_3);
        
        JLabel label = new JLabel("Password");
        panel_3.add(label);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setColumns(10);
        panel_3.add(passwordField_1);
        
        passwordPanel = new JPanel();
        panelOcultar.add(passwordPanel);
        
        JLabel lblNewLabel_3 = new JLabel("Repeat Password");
        passwordPanel.add(lblNewLabel_3);
        
        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordPanel.add(passwordField);
        
        Component horizontalStrut_3 = Box.createHorizontalStrut(2000);
        panelOcultar.add(horizontalStrut_3);
        
        JButton btnNewButton_2 = new JButton("Hide FIle");
        panelOcultar.add(btnNewButton_2);
        
        panelBuscar = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panelBuscar.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        tabbedPane.addTab("Decrypt", null, panelBuscar, null);
        
        panel_1 = new JPanel();
        panelBuscar.add(panel_1);
        
        label_2 = new JLabel("New label");
        panel_1.add(label_2);
        
        textField_4 = new JTextField();
        textField_4.setColumns(20);
        panel_1.add(textField_4);
        
        button_1 = new JButton("...");
        panel_1.add(button_1);
        
        horizontalStrut_5 = Box.createHorizontalStrut(2000);
        panelBuscar.add(horizontalStrut_5);
        
        panel_2 = new JPanel();
        panelBuscar.add(panel_2);
        
        label_3 = new JLabel("New label");
        panel_2.add(label_3);
        
        textField_5 = new JTextField();
        textField_5.setColumns(20);
        panel_2.add(textField_5);
        
        verticalStrut_1 = Box.createVerticalStrut(50);
        panelBuscar.add(verticalStrut_1);
        
        horizontalStrut_6 = Box.createHorizontalStrut(2000);
        panelBuscar.add(horizontalStrut_6);
        
        panel_4 = new JPanel();
        panelBuscar.add(panel_4);
        
        label_4 = new JLabel("Password");
        panel_4.add(label_4);
        
        passwordField_2 = new JPasswordField();
        passwordField_2.setColumns(10);
        panel_4.add(passwordField_2);
        
        horizontalStrut_7 = Box.createHorizontalStrut(2000);
        panelBuscar.add(horizontalStrut_7);
        
        btnFindFile = new JButton("Find File");
        panelBuscar.add(btnFindFile);
        
        panelAdmin = new JPanel();
        tabbedPane.addTab("Password Manager", null, panelAdmin, null);
        panelAdmin.setLayout(new BorderLayout(0, 0));
        
        panel = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) panel.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        panelAdmin.add(panel, BorderLayout.SOUTH);
        
        btnNewButton_3 = new JButton("New button");
        panel.add(btnNewButton_3);
        
        btnNewButton_4 = new JButton("New button");
        panel.add(btnNewButton_4);
        
        panel_5 = new JPanel();
        panelAdmin.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));
        
        tree = new JTree();
        panel_5.add(tree, BorderLayout.CENTER);
        
        log_panel = new JPanel();
        getContentPane().add(log_panel, BorderLayout.SOUTH);
        this.setWindow();
        this.startLeftJComponents();
        this.startRightJComponents();
        this.startLog();
        this.setVisible(true);
    }

    private void setWindow() {
    	
        this.setTitle("DimiX Stega");                   
        this.setSize(800, 500);                                 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    private void startLog() {
    	//LOG{
    	log = new JTextArea();
        log.setBounds(0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight()/3);
        log.setEditable(false);
        log.setMargin(new Insets(5, 5, 5, 5));
    	log.setLineWrap(true);
    	DefaultCaret caret = (DefaultCaret)log.getCaret();
    	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    	log_panel.add(log);
    	// }LOG
        
    	//RESIZE{
    	panelOcultar.addComponentListener(this);
    	//}RESIZE
        //SCROLL{
    	// }SCROLL
        
    	
    }
    
    public void componentResized(ComponentEvent e) {
		log.setBounds(0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight()/3);
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
    	//this.log.append(in);
    }
    
    public static void main(String[] args) throws IOException {
    	Ventana v = new Ventana();
    	
    	v.log.append("Tloc, Nice! \n");
    	v.log.append("Made by Jorge Huete: jorgehuetes@gmail.com \n");
    	v.log.append("All permission for use, modification and distribution is hereby granted to all public"
    			+ " as long as it is for good and not evil. \n\n\n");
    	
    }

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
