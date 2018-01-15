package stega;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class crypto {

	public static void main(String[] args) {
	
		try {
		
				KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
				SecretKey myDesKey = keygenerator.generateKey();
				System.out.println(myDesKey);
				System.out.println(keygenerator);
				
		}catch(Exception e){
			
			System.out.println("Exception");
			
		}
		
	}
	
}
