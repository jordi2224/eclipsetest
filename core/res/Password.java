package stega.core.res;

import stega.core.res.Crypto;

import java.util.Arrays;

import stega.core.Core;

public class Password implements java.io.Serializable{
	
	String dominio;
	byte[] clave;
	static byte[] signature = "CdlNP".getBytes();
	
	public Password(String dominio, byte[] clave, String metaClave){
		this.dominio = dominio;
		this.clave = Crypto.encrypt(Core.concat(clave, signature), metaClave);
	}
	
	public String getDominio(){
		return dominio;
	}
	
	public void setDominio(String dominio){
		this.dominio = dominio;
	}
	
	public byte[] decryptPassword(String metaClave) throws Exception { //TODO crear nueva excepcion
		byte[] res = null;
		
		res = Crypto.decrypt(clave, metaClave);
		byte[] truncatedRes = Arrays.copyOfRange(res, res.length - 5, res.length);
		if(Arrays.equals(truncatedRes,signature)) {
			res = Arrays.copyOfRange(res, 0, res.length-5);
		}else {
			throw new Exception("Wrong encrytpion key");
		}
		
		return res;
	}
	
}