/**
 * Created by jgrant on 1/24/2017.
 */

public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet;

    CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        setShift(key);
    }

    CaesarCipher() {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    void setShift(int key) {
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
    }

    String decryptTwoKeys(String encryptedStr, int key1, int key2) {  //needs to handle capital and lowercase letters
        return encryptTwoKeys(encryptedStr,26-key1,26-key2);
    }

    int[] findLetterFrequency(String message) {   //needs to handle capital and lowercase letters
        int[] letterCounts = new int[26];
        int length = message.length();
        for(int i = 0; i < length; i++) {
            char currentLetter = message.charAt(i); //should I split between declaration and assignment?
            int letterIndexInAlphabet = alphabet.indexOf(currentLetter);
            if (letterIndexInAlphabet != -1) {
                letterCounts[letterIndexInAlphabet]++;
            }
        }
        return letterCounts;
    }

    int findUnknownKey(String message) {
        //call countLettersInMessage
        int[] letterCounts = findLetterFrequency(message);

        //find highest count
        int max = 0;
        char mostCommonLetter = 'A';
        for (int i = 0; i < 25; i++) {
            if (letterCounts[i] > max) {
                max = letterCounts[i];
                mostCommonLetter = alphabet.charAt(i);
            }
        }
        //assume highest count is 'e', do math to shift appropriately
        int key = alphabet.indexOf(mostCommonLetter) - alphabet.indexOf('E');
        System.out.println("Most common letter: " + mostCommonLetter);
        return key;
    }

    String decryptKeyUnknown(String message) {      //needs to handle capital and lowercase letters
        int key = findUnknownKey(message);
        System.out.println("Calculated shift: " + key);
        return decrypt(message, key);
    }

    String encrypt(String message, int key) { //needs to handle capital and lowercase letters
        setShift(key);
        StringBuilder encryptedStr = new StringBuilder();

        for(int i = 0; i < message.length(); i++) {
            char currentLetter = message.charAt(i);
            //find index of letter in shifted alphabet, must do this as uppercase version
            int indexOfChar = alphabet.indexOf(Character.toUpperCase(currentLetter));
            if(indexOfChar == -1) {
                encryptedStr.append(currentLetter);
                continue;
            }
            char charToAdd = shiftedAlphabet.charAt(indexOfChar);
            if(Character.isLowerCase(currentLetter)) {
                //add lowercase shifted letter to encrypted string if original was lowercase
                charToAdd = Character.toLowerCase(charToAdd);
            }
            encryptedStr.append(charToAdd);
        }

        return encryptedStr.toString();
    }

    String encryptTwoKeys(String message, int key1, int key2) {
        int length = message.length();
        StringBuilder encryptedStr = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char currentLetter = message.charAt(i);
            String encryptedLetter = null;
            if(i % 2 == 0) {
                encryptedLetter = encrypt(Character.toString(currentLetter), key1); //Check if the key to modulo comparision is correct
            }
            else if(i % 2 == 1) {
                encryptedLetter = encrypt(Character.toString(currentLetter), key2); //See if statement
            }
            encryptedStr.append(encryptedLetter);
        }
        return encryptedStr.toString();
    }

    String decrypt(String encrypted, int key) {
        return encrypt(encrypted, (26-key)); //decrypting is just a shifted encrypt
    }

    String decryptTwoKeysUnknown(String encryptedStr) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        for(int i = 0; i < encryptedStr.length()-1; i++) {
            char currentLetter = encryptedStr.charAt(i);
            if(i % 2 == 0) {
                string1.append(currentLetter);
            }
            else if(i % 2 == 1) {
                string2.append(currentLetter);
            }
        }
        int key1 = findUnknownKey(string1.toString());
        int key2 = findUnknownKey(string2.toString());

        return decryptTwoKeys(encryptedStr, key1, key2);
    }

}
