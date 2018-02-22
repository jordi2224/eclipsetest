package stega;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

/* Jorge Huete
 * 
 * Este codigo es complicado de entender. En su concepto es un descodificador de archivos BMP.
 * Lee el header de los archivos recibidos y selecciona los bytes importantes para el programa.
 * 
 * Si se desea entender mejor el funcionamiento recomiendo fuertemente mirar las tablas del header para un archivo .bmp
 * 
 * https://en.wikipedia.org/wiki/BMP_file_format
 * 
 */


public class ArchivoBMP {

	public String ruta;			//Ruta al archivo BMP
	public byte[] bytes;		//Todos los bytes del archivo
	public byte[][] kilobytes;  //Bytes del archivo en kilobytes
	public int matDist;			//Distancia en bytes a la matriz RGB, matriz empieza en bytes[matDist]
	public int sizeH;			//Tamaño horizontal en pixeles
	public int sizeV;			//Tamaño vertical en pixeles
	public int fSize;			//Tamaño en bytes del archivo segun el header (puede variar)
	public int sizeOfHeader;	//Tamaño en bytes del header
	public int pixelSize;		//Bits por pixel, generalmente 8 TODO rechazar imagenes con numero distintos
	public int compresion;		//TODO LA APP DEBE RECHAZAR IMAGENES COMPRIMIDAS!!!!!
	public byte[] RGBMat;		//Matriz RGB, incluye el padding
	public byte[] unpadMat;
	public byte[][] pixelMat;	//Esto deberia ser adaptado a cada tamaño posible
	public byte[] header;
	int paddingBytes;			//Numero de bytes usados para el padding
	int rowSize;				//Tamaño real en bytes de una fila (CUIDADO != sizeH*3*pixelSize)
								//Incluye padding
	String fn;					//Nombre del archivo
	
	
	
	ArchivoBMP(String ruta) throws IOException{
		
		try {
			this.ruta = ruta;
			Path p = Paths.get(ruta);
			fn = p.getFileName().toString();
			bytes = Files.readAllBytes(p);
			
			kilobytes = new byte[(int)(bytes.length/1024) +1][1024];
			
			for (int i = 1; i<bytes.length/1024; i++) { //Arrays tiene un metodo para esto pero prefiero no usar codigo externo
				for (int j = 0; j < 1024; j++) {
					kilobytes[i-1][j]=bytes[1024*(i-1)+j];
				}
			}
			
			compresion = Core.toInt(mirrorBytes(bytes, 30, 34), 0);
			sizeH = Core.toInt(mirrorBytes(bytes, 18, 22), 0);
			sizeV = Core.toInt(mirrorBytes(bytes, 22, 26), 0);
			matDist = Core.toInt(mirrorBytes(bytes, 10, 14), 0);
			fSize = Core.toInt(mirrorBytes(bytes, 2, 6), 0);
			sizeOfHeader = Core.toInt(mirrorBytes(bytes, 14, 18), 0);
			pixelSize = Core.toInt(mirrorBytes(bytes, 28, 30), 0);

			
			paddingBytes = 4*(int) (Math.ceil( (double)(sizeH*3)/4)) - (sizeH*3); //TODO y si tiene transparencia??
			rowSize = 4* (int) (Math.ceil( (double)(sizeH*3)/4));
			
			
			RGBMat = genRGBMat();
			pixelMat = genPixelMat();
			header = Arrays.copyOfRange(bytes, 0, matDist);
			
		}catch(Exception e) {
			 
			throw new IllegalStateException("something is wrong", e);
			 
		}
	}
	
	String getInfo() {
		
		
		return "Signature: " + (char)bytes[0] + (char)bytes[1]
				+ "\nH x V: " + sizeH + " , " + sizeV
				+ "\nReported file size: " + fSize
				+ "\nOffset to matrix: " + matDist
				+ "\nHeader size: " + sizeOfHeader
				+ "\nCompression: " + compresion
				+ "\nBits por pixel: " + pixelSize;
		
	}
	
	public String getName() {
		return fn;
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
	
	public byte[][] genPixelMat(){ //TODO: UNPADDEDBYTES
		
		byte[][] res = new byte[sizeV*sizeH][3];
		unpadMat = new byte[sizeV*sizeH*3];
		int k = 0;
		int l = 0;
		int p = 0;
		int q = 0;
		
		for(int i = 0; i< sizeV; i++) {  //TODO Esto es basicamente un triple bucle for....
			
			for(int j = 0; j< rowSize - paddingBytes; j++) {

					res[p][k] =  RGBMat[l];
					unpadMat[q] = RGBMat[l];
					q++;
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
	
	public byte[][] getPixelMat(){
		return pixelMat;
	}

	public byte[] getUnpadMat() {
		return unpadMat;
	}
	
	public byte[] getHeader(){
		return header;
	}

}
