package presentation;

import business_logic.ProductBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI form that allows users to input product details to be added to the database.
 */
public class AddProductForm extends JFrame {

    private JTextField nameField, stockField, priceField;
    private JButton addButton, clearButton, closeButton;

    /**
     * Creates the Add Product form window, setting up layout, components, and event listeners.
     */
    public AddProductForm() {
        setTitle("Add Product");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Add Product");
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
        stockField = new JTextField(20);
        priceField = new JTextField(20);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 10, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Stock:"));
        formPanel.add(stockField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);

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
        stockField.addKeyListener(fieldValidator);
        priceField.addKeyListener(fieldValidator);

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setWindowProperties(400, 300);
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
        setLocationRelativeTo(null);
    }

    /**
     * Validates if all required fields are filled in.
     * Enables or disables the add button accordingly.
     */
    private void validateFields() {
        boolean valid = !nameField.getText().isEmpty()
                && !stockField.getText().isEmpty()
                && !priceField.getText().isEmpty();
        addButton.setEnabled(valid);
    }

    /**
     * Handles the "Add" button logic: adds the client via BLL and clears the form.
     */
    private void handleAdd() {
        ProductBLL bll = new ProductBLL();
        bll.addProduct(nameField.getText(), stockField.getText(), priceField.getText());
        handleClear();
    }

    /**
     * Clears all text fields and disables the add button.
     */
    private void handleClear() {
        nameField.setText("");
        stockField.setText("");
        priceField.setText("");
        addButton.setEnabled(false);
    }

    /**
     * Launches the form independently for testing.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddProductForm form = new AddProductForm();
            form.setVisible(true);
        });
    }
}
