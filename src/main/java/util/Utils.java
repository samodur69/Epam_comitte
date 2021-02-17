package util;

import java.util.Random;

public class Utils {

    public final static String nameValidator = "[A-Z]{1}[a-z]{1,29}";
    public final static String emailValidator = "\\S+@\\S+\\.\\S+";

    public static String generatePin() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
