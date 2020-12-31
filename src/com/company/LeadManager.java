package com.company;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.Arrays;

public class LeadManager {

    int latestId;

    public LeadManager() throws IOException {
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
        return Integer.parseInt(id.substring(id.length() - 3));
    }

    public static String getLeadFromInput(){
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

    public static void updateLead() throws IOException {
        File temp = new File("temp.csv");
        File currentFile = new File("leads.csv");
        PrintWriter tempWriter = new PrintWriter(new FileWriter(temp, true));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the lead to be updated: ");
        String id = scanner.next();

        Scanner fileScanner = new Scanner(currentFile);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (!line.split(",")[0].equals(id)) {
                tempWriter.println(line);
            }
            else{
                String newLeadInfo = getLeadFromInput();
                tempWriter.println(line.split(", ")[0] + ", " + newLeadInfo);
            }
        }
        tempWriter.close();

        // delete current file
        if(currentFile.delete() && temp.renameTo(currentFile)){
            System.out.println("lead updated");
        }
    }

    public static void deleteLead() throws IOException {
        File temp = new File("temp.csv");
        File currentFile = new File("leads.csv");
        PrintWriter tempWriter = new PrintWriter(new FileWriter(temp, true));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the lead to be deleted: ");
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
            System.out.println("lead deleted");
        }
    }

    public static void addLead() throws IOException {
        // TODO: add date from string

        int latestId = getLatestId();
        String id = "lead_" + Integer.toString(latestId);
        System.out.println("latest id: " + id);

        // write input to file
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("leads.csv", true));

            String newLeadInfo = getLeadFromInput();
            pw.println(id + ", " + newLeadInfo);

            pw.close();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }


    public static void showLead() throws IOException {
        Scanner leadIdScanner = new Scanner(System.in);
        System.out.println("Enter the id of the lead: ");
        String leadId = leadIdScanner.next();
        boolean found = false;

        Scanner fileScanner = new Scanner(new File("leads.csv"));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            if (lineContent[0].equals(leadId)) {
                System.out.println(Arrays.toString(lineContent));
                found = true;
            }
        }
        if (!found) {
            System.out.println("no matching lead id found");
        }
    }

    public static void showAllLeads() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("leads.csv"));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            System.out.println(Arrays.toString(lineContent));
        }
    }

}


