package finalproject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public abstract class CSVManager{
    protected String fileName;

    public CSVManager(String filename){
        this.fileName = filename;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static boolean contains(String[] stringArr, String key){
        for (int i = 0; i < stringArr.length; i++) {
            if (stringArr[i].equals(key)){
                return true;
            }
        }
        return false;
    }

    public int getLatestId() throws IOException {
        String lastLine = "";
        String currentLine = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));

        // check for empty file
        if (bufferedReader.readLine() == null){
            return 0;
        }

        while ((currentLine = bufferedReader.readLine()) != null) {
            // System.out.println(currentLine);
            lastLine = currentLine;
        }
        String id = lastLine.split(",")[0];

        bufferedReader.close();
        return Integer.parseInt(id.substring(id.length() - 3));
    }

    public abstract String addEntryFromInput() throws ParseException;

    public void updateEntry() throws IOException, ParseException {
        File temp = new File("temp.csv");
        System.out.println(this.getFileName());

        File currentFile = new File(this.fileName);

        PrintWriter tempWriter = new PrintWriter(new FileWriter(temp, true));

        // Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the entry to be updated: ");
        String id = Main.scanner.next();

        Scanner fileScanner = new Scanner(currentFile);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (!line.split(",")[0].equals(id)) {
                tempWriter.println(line);
            } else {
                String newEntryInfo = addEntryFromInput();
                tempWriter.println(line.split(", ")[0] + ", " + newEntryInfo);
            }
        }
        tempWriter.close();

        // delete current file
        if (currentFile.delete() && temp.renameTo(currentFile)) {
            System.out.println("entry updated");
        }
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
        currentFile.deleteOnExit();

        // Create the new file have the same name as current file
        temp.renameTo(new File(this.fileName));

        // Delete the temp file

        temp.deleteOnExit();
//        if (currentFile.delete() && temp.renameTo(currentFile)) {
//            System.out.println("Entry deleted");
//        }

        scanner.close();
        fileScanner.close();
    }

    public void addEntry() throws IOException {
        // TODO: add date from string

        int latestId = getLatestId();
        String id = "lead_" + Integer.toString(latestId);
        System.out.println("latest id: " + id);

        // write input to file
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, true));

            String newLeadInfo = addEntryFromInput();
            pw.println(id + ", " + newLeadInfo);

            pw.close();
        } catch (IOException | ParseException ioException) {
            System.err.println(ioException.getMessage());
        }
    }

    public void showEntry() throws IOException {
        //Scanner entryIdScanner = new Scanner(System.in);
        System.out.println("Enter the id of the entry: ");
        String entryId = Main.scanner.next();
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

        //entryIdScanner.close();
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
}