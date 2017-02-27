package src.java.Week4;

import java.io.File;
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
        return breakVigenere(keyLength, encryptedMessage, 'e');
    }

    public String breakVigenere(int keyLength, String encryptedMessage) {
        return breakVigenere(keyLength, encryptedMessage, 'e');
    }

    public String breakVigenere(int keyLength, String encryptedMessage, char mostCommonLetter) {
        int[] key = tryKeyLength(encryptedMessage, keyLength, mostCommonLetter);
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

    public HashMap<String, HashSet<String>> readAllDictionaries() {
        HashMap<String, HashSet<String>> allDictionaries = new HashMap<>();
        DirectoryResource dictionaryFiles = new DirectoryResource();

        for (File dictionary : dictionaryFiles.selectedFiles()) {
            HashSet<String> dictContents = readDictionary(new FileResource(dictionary));
            String language = dictionary.getName();
            allDictionaries.put(language, dictContents);
        }

        return allDictionaries;
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
        char mostCommonLetter = mostCommonCharIn(dictionary);

        for (int i = 1; i < 100; i++) {
            String result = breakVigenere(i, encrypted, mostCommonLetter);
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

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> letterCounts = new HashMap<>();
        Character mostFreqLetter = 'e';
        int maxLetterCount = 0;

        for (String word : dictionary) {
            for (char letter : word.toLowerCase().toCharArray()) {
                Character letterObj = letter;
                if (!letterCounts.containsKey(letterObj)) {
                    letterCounts.put(letterObj, 1);
                } else {
                    int currentCount = letterCounts.get(letterObj);
                    letterCounts.put(letterObj, ++currentCount);
                }
            }
        }

        for (Character letter : letterCounts.keySet()) {
            int currentCount = letterCounts.get(letter);
            if (currentCount > maxLetterCount) {
                maxLetterCount = currentCount;
                mostFreqLetter = letter;
            }
        }

        return mostFreqLetter.charValue();
    }

    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> allDictionaries) {
        String languageGuess = "";
        String decryptedMessage = "";
        int maxValidWords = 0;

        for (String language : allDictionaries.keySet()) {
            HashSet<String> currentDictionary = allDictionaries.get(language);
            String decryptionResult = breakForLanguage(encrypted, currentDictionary);
            int validWords = countWords(decryptionResult, currentDictionary);

            if (validWords > maxValidWords) {
                maxValidWords = validWords;
                languageGuess = language;
                decryptedMessage = decryptionResult;
            }
        }

        System.out.println("Best guess language: " + languageGuess);
        System.out.println("\nBest guess decryption: " + decryptedMessage);

    }

}