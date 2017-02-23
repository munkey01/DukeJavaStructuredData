package src.java.Week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder currStrSlice = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char currChar = message.charAt(i);
            currStrSlice.append(currChar);
        }
        return currStrSlice.toString();
    }

    public int[] tryKeyLength(String encrypted, int keyLength, char mostCommon) {
        int[] key = new int[keyLength];
        CaesarCracker cracker = new CaesarCracker();

        for (int i = 0; i < keyLength; i++) {
            String currentSlice = sliceString(encrypted, i, keyLength);
            key[i] = cracker.getKey(currentSlice);
        }

        return key;
    }

    public void breakVigenere(int keyLength) {
        FileResource file = new FileResource();
        String encryptedMessage = file.asString();
        int[] key = tryKeyLength(encryptedMessage, keyLength, 'e');

        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(encryptedMessage));
    }
    
}
