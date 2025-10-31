/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author RC_Student_Lab
 */
public class MessageTest {
     @Test
    public void testMessageLengthValid() {
        Message msg = new Message("+27123456789", "Hello, this is a valid message.");
        assertTrue(msg.checkMessageLength(), "Message length should be valid");
    }

    @Test
    public void testMessageLengthInvalid() {
        String longText = "A".repeat(300); // 300 characters
        Message msg = new Message("+27123456789", longText);
        assertFalse(msg.checkMessageLength(), "Message should be invalid (too long)");
    }

    @Test
    public void testRecipientFormatValid() {
        Message msg = new Message("+27123456789", "Hi");
        assertTrue(msg.checkRecipientCell(), "Recipient format should be valid");
    }

    @Test
    public void testRecipientFormatInvalid() {
        Message msg = new Message("12345", "Hi");
        assertFalse(msg.checkRecipientCell(), "Recipient format should be invalid");
    }

    @Test
    public void testMessageIDNotNull() {
        Message msg = new Message("+27123456789", "Hi");
        assertNotNull(msg.getMessageID(), "Message ID should be generated");
    }

    @Test
    public void testMessageHashNotNull() {
        Message msg = new Message("+27123456789", "Hi");
        assertNotNull(msg.getMessageHash(), "Message Hash should be generated");
    }
}
