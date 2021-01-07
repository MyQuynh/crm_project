package finalproject.leadinput;

import finalproject.Main;

public class LeadGenderInput {

    public boolean leadGenderInput(){
        String genderResult;
        while(true){

            // Get the input from users, and lower case it
            System.out.print("Enter lead's gender which is true for male and false for female: ");
            String gender = Main.scanner.next().toLowerCase();


            // Check if the users enter the correct value which is true and false
            // If the users not enter anything, notify to them and make them enter the again
            if (String.valueOf(gender).equals("")){
                System.out.println("The input cannot be empty");
                System.out.println("Please try again");

                // If the users does not enter the correct given value (true or false), notify them and make the user enter again
            } else if (!gender.equalsIgnoreCase("true") && !gender.equalsIgnoreCase("false")) {
                System.out.println("The gender should be true for male and false for female");
                System.out.println("Please try again");
            } else {
                genderResult = gender;
                break; // The users input meet those condition. Therefore, we stop the while loop and return the matched boolean
            }
        }

        if(genderResult.equals("true")){
            return true;
        };
        return false;
    }
}
