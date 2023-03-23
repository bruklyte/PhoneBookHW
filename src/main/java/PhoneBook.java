import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {

    private static ArrayList<Contact> phonebook = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private Object services;

    public PhoneBook() throws IOException {
    }

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            System.out.println("Welcome to the Phonebook Application!");
            System.out.println("1. Add a contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Find a contact by name");
            System.out.println("4. Find a contact by phone number");
            System.out.println("5. Update a contact");
            System.out.println("6. View all contacts");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    findContactByName();
                    break;
                case 4:
                    findContactByPhoneNumber();
                    break;
                case 5:
                   // updateContact();
                    break;
                case 6:
                    viewAllContacts();
                    break;
                case 7:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        System.out.println("Goodbye!");
    }

    private static void viewAllContacts() {
            if (phonebook.size() > 0) {
                System.out.println("All contacts:");
                phonebook.forEach(System.out::println);
            } else {
                System.out.println("No contacts found.");
            }
        }

    private static void addContact() {
        System.out.print("Enter the name of the contact: ");
        String name = scanner.nextLine();
        System.out.print("Enter the phone number of the contact: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter the email of the contact (optional): ");
        String email = scanner.nextLine();
        Contact contact = new Contact(name, phoneNumber, email);
        phonebook.add(contact);
        System.out.println("Contact added successfully!");
    }

    private static void removeContact() {
        System.out.print("Enter the name or phone number of the contact you want to remove: ");
        String searchQuery = scanner.nextLine();
        Contact contact = findContact(searchQuery);
        if (contact != null) {
            phonebook.remove(contact);
            System.out.println("Contact removed successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static Contact findContact(String name) {
        Contact[] contacts = new Contact[0];
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                    return contact;
                }
            }
            return null;
        }

     static void findContactByName() {
        System.out.print("Enter the name of the contact you want to find: ");
        String name = scanner.nextLine();
        Contact contact = findContact(name);
        if (contact != null) {
            System.out.println(contact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void findContactByPhoneNumber() {
        System.out.print("Enter the phone number of the contact you want to find: ");
        String phoneNumber = scanner.nextLine();
        Contact contact = findContact(phoneNumber);
        if (contact != null) {
            System.out.println(contact);
        } else {
            System.out.println("Contact not found.");
        }
    }
    public void updateContact() {
        String fullName = String.valueOf(this.getUserInput("Enter the name of the contact"));
        String phone = String.valueOf(this.getUserInput("Enter the new phone number"));
        String email = String.valueOf(this.getUserInput("Enter the new email address"));
       // this.services.handleUpdateContact(fullName, phone, email);
        this.displayMessage("Contact successfully updated!");
    }

    private void displayMessage(String s) {
    }

    private static String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + ": ");
        return scanner.nextLine();
    }
    private static <CSVReader> void exportContacts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("contacts.csv"))) {
        // Write header row
        writer.println("Name,Phone Number,Email");

        // Write each contact as a row in the CSV file
        for (Contact contact : phonebook) {
            writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
        }

        System.out.println("Contacts exported successfully to contacts.csv file.");
    } catch ( IOException e) {
        System.out.println("Error exporting contacts to CSV file: " + e.getMessage());
    }


}
}