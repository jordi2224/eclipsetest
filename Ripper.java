package stega;

import java.io.IOException;

//Riiiiiiiip!

/* Jorge Huete - Ripper
 * 
 * Esta clase contiene metodos para cortar, recortar e introducir bytes dentro de bytes
 * Esta es la base del recorte del archivo origen
 * 
 */


public class Ripper {

	static byte ripByte(byte original, int S, int F) { //"Corta" los bytes entre S y F de un byte TODO que compruebe que no es mayor de 8
		
		byte res = original;
		
			for(int i = S; i>=F; i--) {
				res = (byte) (res & ~ (1 << i)); //bit i a 0
			}
		
		return res;
	}
	
	static byte insertBits(byte bigBoss, byte smol, int N) { //Inserta los ultimos N bit de smol en bigBoss
		
		bigBoss = ripByte(bigBoss, N-1, 0);
		smol = ripByte(smol, 7 ,N);
		
		bigBoss = (byte) (bigBoss | smol);
		
		return bigBoss;
	}
	
	static byte[] insertBytes(byte[] vessel, byte payload, int N) throws IOException { 	//Inserta el byte payload recortado en vessel
																						//N es el tamaño de recorte
		if (N != 1 && N != 2 && N != 4) {
			throw new IOException("Non divisible number");
		}else if(8/N != vessel.length) {
			throw new IOException("Wrong byte array length");
		}else {
			for (int i=0; i< vessel.length; i++) {
				vessel[i]=insertBits(vessel[i], (byte)(payload >> (8-(i+1)*N)), N);
			}
		}
		
		return vessel;
	}
	
	static byte[] recoverBytes(byte[] vessel) {
		byte[] message = new byte[(int) Math.ceil((float)vessel.length/4)];
		
			
		
		return message;
	}

}
