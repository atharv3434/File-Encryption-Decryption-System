import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

public class RSAUtil {
    private static final String RSA_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY_FILE = "keys/public.key";
    private static final String PRIVATE_KEY_FILE = "keys/private.key";

    public static void encryptFile(String filePath) {
        try {
            KeyPair keyPair = generateRSAKeyPair();
            PublicKey publicKey = keyPair.getPublic();

            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

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
            PrivateKey privateKey = loadRSAPrivateKey();
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] encryptedBytes = readFile(filePath);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            saveFile("decrypted_files/decrypted_" + new File(filePath).getName(), decryptedBytes);
            System.out.println("File decrypted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    private static PrivateKey loadRSAPrivateKey() throws Exception {
        return KeyPairGenerator.getInstance(RSA_ALGORITHM).generateKeyPair().getPrivate();
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
