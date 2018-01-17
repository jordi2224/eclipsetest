package stega;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Crypto {

	public String s;
	public String output;
	private SecretKey desKey;
	
	public SecretKey getKey() {
		
		
		try {
			
			KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
			desKey = keygenerator.generateKey();
			
			
		}catch(Exception e) {
			
			System.out.println("Exception");
			
		}
		
		return desKey;
		
	}
	
	public String Encrypt(String input, SecretKey desKey) {
	
		try {
		
				
				Cipher desCipher;
				desCipher = Cipher.getInstance("DES");
				
				
				desCipher.init(Cipher.ENCRYPT_MODE, desKey);
				
				byte[] encryptedText = desCipher.doFinal("TEST".getBytes());
				
				output = new String(encryptedText);
				
				System.out.println(s);
				
				
		}catch(Exception e){
			
			System.out.println("Exception");
			
		}
		
		return output;
		
		
		
	}
	
	public String Decrypt(String input, SecretKey desKey) {
		
		try {
			
            Cipher desCipher = Cipher.getInstance("DES");

            desCipher.init(Cipher.DECRYPT_MODE, desKey);
            byte[] decrypted = desCipher.doFinal( input.getBytes() );
            output = new String(decrypted);
			
			
		}catch(Exception e) {
			
			System.out.println("Exception");
			
		}
		
		return output;
		
	}
	
	public static void main(String[] ag){
        Crypto test = new Crypto();
        SecretKey clave = test.getKey();
        String encriptado = test.Encrypt("TEST", clave);
        String desencriptado = test.Decrypt(encriptado, clave);
        
        System.out.println(desencriptado);
    }
	
}
