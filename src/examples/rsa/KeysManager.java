package examples.rsa;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeysManager {

	private static final String PUBLIC_KEY_FILE = "public_key.key";
	private static final String PRIVATE_KEY_FILE = "private_key.key";
	
	public static KeyPair generarClaves() throws NoSuchAlgorithmException{
		KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
		generador.initialize(2048);
		KeyPair claves = generador.generateKeyPair();
		return claves;
	}
	
	public static void guardarClaves(KeyPair claves) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(PUBLIC_KEY_FILE);
			fos.write(claves.getPublic().getEncoded());
			fos.close();
			
			fos = new FileOutputStream(PRIVATE_KEY_FILE);
			fos.write(claves.getPrivate().getEncoded());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
