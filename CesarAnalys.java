import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;



public class CesarAnalys {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,:!? -";
    public static final int ALPHABET_LENGTH = ALPHABET.length();


    //encryption method using a given key
    public static String encrypt (String text, int key) {
       StringBuilder result = new StringBuilder();
       for (int i = 0; i < text.length(); i++) {
           char c = text.charAt(i);
           int index = ALPHABET.indexOf(Character.toLowerCase(c));
           if (index == - 1) {
               result.append(c);
           } else {
               int newIndex = (index + key) % ALPHABET_LENGTH;
               char newChar = ALPHABET.charAt(newIndex);
               result.append(Character.isUpperCase(c) ? Character.toUpperCase(newChar) : newChar);
           }
       }
       return  result.toString();
    }
    // decryption method using the given key
    public  static String decrypt (String text, int key) {
        return encrypt(text, ALPHABET_LENGTH - key);
    }
    // method to read text from a file
public static void encryptFile (String inputFileName, String outputFileName,int key) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            String line;
            while ((line = reader.readLine()) != null){
                String encryptedLine = encrypt(line, key);
                writer.write (encryptedLine);
                writer.newLine();
            }
        }
   }
    // method brute force
    public static String bruteForce(String text) {
        String result = "";
        int maxCorrectSymbols = 0;
        for (int i = 1; i < ALPHABET_LENGTH; i ++) {
            String decryptedText = decrypt(text, i);
            int correctSymbols = countCorrectSymbols(decryptedText);
            if (correctSymbols > maxCorrectSymbols) {
                result = decryptedText;
                maxCorrectSymbols = correctSymbols;
            }
        }
        return result;
    }

    private static int countCorrectSymbols(String decryptedText) {
        return 0;
    }
        // метод статического анализа
        public void startAnalysis() {


    }
}


