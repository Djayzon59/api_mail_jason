package fr.js.tools;

import java.util.regex.Pattern;

public class Validator {

    private Validator() {

    }

    public static boolean isValideMail(String email) {

        String alphaN1 = "[A-Za-z0-9_-]";
        String alphaN2 = "[A-Za-z0-9-]";

        String pattern = String.format("^(?=.{1,64}@)%s+(\\.%s+)*@[^-]%s+(\\.%s+)*(\\.[A-Za-z]{2,})$", alphaN1, alphaN1, alphaN2, alphaN2);

        return Pattern.compile(pattern).matcher(email).matches();
    }
}
