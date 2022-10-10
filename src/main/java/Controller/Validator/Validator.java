package Controller.Validator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static boolean validate(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean nameCheck(String name) {
        return validate(name, Regexes.NAME);
    }

    public static boolean emailCheck(String email) {
        return validate(email, Regexes.EMAIL);
    }

    public static boolean passwordCheck(String password) {
        return validate(password, Regexes.PASSWORD);
    }

    public static boolean titleENGCheck(String title) {
        return validate(title, Regexes.TITLE_ENG);
    }

    public static boolean titleUACheck(String title) {
        return validate(title, Regexes.TITLE_UA);
    }

    public static boolean dateCheck(LocalDateTime start, LocalDateTime end) {
        long days = ChronoUnit.DAYS.between(start, end);
        return days >= 1;
    }
}