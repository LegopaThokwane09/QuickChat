/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
/**
 * MessageStore handles saving and loading Message objects to/from JSON files,
 * preserving message IDs and hashes.
 */
public class MessageStore {
        /**
     * Stores a list of messages to a JSON file using Gson and prints debug info.
     * @param messages List of Message objects to store
     * @param filename Name of the JSON file to write to
     */
    public static void storeMessagesToJSON(List<Message> messages, String filename) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(messages, writer);
            System.out.println("Saving messages to " + filename + "...");
            for (Message m : messages) {
                System.out.println("MessageID: " + m.getMessageID() + " Recipient: " + m.getRecipient());
            }
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    /**
     * Reads messages from a JSON file into a List<Message>, preserving original IDs and hashes.
     * @param filename the JSON file to read
     * @return a List of Message objects, or an empty list if failed
     */
    public static List<Message> readMessagesFromJSON(String filename) {
        List<Message> messages = new ArrayList<>();
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Type messageListType = new TypeToken<List<Message>>(){}.getType();
            messages = gson.fromJson(reader, messageListType);
            System.out.println("Messages successfully loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error reading messages from JSON: " + e.getMessage());
        }
        return messages;
    }
}