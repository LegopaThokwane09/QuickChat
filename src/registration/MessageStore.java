/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author RC_Student_Lab
 */
public class MessageStore {
    /**
     * Stores a list of messages to a JSON file.
     * @param messages List of Message objects to store
     * @param filename Name of the JSON file to write to
     */
    public static void storeMessagesToJSON(List<Message> messages, String filename) {
        JSONArray messageArray = new JSONArray();

        for (Message msg : messages) {
            JSONObject obj = new JSONObject();
            obj.put("MessageID", msg.getMessageID());
            obj.put("MessageHash", msg.getMessageHash());
            obj.put("Recipient", msg.getRecipient());
            obj.put("Message", msg.getText());
            messageArray.add(obj);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(messageArray.toJSONString());
            file.flush();
            System.out.println("Messages successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving messages to JSON: " + e.getMessage());
        }
    }
}

