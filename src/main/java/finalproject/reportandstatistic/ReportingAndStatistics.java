package finalproject.reportandstatistic;

import finalproject.Repeatable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class ReportingAndStatistics {

    Repeatable repeatable  = new Repeatable();

    // Leads by age
    public void leadByAge(){

        LeadByAge led = new LeadByAge();
        do {

            try {
                led.getDateByAge();
            } catch (ParseException | IOException e) {
                System.out.println("Error occur");
            }

        } while (repeatable.repeat());


    }

    // Interaction potential
    public void interactionPotential() {
        PotentialInteract poi = new PotentialInteract();
        do{
            try {
                poi.getInteractionByPotential();
            } catch (ParseException | FileNotFoundException e) {
                System.out.println("Error occur");
                e.printStackTrace();

            }
        } while(repeatable.repeat());

    }

    // Numbers of interaction
    public void numberOfInteraction() {
        NumberOfInteract noi = new NumberOfInteract();
        do {
            try {
                noi.getNumberOfInteract();
            } catch (ParseException | IOException e) {
                System.out.println("Error occur");
                System.out.println("Please try again");
            }

        } while (repeatable.repeat());
    }


}
