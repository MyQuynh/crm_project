package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class LeadManager extends CSVManager {

    private String fileName;
    private int latestId;
    private final DateManager dateManager;

    public LeadManager() throws IOException {
        this.fileName = "leads.csv";
        this.latestId = getLatestId();
        dateManager = new DateManager();
    }

    @Override
    public String addEntryFromInput() throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        String leadName, leadDob, leadGender, leadPhone, leadMail, leadAddress;

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};

        String leadId = "inter_" + this.latestId;
        this.latestId += 1;

        do {
            System.out.println("Enter lead's name: ");
            leadName = dateManager.getDateFromInput();
        } while (leadName.isBlank());

        do {
            System.out.println("Enter lead's dob: ");
            leadDob = dateManager.getDateFromInput();
        } while (leadDob.isBlank() || dateManager.isCorrectDateFormat(leadDob));

        do {
            System.out.println("Enter lead's gender: ");
            leadGender = inputScanner.next();
        } while (leadGender.isBlank());

        do {
            System.out.println("Enter lead's phone number: ");
            leadPhone = inputScanner.next();
        } while (leadPhone.isBlank());

        do {
            System.out.println("Enter lead's email: ");
            leadMail = inputScanner.next();
        } while (leadMail.isBlank());

        do {
            System.out.println("Enter lead's address: ");
            leadAddress = inputScanner.next();
        } while (leadAddress.isBlank());

        return String.join(",", leadId, leadName, leadDob, leadGender, leadPhone, leadMail, leadAddress);
    }
}
