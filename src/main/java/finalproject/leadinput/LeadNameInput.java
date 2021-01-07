package finalproject.leadinput;

import finalproject.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeadNameInput {

    public String toUpperCaseFirstLetter(String word){

        // Changing the first letter to uppercase and concat with the remain
        return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
    }

    public String leadNameInput(){

        // Looping until it meet the condition
        while(true){

            // Get the input lead's input name from the user
            System.out.print("Please enter the lead's name: ");
            String name = Main.scanner.nextLine().toLowerCase();

            // If the name is empty, notify it and make user enter again
            if (name.equals("")){
                System.out.println("The name cannot be empty");
                System.out.println("Please try again");
            } else {
                // Check if the name contains characters
                Pattern character = Pattern.compile("[a-z]*");
                Matcher matcher = character.matcher(name);

                if (matcher.matches()){
                    // Split the name by space and lowercase all of the name
                    String[] newNameArray = name.split(" ");

                    // Creating the new value called newName to store later
                    StringBuffer newName = new StringBuffer();
                    // Looping through the array of newName and change the first letter to UpperCase
                    for (int i = 0; i < newNameArray.length; i++) {

                        // Call the function to upperCaseLetter
                        newName.append(toUpperCaseFirstLetter(newNameArray[i]));

                        // If not i not equal not length, not append space
                        if (i == newName.length()){
                            break;
                        }
                        newName.append(" ");
                    }
                    return newName.toString();

                // If it contains any digits or special character, make the user enter again
                } else {
                    System.out.println("The lead's name should not contains any digits and special characters");
                    System.out.println("Please try again");
                }



            }
        }
    }



}
