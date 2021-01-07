package finalproject.interactioninput;

import finalproject.OptionCheck;

public class InteractionMethodInput {

    public String interactionMethodInput (){

        // Menu bar for the method of interaction
        System.out.println("Please choose the method from below");
        System.out.println("1) Email");
        System.out.println("2) Phone");
        System.out.println("3) Face to Face");
        System.out.println("4) Social media");

        // Get the valid option from user
        int option = OptionCheck.optionCheck(1,4);

        // Depending on the input, get the method
        if (option == 1){
            return "email";
        } else if (option == 2){
            return "phone";
        } else if (option == 3){
            return "face to face";
        } else {
            return "social media";
        }

    }
}
