/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import javax.swing.*;
/**
 *
 * @author RC_Student_Lab
 */
public class Message {
    private static int idCounter = 1;

    private String messageID;
    private String recipient;
    private String text;
    private String messageHash;

    public Message(String recipient, String text) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.text = text;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        String id = String.format("%010d", idCounter);
        idCounter++;
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient != null && recipient.matches("^\\+\\d{9,15}$");
    }

    public boolean checkMessageLength() {
        return text != null && text.length() <= 250;
    }

    public String createMessageHash() {
        String[] words = text.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (messageID.substring(0, 2) + ":" + messageID + ":" + (firstWord + lastWord)).toUpperCase();
    }

    // Console version of sendMessage
    public void sendMessage() {
        System.out.println("\n====Message Details=====");
        System.out.println("MessageID: " + messageID);
        System.out.println("MessageHash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: \"" + text + "\"");
        System.out.println("=========================== \n");
        System.out.println("Message successfully sent.\n");
    }

    // GUI version (optional)
    public void sendMessageGUI() {
        javax.swing.JOptionPane.showMessageDialog(null,
                "MessageID: " + messageID +
                        "\nMessageHash: " + messageHash +
                        "\nRecipient: " + recipient +
                        "\nMessage: " + text,
                "Message Sent", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    // --- Getters ---
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
}