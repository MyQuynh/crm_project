package com.company;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        LeadManager leadManager = new LeadManager();
        InteractionManager interactionManager = new InteractionManager();

        // lead's functions
        // add lead
        leadManager.addEntryFromInput();

        // show all lead
        leadManager.showAllEntries();

        // update lead
        leadManager.updateEntry();

        // remove lead
        leadManager.deleteEntry();
        leadManager.showAllEntries();

        // interaction's functions
        // add interaction
        interactionManager.addEntryFromInput();

        // show all interactions
        interactionManager.showAllEntries();

        // update interaction
        interactionManager.updateEntry();

        // remove interaction
        interactionManager.deleteEntry();
        interactionManager.showAllEntries();

        // report and statistic functions
    }
}
