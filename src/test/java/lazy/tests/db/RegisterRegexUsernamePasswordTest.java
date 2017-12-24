package lazy.tests.db;

import java.util.regex.Pattern;

public class RegisterRegexUsernamePasswordTest {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("expecting falses");
        boolean underSixPassword = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("a2AAA").matches();
        boolean over20Password = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("aidhfioasdfpioaAAA2fiajspdfasdfad").matches();
        boolean passwordNoUpperCase = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("ksjdk5jf").matches();
        boolean passwordNoLowercase = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("AAAAA5555").matches();
        boolean passwordNoDigit = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("JAKjjjjaaaa").matches();
        boolean nonAlphaNumericPassword = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("JAKjj444@jjaaaa").matches();
        boolean nonBeginningWithWordPassword = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher(" jKK2aaaaA5").matches();

        System.out.println(underSixPassword);
        System.out.println(over20Password);
        System.out.println(passwordNoUpperCase);
        System.out.println(passwordNoLowercase);
        System.out.println(passwordNoDigit);
        System.out.println(nonAlphaNumericPassword);
        System.out.println(nonBeginningWithWordPassword);

        boolean overTenUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher("kajsdfaijaisdjfad").matches();
        boolean underFourUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher("kaf").matches();
        boolean nonAlphaNumericUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher("ka@f").matches();
        boolean nonBeginningWithWordUsername = Pattern.compile("\\A\\w{4,10}\\z").matcher(" ka@f").matches();

        System.out.println(overTenUsername);
        System.out.println(underFourUsername);
        System.out.println(nonAlphaNumericUsername);
        System.out.println(nonBeginningWithWordPassword);

        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("now expecting trues");
        boolean properPassword1 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("JJKK_KKKa1").matches();
        boolean properPassword2 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("akj__dfaksdf14A").matches();
        boolean properPassword3 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("kiO__apaj22").matches();
        boolean properPassword4 = Pattern.compile("\\A(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]))(?=\\D*\\d)\\w{6,20}\\z").matcher("KOkANO__41").matches();

        System.out.println(properPassword1);
        System.out.println(properPassword2);
        System.out.println(properPassword3);
        System.out.println(properPassword4);

        boolean properUserName1 = Pattern.compile("\\A\\w{4,10}\\z").matcher("askj").matches();
        boolean properUserName2 = Pattern.compile("\\A\\w{4,10}\\z").matcher("asKJ").matches();
        boolean properUserName3 = Pattern.compile("\\A\\w{4,10}\\z").matcher("asK55___j").matches();
        boolean properUserName4 = Pattern.compile("\\A\\w{4,10}\\z").matcher("a__skj").matches();
        boolean properUserName5 = Pattern.compile("\\A\\w{4,10}\\z").matcher("___askj").matches();

        System.out.println(properUserName1);
        System.out.println(properUserName2);
        System.out.println(properUserName3);
        System.out.println(properUserName4);
        System.out.println(properUserName5);
    }
}
