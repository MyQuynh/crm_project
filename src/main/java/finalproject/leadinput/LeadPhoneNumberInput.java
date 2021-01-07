package finalproject.leadinput;

import finalproject.Main;

import java.util.regex.Pattern;

public class LeadPhoneNumberInput {

    public String leadPhoneNumberInput(){

        // Creating the valid pattern Vietnam phone number (which between 10 to 12 digits) using RegEx
        Pattern patternPhone = Pattern.compile("^[0-9]{10,12}$");

        // Looping until the users meet the condition
        while (true){

            // Get the input from users
            System.out.print("Enter the lead's phone number: ");
            String phoneNumber = Main.scanner.next();

            // If it match the phone pattern given below stop the while - loop
            if (patternPhone.matcher(phoneNumber).matches()){
                return phoneNumber;
                // If it is empty, notify to the user and make they enter again
            } else if (phoneNumber.equals("")){
                System.out.println("Phone number cannot be empty");
                System.out.println("Please try again");
                // If it not matches the following patter phone number, make the user enter again and notify them
            } else {
                System.out.println("Invalid phone number, the number should had the numbers of digit in range from 10 to 12");
                System.out.println("Please try again");
            }
        }
    }
}
