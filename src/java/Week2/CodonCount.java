package src.java.Week2;

import java.util.HashMap;

/**
 * Created by jgrant on 2/1/2017.
 */
public class CodonCount {

    private HashMap<String, Integer> codonCount;

    public void buildCodonMap(int start, String dna) {
        HashMap<String, Integer> codonByFrame = new HashMap<>();
        for (int i = start; i < dna.length()-3; i++) {
            String currentCodon = dna.substring(i,i+3);
            codonByFrame.put(currentCodon, i % 3);
        }
        /*
        for (String s : codonByFrame.keySet()) {
            System.out.println(s + " " + codonByFrame.get(s));
        } */
    }

}
