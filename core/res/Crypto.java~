package stega.core.res;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* 
 * Jorge Huete
 * 
 * Esta clase contiene los metodos necesarios para encriptar arrays de bytes
 * No puedo asegurar la integridad de la informacion o la seguridad de la encriptacion
 * 
 */

//TODO: cambiar los prints a excepciones

public class Crypto {
	
	
	
	public byte[] encrypt(byte[] entrada, String clave){ //Encripta los bytes entrantes con AES usando el string recibido
	      
		 byte[] salida = new byte[0];
		 
		 try {
			 
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, genKey(clave));
	        salida = cipher.doFinal(entrada);
	        
		 }catch(Exception e) {
			 
			 throw new IllegalStateException("something is wrong", e);
			 
		 }
		 
		 return salida;
	        
	}
	 
	public byte[] decrypt(byte[] entrada, String clave) { //Desencripta los bytes entrantes con AES usando el string recibido
		
		byte[] salida = new byte[0];
		SecretKeySpec key = genKey(clave);
		try {
			
			Cipher cipherd = Cipher.getInstance("AES");
	        cipherd.init(Cipher.DECRYPT_MODE, key);
	        salida = cipherd.doFinal(entrada);
			
		}catch(Exception e) {
			
			throw new IllegalStateException("something went wrong", e);
			
		}
		
		return salida;
		
	}
	
	public SecretKeySpec genKey(String clave) { //Recibe un string de menos de 64 ch y devuelve la clave AES resultante
		
		String formattedKey = formatKey(clave);
		SecretKeySpec aesKey = null;
		try {
			
	        aesKey = new SecretKeySpec(formattedKey.getBytes(), "AES");
			
		}catch(Exception e) {
			
			System.out.println("Exception in genKey");
			
		}
		
		return aesKey;
		
	}
	
	public String formatKey(String clave) { //Devuelve la clave con 1s detras para que mida 16, 32 o 64 ch
		String formattedKey = null;
		if (clave.length() == 0) {
			System.out.println("Password length must be greater than 0 \n");
		}else if (clave.length() <= 16) {
			formattedKey = clave;
			for (int i=0; i< 16-clave.length(); i++) {
				formattedKey = formattedKey + "1";
				
			}
			//System.out.println(formattedKey + " " + formattedKey.length());
			
		}else if (clave.length() <= 32) {
			formattedKey = clave;
			for (int i=0; i< 32-clave.length(); i++) {
				formattedKey = formattedKey + "1";
				
			}
			//System.out.println(formattedKey + " " + formattedKey.length());
			
		}else if (clave.length() <= 64) {
			formattedKey = clave;
			for (int i=0; i< 64-clave.length(); i++) {
				formattedKey = formattedKey + "1";
				
			}
			//System.out.println(formattedKey + " " + formattedKey.length());
			
		}else if (clave.length() > 64) {
			System.out.println("Password lenght must be lower than 64 \n");//TODO esto no
		}else {
			System.out.println("Unknown error \n");//TODO esto no
		}
		
		return formattedKey;
		
	}
	
}
