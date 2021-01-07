package finalproject;
import finalproject.CSVManager;
import finalproject.interactioninput.InteractionLeadIDExistInput;
import finalproject.interactioninput.InteractionMethodInput;
import finalproject.interactioninput.InteractionPotentialInput;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class InteractionManager extends CSVManager {

    private String interactionID;
    private Date dateOfInteraction;
    private String leadInCharge;
    private String methods;
    private String potential;

    private String fileName;
    private int latestId;
    private final DateManager dateManager = new DateManager();

    public InteractionManager(String interactionID, Date dateOfInteraction, String leadInCharge, String methods, String potential) {
        super("interactions.csv");
        this.interactionID = interactionID;
        this.dateOfInteraction = dateOfInteraction;
        this.leadInCharge = leadInCharge;
        this.methods = methods;
        this.potential = potential;
    }

    public InteractionManager() throws IOException {
        super("interactions.csv");
        this.latestId = getLatestId();
        //dateManager = new DateManager();
    }

    public String getInteractionID() {
        return interactionID;
    }

    public void setInteractionID(String interactionID) {
        this.interactionID = interactionID;
    }

    public Date getDateOfInteraction() {
        return dateOfInteraction;
    }

    public void setDateOfInteraction(Date dateOfInteraction) {
        this.dateOfInteraction = dateOfInteraction;
    }

    public String getLeadInCharge() {
        return leadInCharge;
    }

    public void setLeadInCharge(String leadInCharge) {
        this.leadInCharge = leadInCharge;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getPotential() {
        return potential;
    }

    public void setPotential(String potential) {
        this.potential = potential;
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
        Scanner fileScanner = new Scanner(new File("interactions.csv"));
        System.out.println();
        System.out.printf("%s","INTERACTION_ID");
        System.out.printf("%20s","DATE_OF_INTERACTION");
        System.out.printf("%16s","LEAD_INVOLVES");
        System.out.printf("%14s","METHODS");
        System.out.printf("%18s","POTENTIAL");
        System.out.println();

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] word = line.split(",");
            System.out.printf("%1s", word[0]);    // Get and print the leadID value
            System.out.printf("%1$25s", word[1]); // Get and print the name value
            System.out.printf("%1$16s", word[2]); // Get and print the dob value
            System.out.printf("%1$14s", word[3]); // Get and print the gender value
            System.out.printf("%1$18s", word[4]); // Get and print the phone number value
            System.out.println(); // Start from the next line
        }
        System.out.println();
    }

    @Override
    public String addEntryFromInput() throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        String interactionDate, leadId, communicationMethod, result;
        IsValid isValid = new IsValid();

        String[] allowedCommunicationMethod = new String[]{"email", "phone"};
        String[] allowedResult = new String[]{"neutral", "positive", "negative"};

        String inter_id = "inter_" + this.latestId;
        this.latestId += 1;

        do {
            System.out.println("Enter interaction's date: ");
            interactionDate = dateManager.getDateFromInput();
        } while (interactionDate.isBlank() || !dateManager.isCorrectDateFormat(interactionDate));

        // Check if the leadID is exist, if does return the LeadID, if don't, make the user enter again
        InteractionLeadIDExistInput interactionLeadIDExistInput = new InteractionLeadIDExistInput();
        leadId = interactionLeadIDExistInput.leadIDExistInput();
        //leadId = isValid.isExistLeadID();

//        do {
//            System.out.println("Enter interaction's communication method: ");
//            communicationMethod = inputScanner.next(); // TODO: Can we make is lowercase
//        } while (communicationMethod.isBlank() || !contains(allowedCommunicationMethod, communicationMethod));
        InteractionMethodInput interactionMethodInput = new InteractionMethodInput();
        communicationMethod = interactionMethodInput.interactionMethodInput();

//        do {
//            System.out.println("Enter interaction's result: ");
//            result = inputScanner.next();
//        } while (result.isBlank() || !contains(allowedResult, result));
        InteractionPotentialInput interactionPotentialInput = new InteractionPotentialInput();
        result = interactionPotentialInput.inputPotentialFromInteraction();

        return String.join(",", inter_id, interactionDate, leadId, communicationMethod, result);
    }
}