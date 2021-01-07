package finalproject.reportandstatistic;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LeadByAge {

    private String getLeadDob(String line) {
        // SPLIT LINE AND RETURN THE THIRD ELEMENT OF THE ARRAY (LEAD'S DOB)
        String[] lineContent = line.split(",");
        return lineContent[2];
    }

    private int calculateAge(String leadDob) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = simpleDateFormat.parse(leadDob);
        Date todayDate = new Date();

        long duration = todayDate.getTime() - dob.getTime();
        return (int) duration / 365;
    }

    public void getDateByAge() throws FileNotFoundException, ParseException {
        Scanner fileScanner = new Scanner(new File("leads.csv"));
        int count0_10 = 0;
        int count10_20 = 0;
        int count20_60 = 0;
        int countHigher60 = 0;

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String leadDob = getLeadDob(line);
            int leadAge = calculateAge(leadDob);

            if (leadAge <= 10) {
                count0_10++;
            } else if (leadAge <= 20) {
                count10_20++;
            } else if (leadAge <= 60) {
                count20_60++;
            } else {
                countHigher60++;
            }
        }
        System.out.println("NUMBER OF LEADS BY AGES");
        System.out.println("-----------------------");
        System.out.println("| " + String.format("%1$18s", "0-10(years old)") + " | " + String.format("%1$18s", "10-20(years old)") + " | " + String.format("%1$18s", "20-60(years old)") + " | " + String.format("%1$18s", "over 60(years old)") + " |");
        System.out.println("| " + String.format("%1$18d", count0_10) + " | " + String.format("%1$18d", count10_20) + " | " + String.format("%1$18d", count20_60) + " | " + String.format("%1$18d", countHigher60) + " |");
    }
}