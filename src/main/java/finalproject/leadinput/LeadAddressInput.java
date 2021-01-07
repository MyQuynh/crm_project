package finalproject.leadinput;

import finalproject.Main;

import java.util.regex.Pattern;

public class LeadAddressInput {

    public String leadAddressInput(){

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
}
