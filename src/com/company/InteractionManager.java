package com.company;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class InteractionManager extends CSVManager{

    private int latestId;
    private final DateManager dateManager;
    private final InputValidator inputValidator;

    public InteractionManager() throws IOException {
        super("interactions.csv");
        this.latestId = getLatestId();
        dateManager = new DateManager();
        inputValidator = new InputValidator();
    }

    public void addEntry() throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        String interactionDate, leadId, communicationMethod, result;

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};

        String interId = "inter_";
        if (this.latestId >= 100) {
            interId += this.latestId;
        } else {
            String prefix = Math.log(100) / Math.log(100 - this.latestId) >= 1 ? "0" : "00";
            interId += prefix + this.latestId;
        }
        this.latestId += 1;

        do {
            System.out.println("Enter interaction's date: ");
            interactionDate = dateManager.getDateFromInput();
        } while (interactionDate.isBlank() || !dateManager.isCorrectDateFormat(interactionDate, ""));

        if (dateManager.isCorrectDateFormat(interactionDate, "yyyy/MM/dd")) {
            interactionDate = dateManager.convertDateFormat(interactionDate, "yyyy/MM/dd");
        }

        do {
            System.out.println("Enter interaction's lead's id: ");
            leadId = inputScanner.next();
        } while (leadId.isBlank());
        if (!leadId.contains("lead_")){
            leadId = "lead_" + leadId;
        }

        do {
            System.out.println("Enter interaction's communication method: ");
            communicationMethod = inputScanner.next();
        } while (communicationMethod.isBlank()
                || !contains(allowedCommunicationMethod, communicationMethod));

        do {
            System.out.println("Enter interaction's result: ");
            result = inputScanner.next();
        } while (result.isBlank()
                || !contains(allowedResult, result));

        String newLine = String.join(",", interId, interactionDate, leadId, communicationMethod, result);
        try (
                FileWriter fileWriter = new FileWriter(this.fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(newLine);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void updateEntry() throws FileNotFoundException, ParseException {
        File temp = new File("temp.csv");
        File currentFile = new File(this.fileName);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the entry to be updated: ");
        String id = scanner.next();

        Scanner fileScanner = new Scanner(currentFile);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (!line.split(",")[0].equals(id)) {
                try (
                        FileWriter fileWriter = new FileWriter(this.fileName);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                ) {
                    addEntry();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                System.out.println("There's no matching entry with matching id.");
            }

            // delete current file
            if (currentFile.delete() && temp.renameTo(currentFile)) {
                System.out.println("entry updated");
            }
        }
    }
}