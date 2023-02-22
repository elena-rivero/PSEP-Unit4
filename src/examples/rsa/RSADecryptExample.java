package examples.rsa;

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

public class RSADecryptExample {
	public static final String MENSAJE_CIFRADO = "Z2qKccwBQdEcgOdcBcA6rF4oeBHi52ifLUyAFakvx8iu5qo3LEb0TR2zYzQjwROdpt5M83Yt4eI8OW8gEpgTv0Z6nz+tzPBzVaWEoWW6EwKIGGP9ahUGg6IKWTy/tkytlme96qJWe7KSLIwkDQtKTVnVFsA1EonkTEZjegDyJsEYybRd4z1IYkkNRA2o0IIYrZoo8R8dN4ioNO9id5ztf69vDRVqXsc+YaoKwlVbZ+LC//3XS3Lxlox1vFCRMLp466bPvgXtv79W7au82Wl6nYWSPnTDRbIyx8gwauJ9GH6Q7+GCpT16rqFwvZ/nTKpkJUCInaBDxbrH9lpsvztQ6w==";
	
	public static void main(String[] args) {
		
		try {
			// Tomamos la clave privada
			PrivateKey clavePrivada = KeysManager.getClavePrivada();
			
			Cipher cipher = Cipher.getInstance("RSA");
			
			// Desciframos con la clave privada
			cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
			
			byte[] mensajeCifrado = Base64.getDecoder().decode(MENSAJE_CIFRADO);
			
			// Se obtiene el mensaje descifrado
			byte[] mensaje = cipher.doFinal(mensajeCifrado);
			
			// Lo imprimimos por pantalla en Base64
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
		}
		
	}

}
