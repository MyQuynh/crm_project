package com.company;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class LeadManager extends CSVManager{

    private int latestId;
    private final DateManager dateManager;
    private final InputValidator inputValidator;

    public LeadManager() throws IOException {
        super("leads.csv");
        this.latestId = getLatestId();
        dateManager = new DateManager();
        inputValidator = new InputValidator();
    }

    public void addEntry() throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        String leadName, leadDob, leadGender, leadPhone, leadMail, leadAddress;

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};
        String[] allowedGender = new String[]{"true", "false"};

        String leadId = "lead_";
        this.latestId += 1;
        if (this.latestId >= 100) {
            leadId += this.latestId;
        } else {
            String prefix = Math.log(this.latestId) / Math.log(10) >= 1 ? "0" : "00";
            leadId += prefix + this.latestId;
        }
        do {
            System.out.println("Enter lead's name: ");
            leadName = inputScanner.next();
        } while (leadName.isBlank() || inputValidator.isValidPattern(leadName, 3));

        do {
            System.out.println("Enter lead's dob: ");
            leadDob = dateManager.getDateFromInput();
        } while (leadDob.isBlank() || !dateManager.isCorrectDateFormat(leadDob, ""));

        if(dateManager.isCorrectDateFormat(leadDob, "yyyy/MM/dd")){
            leadDob = dateManager.convertDateFormat(leadDob, "yyyy/MM/dd");
        }

        do {
            System.out.println("Enter lead's gender: ");
            leadGender = inputScanner.next();
        } while (leadGender.isBlank() || !contains(allowedGender, leadGender));

        do {
            System.out.println("Enter lead's phone number: ");
            leadPhone = inputScanner.next();
        } while (leadPhone.isBlank() || inputValidator.isValidPattern(leadPhone, 1));

        do {
            System.out.println("Enter lead's email: ");
            leadMail = inputScanner.next();
        } while (leadMail.isBlank() || inputValidator.isValidPattern(leadMail, 0));

        do {
            System.out.println("Enter lead's address: ");
            leadAddress = inputScanner.next();
        } while (leadAddress.isBlank() || inputValidator.isValidPattern(leadAddress, 2));

        String newLine = String.join(",", leadId, leadName, leadDob, leadGender, leadPhone, leadMail, leadAddress);
        try (
                FileWriter fileWriter = new FileWriter(this.fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(newLine);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
