package com.company.ReportsAndStatistics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Report {
    LeadByAge leadByAge = new LeadByAge();
    InteractPotential interactPotential = new InteractPotential();
    NumberOfInteractor numberOfInteractor = new NumberOfInteractor();

    public void getLeadByAge() throws FileNotFoundException, ParseException {
        leadByAge.getDateByAge();
    };

    public void getInteractionPotential() throws ParseException, FileNotFoundException {
        interactPotential.getInteractionByPotential();
    };

    public void getNumberOfInteraction() throws IOException, ParseException {
        numberOfInteractor.getNumberOfInteractor();
    };
}
