package Week4;

import edu.duke.FileResource;
import src.java.Week4.VigenereBreaker;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jgrant on 2/23/2017.
 */

public class testVigenereBreaker {

    public static void main(String[] args) {
        //testTryKeyLength();
        //testBreakVigenere();
        //testBreakForLanguage();
        //testMostCommonCharIn();
        testBreakAllLanguages();
    }

    public static void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere(4);
    }

    public static void testTryKeyLength() {
        FileResource file = new FileResource();
        String message = file.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(message, 4, 'e');
        for (int i : key) {
            System.out.print(i + " ");
        }
    }

    public static void testBreakForLanguage() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource dictionaryFile = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(dictionaryFile);
        FileResource encryptedMessageFile = new FileResource();
        String encryptedMessage = encryptedMessageFile.asString();
        System.out.println(vb.breakForLanguage(encryptedMessage, dictionary));
    }

    public static void testMostCommonCharIn() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(fr);
        System.out.println("Most common letter in : Italian " + vb.mostCommonCharIn(dictionary));
    }

    public static void testBreakAllLanguages() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println("Choose the encrypted file.");
        FileResource encryptedFile = new FileResource();
        String encryptedMessage = encryptedFile.asString();
        System.out.println("Choose appropriate dictionary files.");
        HashMap<String, HashSet<String>> allDictionaries = vb.readAllDictionaries();
        vb.breakForAllLanguages(encryptedMessage, allDictionaries);
    }

}
