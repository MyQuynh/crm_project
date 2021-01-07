package com.company;

import com.company.ReportsAndStatistics.Report;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;


public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void mainMenu() throws IOException, ParseException {
        LeadManager leadManager = new LeadManager();
        InteractionManager interactionManager = new InteractionManager();
        Report report = new Report();
        int userChoice;
        do {
            System.out.println("Enter an option" +
                    "\n1. Modify interactions list" +
                    "\n2. Modify leads list" +
                    "\n3. View reports" +
                    "\n4. Quit");
            userChoice = scanner.nextInt();
        } while (1 > userChoice || userChoice > 4);
        if (userChoice == 1) {
            interactionMenu(interactionManager);
        } else if (userChoice == 2) {
            leadMenu(leadManager);
        } else if (userChoice == 3) {
            reportMenu(report);
        } else {
            System.out.println("Quitting.");
        }
    }

    public static void leadMenu(LeadManager leadManager) throws IOException, ParseException {
        int userChoice;
        do {
            System.out.println("Enter an option" +
                    "\n1. Show all entries" +
                    "\n2. Add entry" +
                    "\n3. Update entry" +
                    "\n4. Delete entry" +
                    "\n5. Go back to main menu" +
                    "\n6. Quit");
            userChoice = scanner.nextInt();
        } while (1 > userChoice || userChoice > 6);
        if (userChoice == 1) {
            leadManager.showAllEntries();
            mainMenu();
        } else if (userChoice == 2) {
            leadManager.addEntry();
            mainMenu();
        } else if (userChoice == 3) {
            leadManager.updateEntry();
            mainMenu();
        } else if (userChoice == 4) {
            leadManager.deleteEntry();
            mainMenu();
        } else if (userChoice == 5) {
            mainMenu();
        } else {
            System.out.println("Quitting.");
        }
    }

    public static void interactionMenu(InteractionManager interactionManager) throws IOException, ParseException {
        int userChoice;
        do {
            System.out.println("Enter an option" +
                    "\n1. Show all entries" +
                    "\n2. Add entry" +
                    "\n3. Update entry" +
                    "\n4. Delete entry" +
                    "\n5. Go back to main menu" +
                    "\n6. Quit");
            userChoice = scanner.nextInt();
        } while (1 > userChoice || userChoice > 6);
        if (userChoice == 1) {
            interactionManager.showAllEntries();
            mainMenu();
        } else if (userChoice == 2) {
            interactionManager.addEntry();
            mainMenu();
        } else if (userChoice == 3) {
            interactionManager.updateEntry();
            mainMenu();
        } else if (userChoice == 4) {
            interactionManager.deleteEntry();
            mainMenu();
        } else if (userChoice == 5) {
            mainMenu();
        } else {
            System.out.println("Quitting.");
        }
    }

    public static void reportMenu(Report report) throws IOException, ParseException {
        int userChoice;
        do {
            System.out.println("Enter an option" +
                    "\n1. Show all leads by age" +
                    "\n2. Show interaction by potentials" +
                    "\n3. Show interactions by month" +
                    "\n4. Go back to main menu" +
                    "\n5. Quit");
            userChoice = scanner.nextInt();
        } while (1 > userChoice || userChoice > 5);
        if (userChoice == 1){
            report.getleadByAge();
            mainMenu();
        }
        else if (userChoice == 2){
            report.getInteractionPotential();
            mainMenu();
        }
        else if (userChoice == 3){
            report.getNumberOfInteraction();
            mainMenu();
        }
        else if (userChoice == 4){
            mainMenu();
        }
        else{
            System.out.println("Quitting.");
        }
    }


    public static void main(String[] args) throws IOException, ParseException {
        mainMenu();
    }
}
