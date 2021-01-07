package finalproject.reportandstatistic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfInteractor {

    public void NumberOfInteractor() throws ParseException, IOException {
        BufferedReader file = new BufferedReader(new FileReader("interactions.csv"));
        String line;
        Pattern p = Pattern.compile("(.+?),(.+?),(.+?),(.+?),(.+?)");

        // Check the option if it valid
        System.out.println("Please choose one of the option below");
        System.out.println("1.MM-dd-yyyy");
        System.out.println("2.dd-MM-yyyy");
        System.out.println("3.yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        SimpleDateFormat dob1 = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat dob2 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dob3 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat inputFormatUser = new SimpleDateFormat();

        SimpleDateFormat readDOB = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat printDOB = new SimpleDateFormat("MMM dd yyyy");
        SimpleDateFormat printDOB1 = new SimpleDateFormat("MMM yyyy");



        if (option == 1){
            inputFormatUser = dob1;
        } else if (option == 2){
            inputFormatUser = dob2;
        } else {
            inputFormatUser = dob3;
        }

        System.out.println("Please enter the start date");
        String dateS = scanner.next();
        Date dateStart = inputFormatUser.parse(dateS);

        System.out.println("Please enter the end date");
        Date dateEnd = inputFormatUser.parse(scanner.next());

        int yearStart = dateStart.getYear();
        int yearEnd = dateEnd.getYear();
        int yearCount = yearEnd - yearStart + 1;
        int[][] yearDiff = new int[yearCount][12];
        
        String[][] yearPrint = new String[yearCount][12];
        
        while ((line = file.readLine()) != null){
            Matcher m = p.matcher(line);
            if (m.matches()){
                Date dateFile = readDOB.parse(m.group(2));
                int dateMonth = dateFile.getMonth();
                int dateYear = dateFile.getYear();
                if (dateFile.after(dateStart) && dateFile.before(dateEnd)){
                    for (int i = 0; i < yearCount; i++) {
                        for (int j = 0; j < 12; j++) {
                            if (dateYear == i + yearStart && dateMonth == j){
                                yearDiff[i][j]++;
                                yearPrint[i][j] = printDOB1.format(dateFile);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("NUMBER OF INTERACTIONS BY MONTH");
        System.out.println("-------------------------------");
        System.out.println("Input: " + printDOB.format(dateStart) + " - " + printDOB.format(dateEnd));

        for (int a = 0; a < yearCount; a++) {
            for (int b = 0; b < 12; b++) {
                if (yearDiff[a][b] > 0) {
                    System.out.print("| " + String.format("%1$10s", yearPrint[a][b]) + " |");
                }
            }
        }
        System.out.print("\n");

        for (int a = 0; a < yearCount; a++) {
            for (int b = 0; b < 12; b++) {
                if (yearDiff[a][b] > 0) {
                    System.out.print("| " + String.format("%1$10d", yearDiff[a][b]) + " |");
                }
            }
        }
        System.out.println("\n");

    }
}
