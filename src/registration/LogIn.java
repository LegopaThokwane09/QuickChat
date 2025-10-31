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
        case "1": // --- Send Messages ---
             if (messagesSent >= numMessages) {
                        System.out.println("You have reached the maximum number of messages for this session.");
                        break;
                    }

                    // ✅ For loop for multiple messages
                    for (int i = messagesSent; i < numMessages; i++) {
                        System.out.print("Enter recipient (+country code, max 10 digits after code): ");
                        String recipient = scanner.nextLine();
                        System.out.print("Enter message (max 250 chars): ");
                        String text = scanner.nextLine();

                        Message msg = new Message(recipient, text);

                        // Validation
                        if (!msg.checkMessageLength()) {
                            System.out.println("Please enter a message of less than 250 characters.");
                            i--; // retry
                            continue;
                        }
                        if (!msg.checkRecipientCell()) {
                            System.out.println("Recipient number is incorrectly formatted or missing international code.");
                            i--; // retry
                            continue;
                        }

                        // Message action options
                        System.out.println("1) Send Message\n2) Disregard Message\n3) Store Message");
                        System.out.print("Select: ");
                        String action = scanner.nextLine();

                        switch (action) {
                            case "1":
                                msg.sendMessage(); // Display message info
                                sentMessages.add(msg);
                                messagesSent++;

                                System.out.println("Press 0 to delete this message, or any other key to continue.");
                                String deleteInput = scanner.nextLine();
                                if (deleteInput.equals("0")) {
                                    sentMessages.remove(msg);
                                    messagesSent--;
                                    System.out.println("Message deleted successfully.");
                                }
                                break;

                            case "2":
                                System.out.println("Message disregarded.");
                                messagesSent++;
                                break;

                            case "3":
                                System.out.println("Message successfully stored.");
                                sentMessages.add(msg);
                                messagesSent++;
                                break;

                            default:
                                System.out.println("Invalid option. Try again.");
                                i--; // retry
                                break;
                        }

                        // Stop if user has sent enough messages
                        if (messagesSent >= numMessages) {
                            System.out.println("You’ve reached your message limit for this session.");
                            break;
                        }
                    }
                    break;

                case "2": // --- View stored messages ---
                    if (sentMessages.isEmpty()) {
                        System.out.println("No messages have been sent or stored yet.");
                    } else {
                        System.out.println("\n--- Recently Sent Messages ---");
                        for (Message m : sentMessages) {
                            System.out.println("MessageID: " + m.getMessageID());
                            System.out.println("Recipient: " + m.getRecipient());
                            System.out.println("Message: " + m.getText());
                            System.out.println("Hash: " + m.getMessageHash());
                            System.out.println("---------------------------");
                        }
                    }
                    break;

                case "3": // --- Quit ---
                    running = false;
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        }

        // ✅ Save all messages to JSON file before exit
        System.out.println("Exiting QuickChat. Total messages sent: " + sentMessages.size());
        MessageStore.storeMessagesToJSON(sentMessages, "messages.json");
        System.out.println("All messages saved to messages.json successfully!");
        scanner.close();
    }
}