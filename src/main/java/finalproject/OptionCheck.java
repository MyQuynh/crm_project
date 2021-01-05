package finalproject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionCheck {

    public OptionCheck() {
    }

    public static int optionCheck(int min, int max){

        int option = 0;

        while (true) {
            try {
                System.out.print("Please enter your option: ");
                option = Main.scanner.nextInt();
                if (option < min || option > max) {
                    System.out.println("The option has to be between "+ min+" and "+max);
                    System.out.println("Please try again");
                } else if (String.valueOf(option).equalsIgnoreCase("")){
                    System.out.println("The option cannot be empty");
                    System.out.println("Please try again");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                Main.scanner.nextLine();
                System.out.println("Please enter only number");
                System.out.println("Please try again");
            }
        }
        return option;

    }
}
