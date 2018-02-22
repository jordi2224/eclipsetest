package stega.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import stega.core.res.ArchivoBMP;
import stega.core.res.Crypto;
import stega.core.res.Ripper;
import stega.ui.Ventana;

public class Core {

	public static void encrypt(Ventana v, String ruta1, String ruta2, String ruta3, char[] password) throws IOException{ //TODO eliminar throw?

		v.print("\nLoading file...\n");
		byte[] paquete = null;
		try {
			Path p1 = Paths.get(ruta1);
			String fn = p1.getFileName().toString();
			paquete = Files.readAllBytes(p1);
			v.print("File loaded: " + fn + "\n");
			v.print("\nFile size: " + paquete.length + " bytes.\n");
		}catch (Exception e) {											//Si no encuentra el archivo origen
			v.print("\nFile not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		
		v.print("Loading image... \n");
		ArchivoBMP origen = null;
		byte[] matOrigen = null;
		
		try {
			origen = new ArchivoBMP(ruta2);
			v.print("\nImage loaded: " + origen.getName() + "\n");
			v.print(origen.getInfo());
			matOrigen = origen.getUnpadMat();
			v.print("\nPixeles encontrados:" +  matOrigen.length);
			
		}catch (Exception e) { 											//Si no encuentra la imagen origen
			v.print("\nImage not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		v.print("\n\nEncrypting file....");
		
		byte[] matDestino = matOrigen;
		Crypto cr1 = new Crypto();
		Crypto sign = new Crypto();
		
		//TODO: "firmar" el paquete con el nombre y extension
		
		String pass=new String(password);
		v.print("\nSize of payload: "+ paquete.length);
		paquete = concat(cr1.encrypt(paquete, pass),  sign.encrypt("SAYONARA".getBytes(), pass));

		v.print("\nDone encrypting using AES 128bit");

		v.print("\nStarting hide process");
		try {
			for(int i = 0; i< paquete.length; i++) {
				byte[] hiddenBytes = Ripper.insertBytes(new byte[] {matOrigen[i*4], matOrigen[i*4+1], matOrigen[i*4+2], matOrigen[i*4+3]}, paquete[i], 2);
				matDestino[i*4]=hiddenBytes[0];
				matDestino[i*4+1]=hiddenBytes[1];
				matDestino[i*4+2]=hiddenBytes[2];
				matDestino[i*4+3]=hiddenBytes[3];
			}
		}catch (Exception e) {
			v.print("\nFile too big! Try with smaller files or bigger image");
		}
		v.print("\n Done hiding");
		
		byte[] finalBytes = concat(origen.getHeader(), matDestino);
		v.print("\nFinal size of is " + finalBytes.length + " bytes.");

		v.print("\n\nAtempting to save file...");
		try (FileOutputStream fos = new FileOutputStream(ruta3)) {//TODO usar la entrada de la ventana para el nombre
			   fos.write(finalBytes);
			   fos.close();
		}catch (Exception e) {
			v.print("\nError saving to file!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		v.print("\nFile saved at " + ruta3 + "!");
		
	}
	
	public static void decrypt(Ventana v, String inputPath, String outputName, char[] password) {
		v.print("Loading image... \n");
		ArchivoBMP origen = null;
		byte[] matOrigen = null;
		String pass = new String(password);
		
		try {
			origen = new ArchivoBMP(inputPath);
			v.print("\nImage loaded: " + origen.getName() + "\n");
			v.print(origen.getInfo());
			matOrigen = origen.getUnpadMat();
			v.print("\nPixeles encontrados:" +  matOrigen.length);
			
		}catch (Exception e) { 											//Si no encuentra la imagen origen
			v.print("\nImage not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		v.print("\nExtracting data...");
		byte[] paquete = Ripper.recoverBytes(matOrigen);
		v.print(" Done!");
		Crypto cr2 = new Crypto();
		v.print("\nVerifying data...");
		byte[] signature = cr2.encrypt("SAYONARA".getBytes(), pass);
		int blockStart=0;
		for(int i = 0; i< paquete.length/16; i++) {
			if(Arrays.equals(Arrays.copyOfRange(paquete, i*16, (i+1)*16),signature)) {
				v.print("\nSignature found at block: " + i + "!");
				blockStart = i;
			}
		}
		if(blockStart == 0) {
			v.print("\nCould not find any valid data.\nThe selected image may not contain any information or password is wrong.");
			throw new IllegalStateException("End of file reached without signature");
		}
		
		v.print("\nStarting decryption...");
		byte[] resultado = cr2.decrypt(Arrays.copyOfRange(paquete, 0, 16*blockStart), pass);
		v.print("\nDone decrypting.");
		
		v.print("\n\nAttempting to save file.");
		try (FileOutputStream fos = new FileOutputStream(outputName)) {
			   fos.write(resultado);
			   fos.close();
		}catch (Exception e) {
			v.print("\nError saving to file!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		v.print("Done!");
		
	}
	//recibido = cr1.decrypt(Arrays.copyOfRange(recibido, 0, paquete.length), pass);
	
	public static String toBinary( byte[] bytes ){
	    StringBuilder sb = new StringBuilder(bytes.length * 8);
	    for( int i = 0; i < 8 * bytes.length; i++ ) {
	        sb.append((bytes[i / 8] << i % 8 & 0x80) == 0 ? '0' : '1');
	    	if((i+1)%8 == 0 && i!=0){
	    		sb.append(' ');
	    	}
	    }
	    return sb.toString();
	}
	
	public static String toBinary( byte bb ) {
		StringBuilder sb = new StringBuilder(8);
		for( int i = 0; i < 8 ; i++ )
	        sb.append((bb << i % 8 & 0x80) == 0 ? '0' : '1');
	    return sb.toString();
	}
	
	public static int toInt(byte[] bytes, int offset) { //TODO: Reescribir esto 
		  int ret = 0;
		  for (int i=0; i<4 && i+offset<bytes.length; i++) {
		    ret <<= 8;
		    ret |= (int)bytes[i] & 0xFF;
		  }
		  return ret;
	}
	
	public static byte[] concat(byte[]...arrays)
	{
	    // Determine the length of the result array
	    int totalLength = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        totalLength += arrays[i].length;
	    }

	    // create the result array
	    byte[] result = new byte[totalLength];

	    // copy the source arrays into the result array
	    int currentIndex = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        System.arraycopy(arrays[i], 0, result, currentIndex, arrays[i].length);
	        currentIndex += arrays[i].length;
	    }

	    return result;
	}
	
	
}
