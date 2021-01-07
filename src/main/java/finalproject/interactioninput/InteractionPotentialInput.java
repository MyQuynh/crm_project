package finalproject.interactioninput;

import finalproject.Main;

import java.lang.reflect.Array;
import java.util.Arrays;

public class InteractionPotentialInput {

    public String inputPotentialFromInteraction(){
        while(true){

            // Get the input from users and lower case it
            System.out.print("Please enter the potential of interaction (positive, negative, neutral): ");
            String potential = Main.scanner.next().toLowerCase();

            // Check if it was empty
            // If does notify to the user and make the user enter again
            if (potential.equals("")){
                System.out.println("The potential cannot be empty");
                System.out.println("Please try again");
            } else {
                // Creating the array with the allowing potential interaction
                String[] potentialInteraction = new String[]{"positive", "negative","neutral"};
                Arrays.sort(potentialInteraction);

                // Searching through the array if it contains the input from users,
                // If it has, return the value
                if (Arrays.binarySearch(potentialInteraction,potential) >= 0){
                    return potential;

                // else notify to the user and make them enter again
                } else {
                    System.out.println("The potential should contains positive, negative or neutral");
                    System.out.println("Please try again");
                }
            }
        }
    }
}
