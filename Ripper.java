package stega;

//Reeeeeep! 


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
	
	 public static void main(String[] args) {
		
		 byte n = (byte) 63;
		 byte m = (byte) 101;
		 
		 System.out.println( ArchivoBMP.toBinary(new byte[] {n} ) );
		 System.out.println( ArchivoBMP.toBinary(new byte[] {m} ) +"\n");
		 
		 System.out.println( ArchivoBMP.toBinary(new byte[] {ripByte(n,1,0)} ) + " \n");
		 
		 System.out.println( ArchivoBMP.toBinary(new byte[] {insertBits(n , m , 2)} ) + " \n");
		 
		 System.out.println( ArchivoBMP.toInt(new byte[] {insertBits(n , m , 2)}, 0) + " \n");

	 }
}
