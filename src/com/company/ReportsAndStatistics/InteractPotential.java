package com.company.ReportsAndStatistics;

import com.company.DateManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class InteractPotential {
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
        DateManager dateManager = new DateManager();

        int negativeCount = 0;
        int neutralCount = 0;
        int positiveCount = 0;

        // SET DATE FORMAT
        String startDate, endDate, dateFormat;
        String[] dateFormatList = new String[]{"MM/dd/yyyy", "dd/MM/yyyy", "yyyy/MM/dd"};
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please choose one of the option below");
        System.out.println("1.MM/dd/yyyy");
        System.out.println("2.dd/MM/yyyy");
        System.out.println("3.yyyy/MM/dd");
        dateFormat = dateFormatList[inputScanner.nextInt()];


        // GET START AND END DATES, THEN CONVERT TO FORMAT USED IN CSV FILES
        do {
            System.out.println("Enter start date: ");
            startDate = inputScanner.next();
        } while (!dateManager.isCorrectDateFormat(startDate, dateFormat));
        if (dateManager.isCorrectDateFormat(startDate, "yyyy/MM/dd")) {
            startDate = dateManager.convertDateFormat(startDate, "yyyy/MM/dd");
        }

        do {
            System.out.println("Enter end date: ");
            endDate = inputScanner.next();
        } while (!dateManager.isCorrectDateFormat(endDate, dateFormat));
        if (dateManager.isCorrectDateFormat(endDate, "yyyy/MM/dd")) {
            endDate = dateManager.convertDateFormat(endDate, "yyyy/MM/dd");
        }

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

            System.out.println("NUMBER OF INTERACTIONS BY POTENTIAL");
            System.out.println("-----------------------------------");
            System.out.println("Input: " + startDate + " - " + endDate);
            System.out.println("| " + String.format("%1$18s", "Positive") + " | " + String.format("%1$18s", "Neutral") + " | " + String.format("%1$18s", "Negative") + " |");
            System.out.println("| " + String.format("%1$18d", positiveCount) + " | " + String.format("%1$18d", neutralCount) + " | " + String.format("%1$18d", negativeCount) + " |");
            System.out.println();
        }
    }
}
