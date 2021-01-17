package finalproject.reportandstatistic;
import finalproject.DateManager;
import finalproject.OptionCheck;

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

public class NumberOfInteract {

    public void getNumberOfInteract() throws ParseException, IOException {
        BufferedReader file = new BufferedReader(new FileReader("interactions.csv"));
        String line;
        DateManager dateManager = new DateManager();
        Date now = new Date();
        Date dateStart, dateEnd;

        // Declare the pattern using Regex
        Pattern p = Pattern.compile("(.+?),(.+?),(.+?),(.+?),(.+?)");

        // Declare of date format
        SimpleDateFormat readDOB = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat printDOB = new SimpleDateFormat("MMM dd yyyy");
        SimpleDateFormat printDOB1 = new SimpleDateFormat("MMM yyyy");

        // Menu for user to choose the date format
        // Declare the String of start date, end date and date format
        String startDate, endDate, dateFormat;
        String[] dateFormatList = new String[]{"MM-dd-yyyy", "dd-MM-yyyy", "yyyy-MM-dd"};
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please choose one of the option below");
        System.out.println("1.MM-dd-yyyy");
        System.out.println("2.dd-MM-yyyy");
        System.out.println("3.yyyy-MM-dd");

        // Check if the input choice is in range of (1,3) and if it is a digit
        dateFormat = dateFormatList[OptionCheck.optionCheck(1,3) - 1];
        System.out.println("-----------------------------------------");
        System.out.println("The date format is using is " + dateFormat);

        // SET DATE FORMAT
        dateManager.setDateFormat(dateFormat);


        // GET START AND END DATES, THEN CONVERT TO FORMAT USED IN CSV FILES
        while (true){

            // Check if the input of the start date is with the valid format, if not asking them to enter again
            do{
                System.out.print("\nPlease enter the start day ("+dateFormat+"): ");
                //startDate = startDate1.getDateFromInput();
                startDate = inputScanner.nextLine();
            } while (!dateManager.isCorrectDateFormat(startDate, dateFormat));

            // Check if the input of the end date is with the valid format, if not asking them to enter again
            do{
                System.out.print("\nPlease enter the end day ("+dateFormat+":) ");
                //startDate = startDate1.getDateFromInput();
                endDate = inputScanner.nextLine();
            } while (!dateManager.isCorrectDateFormat(endDate, dateFormat));


            // Check the validity of the start date end the end date
            // The start date cannot be after the end date
            if (dateManager.convertStringToDate(startDate).getTime() > dateManager.convertStringToDate(endDate).getTime()){

                System.out.println();
                System.out.println("The start day cannot after the end day");
                System.out.println("Please try again");
                System.out.println();

            }

            // The start date or the end date cannot after today
            else if (dateManager.convertStringToDate(startDate).getTime() > now.getTime() || dateManager.convertStringToDate(endDate).getTime() > now.getTime()){

                System.out.println();
                System.out.println("The start day or the end day cannot be after today");
                System.out.println("Please try again");
                System.out.println();

                // If it satisfy those condition, stop the while - loop
            } else {
                break;
            }
        }

        // Convert the date format to different one
        startDate = dateManager.convertDateFormat(startDate,"yyyy-MM-dd");

        // Set it back to the origin date format
        dateManager.setDateFormat(dateFormat);

        // Convert the date format to different one
        endDate = dateManager.convertDateFormat(endDate,"yyyy-MM-dd");

        // Convert String to Date
        dateStart = dateManager.convertStringToDate(startDate);
        dateEnd = dateManager.convertStringToDate(endDate);

        // Get the year of the start date and end date
        int yearStart = dateStart.getYear();
        int yearEnd = dateEnd.getYear();


        int yearCount = yearEnd - yearStart + 1;

        // Create the 2 arrays (String and Integer) have 12 column which stands for 12 months and yearCount rows
        int[][] yearDiff = new int[yearCount][12];
        String[][] yearPrint = new String[yearCount][12];

        // Scanning through the file until the end of it
        while ((line = file.readLine()) != null){
            Matcher m = p.matcher(line);
            if (m.matches()){

                // Convert the String from line (second group) in the file to Date
                Date dateFile = readDOB.parse(m.group(2));

                // Get the year and the month from the dateFile
                int dateMonth = dateFile.getMonth();
                int dateYear = dateFile.getYear();

                // Check if it in the range of the start date and the end date
                if (dateFile.after(dateStart) && dateFile.before(dateEnd)){

                    // Looping through the 2D array
                    for (int i = 0; i < yearCount; i++) {
                        for (int j = 0; j < 12; j++) {

                            // If it match the year and the month
                            if (dateYear == i + yearStart && dateMonth == j){

                                // Increase the number of it by one
                                yearDiff[i][j]++;
                                yearPrint[i][j] = printDOB1.format(dateFile);
                            }
                        }
                    }
                }
            }
        }

        // Format and print out the statistics of number of interactions
        System.out.println();
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
