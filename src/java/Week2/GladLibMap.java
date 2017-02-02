/**
 * Created by jgrant on 2/2/2017.
 * This class is a version of GladLib class using maps instead of parallel ArrayLists.
 *
 * As of 2/2/2017: Class is not functional and has not been completed yet.
 */

package src.java.Week2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {

    private final String path = "/resources/";
    private final String nounsFile = "nouns.txt";
    private final String verbsFile = "verbs.txt";
    private final String adjFile = "adjectives.txt";
    private final String storyTemplateFileName;
    private String storyTemplate;
    private HashMap<String, ArrayList<String>> wordMap = new HashMap<>();

    public GladLibMap() {
        this("story_template.txt");
    }

    public GladLibMap(String templateFile) {
        storyTemplateFileName = templateFile;
        try {
            loadWords(nouns, nounsFile);
            loadWords(verbs, verbsFile);
            loadWords(adjectives, adjFile);
            loadStoryTemplate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, "Cp1252");
    }

    private void loadWords(ArrayList<String> list, String filename) throws IOException  {
        String fileAsString = readFile(path + filename);
        Collections.addAll(list, fileAsString.split("\\s"));
    }

    private void loadStoryTemplate() throws IOException {
        storyTemplate = readFile(path + storyTemplateFileName);
    }

    /* Will pick random word from given type and return the word as a string. */
    private String pickRandomWord(ArrayList<String> wordList) {
        Random rand = new Random();
        int size = wordList.size();
        int index = rand.nextInt(size);
        return wordList.get(index);
    }

    /* Takes story template, calls pickRandomWord() for each tagged word. Replaces word
     * with return value from pickRandomWord. Include catch for error from pickRandomWord */
    public String createStory() {
        StringBuilder story = new StringBuilder();
        for (String word : storyTemplate.split("\\s")) {
            switch (word) {
                case "<noun>" :
                    story.append(pickRandomWord(nouns) + " ");
                    continue;
                case "<verb>" :
                    story.append(pickRandomWord(verbs) + " ");
                    continue;
                case "<adjective>" :
                    story.append(pickRandomWord(adjectives) + " ");
                    continue;
                default:
                    story.append(word + " ");
            }
        }
        return story.toString();
    }

}
