package stega.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/* Jorge Huete
 * 
 * Recursos de codigo
 * 
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import stega.core.res.ArchivoBMP;
import stega.core.res.Crypto;
import stega.core.res.PasswordTree;
import stega.core.res.Ripper;
import stega.ui.Ventana;

public class Core {

	public static void encrypt(Ventana v, String ruta1, String ruta2, String ruta3, char[] password) throws Exception{ //TODO eliminar throw?

		v.print("\nLoading file...\n");
		byte[] paquete = null;
		try {
			Path p1 = Paths.get(ruta1);
			String fn = p1.getFileName().toString();
			paquete = Files.readAllBytes(p1);
			v.print("File loaded: " + fn + "\n");
			v.print("\nFile size: " + paquete.length + " bytes.\n");
		}catch (Exception e) {	//Si no encuentra el archivo origen
			v.print("\nFile not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}

		v.print("Loading image... \n");
		ArchivoBMP origen = abrirBMP(ruta2, v);
		byte[] matOrigen = origen.getUnpadMat();

		v.print("\n\nEncrypting file....");
		String pass=new String(password);

		//FIRMAS
			byte[] firmaExtension = Crypto.encrypt(selExtension(ruta1).getBytes(), pass); //Firma extension
			byte[] firmaEOF = Crypto.encrypt("SAYONARA".getBytes(), pass); //Firma End Of File

		paquete = concat(Crypto.encrypt(paquete, pass), firmaExtension, firmaEOF); //Array con las firmas encriptadas
		pass = null;
		password = null;
		v.print("\nDone encrypting using AES 128bit");

		v.print("\nStarting hide process");
		try {
			matOrigen=Ripper.insertByteArray(matOrigen, paquete);
		}catch(Exception e){
			v.print("\n\nSelected file is too big to hide in this image.");
			v.print("\nPlease selected smaller file or bigger image.");
			throw e;
		}
		v.print("\nDone hiding");

		byte[] finalBytes = concat(origen.getHeader(), matOrigen);
		v.print("\nFinal size of is " + finalBytes.length + " bytes.");

		v.print("\n\nAtempting to save file...");
		saveFile(ruta3+".bmp", finalBytes, v);
		v.print("\nFile saved at " + ruta3 );
	}
	
	public static void decrypt(Ventana v, String inputPath, String outputName, char[] password) throws Exception{

		String pass = new String(password);

		ArchivoBMP origen = abrirBMP(inputPath, v);
		byte[] matOrigen = origen.getUnpadMat();

		v.print("\nExtracting data...");
		byte[] paquete = Ripper.recoverBytes(matOrigen);
		v.print(" Done!");

		v.print("\nVerifying data...");
		byte[] signature = Crypto.encrypt("SAYONARA".getBytes(), pass);
		int blockStart=0;
		for(int i = 0; i< paquete.length/16; i++) {
			if(Arrays.equals(Arrays.copyOfRange(paquete, i*16, (i+1)*16),signature)) { //Busca la firma EOF
				v.print("\nSignature found at block: " + i + "!");
				blockStart = i;
			}
		}
		if(blockStart == 0) { //Si no encuentra la firma correcta
			v.print("\n\nCould not find any valid data.");
			v.print("\nThe selected image may not contain any information or password is wrong.");
			v.print("\n¯\\_(^o^)_/¯");
			throw new Exception("End of file reached without signature");
		}
		
		v.print("\nStarting decryption...");
		byte[] resultado = Crypto.decrypt(Arrays.copyOfRange(paquete, 0, 16*(blockStart-1)), pass);
		byte[] extension = Crypto.decrypt(Arrays.copyOfRange(paquete, 16*(blockStart-1), 16*blockStart), pass); //Recupera la extenion de la firma
		pass = null;
		password = null;
		v.print("\nDone decrypting.");
		
		v.print("\n\nAttempting to save file.");
		outputName = outputName + new String(extension);
		saveFile(outputName, resultado, v);
		v.print("\nDone!  ( ͡° ͜ʖ ͡°)");
		
	}
	
	public static String toBinary( byte[] bytes ){ //Convertir bytes a un String de 0s y 1s separados por espacios
	    StringBuilder sb = new StringBuilder(bytes.length * 8);
	    for( int i = 0; i < 8 * bytes.length; i++ ) {
	        sb.append((bytes[i / 8] << i % 8 & 0x80) == 0 ? '0' : '1');
	    	if((i+1)%8 == 0 && i!=0){
	    		sb.append(' ');
	    	}
	    }
	    return sb.toString();
	}
	
	public static String toBinary( byte bb ){ //Para un solo byte
		StringBuilder sb = new StringBuilder(8);
		for( int i = 0; i < 8 ; i++ )
	        sb.append((bb << i % 8 & 0x80) == 0 ? '0' : '1');
	    return sb.toString();
	}
	
	public static int toInt(byte[] bytes, int offset){ //Convertir bytes a un entero, codificacion positiva
		  int ret = 0;
		  for (int i=0; i<4 && i+offset<bytes.length; i++) {
		    ret <<= 8;
		    ret |= (int)bytes[i] & 0xFF;
		  }
		  return ret;
	}
	
	public static byte[] concat(byte[]...arrays){ //Concatena n arrays de bytes
	    // Determina el tamaño del array resultante
	    int totalLength = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        totalLength += arrays[i].length;
	    }
	    // crea el array resultante
	    byte[] result = new byte[totalLength];
	    // copia los arrays
	    int currentIndex = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        System.arraycopy(arrays[i], 0, result, currentIndex, arrays[i].length);
	        currentIndex += arrays[i].length;
	    }
	    return result;
	}
	
	public static String selExtension(String ruta) { //Busca la extension de un archivo a partir de la ruta
		char[] res = null;
		for(int i = ruta.length()-1; i >0; i--) {
			if (ruta.toCharArray()[i]=='.') {
				char[] res2 = new char[ruta.length()-i];
				int j=0;
				while(i<ruta.length()) {
					res2[j]=ruta.toCharArray()[i];
					i++;
					j++;
				}
				i=0; //Salimos del bucle for
				res = res2;
			}
		}
		return new String(res);
	}
	
	public static char selSlash(String ruta) { //Busca el caracter delimitador de una ruta "\" o "/"
		char res = '\0';
		for(int i = ruta.length()-1; i >0; i--) {
			if (ruta.toCharArray()[i]=='/'|| ruta.toCharArray()[i]=='\\') {
				i=0; //Salimos del bucle for
				res = ruta.toCharArray()[i];
			}
		}
		return res;
	}

	private static ArchivoBMP abrirBMP(String ruta, Ventana v){
		ArchivoBMP imagen = null;
		try {
			imagen = new ArchivoBMP(ruta);
			v.print("\nImage loaded: " + imagen.getName() + "\n");
			v.print(imagen.toString());
			v.print("\nPixeles encontrados:" +  imagen.getUnpadMat().length);
			
			if(imagen.getCompresion()!=0) {
				v.print("\nLa imagen seleccionada usa compresión. Vete a la mierda, paso muy fuertede hacer eso..."); //TODO, jorge eres gilipollas?
				throw new IllegalStateException("Compression not null");
			}
			if(imagen.getBPP()!=24) {
				v.print("\nLa imagen seleccionada no usa 8bits por color. Este versión solo funciona con 16581375 colores...");
				throw new IllegalStateException("Wrong pixel size");
			}
			
		}catch (Exception e) { 	//Si no encuentra la imagen origen
			v.print("\nImage not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		return imagen;
	}

	private static void saveFile(String ruta, byte[] info, Ventana v){
		try (FileOutputStream fos = new FileOutputStream(ruta)) {
				   fos.write(info);
				   fos.close();
		}catch (Exception e) {
				v.print("\nError saving to file!\n");	
				throw new IllegalStateException("something is wrong", e);
		}
	}
	
	public static void guardarPass(Ventana v, PasswordTree pTree) throws IOException {
			v.print("Attempting to save password file...\n");
			FileOutputStream fos = new FileOutputStream("pTree.obj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			v.print("Writing object\n");
			oos.writeObject(pTree);
			
			oos.close();
			fos.close();
			v.print("Closed\n");
	}
	
	public static PasswordTree leerPass(Ventana v) throws IOException, ClassNotFoundException {
		PasswordTree pTree = null;
		v.print("Abriendo archivo\n");
		FileInputStream fis = new FileInputStream("pTree.obj");
		v.print("Archivo abierto\n");
		ObjectInputStream ois = new ObjectInputStream(fis);
		v.print("Leyendo objeto\n");
		pTree = (PasswordTree) ois.readObject();
		v.print("Objeto leido\n");
		ois.close();
		fis.close();
	
		return pTree;
	}

}
