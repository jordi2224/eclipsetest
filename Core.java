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
		}catch (Exception e) {											//Si no encuentra el archivo origen
			v.print("File not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		
		v.print("Loading image... \n");
		ArchivoBMP origen = null;
		byte[][] matOrigen = null;
		
		try {
			origen = new ArchivoBMP(ruta2);
			v.print("Image loaded: " + origen.getName() + "\n");
			v.print(origen.getInfo());
			matOrigen = origen.getPixelMat();
			v.print("\nPixeles encontrados:" +  matOrigen.length);
		}catch (Exception e) { 											//Si no encuentra la imagen origen
			v.print("Image not found\nFATAL ERROR!\n");	
			throw new IllegalStateException("something is wrong", e);
		}
		
		byte[][] matDestino = new byte[matOrigen.length][3];
		
		int i = 0;
		//TODO: encriptar origen... mucha pereza ahora mismo
		while(i/4 < paquete.length) { //TODO convertirlo a for cuando este acabado TODO USAR UNPADDED MAT
			byte[] hiddenbytes = Ripper.insertBytes(new byte[] {}, paquete[i], 2);
			matDestino[(int) Math.floor((float)i/3)][0] = hiddenbytes[0];
			matDestino[(int) Math.floor((float)i/3)][1] = hiddenbytes[1];
			matDestino[(int) Math.floor((float)i/3)][2] = hiddenbytes[2];
			matDestino[(int) Math.floor((float)i/3) + 1][0] = hiddenbytes[3];
			i = i+4;
		}
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
