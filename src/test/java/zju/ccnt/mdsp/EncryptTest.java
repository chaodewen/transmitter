package zju.ccnt.mdsp;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Dewayne on 2016/12/27.
 */

public class EncryptTest {
    @Test
    public void testHMAC() {
        String secret = "secret";
        String message = "Message";

        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

        try {
            sha256_HMAC.init(secret_key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        System.out.println(byteArrayToHexString(sha256_HMAC.doFinal(message.getBytes())));
        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
        System.out.println(hash);
    }

    private static String byteToHexString(byte b) {
        String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8"
                , "9", "a", "b", "c", "d", "e", "f"};
        int ret = b;
        //System.out.println("ret = " + ret);
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }
}