/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registration;


import java.util.regex.Pattern;

public class Registration {
    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    // --- Username validation ---
    public static boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // --- Password validation ---
    public static boolean checkPassword(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*()_+-=<>?/".indexOf(c) >= 0) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    // --- Cell phone validation (SA numbers) ---
    public static boolean checkCellNumber(String cellNumber) {
        String regex = "^\\+\\d{1,3}\\d{9,10}$";
        return cellNumber != null && Pattern.matches(regex, cellNumber);
    }

    // --- Registration method ---
    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        StringBuilder errors = new StringBuilder();

        if (!checkUserName(username)) {
            errors.append("Username is not correctly formatted, please ensure it contains an underscore and is no more than 5 characters long.\n");
        }

        if (!checkPassword(password)) {
            errors.append("Password is not correctly formatted, please ensure it has at least 8 characters, a capital letter, a number, and a special character.\n");
        }

        if (!checkCellNumber(cellNumber)) {
            errors.append("Cell phone number is not correctly formatted, please ensure it contains the international country code and no more than 10 digits.\n");
        }

        if (errors.length() > 0) {
            return errors.toString();
        }

        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        return "Registration successful for " + firstName + " " + lastName + ".";
    }

    // --- Getters ---
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}