/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package registration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * @author RC_Student_Lab
 */
public class MessageNGTest {
    private Message message1;
    private Message message2;

    @BeforeMethod
    public void setUp() {
        // Sample messages
        message1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        message2 = new Message("+278575975889", "Hi Keegan, did you receive the payment?");
    }

    // --- Test message length ---
    @Test
    public void testMessageLengthSuccess() {
        Assert.assertTrue(message1.checkMessageLength(), "Message should be valid (≤250 chars)");
    }

    @Test
    public void testMessageLengthFailure() {
        Message longMsg = new Message("+27712345678", "This message is intentionally made longer than 250 characters. " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        Assert.assertFalse(longMsg.checkMessageLength(), "Message should be invalid (>250 chars)");
    }

    // --- Test recipient format ---
    @Test
    public void testRecipientValid() {
        Assert.assertTrue(message1.checkRecipientCell(), "Recipient should be valid with +country code");
    }

    @Test
    public void testRecipientInvalid() {
        Message invalidRecipient = new Message("08575975889", "Test message");
        Assert.assertFalse(invalidRecipient.checkRecipientCell(), "Recipient should be invalid without +country code");
    }

    // --- Test message hash ---
    @Test
    public void testMessageHash() {
        String expectedStart = message1.getMessageID().substring(0,2) + ":" + message1.getMessageID() + ":";
        String actualHash = message1.getMessageHash();
        Assert.assertTrue(actualHash.startsWith(expectedStart), "Message hash should start with 'ID first two digits:ID:'");
    }

    // --- Test message ID generation ---
    @Test
    public void testMessageID() {
        Assert.assertTrue(message1.getMessageID().length() == 10, "Message ID should be 10 digits long");
    }

    // --- Test sending message ---
    @Test
    public void testSendMessage() {
        // This will only check that sendMessage() does not throw exceptions
        message1.sendMessage();
    }
}
    
