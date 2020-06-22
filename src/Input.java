import java.util.Scanner;

public class Input {

    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString() {
        return scanner.nextLine();
    }

    public boolean yesNo() {
        String userInput = scanner.next();
        return userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y");
    }

    public int getInt() {
        try {
            return Integer.valueOf(getString());
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer.");
            return getInt();
        }
    }
}