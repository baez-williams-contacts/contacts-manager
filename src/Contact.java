import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private int phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static List<String> contactsToNameStrings(List<Contact> contacts) {
        List<String> contactInfo = new ArrayList<>();
        for (Contact contact : contacts) {
            String contactString = contact.getName() + " | " + contact.getPhoneNumber();
            contactInfo.add(contactString);
        }
        return contactInfo;
    }

    public static List<Contact> nameStringsToContacts(List<String> contactInfo) {
        List<Contact> contactObj = new ArrayList<>();
        for (String contact : contactInfo) {
            String[] contactArr = contact.split("|", 2);
            contactObj.add(new Contact(contactArr[0], Integer.parseInt(contactArr[1])));
        }
        return contactObj;
    }
}
