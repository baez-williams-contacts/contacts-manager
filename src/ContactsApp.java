public class ContactsApp {
    public static void main(String[] args) {
        userChoice();
    }

    public static int userChoice() {
        Input userInput = new Input();
        System.out.println("Please select one of the following options:\n" +
                            "1. View Contacts.\n" +
                            "2. Add a new contact.\n" +
                            "3. Search a contact by name.\n" +
                            "4. Delete an existing contact\n" +
                            "5. Exit.");
        return userInput.getInt(1, 5);
    }
}
