package com.company;

import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public abstract class CSVManager {
    protected String fileName;

    public CSVManager(String filename) {
        this.fileName = filename;
    }

    public static boolean contains(String[] stringArr, String key) {
        for (int i = 0; i < stringArr.length; i++) {
            if (stringArr[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int getLatestId() throws IOException {
        /*
        Get the latest id used in file
         */
        String lastLine = "";
        String currentLine = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));
        // check for empty file

        currentLine = bufferedReader.readLine();
        if (currentLine == null){
            return 0;
        }
        while (currentLine != null) {
            if (!currentLine.isBlank()) {
                lastLine = currentLine;
            }
            currentLine = bufferedReader.readLine();
        }

        String id = lastLine.split(",")[0];
        return Integer.parseInt(id.substring(id.length() - 3));
    }

    public void deleteEntry() throws IOException {
        File temp = new File("temp.csv");
        File currentFile = new File(this.fileName);
        PrintWriter tempWriter = new PrintWriter(new FileWriter(temp, true));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the entry to be deleted: ");
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
        if (currentFile.delete() && temp.renameTo(currentFile)) {
            System.out.println("Entry deleted");
        }
        fileScanner.close();
    }

    public void showEntry() throws IOException {
        Scanner entryIdScanner = new Scanner(System.in);
        System.out.println("Enter the id of the entry: ");
        String entryId = entryIdScanner.next();
        boolean found = false;

        Scanner fileScanner = new Scanner(new File(this.fileName));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            if (lineContent[0].equals(entryId)) {
                System.out.println(Arrays.toString(lineContent));
                found = true;
            }
        }
        if (!found) {
            System.out.println("no matching lead id found");
        }

        entryIdScanner.close();
    }

    public void showAllEntries() throws FileNotFoundException {
        System.out.println("filename: " + this.fileName);
        Scanner fileScanner = new Scanner(new File(this.fileName));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            System.out.println(Arrays.toString(lineContent));
        }
    }

    public void addEntry() throws ParseException {
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
