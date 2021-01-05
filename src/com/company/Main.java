package com.company;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        LeadManager leadManager = new LeadManager();
        InteractionManager interactionManager = new InteractionManager();

        System.out.println("interaction filename: " + interactionManager.fileName);

        interactionManager.addEntry();
        interactionManager.showAllEntries();
    }
}
