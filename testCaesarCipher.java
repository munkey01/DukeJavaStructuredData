/**
 * Created by joey on 1/25/17.
 */

public class testCaesarCipher {

    CaesarCipher cc;
    private String message;
    private int key;

    testCaesarCipher(String input, int shiftKey) {
        cc = new CaesarCipher();
        setMessage(input);
        setKey(shiftKey);
    }

    void setMessage(String input) {
        message = input;
    }

    String getMessage() {
        return this.message;
    }

    void setKey(int shiftKey) {
        key = shiftKey;
    }

    int getKey() {
        return this.key;
    }

    void testEncrypt() {
        System.out.println("Running testEncrypt.\nMessage: \t" + getMessage() + "\nKey:\t" +getKey() + "\n" + "Encrypted message: ");
        System.out.println(cc.encrypt(getMessage(), getKey()));
   }

}
