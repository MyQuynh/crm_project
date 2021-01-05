package finalproject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        mainMenu();
    }


    public static void mainMenu(){

        // Keeping looping the main menu until the users choose the option exit
        while (true){
            // The main menu
            System.out.println("----------------------------------------");
            System.out.println("----------------MAIN_MENU---------------");
            System.out.println("1) Managing leads");
            System.out.println("2) Managing interactions");
            System.out.println("3) Managing Reporting and Statistics");
            System.out.println("4) Exit");
            System.out.println("----------------------------------------");

            // Get and check if the option is valid
            int option = OptionCheck.optionCheck(1,4);

            // After checking the option
            // Depend on the option, the menu of lead, interaction and statistic report will display
            // If the user choose the option exit which is 4, the checkMainMenu does not change (which is 0)
            // Therefore, it will stop looping since it is a stop condition (checkMainMenu == 0)
            if (option == 1){
                // Call the lead menu method
                leadMenu();

            } else if (option == 2){
                // Call the interaction menu method
                interactionMenu();

            } else if (option == 3) {
                // Call the report and statistic menu method
                reportAndStatisticMenu();

            } else {
                // Exit the program
                System.out.println("Thank your for using the CRM program, have a nice day");
                break;
            }

        }
    }

    public static void leadMenu() {

        while (true) {
            // Creat a class LeadManager to later call function
            try {
                LeadManager leadManager = new LeadManager();
                // Displaying the menu bar of Lead
                System.out.println("----------------LEAD_MENU---------------");
                System.out.println("1) See list of leads");
                System.out.println("2) See the lead of given ID");
                System.out.println("3) Add leads");
                System.out.println("4) Delete a lead");
                System.out.println("5) Update a lead");
                System.out.println("6) Return to the main menu");
                System.out.println("----------------------------------------");

                // Check the option if it valid
                int option = OptionCheck.optionCheck(1,6);

                // Depend on the option, the user can choose: see all the list of leads, adding lead, delete lead, update lead or return to the main menu
                if (option == 1) {

                    // Call the function showing the lead that the user want
                    leadManager.showAllEntries();

                } else if (option == 2){

                    // Call the function to show all the leads
                    leadManager.showEntry();
                    //System.out.println("Choice two");

                } else if (option == 3) {

                    // Call the function adding the Lead
                    leadManager.addEntryFromInput();
                    System.out.println("choice 3");

                } else if (option == 4) {

                    // Call the function delete the lead
                    leadManager.deleteEntry();
                    System.out.println("Choice 4");

                } else if (option == 5) {

                    // Call the function updating the lead
                    leadManager.updateEntry();
                    System.out.println("Choice five");
                } else {

                    // Return to the main menu
                    break;
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public static void interactionMenu(){

        while(true){
            try {
                InteractionManager interactionManager = new InteractionManager();

                // Display the menu bar of Interactions
                System.out.println("---------------INTER_MENU---------------");
                System.out.println("1) See list of interactions");
                System.out.println("2) Add an interaction");
                System.out.println("3) Delete an interaction");
                System.out.println("4) Update an interaction");
                System.out.println("5) Return to the main menu");
                System.out.println("----------------------------------------");

                Scanner scanner = new Scanner(System.in);

                // Check the input from users if it is valid
                //int option = OptionCheck.optionCheck(1,5);
                int option = scanner.nextInt();

                // Depending on the option that the user choose, it will display the see list of all the interaction, adding an interaction, delete an interaction, update an interaction or return to the main menu
                if (option == 1){
                    // Call the functions to show all the Interactions
                    interactionManager.showAllEntries();

                } else if (option == 2){

                    // Call the function to add an interaction
                    interactionManager.addEntry();

                } else if (option == 3) {

                    // Call the function to delete an given interaction
                    interactionManager.deleteEntry();

                } else if (option == 4){

                    // Call the function to update an interaction
                    interactionManager.updateEntry();
                }
                else {
                    // Stop the while-loop and return to the main menu
                    break;
                }

            } catch (IOException | ParseException e) {
                System.out.println("Cannot file file");
            }

        }
    }

    public static void reportAndStatisticMenu(){
        ReportingAndStatistics reportingAndStatistics = new ReportingAndStatistics();

        while (true){

            // Display the menu bar of Report and Statistic
            System.out.println("----------------REPORT_MENU--------------");
            System.out.println("1) Display a summary report that contains all number of leads by ages");
            System.out.println("2) Display a summary report that contains all number of interactions by potential");
            System.out.println("3) Display a summary report that contains all number of interactions by month");
            System.out.println("4) Return to the main menu");
            System.out.println("----------------------------------------");

            // Check the option if it valid
            int option = OptionCheck.optionCheck(1, 4);
            try {
                // Depend on the option, the user can choose: see all the list of leads, adding lead, delete lead, update lead or return to the main menu
                if (option == 1) {

                    // Call the function showing all the leads
                    reportingAndStatistics.leadByAge();

                } else if (option == 2) {

                    // Call the function adding the Lead
                    reportingAndStatistics.numberOfInteraction();

                } else if (option == 3) {

                    // Call the function delete the lead
                    reportingAndStatistics.interactionPotential();

                } else {
                    // Return to the main menu
                    break;
                }
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }


        }
    }
}
