package src.java.Week2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

/**
 * Created by jgrant on 2/2/2017.
 */

public class WordInFiles {

    private HashMap<String, ArrayList<String>> wordLocations = new HashMap<>();

    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Most files word found in: " + max);
        for (String word : wordsInNumFiles(max)) {
            ArrayList<String> files = wordLocations.get(word);
            System.out.println(word + " " + String.join(" ", files));
        }
    }

    /* Stores each word and filenames in which it is found. */
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);

        for (String word : fr.words()) {
            if (wordLocations.containsKey(word)) {                              //if the word has been found before
                ArrayList<String> currentFiles = wordLocations.get(word);
                if (!currentFiles.contains(f.getName())) {                      //but not in the current file before
                    currentFiles.add(f.getName());                              //add filename to list of files
                    wordLocations.put(word, currentFiles);                      //update HashMap
                }

            } else {
                ArrayList<String> filenameList = new ArrayList<>();
                filenameList.add(f.getName());
                wordLocations.put(word, filenameList);
            }
        }
    }

    /* Allows user to select files to search through for words. */
    private void buildWordFileMap() {
        wordLocations.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    /* Finds and returns the maximum number of files in which any word is found. */
    private int maxNumber() {
        int max = 0;
        for (Map.Entry e : wordLocations.entrySet()) {
            String word = e.getKey().toString();
            ArrayList<String> fileList = wordLocations.get(word);
            if (fileList.size() > max) {
                max = fileList.size();
            }
        }
        return max;
    }

    /*Returns ArrayList of words that are found in exactly n files. */
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<>();
        for (Map.Entry entry : wordLocations.entrySet()) {
            ArrayList<String> fileNameList = new ArrayList<>();
            try {
                fileNameList = (ArrayList<String>) entry.getValue();       //try-catch this
            } catch (Exception e) {
                System.out.println("Cast to ArrayList failed.\ne");
                return new ArrayList<>();
            }
            if (fileNameList.size() == number) {
                words.add(entry.getKey().toString());
            }
        }
        return words;
    }

    /* Print names of all files that word is found in. */
    private void printFilesIn(String word) {
        if (wordLocations.containsKey(word)) {
            for (String filename : wordLocations.get(word)) {
                System.out.println(filename);
            }
        } else {
            System.out.println("Word not found.");
        }
    }

}
