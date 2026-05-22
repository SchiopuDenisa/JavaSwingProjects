package presentation;

import business_logic.ClientBLL;
import business_logic.ReflectionTable;
import dao.ClientDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI form for viewing, editing, and deleting client records from a database.
 */
public class ViewEditDeleteClientForm extends JFrame {

    private JLabel idLabel, nameLabel, emailLabel, addressLabel, ageLabel;
    private JTextField idField, nameField, emailField, addressField, ageField;
    private JButton updateButton, deleteButton, clearButton, closeButton;
    private JTable clientTable;

    /**
     * Creates the form window, setting up layout, components, and event listeners.
     */
    public ViewEditDeleteClientForm() {
        setTitle("View, Edit & Delete Clients");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("View, Edit & Delete Clients");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);

        closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> handleClose());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(closeButton, BorderLayout.EAST);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        idField.setEditable(false);

        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);

        ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);

        formPanel.add(idLabel);      formPanel.add(idField);
        formPanel.add(nameLabel);    formPanel.add(nameField);
        formPanel.add(emailLabel);   formPanel.add(emailField);
        formPanel.add(addressLabel); formPanel.add(addressField);
        formPanel.add(ageLabel);     formPanel.add(ageField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        updateButton.setEnabled(false);

        updateButton.addActionListener(e -> handleUpdate());
        deleteButton.addActionListener(e -> handleDelete());
        clearButton.addActionListener(e -> handleClear());

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        clientTable = new JTable();
        refreshTable();

        JScrollPane tableScrollPane = new JScrollPane(clientTable);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setWindowProperties(900, 450);

        clientTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow != -1) {
                idField.setText(String.valueOf(clientTable.getValueAt(selectedRow, 0)));
                nameField.setText(String.valueOf(clientTable.getValueAt(selectedRow, 1)));
                emailField.setText(String.valueOf(clientTable.getValueAt(selectedRow, 2)));
                addressField.setText(String.valueOf(clientTable.getValueAt(selectedRow, 3)));
                ageField.setText(String.valueOf(clientTable.getValueAt(selectedRow, 4)));
            }
        });

        KeyAdapter validationAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateField();
            }
        };

        nameField.addKeyListener(validationAdapter);
        emailField.addKeyListener(validationAdapter);
        addressField.addKeyListener(validationAdapter);
        ageField.addKeyListener(validationAdapter);
    }

    /**
     * Clears all form fields and disables the Update button.
     */
    private void handleClear() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        addressField.setText("");
        ageField.setText("");
        updateButton.setEnabled(false);
    }

    /**
     * Enables the Update button only if all fields are filled.
     */
    private void validateField() {
        boolean isValid = !nameField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !addressField.getText().isEmpty() &&
                !ageField.getText().isEmpty();
        updateButton.setEnabled(isValid);
    }

    /**
     * Updates the client data using the updateClient method in ClientBLL.
     * Also clears the form and refreshes the table.
     */
    private void handleUpdate() {
        ClientBLL bll = new ClientBLL();
        bll.updateClient(
                idField.getText(),
                nameField.getText(),
                emailField.getText(),
                addressField.getText(),
                ageField.getText()
        );
        handleClear();
        refreshTable();
    }

    /**
     * Deletes the selected client using the method in ClientBLL.
     * Also clears the form and refreshes the table.
     */
    private void handleDelete() {
        ClientBLL bll = new ClientBLL();
        bll.deleteClient(idField.getText());
        handleClear();
        refreshTable();
    }

    /**
     * Closes the form window by hiding it.
     */
    private void handleClose() {
        setVisible(false);
    }

    /**
     * Sets the size, default close operation, and centers the window.
     *
     * @param width is the width of the form
     * @param height is the height of the form
     */
    private void setWindowProperties(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Reloads the client data into the table using reflection.
     */
    private void refreshTable() {
        ReflectionTable.populateTableFromList(clientTable, new ClientDAO().findAll());
    }
    /**
     * Launches the form independently for testing.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewEditDeleteClientForm form = new ViewEditDeleteClientForm();
            form.setVisible(true);
        });
    }
}
