import java.util.*;

public class ContactManager {

    public static void main(String[] args) { 
 
        HashMap<String, Contact> contacts = new HashMap<>();
        Contact colby = new Contact("Colby S", "934-239-0985");
        Contact john = new Contact("John K", "934-289-0335");
        Contact bill = new Contact("Bill D", "994-189-0935");
        Contact phil = new Contact("Phil W", "934-009-0035");
        Contact mike = new Contact("Mike K", "900-299-0335");
        contacts.put("Colby S", colby);
        contacts.put("John K", john);
        contacts.put("Bill D", bill);
        contacts.put("Phil W", phil);
        contacts.put("Mike W", mike);

        String name1 = "Colby S";
        printContactDetails(name1, contacts);
        String name2 = "Susan H";
        printContactDetails(name2, contacts);

        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));
        System.out.println(String.format("=== All Contacts ==="));
        for(Contact contact : sorted) {
            System.out.println(String.format("Name: %s, Phone Number %s", contact.getName(), contact.getPhone()));
        }
    }

    private static void printContactDetails(String name, HashMap<String, Contact> contacts) {
        Contact currContact = contacts.get(name);
        if (currContact == null) {
            System.out.println(String.format("Contact with name %s does not exist.", name));
        } else {
            System.out.println(String.format("Contact details: Name: %s, Phone Number: %s", currContact.getName(), currContact.getPhone()));
        }
    }
}
