import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class Crypto
{

    /**
     * @return true is {@code signatureToVerify} is a valid digital signature of {@code message} under the
     * key {@code publicKey}. Internally, this uses RSA signatureToVerify, but the student does not
     * have to deal with any of the implementation details of the specific signatureToVerify
     * algorithm
     */
    public static boolean verifySignature(PublicKey publicKey, byte[] message, byte[] signatureToVerify)
    {
        Signature signature = null;

        try
        {
            signature = Signature.getInstance("SHA256withRSA");
        }
        catch (NoSuchAlgorithmException exception)
        {
            exception.printStackTrace();
        }

        try
        {
            signature.initVerify(publicKey);
        }
        catch (InvalidKeyException exception)
        {
            exception.printStackTrace();
        }

        try
        {
            signature.update(message);
            return signature.verify(signatureToVerify);
        }
        catch (SignatureException exception)
        {
            exception.printStackTrace();
        }

        return false;
    }
}
