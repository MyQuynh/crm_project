package finalproject;
import java.io.*;
import java.text.*;
import java.util.*;

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

    public int getLatestIdTest() throws IOException {
        /*
        Get the latest id used in file
         */
        String lastLine = "";
        String currentLine = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));

        // check for empty file
        if (bufferedReader.readLine() == null){
            System.out.println("Not found");
            return 0;
        }
        while ((currentLine = bufferedReader.readLine()) != null) {
            lastLine = currentLine;
        }
        String id = (lastLine.split(","))[0];
        bufferedReader.close();
        return Integer.parseInt(id.substring(id.length() - 3));
    }


    public abstract String addEntryFromInput() throws ParseException;

    public void updateEntry() throws IOException, ParseException {
        InputIdExist inputIdExist = new InputIdExist();
        File temp = new File("temp.csv");

        File currentFile = new File(this.fileName);

        PrintWriter tempWriter = new PrintWriter(new FileWriter(temp));

        //System.out.println("Enter the id of the entry to be updated: ");
        String id = inputIdExist.inputIdExist(new File(this.fileName));
        String lineChanging = "";
        String newEntryInfo = "";

        Scanner fileScanner = new Scanner(currentFile);
        Main.scanner.nextLine();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (!line.split(",")[0].equals(id)) {
                tempWriter.println(line);
            } else {
                newEntryInfo = addEntryFromInput();
                lineChanging = line;
                tempWriter.println(line.split(",")[0] + "," + newEntryInfo);
            }
        }
        tempWriter.close();
        fileScanner.close();

        if (currentFile.delete() && temp.renameTo(currentFile)) {
            System.out.println("Entry updated successfully!!!");
            System.out.println("From: " +lineChanging);
            System.out.println("To: "+id+","+newEntryInfo);
        }

        // Delete the temp file
        temp.deleteOnExit();
    }

    public void deleteEntry() throws IOException {
        Repeatable repeatable = new Repeatable();
        InputIdExist inputIdExist = new InputIdExist();

        do {
            File temp = new File("temp.csv");
            File currentFile = new File(this.fileName);
            PrintWriter tempWriter = new PrintWriter(new FileWriter(temp, true));

            String id = inputIdExist.inputIdExist(new File(this.fileName));
            String lineDelete = "";

            Scanner fileScanner = new Scanner(currentFile);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                if (!line.split(",")[0].equals(id)) {
                    tempWriter.println(line);
                } else {
                    lineDelete = line;
                }
            }
            tempWriter.close();
            fileScanner.close();

            if (currentFile.delete() && temp.renameTo(currentFile)) {
                System.out.println();
                System.out.println("Entry deleted successfully");
                System.out.println("Information delete: " + lineDelete);
                System.out.println();
            }
            temp.deleteOnExit();
        } while (repeatable.repeat());
        System.out.println("Ending the delete entry ");
        System.out.println("Heading back...");

    }

    public abstract void addEntry() throws IOException;


    public void showEntry() throws IOException {
        //Scanner entryIdScanner = new Scanner(System.in);
        InputIdExist inputIdExist = new InputIdExist();
        Repeatable repeatable = new Repeatable();
        do {
            String entryId = inputIdExist.inputIdExist(new File(this.fileName));

            Scanner fileScanner = new Scanner(new File(this.fileName));
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] lineContent = line.split(",");
                if (lineContent[0].equals(entryId)) {
                    System.out.println(Arrays.toString(lineContent));
                    //found = true;
                }
            }
            fileScanner.close();

        } while (repeatable.repeat());
    }

    public void showAllEntries() throws FileNotFoundException {
        System.out.println("filename: " + this.fileName);
        Scanner fileScanner = new Scanner(new File(this.fileName));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            System.out.println(Arrays.toString(lineContent));
        }
        fileScanner.close();
    }
}