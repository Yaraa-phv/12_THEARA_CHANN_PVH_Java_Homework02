import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPractice {
    public static void main(String[] args) {
        String name = "";
        String age = "";
        String phoneNum = "";

        // Practice with Regex
        System.out.println("Personal Information\n--------------------");

        // Scan Name
        System.out.println("Input your name: ");
        Scanner scanName = new Scanner(System.in);
        name = scanName.nextLine();

        // Scan Age
        System.out.println("Input your age: ");
        Scanner scanAge = new Scanner(System.in);
        age = scanAge.nextLine();

        // Scan Phone Number
        System.out.println("Input your phone number (099 999 9999) : ");
        Scanner scanPhone = new Scanner(System.in);
        phoneNum = scanPhone.nextLine();

        // Pattern and Condition for Name
        Pattern patternName = Pattern.compile("^\\w[a-zA-Z\\s]");
        Matcher matcherName = patternName.matcher(name);
        String messageName = matcherName.find() ? " " : "Error message";
        System.out.println(messageName);
        name = messageName.equals(" ") ? name : "-";

        // Pattern and Condition for Age
        Pattern patternAge = Pattern.compile("^\\d[0-9]{2}");
        Matcher matcherAge = patternAge.matcher(age);
        String messageAge = matcherAge.find() ? " " : "Error message";
        System.out.println(messageAge);
        age = messageAge.equals(" ") ? age : "-";


        // Pattern and Condition for Phone Number
        Pattern patternPhone = Pattern.compile("^0[0-9]{2}\\s[0-9]{3}\\s[0-9]{3,4}");
        Matcher matcherPhone = patternPhone.matcher(phoneNum);
        String messagePhone = matcherPhone.find() ? " " : "Error message!";
        System.out.println(messagePhone);
        phoneNum = messagePhone.equals(" ") ? phoneNum : "-";

        // Final output
        System.out.println("Personal Information\n--------------------");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone Number: " + phoneNum);
    }
}