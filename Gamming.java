import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Gamming {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        try {
            encoder();
            decoder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void encoder() throws IOException {
        System.out.print("Введите текст: ");
        String binaryText = toBinary(scan.nextLine());
        System.out.print("Введите кодовое слово: ");
        String gamma = toBinary(scan.nextLine());
        String textForFile = XOR_Text(binaryText.toCharArray(), gamma.toCharArray());
        FileWriter encodedText = new FileWriter("ШифроТекст.txt");
        FileWriter gammaKey = new FileWriter("Ключ.txt");
        encodedText.write(textForFile);
        gammaKey.write(gamma);
        encodedText.close();
        gammaKey.close();
    }

    private static void decoder() throws IOException{
        Path encodedText = Path.of("ШифроТекст.txt");
        Path gammaKey = Path.of("Ключ.txt");
        String binaryText = Files.readString(encodedText);
        String gamma = Files.readString(gammaKey);
        String decodedBinary = XOR_Text(binaryText.toCharArray(), gamma.toCharArray());
        System.out.println(toText(decodedBinary));
    }

    private static String toBinary(String text){
        StringBuilder textInBits = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            textInBits.append(Integer.toBinaryString(c));
        }
        return textInBits.toString();
    }

    private static String toText(String binaryText){
        StringBuilder textInChars = new StringBuilder();
        for (int i = 0; i < binaryText.length()/11; i++){
            int bitsInInt = Integer.parseInt(binaryText.substring(i*11, (i+1)*11), 2);
            textInChars.append((char) bitsInInt);
        }
        return textInChars.toString();
    }

    private static String XOR_Text(char[] textToCrypt, char[] gammaKey){
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < textToCrypt.length; i++){
            encoded.append((int) textToCrypt[i] ^ (int) gammaKey[i % gammaKey.length]);
        }
        return encoded.toString();
    }
}