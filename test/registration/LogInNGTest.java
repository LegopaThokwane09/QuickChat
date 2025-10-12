/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package registration;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author RC_Student_lab
 */
import static org.testng.Assert.*;
public class LogInNGTest {
    
    private Registration reg;
    private LogIn login;

    @BeforeMethod
    public void setUp() {
        reg = new Registration();
        reg.registerUser("Kyl_1", "Password1!", "+27831234567", "John", "Doe");
        login = new LogIn(reg);
    }

    @Test
    public void testAuthenticateSuccess() {
        assertTrue(login.authenticate("Kyl_1", "Password1!"),
                "Login should succeed with correct credentials.");
    }

    @Test
    public void testAuthenticateWrongPassword() {
        assertFalse(login.authenticate("Kyl_1", "WrongPass"),
                "Login should fail with incorrect password.");
    }

    @Test
    public void testAuthenticateWrongUsername() {
        assertFalse(login.authenticate("wrong_", "Password1!"),
                "Login should fail with incorrect username.");
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        String status = login.returnLoginStatus("Kyl_1", "Password1!");
        assertEquals(status, "Welcome John Doe, it is great to see you.",
                "Login success message should be correct.");
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String status = login.returnLoginStatus("Kyl_1", "WrongPass");
        assertEquals(status, "Username or password incorrect, please try again.",
                "Login failure message should be correct.");
    }
}
 
