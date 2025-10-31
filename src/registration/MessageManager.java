/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author RC_Student_Lab
 */
public class MessageManager {
   
   private final List<Message> sentMessages = new ArrayList<>();
    private final List<Message> storedMessages = new ArrayList<>();
    private final List<Message> disregardedMessages = new ArrayList<>();

    private final List<String> messageHashes = new ArrayList<>();
    private final List<String> messageIDs = new ArrayList<>();

    private Registration reg;

    public MessageManager(Registration reg) {
        this.reg = reg;
    }

    // ✅ Add a message based on its flag type
    public void addMessage(Message msg, String flag) {
        switch (flag.toLowerCase()) {
            case "sent" -> sentMessages.add(msg);
            case "store" -> storedMessages.add(msg);
            case "disregard" -> disregardedMessages.add(msg);
            default -> {
                System.out.println("Invalid flag: " + flag);
                return;
            }
        }

        // Track hashes & IDs
        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());
    }

    // ✅ Getter used in tests and LogIn
    public List<Message> getSentMessages() {
        return sentMessages;
    }

    // ✅ Display all sent messages
    public void displaySentMessages() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages yet.");
            return;
        }
        System.out.println("\n=== SENT MESSAGES ===");
        for (Message m : sentMessages) {
            System.out.println("Message ID: " + m.getMessageID());
            System.out.println("Recipient: " + m.getRecipient());
            System.out.println("Message: " + m.getText());
            System.out.println("Hash: " + m.getMessageHash());
            System.out.println("---------------------");
        }
    }

    // ✅ Display the longest sent message
    public void displayLongestMessage() {
        if (sentMessages.isEmpty()) {
            System.out.println("No messages available.");
            return;
        }

        Message longest = sentMessages.get(0);
        for (Message m : sentMessages) {
            if (m.getText().length() > longest.getText().length()) {
                longest = m;
            }
        }

        System.out.println("\n=== LONGEST MESSAGE ===");
        System.out.println("Recipient: " + longest.getRecipient());
        System.out.println("Message: " + longest.getText());
        System.out.println("Length: " + longest.getText().length());
    }

    // ✅ Search message by ID
    public void searchByMessageID(String id) {
        for (Message m : sentMessages) {
            if (m.getMessageID().equalsIgnoreCase(id)) {
                System.out.println("\nMessage found!");
                System.out.println("Recipient: " + m.getRecipient());
                System.out.println("Message: " + m.getText());
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    // ✅ Search messages by recipient
    public void searchByRecipient(String recipient) {
        boolean found = false;
        System.out.println("\n=== Messages for recipient: " + recipient + " ===");
        for (Message m : sentMessages) {
            if (m.getRecipient().equalsIgnoreCase(recipient)) {
                System.out.println("Message ID: " + m.getMessageID());
                System.out.println("Message: " + m.getText());
                System.out.println("-------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for that recipient.");
        }
    }

    // ✅ Delete message by its hash
    public void deleteByHash(String hash) {
        for (Message m : new ArrayList<>(sentMessages)) { // avoid concurrent modification
            if (m.getMessageHash().equals(hash)) {
                sentMessages.remove(m);
                messageHashes.remove(hash);
                messageIDs.remove(m.getMessageID());
                System.out.println("Message with hash " + hash + " successfully deleted.");
                return;
            }
        }
        System.out.println("Message hash not found.");
    }

    // ✅ Display a full sent message report
    public void displayReport() {
        if (sentMessages.isEmpty()) {
            System.out.println("No messages to report.");
            return;
        }
        System.out.println("\n=== SENT MESSAGE REPORT ===");
        for (Message m : sentMessages) {
            System.out.println("Message ID: " + m.getMessageID());
            System.out.println("Message Hash: " + m.getMessageHash());
            System.out.println("Recipient: " + m.getRecipient());
            System.out.println("Message: " + m.getText());
            System.out.println("---------------------------");
        }
        System.out.println("Total sent messages: " + sentMessages.size());
    }
}