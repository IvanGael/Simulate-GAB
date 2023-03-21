package utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class SignatureUtils {

    public static boolean verifierSignature(String message, String cle, String signature) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(RSAUtils.clePubliqueFromString(cle));
            sign.update(message.getBytes());
            return sign.verify(Base64.getDecoder().decode(signature));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

}
