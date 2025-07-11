import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Encryptor class provides methods to encrypt and decrypt files using a simple XOR cipher.
 * It allows users to select a file, specify a seed for the encryption key, and perform the encryption or decryption.
 */
public class Encryptor {

    public static String select () {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        return jfc.getSelectedFile().getAbsolutePath();
    }

    public static byte [] XOR ( String filePath, String seed) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(filePath));
            byte[] key = generateKey(seed, data.length);

            // XOR encrypt
            byte[] cipher = new byte[data.length];
            for (int i = 0; i < cipher.length; i++) {
                cipher[i] = (byte) (data[i] ^ key[i]);
            }

            return cipher;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void ciphering (String filePath, String seed, JFrame parent) {
        try {
            // XOR encrypt
            byte[] cipher = XOR(filePath, seed);

            // Write the data to a new file
            Files.write(Paths.get(filePath), cipher);

            JOptionPane.showMessageDialog(parent, "File XOR-ed successfully: " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error XOR-ing file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static byte[] generateKey(String seed, int length) {
        Random random = new Random(seed.hashCode());
        byte[] key = new byte[length];
        random.nextBytes(key);
        return key;
    }

}
