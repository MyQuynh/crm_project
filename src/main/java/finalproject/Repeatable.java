package finalproject;

public class Repeatable implements Repeat {

    @Override
    public boolean repeat() {
        while(true){
            System.out.print("\nDo you want to continue this action (Y/N): ");
            String input = Main.scanner.nextLine();
            System.out.println();
            if(input.equalsIgnoreCase("y")){
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid input, please enter only Y/y or N/n");
            }
        }
    }
}
