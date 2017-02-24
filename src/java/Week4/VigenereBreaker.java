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

    public String breakVigenere(int keyLength) {
        FileResource fr = new FileResource();
        return breakVigenere(keyLength, fr);
    }

    public String breakVigenere(int keyLength, FileResource fr) {
        String encryptedMessage = fr.asString();
        return breakVigenere(keyLength, encryptedMessage);
    }

    public String breakVigenere(int keyLength, String encryptedMessage) {
        int[] key = tryKeyLength(encryptedMessage, keyLength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decryptedMessage = vc.decrypt(encryptedMessage);
        return decryptedMessage;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<>();

        for (String word : fr.lines()) {
            words.add(word.toLowerCase());
        }

        return words;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int validWordCount = 0;

        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                validWordCount++;
            }
        }

        return validWordCount;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String bestGuessDecrypted = new String();
        int maxValidWord = 0;
        int bestGuessKeyLength = 0;
        int finalValidWordCount = 0;
        for (int i = 1; i < 100; i++) {
            String result = breakVigenere(i, encrypted);
            int validWordCount = countWords(result, dictionary);

            if (validWordCount > maxValidWord) {
                maxValidWord = validWordCount;
                bestGuessDecrypted = result;
                bestGuessKeyLength = i;
                finalValidWordCount = validWordCount;
            }
        }

        System.out.println("Best guess key length: " + bestGuessKeyLength +
                "\nValid word count: " + finalValidWordCount +
                "\n\n---------------------\n");

        return bestGuessDecrypted;
    }

}
