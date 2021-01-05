package finalproject;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class LeadManager extends CSVManager {

    // List of all the components of the lead
    private String leadID;
    private String leadName;
    private Date leadDob;
    private boolean leadGender;
    private String leadNumber;
    private String leadEmail;
    private String leadAddress;

    private String fileName = "leads.csv";
    private int latestId;
    private final DateManager dateManager = new DateManager();


    // Creating the constructors for Lead
    public LeadManager() throws IOException {
        super("leads.csv");
        this.latestId = getLatestId();
    }

    public LeadManager(String filename, String leadID, String leadName, Date leadDob, boolean leadGender, String leadNumber, String leadEmail, String leadAddress, String fileName, int latestId) {
        super(filename);
        this.leadID = leadID;
        this.leadName = leadName;
        this.leadDob = leadDob;
        this.leadGender = leadGender;
        this.leadNumber = leadNumber;
        this.leadEmail = leadEmail;
        this.leadAddress = leadAddress;
        this.fileName = fileName;
        this.latestId = latestId;
    }

    public String getLeadID() {
        return leadID;
    }

    public void setLeadID(String leadID) {
        this.leadID = leadID;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public Date getLeadDob() {
        return leadDob;
    }

    public void setLeadDob(Date leadDob) {
        this.leadDob = leadDob;
    }

    public boolean isLeadGender() {
        return leadGender;
    }

    public void setLeadGender(boolean leadGender) {
        this.leadGender = leadGender;
    }

    public String getLeadNumber() {
        return leadNumber;
    }

    public void setLeadNumber(String leadNumber) {
        this.leadNumber = leadNumber;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getLeadAddress() {
        return leadAddress;
    }

    public void setLeadAddress(String leadAddress) {
        this.leadAddress = leadAddress;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int getLatestId() {
        return latestId;
    }

    public void setLatestId(int latestId) {
        this.latestId = latestId;
    }

    public DateManager getDateManager() {
        return dateManager;
    }

    @Override
    public void showAllEntries() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(this.fileName));
        System.out.printf("%s","LEAD_ID");
        System.out.printf("%15s","LEAD_NAME");
        System.out.printf("%16s","DATE_OF_BIRTH");
        System.out.printf("%14s","GENDER");
        System.out.printf("%18s","PHONE_NUMBER");
        System.out.printf("%25s","EMAIL");
        System.out.printf("%25s","ADDRESS");

        System.out.println();

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] word = line.split(",");
            System.out.printf("%1s", word[0]);    // Get and print the leadID value
            System.out.printf("%1$14s", word[1]); // Get and print the name value
            System.out.printf("%1$16s", word[2]); // Get and print the dob value
            System.out.printf("%1$14s", word[3]); // Get and print the gender value
            System.out.printf("%1$18s", word[4]); // Get and print the phone number value
            System.out.printf("%1$25s", word[5]); // Get and print the email value
            System.out.printf("%1$25s", word[6]); // Get and print the address value
            System.out.println(""); // Start from the next line
        }
        System.out.println();
    }

    @Override
    public String addEntryFromInput() throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        IsValid isValid = new IsValid();
        String leadName, leadDob, leadPhone, leadMail, leadAddress;
        boolean leadGender;

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};
        String[] allowedGender = new String[]{"true", "false"};

        String leadId = "lead_" + this.latestId;
        this.latestId += 1;

//        do {
//            System.out.println("Enter lead's name: ");
//            leadName = inputScanner.next();
//        } while (leadName.isBlank());
        System.out.println("Enter lead's name: ");
        //leadName = isValid.isValidName();

        // Get the input name from the user and check if it valid
        while(true){
            leadName = inputScanner.nextLine();
            if (isValid.checkNameValid(leadName)){
                 break;
            } else {
                System.out.println("Invalid name");
                System.out.println("Please try again");
            }
        }

        do {
            System.out.println("Enter lead's dob: ");
            leadDob = dateManager.getDateFromInput();
        } while (leadDob.isBlank() || !dateManager.isCorrectDateFormat(leadDob));

//        do {
//            System.out.println("Enter lead's gender: (true for male and false for female)");
//            leadGender = inputScanner.next();
//        } while (leadGender.isBlank() || !contains(allowedGender, leadGender));

        // Get the input gender and check if it valid
        leadGender = isValid.isValidGender();


//        do {
//            System.out.println("Enter lead's phone number: ");
//            leadPhone = inputScanner.next();
//        } while (leadPhone.isBlank());

        // Get the input phone number and check if it valid
        leadPhone = isValid.isValidPhoneNumber();

//        do {
//            System.out.println("Enter lead's email: ");
//            leadMail = inputScanner.next();
//        } while (leadMail.isBlank() || !isValid.isValidEmail(leadMail));

        // Get the input email from the user and check the validation
        leadMail = isValid.isValidEmail();

//        do {
//            System.out.println("Enter lead's address: ");
//            leadAddress = inputScanner.next();
//        } while (leadAddress.isBlank());

        // Get the input address from the user and check if it is valid
        leadAddress = isValid.isValidAddress();

        return String.join(",", leadId, leadName, leadDob, String.valueOf(leadGender), leadPhone, leadMail, leadAddress);
    }

}