package stega;

/*Jorge Huete
 * 
 * Este codigo es complicado de entender. En su concepto es un descodificador de archivos BMP.
 * Lee el header de los archivos recibidos y selecciona los bytes importantes para el programa.
 * 
 * Si se desea entender mejor el funcionamiento recomiendo fuertemente mirar las tablas del header para un archivo .bmp
 * 
 * https://en.wikipedia.org/wiki/BMP_file_format
 * 
 */

import java.io.UnsupportedEncodingException;
import java.nio.file.*;
import java.util.Arrays;

public class ArchivoBMP {

	public String ruta;			//Ruta al archivo BMP
	public byte[] bytes;		//Todos los bytes del archivo
	public byte[][] kilobytes;  //Bytes del archivo en kilobytes
	private int matDist;		//Distancia en bytes a la matriz RGB, matriz empieza en bytes[matDist]
	public int sizeH;			//Tamaño horizontal en pixeles
	public int sizeV;			//Tamaño vertical en pixeles
	public int fSize;			//Tamaño en bytes del archivo segun el header (puede variar)
	public int sizeOfHeader;	//Tamaño en bytes del header
	public int pixelSize;		//Bits por pixel, generalmente 8 TODO rechazar imagenes con numero distintos
	public int compresion;		//TODO LA APP DEBE RECHAZAR IMAGENES COMPRIMIDAS!!!!!
	public byte[] RGBMat;		//Matriz RGB, incluye el padding
	public byte[][] pixelMat;	//Esto deberia ser adaptado a cada tamaño posible	
	int paddingBytes;			//Numero de bytes usados para el padding
	int rowSize;				//Tamaño real en bytes de una fila (CUIDADO != sizeH*3*pixelSize)
								//Incluye padding
	
	
	
	
	ArchivoBMP(String ruta){
		
		try {
			this.ruta = ruta;
			bytes = Files.readAllBytes(Paths.get(ruta));
			
			kilobytes = new byte[(int)(bytes.length/1024) +1][1024];
			
			for (int i = 1; i<bytes.length/1024; i++) { //Arrays tiene un metodo para esto pero prefiero no usar codigo externo
				for (int j = 0; j < 1024; j++) {
					kilobytes[i-1][j]=bytes[1024*(i-1)+j];
				}
			}
			
			compresion = toInt(mirrorBytes(bytes, 30, 34), 0);
			sizeH = toInt(mirrorBytes(bytes, 18, 22), 0);
			sizeV = toInt(mirrorBytes(bytes, 22, 26), 0);
			matDist = toInt(mirrorBytes(bytes, 10, 14), 0);
			fSize = toInt(mirrorBytes(bytes, 2, 6), 0);
			sizeOfHeader = toInt(mirrorBytes(bytes, 14, 18), 0);
			pixelSize = toInt(mirrorBytes(bytes, 28, 30), 0);

			
			paddingBytes = 4*(int) (Math.ceil( (double)(sizeH*3)/4)) - (sizeH*3); //TODO y si tiene transparencia??
			rowSize = 4* (int) (Math.ceil( (double)(sizeH*3)/4));
			
			
			RGBMat = genRGBMat();
			pixelMat = genPixelMat();
			
		}catch(Exception e) {
			 
			throw new IllegalStateException("something is wrong", e);
			 
		}
	}
	
	void printInfo() {
		
		
		System.out.println("Signature: " + (char)bytes[0] + (char)bytes[1]);
		System.out.println("H x V: " + sizeH + " , " + sizeV);
		System.out.println("Reported file size: " + fSize);
		System.out.println("Offset to matrix: " + matDist);
		System.out.println("Header size: " + sizeOfHeader);
		System.out.println("Compression: " + compresion);
		System.out.println("Bits por pixel: " + pixelSize);
		
	}
	
	byte[] getBytes(){
		return bytes;
	}
	
	byte[][] getKilobytes(){
		return kilobytes;
	}
	
	private byte[] mirrorBytes(byte[] arr, int inicio, int fin) {
			
		byte[] res = new byte[fin-inicio];
		byte[] assis = new byte[fin-inicio];
		int limite = fin-inicio;
		
		for(int i = inicio; i < fin; i++) { //Copia el tramo que nos interesa
			assis[i-inicio]=arr[i];
		}
		
		for(int i = 0; i<limite; i++) { //Le da la vuelta
			res[i] = assis[limite-i-1];
		}
		
		return res;
	}
	
	int[] getSize(){
		return new int[] {sizeH, sizeV};
	}
	
	byte[] genRGBMat() {		//TODO: evitar usar copyOfRange, no usar funciones externas
		
		byte[] mat = new byte[bytes.length-matDist];
		
		mat = Arrays.copyOfRange(bytes, matDist, bytes.length);
		
		return mat;
		
	}
	
	public byte[][] genPixelMat(){
		
		byte[][] res = new byte[sizeV*sizeH][3];
		int k = 0;
		int l = 0;
		int p = 0;
		
		for(int i = 0; i< sizeV; i++) {  //TODO Esto es basicamente un triple bucle for....
			
			for(int j = 0; j< rowSize - paddingBytes; j++) {

				
					System.out.println("Attempting to read pixel: " + p);
					System.out.println("Subpixel: " + k);
					System.out.println("Byte is: " + l);
					
					res[p][k] =  RGBMat[l];
					
					l++;
					k++;
					if (k == 3) {
						k=0;
						p++;
					}

				
			}
			
			l= l+paddingBytes;
			
		}
		
		
		return res;
	}
	
	public static String toBinary( byte[] bytes )
	{
	    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
	    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
	        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
	    return sb.toString();
	}
	
	public static int toInt(byte[] bytes, int offset) { //TODO: Reescribir esto y hacer toInt(byte)
		  int ret = 0;
		  for (int i=0; i<4 && i+offset<bytes.length; i++) {
		    ret <<= 8;
		    ret |= (int)bytes[i] & 0xFF;
		  }
		  return ret;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String ruta = "/home/jorge/eclipse-workspace/stega/bin/stega/BlissW.bmp";
		
		ArchivoBMP file1 = new ArchivoBMP(ruta);
		
		file1.printInfo();
		
		/*for (int i = 0; i < file1.sizeV; i++) {
			System.out.println(toBinary(Arrays.copyOfRange(file1.getBytes(), start+i*file1.rowSize, start+((i+1)*file1.rowSize))));
		}*/
		
		//System.out.println(toBinary(Arrays.copyOfRange(file1.getBytes(), file1.getBytes().length-10, file1.getBytes().length)));
		
		//System.out.println(file1.genPixelMat()[0]);
		
		for (int i = 0; i < file1.sizeH * file1.sizeV; i++) {
			for (int j = 0; j < 3; j++) {
				
				System.out.println("Pixel: " + i);
				
				switch(j) {
				case 0 : 
					System.out.println("R: " + toInt(new byte[] {file1.pixelMat[i][j]}, 0));
					break;
				case 1 :
					System.out.println("G: " + toInt(new byte[] {file1.pixelMat[i][j]}, 0));
					break;
				case 2 :
					System.out.println("B: " + toInt(new byte[] {file1.pixelMat[i][j]}, 0));
					break;
				}
				
			}
		}
		
	}
	
}
