package com.company;

import com.company.ReportsAndStatistics.InteractPotential;
import com.company.ReportsAndStatistics.LeadByAge;
import com.company.ReportsAndStatistics.NumberOfInteractorByMonth;

import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    // Creating the global scanner so that preventing the No such Element Exception
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
            int option = optionCheck(1,4);

            // After checking the option
            // Depend on the option, the menu of lead, interaction and statistic report will display
            // If the user choose the option exit which is 4, the checkMainMenu does not change (which is 0)
            // Therefore, it will stop looping since it is a stop condition (checkMainMenu == 0)
            if (option == 1){
                leadMenu();
            } else if (option == 2){
                interactionMenu();
            } else if (option == 3) {
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
                int option = optionCheck(1,6);
                if (option == 1) {
                    leadManager.showAllEntries();
                } else if (option == 2){
                    leadManager.showEntry();
                } else if (option == 3) {
                    leadManager.addEntry();
                } else if (option == 4) {
                    leadManager.deleteEntry();
                } else if (option == 5) {
                    leadManager.updateEntry();
                } else {
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
                int option = optionCheck(1,5);

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
        LeadByAge leadByAge = new LeadByAge();
        InteractPotential interactPotential = new InteractPotential();
        NumberOfInteractorByMonth numberOfInteractor = new NumberOfInteractorByMonth();
        while (true){
            System.out.println("----------------REPORT_MENU--------------");
            System.out.println("1) Display a summary report that contains all number of leads by ages");
            System.out.println("2) Display a summary report that contains all number of interactions by potential");
            System.out.println("3) Display a summary report that contains all number of interactions by month");
            System.out.println("4) Return to the main menu");
            System.out.println("----------------------------------------");

            // Check the option if it valid
            int option = optionCheck(1, 4);
            try {
                if (option == 1) {
                    leadByAge.getDateByAge();
                } else if (option == 2) {
                    numberOfInteractor.getNumberOfInteractor();
                } else if (option == 3) {
                    interactPotential.getInteractionByPotential();
                } else {
                    // Return to the main menu
                    break;
                }
            } catch (ParseException | IOException e) {
                System.out.println("The file was caught the problems");;
            }
        }
    }

    public static int optionCheck(int min, int max){
        int option = 0;
        while (true) {
            try {
                System.out.print("Please enter your option: ");
                option = Main.scanner.nextInt();
                if (option < min || option > max) {
                    System.out.println("The option has to be between "+ min+" and "+max);
                    System.out.println("Please try again");
                } else if (String.valueOf(option).equalsIgnoreCase("")){
                    System.out.println("The option cannot be empty");
                    System.out.println("Please try again");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                Main.scanner.nextLine();
                System.out.println("Please enter only number");
                System.out.println("Please try again");
            }
        }
        return option;
    }
}
