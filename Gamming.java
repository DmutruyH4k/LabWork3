import java.util.Scanner;

public class Gamming {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        String text = scan.nextLine();
        String binaryText = toBinary(text);
        System.out.println(binaryText);
    }

    private static String toBinary(String text){
        StringBuilder textInBits = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            textInBits.append(Integer.toBinaryString(c));
        }
        return textInBits.toString();
    }

    private static String gammaGenerator(char[] binaryText){
        StringBuilder gamma = new StringBuilder();
        for (int i = 0; i < binaryText.length; i++){
            gamma.append((int)binaryText[0] ^ (int)binaryText[binaryText.length-1]);
        }
        return binaryText;
    }
}
