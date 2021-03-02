package util;

import java.security.SecureRandom;

public class Utils {

    /**
     * static method to generate pin code
     * @return pin as String
     */
    public static String generatePin() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
