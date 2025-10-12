/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import java.util.Scanner;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */
public class LogIn {
    private Registration registeredUser;
    private static List<Message> sentMessages = new ArrayList<>();

    // Constructor
    public LogIn(Registration registeredUser) {
        this.registeredUser = registeredUser;
    }

    // Authentication
    public boolean authenticate(String username, String password) {
        return registeredUser != null
                && registeredUser.getUsername().equals(username)
                && registeredUser.getPassword().equals(password);
    }

    public String returnLoginStatus(String username, String password) {
        if (authenticate(username, password)) {
            return "Welcome " + registeredUser.getFirstName() + " "
                    + registeredUser.getLastName() + ", it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // --- MAIN method -
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Registration Phase ---
        Registration reg = new Registration();
        System.out.println("=== WELCOME TO QUICK-APP REGISTRATION ===");
        System.out.print("Enter first name: "); String firstName = scanner.nextLine();
        System.out.print("Enter last name: "); String lastName = scanner.nextLine();
        System.out.print("Enter username: "); String username = scanner.nextLine();
        System.out.print("Enter password: "); String password = scanner.nextLine();
        System.out.print("Enter South African cell phone number (+27...): "); String cellNumber = scanner.nextLine();

        String regMessage = reg.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println(regMessage);

        if (!regMessage.startsWith("Registration successful")) {
            System.out.println("\nCannot proceed: registration failed.");
            scanner.close();
            return;
        }

        // --- Login ---
        LogIn login = new LogIn(reg);
        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: "); String loginUser = scanner.nextLine();
        System.out.print("Enter password: "); String loginPass = scanner.nextLine();

        if (!login.authenticate(loginUser, loginPass)) {
            System.out.println("Login failed. Exiting.");
            scanner.close();
            return;
        }

        System.out.println("Login successful. " + login.returnLoginStatus(loginUser, loginPass));

        // --- QuickChat ---
        System.out.println("\n=== WELCOME TO QUICKCHAT ===");

        int numMessages = 0;
        while (true) {
            try {
                System.out.print("How many messages would you like to enter? ");
                numMessages = Integer.parseInt(scanner.nextLine());
                if (numMessages < 1) numMessages = 1;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        int messagesSent = 0;
        boolean running = true;

while (running) {
    System.out.println("\nMenu:");
    System.out.println("1) Send Messages");
    System.out.println("2) Show recently sent messages ");
    System.out.println("3) Quit");
    System.out.print("Select: ");
    String choice = scanner.nextLine();

    switch (choice) {
        case "1": // Send Messages
            if (messagesSent >= numMessages) {
                System.out.println("You have reached the maximum number of messages for this session.");
                break; // go back to menu
            }

            System.out.print("Enter recipient (+country code, max 10 digits after code): ");
            String recipient = scanner.nextLine();
            System.out.print("Enter message (max 250 chars): ");
            String text = scanner.nextLine();

            Message msg = new Message(recipient, text);

            if (!msg.checkMessageLength()) {
                System.out.println("Please enter a message of less than 250 characters.");
                break;
            }

            if (!msg.checkRecipientCell()) {
                System.out.println("Recipient number is incorrectly formatted or missing international code.");
                break;
            }

            System.out.println("1) Send Message\n2) Disregard Message\n3) Store Message");
            System.out.print("Select: ");
            String action = scanner.nextLine();

            switch (action) {
                case "1":
                    msg.sendMessage(); //This prints message successfully sent
                    sentMessages.add(msg);
                    messagesSent++;
                    
                    System.out.println("Press 0 to delete message, or any other key to continue.");
            String deleteInput = scanner.nextLine();
            if (deleteInput.equals("0")) {
                sentMessages.remove(msg);
                messagesSent--;
                System.out.println("Message deleted successfully.");
            }
            break;
                    
                case "2": // Disregard Message
             System.out.println("Message disregarded. Press 0 to delete message.");
            String del = scanner.nextLine();
            if (del.equals("0")) {
                System.out.println("Disregarded message deleted.");
            }
            messagesSent++;
            break;
                case "3":
                    System.out.println("Message successfully stored.");
                    sentMessages.add(msg);
                    messagesSent++;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
            break;

        case "2":
            System.out.println("Coming Soon.");
            break;

        case "3": // Quit
            running = false;
            break;

        default:
            System.out.println("Invalid selection.");
    }
}

        System.out.println("Exiting QuickChat. Total messages sent: " + sentMessages.size());
        scanner.close();
    }
}
