package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class InteractionManager {
    int latestId;

    public InteractionManager() throws IOException {
        latestId = getLatestId();
    }

    public static int getLatestId() throws IOException {
        String lastLine = "";
        String currentLine = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader("leads.csv"));
        while ((currentLine = bufferedReader.readLine()) != null) {
//            System.out.println(currentLine);
            lastLine = currentLine;
        }
        String id = lastLine.split(",")[0];
        return Integer.parseInt(id.substring(id.length() - 5));
    }

    public static String getInteractionFromInput(){
        Scanner inputScanner = new Scanner(System.in);
        String name, dobString, email, address, phone;
        boolean gender;

        do{
            System.out.println("Enter lead's name: ");
            name = inputScanner.next();
        }while(name.isBlank());

        do{
            System.out.println("Enter lead's email: ");
            email = inputScanner.next();
        }while(email.isBlank());

        do{
            System.out.println("Enter lead's phone: ");
            phone = inputScanner.next();
        }while(phone.isBlank());

        do{
            System.out.println("Enter lead's name: ");
            address = inputScanner.next();
        }while(address.isBlank());

        System.out.println("Enter lead's gender: ");
        gender = inputScanner.nextBoolean();

        return String.join(",", name, email, address, phone, Boolean.toString(gender));
    }

    public static void updateInteraction() throws IOException {
        File temp = new File("temp.csv");
        File currentFile = new File("interactions.csv");
        PrintWriter tempWriter = new PrintWriter(new FileWriter(temp, true));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the interaction to be updated: ");
        String id = scanner.next();

        Scanner fileScanner = new Scanner(currentFile);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (!line.split(",")[0].equals(id)) {
                tempWriter.println(line);
            }
            else{
                String newInteractionInfo = getInteractionFromInput();
                tempWriter.println(line.split(", ")[0] + ", " + newInteractionInfo);
            }
        }
        tempWriter.close();

        // delete current file
        if(currentFile.delete() && temp.renameTo(currentFile)){
            System.out.println("interaction updated");
        }
    }

    public static void deleteInteraction() throws IOException {
        /*

        */
        File temp = new File("temp.csv");
        File currentFile = new File("interactions.csv");
        PrintWriter tempWriter = new PrintWriter(new FileWriter(temp, true));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the interaction to be deleted: ");
        String id = scanner.next();

        Scanner fileScanner = new Scanner(currentFile);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (!line.split(",")[0].equals(id)) {
                tempWriter.println(line);
            }
        }
        tempWriter.close();

        // delete current file
        if(currentFile.delete() && temp.renameTo(currentFile)){
            System.out.println("interaction deleted");
        }
    }

    public static void addInteraction() throws IOException {
        // TODO: add date from string

        int latestId = getLatestId();
        String id = "inter_" + Integer.toString(latestId);
        System.out.println("latest id: " + id);

        // write input to file
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("interactions.csv", true));

            String newInteractionInfo = getInteractionFromInput();
            pw.println(id + ", " + newInteractionInfo);

            pw.close();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }


    public static void showInteraction() throws IOException {
        Scanner leadIdScanner = new Scanner(System.in);
        System.out.println("Enter the id of the interaction: ");
        String leadId = leadIdScanner.next();
        boolean found = false;

        Scanner fileScanner = new Scanner(new File("interactions.csv"));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            if (lineContent[0].equals(leadId)) {
                System.out.println(Arrays.toString(lineContent));
                found = true;
            }
        }
        if (!found) {
            System.out.println("no matching interaction id found");
        }
    }

    public static void showAllInteractions() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("interactions.csv"));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            System.out.println(Arrays.toString(lineContent));
        }
    }
}
