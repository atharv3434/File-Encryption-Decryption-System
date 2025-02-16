import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n🔐 File Encryption & Decryption System 🔐");
            System.out.println("1. Encrypt a file (AES)");
            System.out.println("2. Decrypt a file (AES)");
            System.out.println("3. Encrypt a file (RSA)");
            System.out.println("4. Decrypt a file (RSA)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter file path to encrypt (AES): ");
                    String inputFileAES = scanner.nextLine();
                    AESUtil.encryptFile(inputFileAES);
                    break;
                case 2:
                    System.out.print("Enter file path to decrypt (AES): ");
                    String outputFileAES = scanner.nextLine();
                    AESUtil.decryptFile(outputFileAES);
                    break;
                case 3:
                    System.out.print("Enter file path to encrypt (RSA): ");
                    String inputFileRSA = scanner.nextLine();
                    RSAUtil.encryptFile(inputFileRSA);
                    break;
                case 4:
                    System.out.print("Enter file path to decrypt (RSA): ");
                    String outputFileRSA = scanner.nextLine();
                    RSAUtil.decryptFile(outputFileRSA);
                    break;
                case 5:
                    System.out.println("Exiting... Stay Secure! 🔒");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
