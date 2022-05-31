import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 *  1. base64转码+ase加解密测试
 *  2. new String（）一般使用字符转码的时候,byte[]数组的时候,toString（）将对象打印的时候使用
 *  str.toString是调用了b这个object对象的类的toString方法。一般是返回这么一个String：[class name]@[hashCode]。
 *  new String(str)是根据parameter是一个字节数组，使用java虚拟机默认的编码格式，将这个字节数组decode为对应的字符。
 *  若虚拟机默认的编码格式是ISO-8859-1，按照ascii编码表即可得到字节对应的字符。
 */
public class Test {

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();
    public static void main(String[] args) throws Exception {
        String encriptValue = encrypt("dude5");
        decrypt(encriptValue);

    }

    /**
     *
     * @param valueToEnc
     * @return
     * @throws Exception
     */
    public static String encrypt(String valueToEnc) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        System.out.println("valueToEnc.getBytes().length " + valueToEnc.getBytes().length);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        System.out.println("encValue length" + encValue.length);
        byte[] encryptedByteValue = new Base64().encode(encValue);
        String encryptedValue = new String(encryptedByteValue);
        System.out.println("encryptedValue " + encryptedValue);
        return encryptedValue;
    }

    /**
     *
     * @param encryptedValue
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new Base64().decode(encryptedValue.getBytes());
        byte[] decval = c.doFinal(decodedValue);
        return new String(decval);
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

}