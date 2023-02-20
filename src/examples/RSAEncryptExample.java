package examples;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryptExample {
	public static final String MENSAJE = "Mensaje a cifrar";
	
	public static void main(String[] args) {
		KeyPairGenerator generador;
		try {
			generador = KeyPairGenerator.getInstance("RSA");
			KeyPair claves = generador.generateKeyPair();
			
			KeyFactory factory = KeyFactory.getInstance("RSA");
			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(claves.getPublic().getEncoded());
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(claves.getPrivate().getEncoded());
			PrivateKey clavePrivada = factory.generatePrivate(privateKeySpec);			
			PublicKey clavePublica = factory.generatePublic(publicKeySpec);
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
			
			byte[] mensaje = MENSAJE.getBytes(StandardCharsets.UTF_8);
			byte[] mensajeCifrado = cipher.doFinal(mensaje);
			
			System.out.println(Base64.getEncoder().encodeToString(mensajeCifrado));
			
			cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
			
			mensaje = cipher.doFinal(mensajeCifrado);
			
			System.out.println(new String(mensaje));
			
		} catch (NoSuchAlgorithmException e) {
			System.err.println("El algoritmo seleccionado no existe");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			System.err.println("No existe el padding seleccionado");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.err.println("La clave introducida no es válida");
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			System.err.println("El tamaño del bloque utilizado no es correcto");
			e.printStackTrace();
		} catch (BadPaddingException e) {
			System.err.println("El padding utilizado es erróneo");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			System.err.println("No es válida la especificación de la clave");
			e.printStackTrace();
		}
		
	}

}
