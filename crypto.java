package stega;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class crypto {

	public static void main(String[] args) {
	
		try {
		
				KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
				SecretKey myDesKey = keygenerator.generateKey();
				
				//System.out.println(myDesKey);
				//System.out.println(keygenerator);
				
				Cipher desCipher;
				desCipher = Cipher.getInstance("DES");
				
				System.out.println(desCipher);
				
				desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
				
				byte[] encryptedtext = desCipher.doFinal(1234);
				
		}catch(Exception e){
			
			System.out.println("Exception");
			
		}
		
	}
	
}
