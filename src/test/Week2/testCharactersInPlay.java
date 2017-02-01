package Week2;

import src.java.Week2.CharactersInPlay;

/**
 * Created by jgrant on 2/1/2017.
 */

public class testCharactersInPlay {

    public static void main(String[] args) {
        CharactersInPlay playCounter = new CharactersInPlay();
        playCounter.displayCharacters(10);
        System.out.println("+++++++++++++++++++++++++");
        playCounter.charactersWithNumParts(2,5);
    }

}
