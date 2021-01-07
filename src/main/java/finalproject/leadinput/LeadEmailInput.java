package finalproject.leadinput;

import finalproject.Main;

import java.util.regex.Pattern;

public class LeadEmailInput {

    public String leadEmailInput() {
        while (true) {
            // Get the input from users
            System.out.print("Enter the lead's email: ");
            String email = Main.scanner.nextLine();

            // Creat the valid Pattern email using RegEx
            Pattern patternEmail = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@gmail.com$");

            // If it matches the Pattern below, it will stop the loop and return the email
            if (patternEmail.matcher(email).matches()) {
                return email;

                // If the input email is empty, notify to the users and make the user write the email again
            } else if (email.equals("")) {
                System.out.println("Cannot be empty");
                System.out.println("Please try again");

                // The user not match the below patter, notify to the user and make the user write the email again
            } else {
                System.out.println("Invalid Email: The email should be like this test123@gmail.com");
                System.out.println("Please try again");
            }
        }
    }
}
