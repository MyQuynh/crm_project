package finalproject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IsValid {

    public String isValidEmail(){
        while (true){
            // Get the input from users
            System.out.print("Enter the lead's email: ");
            String email = Main.scanner.nextLine();

            // Creat the valid Pattern email using RegEx
            Pattern patternEmail = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@gmail.com$");

            // If it matches the Pattern below, it will stop the loop and return the email
            if (patternEmail.matcher(email).matches()){
                return email;

            // If the input email is empty, notify to the users and make the user write the email again
            } else if(email.equals("")){
                System.out.println("Cannot be empty");
                System.out.println("Please try again");

            // The user not match the below patter, notify to the user and make the user write the email again
            } else {
                System.out.println("Invalid Email: The email should be like this test123@gmail.com");
                System.out.println("Please try again");
            }
        }
    }

    public boolean checkNameValid(String name){

        // First split the name into word by space
        String[] word = name.split("\\s+");
        boolean result = true;

        // If the user does not enter anything return false
        if (word.length <= 0){
            return false;
        }

        // Looping through the name
        for (int i = 0; i < word.length;++i) {

            // Get the first index of each word to see if it is an Uppercase Character
            char c = word[i].charAt(0);
            if ('A' <= c & 'Z' >= c) {
                // Looping through the remain to check if it lowercase character
                for (int j = 1; j < word[i].length(); ++j) {
                    //System.out.println(word[i].charAt(j));
                    if ('a' <= word[i].charAt(j) & 'z' >= word[i].charAt(j)){
                        result = true; // If it meet those condition, set the result to true
                    } else {
                        return false;
                    }
                }
            // If the first index is not Uppercase Character return false
            } else {
                return false;
            }

        }
        return result;
    }

    public boolean isValidGender(){
        String genderResult;
        while(true){

            // Get the input from users
            System.out.print("Enter lead's gender which is true for male and false for female: ");
            String gender = Main.scanner.next();


            // Check if the users enter the correct value which is true and false
            // If the users not enter anything, notify to them and make them enter the again
            if (String.valueOf(gender).equals("")){
                System.out.println("The input cannot be empty");
                System.out.println("Please try again");

            // If the users does not enter the correct given value (true or false), notify them and make the user enter again
            } else if (!gender.equalsIgnoreCase("true") && !gender.equalsIgnoreCase("false")) {
                System.out.println("Not valid gender");
                System.out.println("Please try again");
            } else {
                genderResult = gender;
                break; // The users input meet those condition. Therefore, we stop the while loop and return the matched boolean
            }
        }

        return genderResult.equalsIgnoreCase("true");
    }

    public String isValidPhoneNumber(){

        // Creating the valid pattern Vietnam phone number (which has 10 digits) using RegEx
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

    public String isValidAddress(){

        // Creating the pattern of Vietnam Address: Have number first (can be included alley address) and the Street
        Pattern patternAddress = Pattern.compile("(\\d+\\/?\\d+) [a-zA-Z_]+( [a-zA-Z_]+)*");
        String finalAddress;

        // Looping until the input match the condition pattern
        while (true){

            // Get the input from users
            System.out.print("Enter the address: ");
            String address = Main.scanner.nextLine();

            // If it matches the below Pattern, stop the loop
            if(patternAddress.matcher(address).matches()){

                // Make the address become LowerCase
                finalAddress = address.toLowerCase();
                break;

            // If the address is empty, inform the user and make the user enter again
            } else if (address.equals("")){
                System.out.println("The address cannot be empty");
                System.out.println("Please try again");

            // If the address does not matched, inform the users and make the user enter again
            } else {
                System.out.println("Invalid address: The address should be include number and Street name like this 100 Nguyen Van Linh");
                System.out.println("Please try again");
            }
        }

        // The pattern address below cannot test case the if the first character of the street is UpperCase
        // Therefore, we will make all the address become lowercase and the changing the first index of Street Name to UpperCase
        StringBuffer bufferUpperCase = new StringBuffer();
        String[] words = finalAddress.split(" ");
        bufferUpperCase.append(words[0]);

        // Start at one to ignore the number address
        for (int i = 1; i < words.length; i++) {

            // Get the first character, make it to upperCase and concat it with the remain String
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1);
            bufferUpperCase.append(words[i]);

        }

        return bufferUpperCase.toString();

    }

    public String isExistLeadID(){

        // Creating the array to store exist LeadID
        ArrayList<String> listLead = new ArrayList<>();

        try {
            // Reading through the file
            FileReader fileWriter = new FileReader(new File("leads.csv"));
            Scanner scannerFile = new Scanner(fileWriter);


            // Looping through the file and stop till the end of the file
            while (scannerFile.hasNext()){

                // Split the line into word array and get the leadID store into the listLead array
                String[] word = scannerFile.nextLine().split(",");
                listLead.add(word[0]);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot read the file");
        }

        // Looping until the users enter the valid LeadID
        while(true){

            // Get the input from users
            System.out.print("Enter interaction's lead's id: ");
            String leadID = Main.scanner.next();

            // If the input is empty, make the user enter again
            if (leadID.equals("")){
                System.out.println("The LeadID cannot be empty");
                System.out.println("Please try again");
            } else {

                // Looping through the listLead to see if it match th input from users
                for (int i = 0; i < listLead.size(); i++) {

                    // If it match, return the leadID
                    if(leadID.equals(listLead.get(i))){
                        return leadID;
                    }
                }
                // If does not, make the user enter again
                System.out.println("The lead ID is not in the system\nPlease try again");

            }


        }

    }

}