import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact(){};

    public Contact(String name, String phoneNumber) {
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
            String[] contactArr = contact.split(" \\| ", 2);
            contactObj.add(new Contact(contactArr[0], contactArr[1]));
        }
        return contactObj;
    }
}