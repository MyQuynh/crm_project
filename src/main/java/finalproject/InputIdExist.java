package finalproject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputIdExist {

    public String inputIdExist(File file) {
        // Creating the array to store exist LeadID
        ArrayList<String> listID = new ArrayList<>();

        try {
            // Reading through the file
            FileReader fileWriter = new FileReader(file);
            Scanner scannerFile = new Scanner(fileWriter);


            // Looping through the file and stop till the end of the file
            while (scannerFile.hasNext()){

                // Split the line into word array and get the leadID store into the listLead array
                String[] word = scannerFile.nextLine().split(",");
                listID.add(word[0]);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot read the file");
        }

        // Looping until the users enter the valid LeadID
        while(true){

            // Get the input from users
            System.out.println("Available list of ID:");
            System.out.println(Arrays.toString(listID.toArray()));
            System.out.print("Enter the valuable ID from the list above: ");
            String leadID = Main.scanner.nextLine();

            // If the input is empty, make the user enter again
            if (leadID.equals("")){
                System.out.println("The id cannot be empty");
                System.out.println("Please try again");
            } else {

                // Looping through the listLead to see if it match th input from users
                for (int i = 0; i < listID.size(); i++) {

                    // If it match, return the leadID
                    if(leadID.equals(listID.get(i))){
                        return leadID;
                    }
                }
                // If does not, make the user enter again
                System.out.println("The ID is not in the system\nPlease try again");
                System.out.println();

            }


        }

    }

}
