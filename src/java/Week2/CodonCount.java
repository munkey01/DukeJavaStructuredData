package src.java.Week2;

import java.util.HashMap;

/**
 * Created by jgrant on 2/1/2017.
 */
public class CodonCount {

    private HashMap<String, Integer> codonCount;

    public void buildCodonMap(int start, String dna) {
        for (int i = start; i < dna.length()-3; i++) {
            String currentCodon = dna.substring(i,i+3);
            if (codonCount.containsKey(currentCodon)) {
                int count = codonCount.get(currentCodon);
                codonCount.put(currentCodon, count+1);
            } else {
                codonCount.put(currentCodon, 1);
            }

        }

        for (String s : codonCount.keySet()) {
            System.out.println(s + " " + codonCount.get(s));
        }
    }

    public String getMostCommonCodon() {

        if (codonCount.isEmpty()) {
            return "Must run buildCodonMap() first.";
        }

        int max = 0;
        String mostFrequentCodon = new String();

        for (String codon : codonCount.keySet()) {
            int currValue = codonCount.get(codon);
            if (currValue > max) {
                mostFrequentCodon = codon;
                max = currValue;
            }
        }
        return mostFrequentCodon;
    }



}
