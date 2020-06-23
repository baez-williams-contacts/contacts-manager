import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class ContactsApp {
    public static void main(String[] args) {
        doContacts();
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
        Path contactPath = Paths.get("contacts.txt");
        try {
            List<String> contactListAll = Files.readAllLines(contactPath);
            System.out.printf("%-17s | %-17s |\n---------------------------------------\n", "Name", "Phone Number");
            for (int i = 0; i < contactListAll.size(); i++) {
                String[] contactArr = contactListAll.get(i).split(" \\| ", 2);
                System.out.printf("%-17s | %-17s |\n", contactArr[0], contactArr[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addContact(){
        System.out.println("Please enter contact full name: ");
        Input userInput = new Input();
        String nameInput = userInput.getString();
        System.out.println("Please enter contact phone number(no dashes): ");
        String numberInput = userInput.getString();
        String contactFormat = nameInput + " | " + numberInput;
        Path contactPath = Paths.get("contacts.txt");
        try {
            List<String> contactListAll = Files.readAllLines(contactPath);
            for (int i = 0; i < contactListAll.size(); i++){
                String[] contactArr = contactListAll.get(i).split(" \\| ", 2);
                if (nameInput.equalsIgnoreCase(contactArr[0])) {

                    System.out.println("There is already a contact named " + nameInput + ". Do you want to overwrite it? yes/no");
                    boolean overwriteContact = userInput.yesNo();
                    if (overwriteContact){
                        contactListAll.set(i, contactFormat);
                        Files.write(contactPath, contactListAll);
                        break;
                    }
                } else {
            Files.write(contactPath, Arrays.asList(contactFormat), StandardOpenOption.APPEND);
                break;
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void deleteContact() {
        Path contactPath = Paths.get("contacts.txt");
        try {
            List<String> contactListAll = Files.readAllLines(contactPath);
            System.out.printf("%-20s | %-17s |\n------------------------------------------\n", "Name", "Phone Number");
            for (int i = 0; i < contactListAll.size(); i += 1) {
                String[] contactArr = contactListAll.get(i).split(" \\| ", 2);
                System.out.printf("%d: %-17s | %-17s |\n", i + 1, contactArr[0], contactArr[1]);
            }
            System.out.println("Please enter integer: ");
            Input userInput = new Input();
            int numberInput = userInput.getInt();

            System.out.println("Contact: " + contactListAll.get(numberInput - 1) + " has been deleted");
            contactListAll.remove(numberInput -1);
            Files.write(contactPath, contactListAll);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void searchContact() {
        Input userInput = new Input();
        Path contactPath = Paths.get("contacts.txt");
        System.out.println("Please enter the name of the contact you'd like to search for:");
        String searchInput = userInput.getString();
        List<String> contactListAll = null;
        try {
            contactListAll = Files.readAllLines(contactPath);
            for (String s : contactListAll) {
                if (s.toUpperCase().startsWith(searchInput.toUpperCase())) {
                    System.out.println("Here's the contact you searched for: " + s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    }
}
