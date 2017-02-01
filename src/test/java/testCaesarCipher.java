package CaesarCipher;

import CaesarCipher.CaesarCipher;

/**
 * Created by joey on 1/25/17.
 */

public class testCaesarCipher {

    //encrypt and decrypt = success
    //decrypt2keys, encrypt2keys = success
    //decryptKeyUnknown, countLettersInMessage = success

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher(1);
        
        String encrypted = cc.encrypt("ABcd! 1", 1);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted, 1);
        System.out.println(decrypted);


        String encryptedMsg2Keys = cc.encryptTwoKeys("ABcd! 1", 1, 2);
        System.out.println(encryptedMsg2Keys);

        System.out.println(cc.decryptTwoKeys("BDdF! 1", 1, 2));
        

        String eMessage = "ABCDEEEEE";
        String encryptedEMessage = cc.encrypt(eMessage,1);
        System.out.println("Encrypted: " + encryptedEMessage);
        String decryptedMsg = cc.decryptKeyUnknown(encryptedEMessage);
        System.out.println(decryptedMsg);
    }

}
