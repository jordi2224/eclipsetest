package stega.core.res;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import stega.core.res.Password;

public class PasswordTree implements Serializable{
	
	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	HashMap<String, List<Password>> hm = new HashMap<String, List<Password>>();

	public PasswordTree(String name){
		this.setName(name);
	}
	
	public void addPassword(String carpeta, Password password) {
		if(hm.containsKey(carpeta)){
			List<Password> passwordsList = hm.get(carpeta);
			passwordsList.add(password);
			hm.put(carpeta, passwordsList);
		}else{
			List<Password> passwordsList = new ArrayList<Password>();
			passwordsList.add(password);
			hm.put(carpeta, passwordsList);
		}
	}

	public HashMap<String, List<Password>> getMap(){
		return hm;
	}
	
	public JTree toJTree(String metaClave) {
	
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.getName());
        
        for(String carpeta : this.getMap().keySet()) {
        	DefaultMutableTreeNode tipo = new DefaultMutableTreeNode(carpeta);
        	root.add(tipo);
        	
        	for(Password tempPass : this.getMap().get(carpeta)) {
        		DefaultMutableTreeNode dominio = null;
				try {
					dominio = new DefaultMutableTreeNode(tempPass.getDominio() + ":  " + new String(tempPass.decryptPassword("MASTER")));
				} catch (Exception e) {
					e.printStackTrace();
				}
        		
        		tipo.add(dominio);

        	}
        }
		
		JTree tree = new JTree(root);
		return tree;
	}
}