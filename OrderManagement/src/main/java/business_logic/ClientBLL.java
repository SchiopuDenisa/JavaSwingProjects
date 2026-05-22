package business_logic;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;


/**
 * Business Logic Layer class for client related operations.
 * <p>
 * Provides validation and business rule enforcement before performing operations (adding, updating, or deleting clients)
 */

public class ClientBLL {

    /**
     * Updates an existing client's information in the database.
     * <p>
     * Creates the Client object that holds the updated information and calls the update operation.
     * Shows an error message if the age is not a number.
     *
     * @param id is the client's ID as a string.
     * @param name is the client's name.
     * @param email is the client's email.
     * @param address is the client's address.
     * @param age is the client's age as a string.
     */
    public void updateClient(String id, String name, String email, String address, String age) {
        try {
            Client client = new Client();
            client.setId(Integer.parseInt(id));
            client.setName(name);
            client.setEmail(email);
            client.setAddress(address);
            client.setAge(Integer.parseInt(age));
            new ClientDAO().update(client);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        }
    }

    /**
     * Deletes a client based on their ID.
     * <p>
     * Calls the delete operation.
     * Shows an error if the ID is not a valid integer.
     *
     * @param id is the client's ID as a string.
     */
    public void deleteClient(String id) {
        try {
            new ClientDAO().delete(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format: " + e.getMessage());
        }
    }

    /**
     * Adds a new client to the database after validating the input.
     * <p>
     * Verifies that the email has a valid format (_@_._) and that the age is numeric.
     * Creates a Client object and calls the addition operation to add it to the database.
     * Shows error dialogs for invalid inputs.
     *
     * @param name is the client's name
     * @param email is the client's email
     * @param address is the client's address
     * @param ageStr is the client's age as a string
     */
    public void addClient(String name, String email, String address, String ageStr) {
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            JOptionPane.showMessageDialog(null, "Invalid email format.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            Client client = new Client();
            client.setName(name);
            client.setEmail(email);
            client.setAddress(address);
            client.setAge(age);

            new ClientDAO().add(client);
            JOptionPane.showMessageDialog(null, "Added Successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Age must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
