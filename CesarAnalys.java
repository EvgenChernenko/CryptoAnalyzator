import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class CesarAnalys {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,:!? -";
    public static final int ALPHABET_LENGTH = ALPHABET.length();


    //метод шифрования с помощью заданного ключа
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
    // метод расшифрования с помощью заданного ключа
    public  static String decrypt (String text, int key) {
        return encrypt(text, ALPHABET_LENGTH - key);
    }
    // метод чтения текста из файла
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
    // метод brute force
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

            String text = GetText.startGetText();
            String result = algorithmStatistical(text, System.out);
            WriteText.startWriting(result);
        }

    public String algorithmStatistical(String textFromUser, PrintStream out) {

        char symbolInCode = GetMaxTimesSymbol(textFromUser);

        int indexSymbolInAlpha = getIndexFromAlphabet(ALPHABET.ALPHABET_ARRAY, symbolInCode);
        int indexSpase = getIndexFromAlphabet(ALPHABET.ALPHABET_ARRAY, ' ');

        if (indexSymbolInAlpha == indexSpase) {
            out.println("It is " + Color.RED + "unencrypted text" + Color.RESET);
            throw new InvalidUserInputException("The text is original");
        }
        int keyMinus = indexSpase - indexSymbolInAlpha;
        int keyPlus = indexSymbolInAlpha - indexSpase;

        String result = null;
        if (DecodingByBruteForce.checkForExit(Decoding.decodeWithKey(textFromUser, keyPlus))) {
            result = Decoding.decodeWithKey(textFromUser, keyPlus);
            out.println("Your KEY = " + keyPlus);
        } else if (DecodingByBruteForce.checkForExit(Decoding.decodeWithKey(textFromUser, keyMinus))) {
            result = Decoding.decodeWithKey(textFromUser, keyMinus);
            out.println("Your KEY = " + keyMinus);
        }
        return result;
    }

    private int getIndexFromAlphabet(char[] array, char symbol) {
        int index = 0;
        for (int i = 0; i < Alphabet.GET_OF_ALPHABET.length(); i++) {
            if (Alphabet.ALPHABET_ARRAY[i] == symbol) {
                index = i;
                continue;
            }
        }
        return index;
    }

    private Character GetMaxTimesSymbol(String textFromUser) {

        String[] arrayTextFromUser = textFromUser.split("");

        Map<Object, Integer> map = new HashMap<>();

        for (int i = 0; i < arrayTextFromUser.length; i++) {
            char temp = arrayTextFromUser[i].charAt(0);

            if (map.containsKey(temp)) {
                map.put(temp, Integer.parseInt(map.get(temp).toString()) + 1);
            } else {
                map.put(temp, 0);
            }
        }
        Integer max = Collections.max(map.values());
        Character result = null;

        for (Map.Entry entry : map.entrySet()) {
            if (max == entry.getValue()) {
                result = (char) entry.getKey();
            }
        }
        return result;
    }
}

}
