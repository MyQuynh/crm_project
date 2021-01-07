package finalproject.reportandstatistic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class ReportingAndStatistics {

    // Leads by age
    public void leadByAge(){

        try {
            LeadByAge led = new LeadByAge();
            led.getDateByAge();
        } catch (ParseException | IOException e) {
            System.out.println("Error occur");
        }

    }

    // Interaction potential
    public void interactionPotential() {
        PotentialInteract poi = new PotentialInteract();
        try {
            poi.getInteractionByPotential();
        } catch (ParseException | FileNotFoundException e) {
            System.out.println("Error occur");;
        }
    }

    // Numbers of interaction
    public void numberOfInteraction() throws IOException, ParseException {
        NumberOfInteractor noi = new NumberOfInteractor();
        noi.NumberOfInteractor();
    }


}
