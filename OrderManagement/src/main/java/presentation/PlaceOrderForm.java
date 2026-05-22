package presentation;

import business_logic.OrderBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI to place orders by selecting a client, a product, and entering the quantity. The form calculates and displays
 * the final price and allows placing the order.
 */
public class PlaceOrderForm extends JFrame {
    private JComboBox<String> clientComboBox, productComboBox;
    private JTextField priceField, quantityField, finalPriceField;
    private JButton placeOrderButton, closeButton;

    /**
     * Creates the Place Order form with all necessary components and listeners.
     */
    public PlaceOrderForm() {
        setTitle("Place Order");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Place Order", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        closeButton = new JButton("X");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.RED);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(closeButton, BorderLayout.EAST);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        clientComboBox = new JComboBox<>();
        productComboBox = new JComboBox<>();

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.add(new JLabel("Client:"));
        formPanel.add(clientComboBox);
        formPanel.add(new JLabel("Product:"));
        formPanel.add(productComboBox);

        priceField = new JTextField(10);
        priceField.setEditable(false);

        quantityField = new JTextField(10);
        finalPriceField = new JTextField(10);
        finalPriceField.setEditable(false);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        infoPanel.add(new JLabel("Product Price:"));
        infoPanel.add(priceField);
        infoPanel.add(new JLabel("Quantity:"));
        infoPanel.add(quantityField);
        infoPanel.add(new JLabel("Final Price:"));
        infoPanel.add(finalPriceField);

        placeOrderButton = new JButton("Place Order");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(placeOrderButton);

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        OrderBLL.populateComboBoxWithClients(clientComboBox);
        OrderBLL.populateComboBoxWithProducts(productComboBox);

        productComboBox.addActionListener(e -> updatePrice());
        quantityField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                OrderBLL.updateFinalPrice(priceField, quantityField, finalPriceField);
            }
        });

        placeOrderButton.addActionListener(e -> handlePlaceOrder());

        if (productComboBox.getItemCount() > 0) {
            productComboBox.setSelectedIndex(0);
        }
    }

    /**
     * Updates the price field when a new product is selected and recalculates the final price.
     */
    private void updatePrice() {
        String product = (String) productComboBox.getSelectedItem();
        if (product != null) {
            double price = OrderBLL.getProductPrice(product);
            priceField.setText(String.valueOf(price));
            OrderBLL.updateFinalPrice(priceField, quantityField, finalPriceField);
        }
    }

    /**
     * Handles the order placement logic by validating input and calling the method in BLL layer.
     */
    private void handlePlaceOrder() {
        String client = (String) clientComboBox.getSelectedItem();
        String product = (String) productComboBox.getSelectedItem();
        String quantity = quantityField.getText();
        String price = priceField.getText();

        if (client == null || product == null || quantity.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        OrderBLL.placeOrder(client, product, quantity, price);
    }

    /**
     * Launches the form independently for testing.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlaceOrderForm form = new PlaceOrderForm();
            form.setVisible(true);
        });
    }
}
