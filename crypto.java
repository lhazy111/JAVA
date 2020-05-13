import java.util.*;

public class crypto {
    public static void main(String[] args) {
        String yesOrNo = "x";
        //take in the string and shift number to be encrypted-----------------------------

        System.out.println("Welcome to Encrypto project . It encryptes and decrypts a string given by the user");
        System.out.print("Please enter a text: ");
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        System.out.println("Enter a shift number for encode: ");
        int shift = input.nextInt();
        System.out.println("Enter a letter group number: ");
        int group = input.nextInt();
        input.nextLine();
        //calling methods----------------------------------------------------------------
        String textToObify = (normalizeText(text));
        System.out.println("normalised text: " + textToObify);
        System.out.println("text obified: " + obify(textToObify));
        String textToCeasar = obify(textToObify);
        System.out.println("ceasarified text = " + ceasarify(textToCeasar, shift));
        String textToGroup = ceasarify(textToCeasar, shift);
        String encryptedText = groupify(textToGroup, group);
        System.out.println("Finally encrypted text = " + encryptedText);


        while (!yesOrNo.equals("y") & !yesOrNo.equals("n")) {
            System.out.println("Do you want to decrypt? (y/n)");
            yesOrNo = input.nextLine();
        }
        if (yesOrNo.equals("y")) {
            String decrypted = ungroupify(encryptedText, shift);
            System.out.println("Text decrypted: " + decrypted);
        }
            System.out.println("Thank you for using Encrypto. Bye bye");

    }

    //normalize Text----------------------------------------------------------------
    public static String normalizeText(String text) {
        String normalizedText = text.replaceAll("\\W+", "").toUpperCase();
        return normalizedText;
    }

    //Obifying text----------------------------------------------------------------
    public static String obify(String textToObify) {
        String obifiedText = "";
        for (int i = 0; i < textToObify.length(); i++) {
            if (textToObify.charAt(i) == 'A' || textToObify.charAt(i) == 'E' || textToObify.charAt(i) == 'I'
                    || textToObify.charAt(i) == 'O' || textToObify.charAt(i) == 'U' || textToObify.charAt(i) == 'Y') {
                obifiedText = obifiedText + textToObify.substring(i, i + 1) + "OB";
            } else {
                obifiedText = obifiedText + textToObify.substring(i, i + 1);
            }
        }
        return obifiedText;
    }

    //Ceasarifiyng text----------------------------------------------------------
    public static String ceasarify(String textToCeasar, int shift) {
        String ceasarifiedText = "";
        for (int i = 0; i < textToCeasar.length(); i++) {
            int currChar = (int) textToCeasar.charAt(i);
            int newChar = (char) currChar + shift;
            //System.out.println (currChar + "shifted:" + (char) newChar);
            ceasarifiedText = ceasarifiedText + (char) newChar;
        }
        return ceasarifiedText;
    }

    //Groupifying text-----------------------------------------------------------------
    public static String groupify(String textToGroup, int group) {
        String groupifiedText = "";
        System.out.println("Dlugosc lancucha to :" + textToGroup.length());
        int groupCount = textToGroup.length() / group;
        int whatLeft = textToGroup.length() % group;
        System.out.println("Lancuch zawiera " + groupCount + " grup po " + group + " znakow, a pozostala reszta to: " + whatLeft);
        for (int g = 0; g < group - whatLeft; g++) {
            textToGroup = textToGroup + "x";
        }
        for (int i = 0; i < textToGroup.length(); i++) {
            groupifiedText = groupifiedText + textToGroup.substring(i, i + 1);
            if ((i + 1) % group == 0) {
                groupifiedText = groupifiedText + " ";
            }
        }
        return groupifiedText;
    }

    //decrypter------------------------------------------------------------------------------------
    public static String ungroupify(String encryptedText, int shift) {
        String textToDecrypt = "";
        String decryptedText = "";
        String finalText = "";
        for (int i = 0; i < encryptedText.length(); i++) {
            if (!encryptedText.substring(i, i + 1).equals(" ") & !encryptedText.substring(i, i + 1).equals("x"))
                textToDecrypt = textToDecrypt + encryptedText.substring(i, i + 1);
        }
        for (int i = 0; i < textToDecrypt.length(); i++) {
            int currChar = (int) textToDecrypt.charAt(i);
            int newChar = (char) currChar - shift;
            decryptedText = decryptedText + (char) newChar;

        }
        for (int i = 0; i < decryptedText.length(); i++) {
            if (decryptedText.charAt(i) == 'A' || decryptedText.charAt(i) == 'E' || decryptedText.charAt(i) == 'I'
                    || decryptedText.charAt(i) == 'O' || decryptedText.charAt(i) == 'U' || decryptedText.charAt(i) == 'Y') {
                finalText = finalText + decryptedText.substring (i, i+1);
                i = i + 2;
            } else {
                finalText = finalText + decryptedText.substring(i, i+1);
            }

        }return finalText;
    }
}


