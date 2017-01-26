/**
 * Created by jgrant on 1/24/2017.
 *
 * CaesarCipher class contains encryption and decryption methods related to Caesar ciphers of various 
 * known and unknown alphabetic shifts. 
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
        while(key > 26) {
            key = key-26; //adjust key to within alphabetic length range
        }
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    String decryptTwoKeys(String encryptedStr, int key1, int key2) {
        return encryptTwoKeys(encryptedStr,26-key1,26-key2);
    }

    int[] findLetterFrequency(String message) {
        int[] letterCounts = new int[26];
        int length = message.length();
        for(int i = 0; i < length; i++) {
            char currentLetter = message.charAt(i);
            int letterIndexInAlphabet = alphabet.indexOf(Character.toUpperCase(currentLetter));
            if (letterIndexInAlphabet != -1) {
                letterCounts[letterIndexInAlphabet]++;
            }
        }
        return letterCounts;
    }

    int findUnknownKey(String message) {
        int[] letterCounts = findLetterFrequency(message);

        //find letter of highest count
        int max = 0;
        char mostCommonLetter = 'A';
        for (int i = 0; i < 25; i++) {
            if (letterCounts[i] > max) {
                max = letterCounts[i];
                mostCommonLetter = alphabet.charAt(i);
            }
        }
        //assume highest count represents decrypted 'e', shift alphabet appropriately
        int key = alphabet.indexOf(mostCommonLetter) - alphabet.indexOf('E');
        System.out.println("Most common letter: " + mostCommonLetter + "\nCalculated key: " + key);
        return key;
    }

    String decryptKeyUnknown(String message) {
        int key = findUnknownKey(message);
        System.out.println("Calculated shift: " + key);
        return decrypt(message, key);
    }

    String encrypt(String message, int key) {
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
                encryptedLetter = encrypt(Character.toString(currentLetter), key1);
            }
            else if(i % 2 == 1) {
                encryptedLetter = encrypt(Character.toString(currentLetter), key2);
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
