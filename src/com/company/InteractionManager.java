package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class InteractionManager extends CSVManager {

    private int latestId;
    private final DateManager dateManager;

    public InteractionManager() throws IOException {
        super("interactions.csv");
        this.latestId = getLatestId();
        dateManager = new DateManager();
    }

    @Override
    public String addEntryFromInput() throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        String interactionDate, leadId, communicationMethod, result;

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};

        String inter_id = "inter_" + this.latestId;
        this.latestId += 1;

        do {
            System.out.println("Enter interaction's date: ");
            interactionDate = dateManager.getDateFromInput();
        } while (interactionDate.isBlank() || !dateManager.isCorrectDateFormat(interactionDate));

        do {
            System.out.println("Enter interaction's lead's id: ");
            leadId = inputScanner.next();
        } while (leadId.isBlank());

        do {
            System.out.println("Enter interaction's communication method: ");
            communicationMethod = inputScanner.next();
        } while (communicationMethod.isBlank() || !contains(allowedCommunicationMethod, communicationMethod));

        do {
            System.out.println("Enter interaction's result: ");
            result = inputScanner.next();
        } while (result.isBlank() || !contains(allowedResult, result));

        return String.join(",", inter_id, interactionDate, leadId, communicationMethod, result);
    }
}
