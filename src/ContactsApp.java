import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsApp extends Contact{
    public static void main(String[] args) {
        doContacts();
    }

    public static List<String> contactStrings() {
        Path contactPath = Paths.get("contacts.txt");
        List<String> contactStrings = new ArrayList<>();
        try {
            contactStrings = Files.readAllLines(contactPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactStrings;
    }

    public static List<Contact> contactObj(List<String> strings) {
        return nameStringsToContacts(strings);
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

    public static void viewAll() {
        List<Contact> contacts = contactObj(contactStrings());
        System.out.printf("%-20s | %-17s |\n------------------------------------------\n", "Name", "Phone Number");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%d: %-17s | %-17s |\n", i + 1, contacts.get(i).getName(), contacts.get(i).getPhoneNumber());
        }
    }

    public static void addContact(){
        System.out.println("Please enter contact full name: ");
        Input userInput = new Input();
        String nameInput = userInput.getString();
        System.out.println("Please enter contact phone number (no dashes): ");
        String numberInput = userInput.getString();
        String contactFormat = nameInput + " | " + numberInput;
        List<Contact> contactObj = contactObj(contactStrings());
        List<String> contactsStr = contactStrings();
        Path contactsFile = Paths.get("contacts.txt");
        for (int i = 0; i < contactObj.size(); i++){
            if (nameInput.equalsIgnoreCase(contactObj.get(i).getName())) {
                System.out.println("There is already a contact named " + nameInput + ". Do you want to overwrite it? yes/no");
                boolean overwriteContact = userInput.yesNo();
                if (overwriteContact){
                    contactsStr.set(i, contactFormat);
                    try {
                        Files.write(contactsFile, contactsStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            } else if (!contactObj.get(i).getName().equalsIgnoreCase(nameInput)){
                try {
                    Files.write(contactsFile, Arrays.asList(contactFormat), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteContact() {
        List<String> contactStr = contactStrings();
        Path contactsFile = Paths.get("contacts.txt");
        viewAll();
        System.out.println("Please enter integer: ");
        Input userInput = new Input();
        int numberInput = userInput.getInt();
        System.out.println("Contact: " + contactStr.get(numberInput - 1) + " has been deleted");
        contactStr.remove(numberInput - 1);
        try {
            Files.write(contactsFile, contactStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchContact() {
        Input userInput = new Input();
        System.out.println("Please enter the name of the contact you'd like to search for:");
        String searchInput = userInput.getString();
        List<String> contactStr = contactStrings();
        for (String s : contactStr) {
            if (s.toUpperCase().startsWith(searchInput.toUpperCase())) {
                System.out.println("Here's the contact you searched for: " + s);
            }
        }
    }

    public static boolean yesNo() {
        System.out.println("Would you like to select another option?");
        Input userInput = new Input();
        return userInput.yesNo();
    }

    public static void doContacts() {
        int userInput;
        do {
            userInput = userChoice();
            if (userInput == 1) {
                viewAll();
            } else if (userInput == 2) {
                addContact();
            } else if (userInput == 3) {
                searchContact();
            } else if (userInput == 4) {
                deleteContact();
            } else if (userInput == 5) {
                System.out.println("Thanks for using the app.");
                break;
            }
        } while (yesNo());
        System.out.println("Thanks for using the app.");
    }
}
