import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class ContactsApp {
    public static void main(String[] args) {
        deleteContact();
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
        for (int i = 0; i < contactListAll.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactListAll.get(i));
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

        String contactFormat = nameInput + "|" + numberInput;
        Path contactPath = Paths.get("contacts.txt");
        try {
            Files.write(contactPath, Arrays.asList(contactFormat), StandardOpenOption.APPEND);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void deleteContact() {
        Path contactPath = Paths.get("contacts.txt");
        try {
            List<String> contactListAll = Files.readAllLines(contactPath);
            for (int i = 0; i < contactListAll.size(); i += 1) {
                System.out.println((i + 1) + ": " + contactListAll.get(i));
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
        Path contactPath = Paths.get("contacts.txt");
        //ask string
        Input userInput = new Input();

        //loop through exact: .getName.equals(userInput) / (.getPhoneNumber)
        //startswith || contains!(boolean if true delete that contact at that index)
            //if either true, return that index
        //if find
        //then print
    }


}
