package finalproject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateManager {
    private String dateFormat;

    public DateManager() {}

    private void setDateFormat() {
        //Scanner inputScanner = new Scanner(System.in);
        int userChoice;

        System.out.println("Please enter a number to choose the date format for your next input.");
        System.out.println("Default format is yyyy/MM/dd.");
        System.out.println("1. dd/MM/yyyy\n2. MM/dd/yyyy\n3. yyyy/MM/dd");

        userChoice = OptionCheck.optionCheck(1,3);

        if (userChoice == 1) {
            this.dateFormat = "dd/MM/yyyy";
        } else if (userChoice == 2) {
            this.dateFormat = "MM/dd/yyyy";
        } else {
            this.dateFormat = "yyyy/MM/dd";
        }
    }


    public String getDateFromInput()throws ParseException {
        Scanner inputScanner = new Scanner(System.in);
        String resultDate = "";
        try {
            do {
                setDateFormat();
                System.out.println("Please enter date according to format entered above.");
                resultDate = inputScanner.next();
            }
            while (!isCorrectDateFormat(resultDate, this.dateFormat) || resultDate == null);
        }catch (ParseException e) {
            System.out.println("Incorrect date format, please try again.");
        }
        return resultDate;
    }

    public boolean isCorrectDateFormat(String date, String targetFormat) throws ParseException {
        if (targetFormat.equals("")){
            targetFormat = this.dateFormat;
        }
        Date formattedDate = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(targetFormat);
            dateFormat.setLenient(false);
            formattedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("\nWrong format. Please enter again\n");
            return false;
        }
        return true;
    }
    public boolean isInRange(String start, String end, String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        Date startDate = dateFormat.parse(start);
        Date endDate = dateFormat.parse(end);
        Date date = dateFormat.parse(dateString);
        return date.after(startDate) && date.before(endDate);
    }

    public String convertDateFormat(String inputDate, String newFormat) {
        String newDateString = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
            Date date = simpleDateFormat.parse(inputDate);
            simpleDateFormat.applyPattern(newFormat);
            newDateString = simpleDateFormat.format(date);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return newDateString;
    }
}
