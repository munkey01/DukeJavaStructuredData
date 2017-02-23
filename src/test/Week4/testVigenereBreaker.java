package Week4;

import edu.duke.FileResource;
import src.java.Week4.VigenereBreaker;

/**
 * Created by jgrant on 2/23/2017.
 */

public class testVigenereBreaker {

    public static void main(String[] args) {
        testTryKeyLength();
        testBreakVigenere();
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

}
