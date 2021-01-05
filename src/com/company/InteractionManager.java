package com.company;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class InteractionManager extends CSVReader implements CSVWriter {

    private int latestId;
    private final DateManager dateManager;

    public InteractionManager() throws IOException {
        super("interactions.csv");
        this.latestId = getLatestId();
        dateManager = new DateManager();
    }

    public void addEntry() throws ParseException {
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

        String newLine = String.join(",", inter_id, interactionDate, leadId, communicationMethod, result);
        try (
                FileWriter fileWriter = new FileWriter(this.fileName);
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