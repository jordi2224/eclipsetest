package stega;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Core {

	public static void encrypt(Ventana v, String ruta1, String ruta2, char[] password) throws IOException{ //TODO eliminar throw?

		v.print("Loading file...\n");
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
			matOrigen = origen.getUnpadMat(); //TODO: esto rompe
			v.print("\nPixeles encontrados:" +  matOrigen.length);
			
		}catch (Exception e) { 											//Si no encuentra la imagen origen
			v.print("\nImage not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		v.print("\n\nEncrypting file....");
		
		byte[] matDestino = matOrigen;
		Crypto cr1 = new Crypto();
		
		//TODO: "firmar" el paquete de informacion con "DONE" o similar
		//TODO: "firmar" el paquete con el nombre y extension
		
		paquete = cr1.encrypt(paquete, password.toString());
		
		v.print("\n Done encrypting using AES 128bit"); //TODO escribir esto

		v.print("\n Starting hide process");
		for(int i = 0; i< paquete.length; i++) {
			byte[] hiddenBytes = Ripper.insertBytes(new byte[] {matOrigen[i*4], matOrigen[i*4+1], matOrigen[i*4+2], matOrigen[i*4+3]}, paquete[i], 2);
			matDestino[i*4]=hiddenBytes[0];
			matDestino[i*4+1]=hiddenBytes[1];
			matDestino[i*4+2]=hiddenBytes[2];
			matDestino[i*4+3]=hiddenBytes[3];
		}
		v.print("\n Done hiding");
		v.print("\n Final size of is " + matDestino.length + " bytes.");
		v.print("\n First byte of encrypted is: " + toBinary(paquete[0]));
		v.print("\n First 4 bytes of target are: " + toBinary(matDestino[0]) + " " + toBinary(matDestino[1]) + " " +toBinary(matDestino[2]) + " " + toBinary(matDestino[3]));
	}
	
	public static String toBinary( byte[] bytes ){
	    StringBuilder sb = new StringBuilder(bytes.length * 8);
	    for( int i = 0; i < 8 * bytes.length; i++ )
	        sb.append((bytes[i / 8] << i % 8 & 0x80) == 0 ? '0' : '1');
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
	
	
}
