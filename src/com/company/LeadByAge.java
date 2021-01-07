package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeadByAge {

    public void LeadByAge() throws ParseException, IOException {

        // Creating the variable
        int count0_10 = 0;
        int count10_20 = 0;
        int count20_60 = 0;
        int countHigher60 = 0;


        BufferedReader file = new BufferedReader(new FileReader("leads.csv"));
        String line;
        Pattern p = Pattern.compile("(.+?),(.+?),(.+?),(.+?),(.+?),(.+?),(.+?)");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        while (null != (line = file.readLine())){
            Matcher m = p.matcher(line);
            if (m.matches()){
                String s = m.group(3);
                Date dob = dateFormat.parse(s);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dob);
                LocalDate dob1 = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                LocalDate now = LocalDate.now();
                System.out.println(calendar.get(Calendar.MONTH));
                int age = Period.between(dob1,now).getYears();

                if (age >= 0 && age < 10){
                    count0_10++;
                } else if (age >= 10 && age < 20 ){
                    count10_20++;
                } else if (age >= 20 && age < 60){
                    count20_60++;
                } else {
                    countHigher60++;
                }

            } else {
                System.out.println("Nono");
            }
        }

        System.out.println("-----------------------");
        System.out.println("NUMBER OF LEADS BY AGES");
        System.out.println("-----------------------");
        System.out.println("| " + String.format("%1$18s", "0-10(years old)") + " | " + String.format("%1$18s", "10-20(years old)") + " | " + String.format("%1$18s", "20-60(years old)") + " | " + String.format("%1$18s", "over 60(years old)") + " |");
        System.out.println("| " + String.format("%1$18d", count0_10) + " | " + String.format("%1$18d", count10_20) + " | " + String.format("%1$18d", count20_60) + " | " + String.format("%1$18d", countHigher60) + " |");
        System.out.println();



    }
}