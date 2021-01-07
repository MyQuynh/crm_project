package finalproject;

import finalproject.leadinput.*;
import jdk.swing.interop.SwingInterOpUtils;

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

//    @Override
//    public String getFileName() {
//        return fileName;
//    }
//
//    @Override
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }

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
            System.out.println(); // Start from the next line
        }
        System.out.println();
    }

    @Override
    public void addEntry() throws IOException {
        // TODO: add date from string

//        int latestId = this.getLatestId();
//        System.out.println(latestId);
//        String id = "lead_" + latestId;
//        System.out.println("latest id: " + id);
        Repeatable repeatable = new Repeatable();

        do {

            String leadId = "lead_";
            this.latestId += 1;

            if (this.latestId >= 100) {
                leadId += this.latestId;
            } else {
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
        //Scanner inputScanner = new Scanner(System.in);
        //IsValid isValid = new IsValid();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String leadName, leadDob, leadPhone, leadMail, leadAddress;
        boolean leadGender;

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};
        String[] allowedGender = new String[]{"true", "false"};





//        do {
//            System.out.println("Enter lead's name: ");
//            leadName = inputScanner.next();
//        } while (leadName.isBlank());
        //System.out.println("Enter lead's name: ");
        //leadName = isValid.isValidName();

        // Get the input name from the user and check if it valid
        LeadNameInput leadNameInput = new LeadNameInput();
        leadName = leadNameInput.leadNameInput();
        System.out.println();

//        while(true){
//            leadName = inputScanner.nextLine();
//            if (isValid.checkNameValid(leadName)){
//                 break;
//            } else {
//                System.out.println("Invalid name");
//                System.out.println("Please try again");
//            }
//        }

        do {
            System.out.println("Enter lead's dob: ");
            leadDob = dateManager.getDateFromInput();
            //leadDob = dateManager.convertDateFormat(leadDob,"yyyy-MM-dd");
        } while (leadDob.isBlank() || !dateManager.isCorrectDateFormat(leadDob,""));
        leadDob = dateManager.convertDateFormat(leadDob,"yyyy-MM-dd");
        System.out.println();

//        do {
//            System.out.println("Enter lead's gender: (true for male and false for female)");
//            leadGender = inputScanner.next();
//        } while (leadGender.isBlank() || !contains(allowedGender, leadGender));

        // Get the input gender and check if it valid
        LeadGenderInput leadGenderInput = new LeadGenderInput();
        leadGender = leadGenderInput.leadGenderInput();
        System.out.println();
        //leadGender = isValid.isValidGender();


//        do {
//            System.out.println("Enter lead's phone number: ");
//            leadPhone = inputScanner.next();
//        } while (leadPhone.isBlank());

        // Get the input phone number and check if it valid
        LeadPhoneNumberInput leadPhoneNumberInput = new LeadPhoneNumberInput();
        leadPhone = leadPhoneNumberInput.leadPhoneNumberInput();
        System.out.println();
        //leadPhone = isValid.isValidPhoneNumber();

//        do {
//            System.out.println("Enter lead's email: ");
//            leadMail = inputScanner.next();
//        } while (leadMail.isBlank() || !isValid.isValidEmail(leadMail));

        // Get the input email from the user and check the validation
        LeadEmailInput leadEmailInput = new LeadEmailInput();
        leadMail = leadEmailInput.leadEmailInput();
        System.out.println();
        //leadMail = isValid.isValidEmail();

//        do {
//            System.out.println("Enter lead's address: ");
//            leadAddress = inputScanner.next();
//        } while (leadAddress.isBlank());

        // Get the input address from the user and check if it is valid
        LeadAddressInput leadAddressInput = new LeadAddressInput();
        leadAddress = leadAddressInput.leadAddressInput();
        System.out.println();
        //leadAddress = isValid.isValidAddress();
        //System.out.println(this.getFileName());
        //System.out.println(this.latestId);
        //System.out.println(leadName +leadDob +String.valueOf(leadGender) + leadPhone + leadMail + leadAddress);
        return String.join(",", leadName, leadDob, String.valueOf(leadGender), leadPhone, leadMail, leadAddress);
    }

}