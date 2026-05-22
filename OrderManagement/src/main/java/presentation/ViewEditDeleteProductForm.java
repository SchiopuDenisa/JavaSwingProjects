package presentation;

import business_logic.ProductBLL;
import business_logic.ReflectionTable;
import dao.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI form for viewing, editing, and deleting products from a database.
 */
public class ViewEditDeleteProductForm extends JFrame {

    private JLabel idLabel, nameLabel, stockLabel, priceLabel;
    private JTextField idField, nameField, stockField, priceField;
    private JButton updateButton, deleteButton, clearButton, closeButton;
    private JTable productTable;

    /**
     * Creates the form window, setting up layout, components, and event listeners.
     */
    public ViewEditDeleteProductForm() {
        setTitle("View, Edit & Delete");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("View, Edit & Delete");
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

        stockLabel = new JLabel("Stock:");
        stockField = new JTextField(20);

        priceLabel = new JLabel("Price:");
        priceField = new JTextField(20);

        formPanel.add(idLabel);     formPanel.add(idField);
        formPanel.add(nameLabel);   formPanel.add(nameField);
        formPanel.add(stockLabel);  formPanel.add(stockField);
        formPanel.add(priceLabel);  formPanel.add(priceField);

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

        productTable = new JTable();
        refreshTable();

        JScrollPane tableScrollPane = new JScrollPane(productTable);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setWindowProperties(800, 400);

        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                Object id = productTable.getValueAt(selectedRow, 0);
                Object name = productTable.getValueAt(selectedRow, 1);
                Object stock = productTable.getValueAt(selectedRow, 2);
                Object price = productTable.getValueAt(selectedRow, 3);

                idField.setText(String.valueOf(id));
                nameField.setText(String.valueOf(name));
                stockField.setText(String.valueOf(stock));
                priceField.setText(String.valueOf(price));
            }
        });

        KeyAdapter validationAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateField();
            }
        };

        nameField.addKeyListener(validationAdapter);
        stockField.addKeyListener(validationAdapter);
        priceField.addKeyListener(validationAdapter);
    }

    /**
     * Clears all form fields and disables the Update button.
     */
    private void handleClear() {
        idField.setText("");
        nameField.setText("");
        stockField.setText("");
        priceField.setText("");
        updateButton.setEnabled(false);
    }

    /**
     * Enables the Update button only if all fields are filled.
     */
    private void validateField() {
        boolean isValid = !nameField.getText().isEmpty() &&
                !stockField.getText().isEmpty() &&
                !priceField.getText().isEmpty();
        updateButton.setEnabled(isValid);
    }

    /**
     * Updates the client data using the updateProduct method in ProductBLL.
     * Also clears the form and refreshes the table.
     */
    private void handleUpdate() {
        ProductBLL bll = new ProductBLL();
        bll.updateProduct(idField.getText(), nameField.getText(), stockField.getText(), priceField.getText());
        handleClear();
        refreshTable();
    }

    /**
     * Deletes the selected client using the method in ProductBLL.
     * Also clears the form and refreshes the table.
     */
    private void handleDelete() {
        ProductBLL bll = new ProductBLL();
        bll.deleteProduct(idField.getText());
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
        ReflectionTable.populateTableFromList(productTable, new ProductDAO().findAll());
    }
    /**
     * Launches the form independently for testing.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewEditDeleteProductForm form = new ViewEditDeleteProductForm();
            form.setVisible(true);
        });
    }
}
