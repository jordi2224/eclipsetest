package stega;

import java.io.IOException;

//Riiiiiiiip! 


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
	
	static byte[] insertBytes(byte[] vessel, byte payload, int N) throws IOException {
		
		if (N != 1 && N != 2 && N != 4) {
			throw new IOException("Non divisible number");
		}else if(8/N != vessel.length) {
			throw new IOException("Wrong byte array length");
		}else {
			
			System.out.println(ArchivoBMP.toBinary(vessel));
			for (int i=0; i< vessel.length; i++) {
				vessel[i]=ripByte(vessel[i], 1, 0);
			}
			System.out.println(ArchivoBMP.toBinary(vessel));

			
		}
		
		return vessel;
	}
	
	public static void main(String[] args) {
		String ini = "1879";
		byte target = ini.getBytes()[2];
		System.out.println(ini.getBytes().length);
		System.out.println(ArchivoBMP.toBinary(target));
		
		try {
			insertBytes(ini.getBytes(), target, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
