/**
 * Created by jgrant on 1/24/2017.
 */

public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet;

    CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
    }

    CaesarCipher() {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    String decrypt(String encrypted, int key) {
        return encrypt(encrypted, 26-key); //decrypting is just a shifted encrypt
    }

    String decryptTwoKeys(String encryptedStr, int key1, int key2) {  //needs to handle capital and lowercase letters
        int length = encryptedStr.length();
        StringBuilder decryptedStr = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char currentLetter = encryptedStr.charAt(i);
            String decryptedLetter = null;
            if(i % 2 == 0) {
                decryptedLetter = decrypt(Character.toString(currentLetter), key1); //Check if the key to modulo comparision is correct
            }
            else if(i % 2 == 1) {
                decryptedLetter = decrypt(Character.toString(currentLetter), key2); //See if statement
            }
            decryptedStr.append(decryptedLetter);
        }
        return decryptedStr.toString();
    }

    int[] countLettersInMessage(String message) {   //needs to handle capital and lowercase letters
        int[] letterCounts = new int[25];
        int length = message.length();
        for(int i = 0; i < length; i++) {
            char currentLetter = message.charAt(i); //should I split between declaration and assignment?
            int letterIndexInAlphabet = alphabet.indexOf(currentLetter);
            if(letterIndexInAlphabet != -1) {
                letterCounts[letterIndexInAlphabet]++;
            }
        }
        return letterCounts;
    }

    String decryptKeyUnknown(String message) {      //needs to handle capital and lowercase letters
        //call countLettersInMessage
        int[] letterCounts = countLettersInMessage(message);

        //find highest count
        int length = alphabet.length();
        int max = 0;
        char mostCommonLetter = 'A';
        for(int i = 0; i < length; i++) {
            if(letterCounts[i] > max) {
                max = letterCounts[i];
                mostCommonLetter = alphabet.charAt(i);
            }
        }

        //assume highest count is 'e', do math to shift appropriately
        int key = alphabet.indexOf('e') - alphabet.indexOf(mostCommonLetter); //other direction?

        //call decrypt with calculated shift value
        System.out.println("Assuming shift of " + key + ". Attempting decryption.\n" + decrypt(message, key));

        //ask user if it is correct, if it is not, then assume second highest count is 'e', continue until success or user exit
    }

    String encrypt(String message, int key) { //needs to handle capital and lowercase letters
        //insert encrypt method body
    }
}
