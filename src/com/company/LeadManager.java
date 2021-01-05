package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class LeadManager extends CSVReader implements CSVWriter {

    private int latestId;
    private final DateManager dateManager;

    public LeadManager() throws IOException {
        super("leads.csv");
        this.latestId = getLatestId();
        dateManager = new DateManager();
    }

    public void addEntry() throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        String leadName, leadDob, leadGender, leadPhone, leadMail, leadAddress;

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};
        String[] allowedGender = new String[]{"true", "false"};

        String leadId = "lead_" + this.latestId;
        this.latestId += 1;

        do {
            System.out.println("Enter lead's name: ");
            leadName = inputScanner.next();
        } while (leadName.isBlank());

        do {
            System.out.println("Enter lead's dob: ");
            leadDob = dateManager.getDateFromInput();
        } while (leadDob.isBlank() || !dateManager.isCorrectDateFormat(leadDob));

        do {
            System.out.println("Enter lead's gender: ");
            leadGender = inputScanner.next();
        } while (leadGender.isBlank() || !contains(allowedGender, leadGender));

        do {
            System.out.println("Enter lead's phone number: ");
            leadPhone = inputScanner.next();
        } while (leadPhone.isBlank());

        do {
            System.out.println("Enter lead's email: ");
            leadMail = inputScanner.next();
        } while (leadMail.isBlank() || !leadMail.contains("@"));

        do {
            System.out.println("Enter lead's address: ");
            leadAddress = inputScanner.next();
        } while (leadAddress.isBlank());

        String newLine = String.join(",", leadId, leadName, leadDob, leadGender, leadPhone, leadMail, leadAddress);
        try (
                FileWriter fileWriter = new FileWriter(this.fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(newLine);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
