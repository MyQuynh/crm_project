package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Report {

    public static boolean dateWithinRange(Date startDate, Date endDate, Date date){
        return (date.after(startDate) && date.before(endDate));
    }

    public static void showLeadsByAge() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("leads.csv"));

    }

    public static void showInteractionByPotential(Date startDate, Date endDate) throws FileNotFoundException, ParseException {
        // TODO: include other date format
        Scanner fileScanner = new Scanner(new File("leads.csv"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int[] potential = new int[]{0, 0, 0};

        while(fileScanner.hasNext()){
            String line = fileScanner.nextLine();
            String[] lineContent = line.split(",");
            Date date  = dateFormat.parse(lineContent[1]);
            if (dateWithinRange(startDate, endDate, date)){
                if (lineContent[4].equals("positive")){
                    potential[0] += 1;
                }
                else if(lineContent[4].equals("negative")){
                    potential[1] += 1;
                }
                else{
                    potential[2] += 1;
                }
            }
        }

        // display result
        for (int i = 0; i < potential.length; i++) {
            if (i == 0){
                System.out.println("positive count: " + potential[i]);
            }
            else if( i == 1){
                System.out.println("negative count: " + potential[i]);
            }
            else{
                System.out.println("neutral count: " + potential[i]);
            }
        }
    }

    public static void showInteractionByMonth(Date startDate, Date endDate){

    }
}
