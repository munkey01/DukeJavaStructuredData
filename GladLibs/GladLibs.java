/**
 * Created by jgrant on 1/27/2017.
 * This class uses a story template to create a randomized story
 * referred to as a GladLib (or a MadLib).
 */

package GladLibs;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class GladLib {

    private final ArrayList<String> nouns = new ArrayList<>();
    private final ArrayList<String> verbs = new ArrayList<>();
    private final ArrayList<String> adjectives = new ArrayList<>();

    GladLibs() {
        loadWords();
    }

    /* Will load words from preset file names.
     * Should there be overloaded methods to allow other files to be load? */
    private boolean loadWords() { }

    /* Will pick random word from given type and return the word as a string. */
    String pickRandomWord(String category) { }

    /* Takes story template, calls pickRandomWord() for each tagged word. Replaces word
     * with return value from pickRandomWord. Include catch for error from pickRandomWord */
    String parseStory(String storyTemplate) { }

}
