/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */
public class MessageManagerNGTest {
   @Test
    public void testSentMessagesArrayPopulated() {
        Registration reg = new Registration();
        reg.registerUser("user1", "Password1!", "+27834567890", "John", "Doe");
        MessageManager manager = new MessageManager(reg);

        Message m1 = new Message("+27834557896", "Did you get the cake?");
        Message m2 = new Message("0838884567", "It is dinner time!");
        manager.addMessage(m1, "sent");
        manager.addMessage(m2, "sent");

        assertEquals(2, manager.getSentMessages().size());
    }

    @Test
    public void testLongestMessage() {
        Registration reg = new Registration();
        reg.registerUser("user1", "Password1!", "+27834567890", "John", "Doe");
        MessageManager manager = new MessageManager(reg);

        Message m1 = new Message("+27834557896", "Short msg");
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        manager.addMessage(m1, "sent");
        manager.addMessage(m2, "sent");

        String expected = "Where are you? You are late! I have asked you to be on time.";
        Message longest = manager.getSentMessages().stream()
                .max((a,b) -> a.getText().length() - b.getText().length())
                .get();
        assertEquals(expected, longest.getText());
    }
}