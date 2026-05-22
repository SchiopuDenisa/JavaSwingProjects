package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * GUI window for managing product operations such as adding new products or viewing, editing, deleting existing products.
 */
public class ProductManagementForm extends JFrame {

    private JButton addProductButton, viewEditDeleteButton, closeButton;

    /**
     * Creates the product management form with UI elements and action listeners.
     */
    public ProductManagementForm() {
        setTitle("Product Management");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Product Management");
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
        addProductButton = new JButton("Add Product");
        viewEditDeleteButton = new JButton("View, Edit, Delete");

        buttonPanel.add(addProductButton);
        buttonPanel.add(viewEditDeleteButton);

        addProductButton.addActionListener(e -> openAddProductForm());
        viewEditDeleteButton.addActionListener(e -> openViewEditDeleteForm());

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setWindowProperties(400, 150);
    }

    /**
     * Opens the form for adding a new product.
     */
    private void openAddProductForm() {
        AddProductForm addForm = new AddProductForm();
        addForm.setVisible(true);
    }

    /**
     * Opens the form for viewing, editing, or deleting products.
     */
    private void openViewEditDeleteForm() {
        ViewEditDeleteProductForm viewForm = new ViewEditDeleteProductForm();
        viewForm.setVisible(true);
    }

    /**
     * Closes and disposes of the current form.
     */
    private void handleClose() {
        setVisible(false);
        dispose();
    }

    /**
     * Sets the size, close operation, and center position for the window.
     *
     * @param width is the width of the window
     * @param height is the height of the window
     */
    private void setWindowProperties(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
    }

    /**
     * Main method for executing the form independently.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductManagementForm form = new ProductManagementForm();
            form.setVisible(true);
        });
    }
}

