package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * GUI window for managing client-related actions such as adding a new client or viewing, editing, and deleting existing clients.
 * <p>
 * It provides buttons to navigate to the respective forms.
 */
public class ClientManagementForm extends JFrame {
    private JButton addClientButton, viewEditDeleteButton, closeButton;

    /**
     * Creates the client management form window, setting up layout, components, and event listeners.
     */
    public ClientManagementForm() {
        setTitle("Client Management");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Client Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);

        closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> handleClose());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(closeButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        addClientButton = new JButton("Add Client");
        viewEditDeleteButton = new JButton("View, Edit, Delete");

        buttonPanel.add(addClientButton);
        buttonPanel.add(viewEditDeleteButton);

        addClientButton.addActionListener(e -> openAddProductForm());
        viewEditDeleteButton.addActionListener(e -> openViewEditDeleteForm());

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setWindowProperties(400, 150);
    }

    /**
     * Opens the form to add a new client.
     */
    private void openAddProductForm() {
        AddClientForm addForm = new AddClientForm();
        addForm.setVisible(true);
    }

    /**
     * Opens the form to view, edit, or delete existing clients.
     */
    private void openViewEditDeleteForm() {
        ViewEditDeleteClientForm viewForm = new ViewEditDeleteClientForm();
        viewForm.setVisible(true);
    }

    /**
     * Handles closing the form window.
     */
    private void handleClose() {
        setVisible(false);
        dispose();
    }

    /**
     * Sets window properties including size, default close operation, and centering.
     *
     * @param width is the width of the window.
     * @param height is the height of the window.
     */
    private void setWindowProperties(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Main method to run the form independently.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientManagementForm form = new ClientManagementForm();
            form.setVisible(true);
        });
    }
}
