
import java.util.ArrayList;
import java.util.Scanner;


class Contact {
    private String name;
    private String phoneNumber;
    private String email;

   
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    public void displayContact() {
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
    }
}

public class ContactManager {
    
    private static ArrayList<Contact> contactList = new ArrayList<>();

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        
        while (!exit) {
            System.out.println("\n==== Contact Management System ====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

           
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();  

                switch (choice) {
                    case 1:
                        addContact(scanner);
                        break; 
                    case 2:
                        viewContacts();
                        break; 
                    case 3:
                        updateContact(scanner);
                        break; 
                    case 4:
                        deleteContact(scanner);
                        break;
                    case 5:
                        exit = true;
                        break; 
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } else {
               
                System.out.println("Please enter a valid number.");
                scanner.next(); 
            }
        }

        System.out.println("Exiting Contact Management System.");
        scanner.close(); 
    }

    
    private static void addContact(Scanner scanner) {
        System.out.println("\n--- Add Contact ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

       
        contactList.add(new Contact(name, phoneNumber, email));
        System.out.println("Contact added successfully!");
    }

   
    private static void viewContacts() {
        System.out.println("\n--- View Contacts ---");

        if (contactList.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (int i = 0; i < contactList.size(); i++) {
                System.out.println("Contact " + (i + 1) + ":");
                contactList.get(i).displayContact();
                System.out.println("---------------------------");
            }
        }
    }

   
    private static void updateContact(Scanner scanner) {
        System.out.println("\n--- Update Contact ---");
        viewContacts();
        if (contactList.isEmpty()) return; 

        System.out.print("Enter the contact number to update: ");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();  

            if (index >= 0 && index < contactList.size()) {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new phone number: ");
                String phoneNumber = scanner.nextLine();
                System.out.print("Enter new email: ");
                String email = scanner.nextLine();

                Contact contact = contactList.get(index);
                contact.setName(name);
                contact.setPhoneNumber(phoneNumber);
                contact.setEmail(email);

                System.out.println("Contact updated successfully!");
            } else {
                System.out.println("Invalid contact number.");
            }
        } else {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }
    }

    
    private static void deleteContact(Scanner scanner) {
        System.out.println("\n--- Delete Contact ---");
        viewContacts();
        if (contactList.isEmpty()) return;  
        System.out.print("Enter the contact number to delete: ");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (index >= 0 && index < contactList.size()) {
                contactList.remove(index);
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("Invalid contact number.");
            }
        } else {
            System.out.println("Invalid input.");
            scanner.nextLine(); 
        }
    }
}
