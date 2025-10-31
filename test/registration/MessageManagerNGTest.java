/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package registration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author RC_Student_Lab
 */
public class MessageManagerNGTest {
  private MessageManager manager;
    private Registration reg;

    @BeforeMethod
    public void setUp() {
        reg = new Registration(); // or mock if needed
        manager = new MessageManager(reg);
    }

    @Test
    public void testAddMessage() {
        Message msg = new Message("Alice", "Hello");
        manager.addMessage(msg, "sent");
        assertEquals(manager.getSentMessages().size(), 1, "Sent messages size should be 1");
        assertEquals(manager.getSentMessages().get(0).getText(), "Hello");
        assertEquals(manager.getSentMessages().get(0).getRecipient(), "Alice");
    }

    @Test
    public void testDeleteMessage() {
        Message msg = new Message("Bob", "Test Delete");
        manager.addMessage(msg, "sent");
        String hash = msg.getMessageHash();
        manager.deleteByHash(hash);
        assertEquals(manager.getSentMessages().size(), 0, "Sent messages size should be 0 after deletion");
    }

    @Test
    public void testSearchByRecipient() {
        Message msg1 = new Message("Alice", "Hi Alice");
        Message msg2 = new Message("Bob", "Hi Bob");
        manager.addMessage(msg1, "sent");
        manager.addMessage(msg2, "sent");

        // Just call search methods to ensure no exceptions
        manager.searchByRecipient("Alice");
        manager.searchByRecipient("Bob");
        manager.searchByRecipient("Charlie"); // recipient not present
    }

    @Test
    public void testSearchByMessageID() {
        Message msg = new Message("Alice", "Check ID");
        manager.addMessage(msg, "sent");

        // Should find the message
        manager.searchByMessageID(msg.getMessageID());
        // Test non-existing ID
        manager.searchByMessageID("0000000000");
    }

    @Test
    public void testDisplayReport() {
        Message msg = new Message("Alice", "Report Test");
        manager.addMessage(msg, "sent");

        // Should not throw exception
        manager.displayReport();
    }

    @Test
    public void testDisplayLongestMessage() {
        Message msg1 = new Message("Alice", "Short");
        Message msg2 = new Message("Bob", "This is a longer message");
        manager.addMessage(msg1, "sent");
        manager.addMessage(msg2, "sent");

        manager.displayLongestMessage(); // Should pick msg2
    }
}