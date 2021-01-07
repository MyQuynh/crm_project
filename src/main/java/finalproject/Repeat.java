package finalproject;

public interface Repeat {
    public default boolean repeat(){
        System.out.println("Do you want to continue this action: Y/N");
        String input = Main.scanner.next();
        if(input.equalsIgnoreCase("y")){
            return true;
        }
        return false;
    }
}
