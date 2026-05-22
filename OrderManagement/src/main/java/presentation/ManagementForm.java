package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * The main GUI window for managing clients, products, and orders.
 * It provides buttons to navigate to specific forms for managing each entity.
 * <p>
 * This form is the entry point of the application.
 */
public class ManagementForm extends JFrame {

    /**
     * Creates the management form window with buttons for Clients, Products, and Orders.
     * Initializes the layout, adds event listeners, and displays the frame.
     */
    public ManagementForm() {
        setTitle("Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel titleLabel = new JLabel("Management");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton clientsButton = new JButton("Clients");
        clientsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clientsButton.addActionListener(e -> openClientManagementForm());

        JButton productsButton = new JButton("Products");
        productsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productsButton.addActionListener(e -> openProductManagementForm());

        JButton ordersButton = new JButton("Orders");
        ordersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ordersButton.addActionListener(e -> openPlaceOrderForm());

        mainPanel.add(clientsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(productsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(ordersButton);

        add(mainPanel);
        setVisible(true);
    }

    /**
     * Opens the form for managing clients.
     */
    private void openClientManagementForm() {
        ClientManagementForm addForm = new ClientManagementForm();
        addForm.setVisible(true);
    }

    /**
     * Opens the form for managing products.
     */
    private void openProductManagementForm() {
        ProductManagementForm addForm = new ProductManagementForm();
        addForm.setVisible(true);
    }

    /**
     * Opens the form for placing orders.
     */
    private void openPlaceOrderForm() {
        PlaceOrderForm addForm = new PlaceOrderForm();
        addForm.setVisible(true);
    }

    /**
     * Main method that launches the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManagementForm::new);
    }
}
