package src.java.Week2;

import edu.duke.FileResource;

import java.util.HashMap;

/** Class reads a string of DNA and establishes counts for each codon type based on user-
 * specified read frame. Class also contains methods to find most common codon and display
 * codons occurring a specified range of times.
 *
 * Created by jgrant on 2/1/2017.
 */

public class CodonCount {

    private HashMap<String, Integer> codonCount = new HashMap<>();
    private String mostCommonCodon = new String();
    private int mostCommonCodonCount;

    public CodonCount() {
        tester();
    }

    private void tester() {
        FileResource dnaFile = new FileResource();
        String dna = dnaFile.asString().toUpperCase().trim();

        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, dna);
            getMostCommonCodon();
            System.out.println("Reading frame starting with " + i + " results in " +
                    codonCount.size() + " unique codons.");
            System.out.println("Most common codon is " + mostCommonCodon + " with count " + mostCommonCodonCount);
            System.out.println("Counts of codons between 1 and 5 inclusive are: ");
            printCodonCounts(1, 5);
            System.out.println("--------------------------------------");
            codonCount.clear();
        }

    }

    private void buildCodonMap(int start, String dna) {
        for (int i = start; i < dna.length()-3; i += 3) {
            String currentCodon = dna.substring(i,i+3);
            if (codonCount.containsKey(currentCodon)) {
                int count = codonCount.get(currentCodon);
                codonCount.put(currentCodon, count+1);
            } else {
                codonCount.put(currentCodon, 1);
            }

        }
    }

    private void getMostCommonCodon() {
        if (codonCount.isEmpty()) {
            System.out.println("Must run buildCodonMap() first.");
            return;
        }

        int max = 0;

        for (String codon : codonCount.keySet()) {
            int currValue = codonCount.get(codon);
            if (currValue > max) {
                mostCommonCodon = codon;
                max = currValue;
            }
        }
        mostCommonCodonCount = max;
    }

    private void printCodonCounts(int min, int max) {
        for (String codon : codonCount.keySet()) {
            int count = codonCount.get(codon);
            if (count >= min && count <= max) {
                System.out.println(codon + " " + count);
            }
        }
    }
}
