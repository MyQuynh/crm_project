package finalproject.reportandstatistic;

import finalproject.DateManager;
import finalproject.Main;
import finalproject.OptionCheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PotentialInteract {
    private String getDateFromLine(String line) {
        // SPLIT LINE AND RETURN THE SECOND ELEMENT OF THE ARRAY (INTERACTION_DATE)
        String[] lineContent = line.split(",");
        return lineContent[1];
    }
    private String getResultFromLine(String line) {
        // SPLIT LINE AND RETURN THE SECOND ELEMENT OF THE ARRAY (INTERACTION_RESULT)
        String[] lineContent = line.split(",");
        return lineContent[4];
    }

    public void getInteractionByPotential() throws ParseException, FileNotFoundException {
        DateManager startDate1 = new DateManager();
        DateManager endDate1 = new DateManager();
        DateManager dateManager = new DateManager();

        Date now = new Date();

        int negativeCount = 0;
        int neutralCount = 0;
        int positiveCount = 0;

        // SET DATE FORMAT
        String startDate, endDate, dateFormat;
        String[] dateFormatList = new String[]{"MM-dd-yyyy", "dd-MM-yyyy", "yyyy-MM-dd"};
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please choose one of the option below");
        System.out.println("1.MM-dd-yyyy");
        System.out.println("2.dd-MM-yyyy");
        System.out.println("3.yyyy-MM-dd");
        dateFormat = dateFormatList[OptionCheck.optionCheck(1,3) - 1];
        System.out.println("-----------------------------------------");
        System.out.println("The date format is using is " + dateFormat);

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
                System.out.print("\nPlease enter the end day ("+dateFormat+"): ");
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

        // Convert date format to different date format
        startDate = dateManager.convertDateFormat(startDate,"yyyy-MM-dd");
        dateManager.setDateFormat(dateFormat); // Set the date format to the origin one
        endDate = dateManager.convertDateFormat(endDate,"yyyy-MM-dd");


        // SCAN THROUGH THE FILE LINE BY LINE AND LOOK FOR INTERACTION IN DATE RANGE
        Scanner fileScanner = new Scanner(new File("interactions.csv"));

        while(fileScanner.hasNext()){
            String line = fileScanner.nextLine();
            String interactionDate = getDateFromLine(line);

            // COUNT INTERACTIONS BY RESULT
            if (dateManager.isInRange(startDate, endDate, interactionDate)){
                String result = getResultFromLine(line);

                if (result.equals("negative")){
                    negativeCount ++;
                }
                else if(result.equals("neutral")){
                    neutralCount ++;
                }
                else{
                    positiveCount ++;
                }
            }
        }

        System.out.println();
        System.out.println("NUMBER OF INTERACTIONS BY POTENTIAL");
        System.out.println("-----------------------------------");
        System.out.println("Input: " + startDate1.convertDateFormat(startDate, "MMM dd yyyy") + " - " + endDate1.convertDateFormat(endDate, "MMM dd yyyy"));
        System.out.println("| " + String.format("%1$18s", "Positive") + " | " + String.format("%1$18s", "Neutral") + " | " + String.format("%1$18s", "Negative") + " |");
        System.out.println("| " + String.format("%1$18d", positiveCount) + " | " + String.format("%1$18d", neutralCount) + " | " + String.format("%1$18d", negativeCount) + " |");
        System.out.println();
    }
}