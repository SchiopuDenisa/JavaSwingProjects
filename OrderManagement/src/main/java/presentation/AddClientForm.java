package presentation;

import business_logic.ClientBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI form that allows users to input client details to be added to the database.
 */
public class AddClientForm extends JFrame {

    private JTextField nameField, emailField, addressField, ageField;
    private JButton addButton, clearButton, closeButton;

    /**
     * Creates the Add Client form window, setting up layout, components, and event listeners.
     */
    public AddClientForm() {
        setTitle("Add Client");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Add Client");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);

        closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> setVisible(false));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(closeButton, BorderLayout.EAST);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        addressField = new JTextField(20);
        ageField = new JTextField(20);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 1, 10, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);

        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
        addButton.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        addButton.addActionListener(e -> handleAdd());
        clearButton.addActionListener(e -> handleClear());

        KeyAdapter fieldValidator = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateFields();
            }
        };
        nameField.addKeyListener(fieldValidator);
        emailField.addKeyListener(fieldValidator);
        addressField.addKeyListener(fieldValidator);
        ageField.addKeyListener(fieldValidator);

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setWindowProperties(400, 350);
    }

    /**
     * Configures window size, position, and default close behavior.
     *
     * @param width is the width of the form
     * @param height is the height of the form
     */
    private void setWindowProperties(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);  // Centers window
    }

    /**
     * Validates if all required fields are filled in.
     * Enables or disables the add button accordingly.
     */
    private void validateFields() {
        boolean valid = !nameField.getText().isEmpty()
                && !emailField.getText().isEmpty()
                && !addressField.getText().isEmpty()
                && !ageField.getText().isEmpty();
        addButton.setEnabled(valid);
    }

    /**
     * Handles the "Add" button logic: adds the client via BLL and clears the form.
     */
    private void handleAdd() {
        ClientBLL bll = new ClientBLL();
        bll.addClient(
                nameField.getText(),
                emailField.getText(),
                addressField.getText(),
                ageField.getText()
        );
        handleClear();
    }

    /**
     * Clears all text fields and disables the add button.
     */
    private void handleClear() {
        nameField.setText("");
        emailField.setText("");
        addressField.setText("");
        ageField.setText("");
        addButton.setEnabled(false);
    }

    /**
     * Launches the form independently for testing.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddClientForm form = new AddClientForm();
            form.setVisible(true);
        });
    }
}
