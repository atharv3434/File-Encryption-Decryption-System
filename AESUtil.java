import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {
    private static final String AES_ALGORITHM = "AES";
    private static final String KEY_FILE = "keys/aes_key.txt";

    public static void encryptFile(String filePath) {
        try {
            SecretKey secretKey = generateAESKey();
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] inputBytes = readFile(filePath);
            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            saveFile("encrypted_files/encrypted_" + new File(filePath).getName(), encryptedBytes);
            System.out.println("File encrypted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String filePath) {
        try {
            SecretKey secretKey = loadAESKey();
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] encryptedBytes = readFile(filePath);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            saveFile("decrypted_files/decrypted_" + new File(filePath).getName(), decryptedBytes);
            System.out.println("File decrypted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGen.init(256, new SecureRandom());
        SecretKey secretKey = keyGen.generateKey();
        saveFile(KEY_FILE, secretKey.getEncoded());
        return secretKey;
    }

    private static SecretKey loadAESKey() throws Exception {
        byte[] keyBytes = readFile(KEY_FILE);
        return new javax.crypto.spec.SecretKeySpec(keyBytes, AES_ALGORITHM);
    }

    private static byte[] readFile(String filePath) throws IOException {
        return new FileInputStream(filePath).readAllBytes();
    }

    private static void saveFile(String filePath, byte[] content) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(content);
        fos.close();
    }
}
