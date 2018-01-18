package stega;

import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

	public SecretKeySpec aesKey;
	
	 public static byte[] encrypt(byte[] entrada, SecretKeySpec key){
	      
		 byte[] salida = new byte[0];
		 
		 try {
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        salida = cipher.doFinal(entrada);
	        
		 }catch(Exception e) {
			 
			 throw new IllegalStateException("something is wrong", e);
			 
		 }
		 
		 return salida;
	        
	}
	 
	public static byte[] decrypt(byte[] entrada, SecretKeySpec key) {
		
		byte[] salida = new byte[0];
		
		try {
			
			Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        salida = cipher.doFinal(entrada);
			
		}catch(Exception e) {
			
			throw new IllegalStateException("something went wrong", e);
			
		}
		
		return salida;
		
	}
	
	public SecretKeySpec getKey() {
		
		
		try {
			
			String secretKey = "javamolamucho111"; //debe de medir exactamente 16, 32 o 64 bits.... horas de desbugeo para esto...
	        aesKey = new SecretKeySpec(secretKey.getBytes(), "AES");
			
			
		}catch(Exception e) {
			
			System.out.println("Exception in getKey");
			
		}
		
		return aesKey;
		
	}
	
	
	public static void main(String[] ag) throws UnsupportedEncodingException{
		
        Crypto test = new Crypto();
        SecretKeySpec clave = test.getKey();
        
        System.out.println(clave);
        
        String testS = "TEST";
        byte[] encriptado = encrypt(testS.getBytes(), clave);
        System.out.println(encriptado);
        
        byte[] desencriptado = decrypt(encriptado, clave);
        String desencriptadoS = new String(desencriptado, "UTF-8"); //TODO: Añadir handle para la excepcion
        
        System.out.println(desencriptadoS);
    }
	
}
