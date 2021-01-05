package finalproject;

import java.io.IOException;
import java.text.ParseException;

public class ReportingAndStatistics {

    // Leads by age
    public void leadByAge(){

        try {
            LeadByAge led = new LeadByAge();
            led.LeadByAge();
        } catch (ParseException | IOException e) {
            System.out.println("Error");
        }

    }

    // Interaction potential
    public void interactionPotential() throws IOException, ParseException {
        PotentialInteract poi = new PotentialInteract();
        poi.PotentialInteract();
    }

    // Numbers of interaction
    public void numberOfInteraction() throws IOException, ParseException {
        NumberOfInteractor noi = new NumberOfInteractor();
        noi.NumberOfInteractor();
    }


}
