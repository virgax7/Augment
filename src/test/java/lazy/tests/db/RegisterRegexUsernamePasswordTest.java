package lazy.tests.db;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterRegexUsernamePasswordTest {

    @Test
    void mainTest() {
        final List<String> failurePasswords = new ArrayList<>();
        boolean underSixPassword = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("a2AAA").matches();
        boolean over20Password = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("aidhfioasdfpioaAAA2fiajspdfasdfad").matches();
        boolean passwordNoUpperCase = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("ksjdk5jf").matches();
        boolean passwordNoLowercase = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("AAAAA5555").matches();
        boolean passwordNoDigit = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("JAKjjjjaaaa").matches();
        boolean nonAlphaNumericPassword = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("JAKjj444@jjaaaa").matches();
        boolean nonBeginningWithWordPassword = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher(" jKK2aaaaA5").matches();

        failurePasswords.add("" + underSixPassword);
        failurePasswords.add("" + over20Password);
        failurePasswords.add("" + passwordNoUpperCase);
        failurePasswords.add("" + passwordNoLowercase);
        failurePasswords.add("" + passwordNoDigit);
        failurePasswords.add("" + nonAlphaNumericPassword);
        failurePasswords.add("" + nonBeginningWithWordPassword);

        final List<String> failureUserNames = new ArrayList<>();
        boolean overTenUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher("kajsdfaijaisdjfad").matches();
        boolean underFourUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher("kaf").matches();
        boolean nonAlphaNumericUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher("ka@f").matches();
        boolean nonBeginningWithWordUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher(" ka@f").matches();

        failureUserNames.add("" + overTenUsername);
        failureUserNames.add("" + underFourUsername);
        failureUserNames.add("" + nonAlphaNumericUsername);
        failureUserNames.add("" + nonBeginningWithWordPassword);
        failureUserNames.add("" + nonBeginningWithWordUsername);

        final List<String> successPasswords = new ArrayList<>();
        boolean properPassword1 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("JJKK_KKKa1").matches();
        boolean properPassword2 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("akj__dfaksdf14A").matches();
        boolean properPassword3 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("kiO__apaj22").matches();
        boolean properPassword4 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("KOkANO__41").matches();

        successPasswords.add("" + properPassword1);
        successPasswords.add("" + properPassword2);
        successPasswords.add("" + properPassword3);
        successPasswords.add("" + properPassword4);

        final List<String> successUserNames = new ArrayList<>();
        boolean properUserName1 = Pattern.compile("\\A\\w{4,10}\\z").matcher("askj").matches();
        boolean properUserName2 = Pattern.compile("\\A\\w{4,10}\\z").matcher("asKJ").matches();
        boolean properUserName3 = Pattern.compile("\\A\\w{4,10}\\z").matcher("asK55___j").matches();
        boolean properUserName4 = Pattern.compile("\\A\\w{4,10}\\z").matcher("a__skj").matches();
        boolean properUserName5 = Pattern.compile("\\A\\w{4,10}\\z").matcher("___askj").matches();

        successUserNames.add("" + properUserName1);
        successUserNames.add("" + properUserName2);
        successUserNames.add("" + properUserName3);
        successUserNames.add("" + properUserName4);
        successUserNames.add("" + properUserName5);

        failurePasswords.stream().forEach(s -> assertTrue(s.equals("false")));
        failureUserNames.stream().forEach(s -> assertTrue(s.equals("false")));
        successPasswords.stream().forEach(s -> assertTrue(s.equals("false")));
        successUserNames.stream().forEach(s -> assertTrue(s.equals("false")));
    }
}
