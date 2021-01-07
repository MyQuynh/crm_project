package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PotentialInteract {

    public void PotentialInteract() throws ParseException, IOException {

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

        int count_neutral = 0;
        int count_positive = 0;
        int count_negative = 0;

        while ((line = file.readLine()) != null){
            Matcher m = p.matcher(line);
            if (m.matches()){
                Date dateFile = readDOB.parse(m.group(2));
                if (dateFile.after(dateStart) && dateFile.before(dateEnd)){
                    String potential = m.group(5);
                    if (potential.equals("negative")){
                        count_negative++;
                    } else if (potential.equals("positive")){
                        count_positive++;
                    } else {
                        count_neutral++;
                    }
                }
            }
        }

        System.out.println("NUMBER OF INTERACTIONS BY POTENTIAL");
        System.out.println("-----------------------------------");
        System.out.println("Input: " + printDOB.format(dateStart) + " - " + printDOB.format(dateEnd));
        System.out.println("| " + String.format("%1$18s", "Positive") + " | " + String.format("%1$18s", "Neutral") + " | " + String.format("%1$18s", "Negative") + " |");
        System.out.println("| " + String.format("%1$18d", count_positive) + " | " + String.format("%1$18d", count_neutral) + " | " + String.format("%1$18d", count_negative) + " |");
        System.out.println();

    }
}