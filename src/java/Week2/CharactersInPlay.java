package src.java.Week2;

import java.util.ArrayList;
import edu.duke.FileResource;

/** Class indexes all speaking parts of a Shakespearian play (based on text formatting).
 *  Also allows printing of character names with most speaking parts (as set by user).
 * Created by jgrant on 2/1/2017.
 */

public class CharactersInPlay {

    private ArrayList<String> characters = new ArrayList<>();
    private ArrayList<Integer> speakCount = new ArrayList<>();

    public CharactersInPlay() {
        findAllCharacters();
    }

    /* Note this method is error-prone. It will detect sentences as characters; however,
     * just displaying characters with more than 1 speaking part should avoid most of the
     * problems.  */
    public void findAllCharacters() {
        FileResource playFile = new FileResource();

        for (String line : playFile.lines()) {
            if (line.contains(".")) {
                int endIndex = line.indexOf(".");
                update(line.substring(0,endIndex).trim());         //take all of sentence prior to period
            }
        }
    }

    private void update(String person) {
        if (!characters.contains(person)) {
            characters.add(person);
            speakCount.add(1);
        } else {
            int index = characters.indexOf(person);
            int count = speakCount.get(index);
            speakCount.set(index, count + 1);
        }
    }

    public void displayCharacters() {
        displayCharacters(1);
    }

    public void displayCharacters(int lowestCount) {
        System.out.println("Characters and how much they said:");
        for (int i = 0; i < characters.size(); i++) {
            if (speakCount.get(i) > lowestCount) {
                System.out.println(characters.get(i) + " : " + speakCount.get(i));
            }

        }
    }

    public void charactersWithNumParts(int min, int max) {
        if (characters.isEmpty()) {
            findAllCharacters();
        }
        for (int i = 0; i < characters.size(); i++) {
            if ((speakCount.get(i) >= min) && (speakCount.get(i) <= max)) {
                System.out.println(characters.get(i) + " : " + speakCount.get(i));
            }

        }
    }
}
