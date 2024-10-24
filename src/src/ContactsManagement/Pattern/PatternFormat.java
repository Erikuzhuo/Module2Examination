package ContactsManagement.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternFormat {
    static Matcher matcher;
    static Pattern pattern;
    static String regex;
    public static boolean checkEmail(String email) {
        regex = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        regex = "^09[0-9]{8}$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean datePattern(String date) {
        regex = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean namePattern(String name) {
        regex = "^[a-zA-Z]$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
