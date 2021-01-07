package finalproject.interactioninput;

import finalproject.Main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InteractionLeadIDExistInput {

    public String leadIDExistInput(){

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
