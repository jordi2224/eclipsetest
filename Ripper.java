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
		byte[] message = new byte[(int) Math.floor((float)vessel.length/4)];
		
		byte[] cVessel = new byte[ vessel.length-vessel.length%4]; //TODO añadir if para evitar hacer esto si ya sale perfecto
		for(int i = 0; i < vessel.length-vessel.length%4; i++) {
			cVessel[i] = (byte) (vessel[i] & (byte) 3);  // 3 = 0000 0011, con & ponemos todos los bits a 0 salvo los 2 menos sign
		}
		vessel = cVessel;
		
		//System.out.println(Core.toBinary(vessel));
		//System.out.println(vessel.length);
		
		for(int i = 0; i < vessel.length; i++) {
			message[i/4]=(byte) (message[i/4]<<2); 			//Desplazamos 2 a la izquierda XXXXXXYY -> XXXXYY00
			message[i/4]=(byte) (message[i/4] | vessel[i]); //XXXXYY00 | 000000AA -> XXXXYYAA "magia negra"
		}
		
		return message;
	}
	
	/*
	public static void main(String args[]) throws IOException {
		byte[] ba = new byte[] {(byte) 255, 21, 31, (byte) 160};//, (byte) 231, 87, 32, 125, 123, 42, 18, 19, (byte) 130};
		byte[] ba2 = insertBytes(ba, (byte)231, 2);
		System.out.println(Core.toBinary(ba));
		System.out.println(Core.toBinary(ba2));
		System.out.println(Core.toBinary(recoverBytes(ba)));
		System.out.println(Core.toBinary((byte)231));

		
	}
	*/

}
