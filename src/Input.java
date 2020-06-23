import java.util.Scanner;

public class Input {

    private final Scanner scanner;

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

    public int getInt(int min, int max) {
        int userInput = getInt();
        if (userInput >= min && userInput <= max) {
            return userInput;
        } else {
            System.out.println("Please enter an integer between the numbers shown above.");
            return getInt(min, max);
        }
    }
}