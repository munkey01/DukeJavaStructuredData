/**
 * Created by jgrant on 2/2/2017.
 * This class is a version of GladLib class using maps instead of parallel ArrayLists.
 *
 * As of 2/2/2017: Class is not functional and has not been completed yet.
 */

package src.java.Week2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class GladLibMap {

    private final String storyTemplateFileName;
    private String storyTemplate;
    private HashMap<String, ArrayList<String>> wordMap = new HashMap<>();

    public GladLibMap() {
        this("story_template.txt");
    }

    public GladLibMap(String templateFile) {
        storyTemplateFileName = templateFile;
        try {
            loadWordFiles();
            loadStoryTemplate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWordFiles() {
        System.out.println("Select all files that you wish to use.");
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            String fileContent = new FileResource(f).asString();
            loadWords(fileContent);
        }
    }

    private void loadWords(String fileContent) {
        ArrayList<String> allWords = new ArrayList<>(Arrays.asList(fileContent.split("\\s")));
        String wordType = allWords.get(0);
        allWords.remove(0);
        wordMap.put(wordType, allWords);
    }

    private void loadStoryTemplate() throws IOException {
        System.out.println("Choose story template file.");
        storyTemplate = new FileResource().asString().trim();
    }

    /* Will pick random word from given type and return the word as a string. */
    private String pickRandomWord(String wordType) {
        Random rand = new Random();
        ArrayList<String> currWords = wordMap.get(wordType.trim());
        int index = rand.nextInt(currWords.size());
        return currWords.get(index);
    }

    /* Takes story template, calls pickRandomWord() for each tagged word. Replaces word
     * with return value from pickRandomWord. Include catch for error from pickRandomWord */
    public String createStory() {
        StringBuilder story = new StringBuilder();
        for (String word : storyTemplate.split("\\s")) {
            if (wordMap.containsKey(word)) {
                story.append(pickRandomWord(word)+ " ");
            } else {
                story.append(word + " ");
            }
        }
        return story.toString();
    }

}
