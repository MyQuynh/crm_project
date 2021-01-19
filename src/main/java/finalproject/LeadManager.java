package finalproject;

import finalproject.leadinput.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    //private String fileName = "leads.csv";
    private int latestId;
    private final DateManager dateManager = new DateManager();


    // Creating the constructors for Lead
    public LeadManager() throws IOException {
        super("leads.csv");
        this.latestId = getLatestIdTest();
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
            System.out.println(); // Start from the next line
        }
        System.out.println();
    }

    @Override
    public void addEntry() throws IOException {
        // TODO: add date from string
        Repeatable repeatable = new Repeatable();

        do {

            String leadId = "lead_";
            this.latestId += 1;

            if (this.latestId >= 100) {
                leadId += this.latestId;
            } else {
                // ADD APPROPRIATE AMOUNT OF PREFIX 0s IF ID < 100
                String prefix = Math.log(100) / Math.log(100 - this.latestId) >= 1 ? "0" : "00";
                leadId += prefix + this.latestId;
            }

            // write input to file
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, true));

                String newLeadInfo = this.addEntryFromInput();
                pw.println(leadId + "," + newLeadInfo);
                System.out.println("Successfully adding the new lead");
                System.out.println("New lead information: " + leadId + "," + newLeadInfo);
                System.out.println();

                pw.close();
            } catch (IOException | ParseException ioException) {
                System.err.println(ioException.getMessage());
            }

        } while (repeatable.repeat());
        System.out.println("Ending the adding section.");
        System.out.println("Go back to the Lead Menu....");
        System.out.println();

    }

    @Override
    public String addEntryFromInput() throws ParseException {
        String leadName, leadDob, leadPhone, leadMail, leadAddress;
        boolean leadGender;

        // Get the input name from the user and check if it valid
        LeadNameInput leadNameInput = new LeadNameInput();
        leadName = leadNameInput.leadNameInput();
        System.out.println();

        do {
            System.out.println("Enter lead's dob: ");
            leadDob = dateManager.getDateFromInput();
            //leadDob = dateManager.convertDateFormat(leadDob,"yyyy-MM-dd");
        } while (leadDob.isBlank() || !dateManager.isCorrectDateFormat(leadDob, ""));
        leadDob = dateManager.convertDateFormat(leadDob,"yyyy-MM-dd");
        System.out.println();

        // Get the input gender and check if it valid
        LeadGenderInput leadGenderInput = new LeadGenderInput();
        leadGender = leadGenderInput.leadGenderInput();
        System.out.println();

        // Get the input phone number and check if it valid
        LeadPhoneNumberInput leadPhoneNumberInput = new LeadPhoneNumberInput();
        leadPhone = leadPhoneNumberInput.leadPhoneNumberInput();
        System.out.println();

        // Get the input email from the user and check the validation
        LeadEmailInput leadEmailInput = new LeadEmailInput();
        leadMail = leadEmailInput.leadEmailInput();
        System.out.println();

        // Get the input address from the user and check if it is valid
        LeadAddressInput leadAddressInput = new LeadAddressInput();
        leadAddress = leadAddressInput.leadAddressInput();
        System.out.println();

        return String.join(",", leadName, leadDob, String.valueOf(leadGender), leadPhone, leadMail, leadAddress);
    }

}