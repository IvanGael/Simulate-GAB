package utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtils {

    public static PublicKey clePubliqueFromString(String clePubliqueStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] clePubliqueBytes = Base64.getDecoder().decode(clePubliqueStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(clePubliqueBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

}
