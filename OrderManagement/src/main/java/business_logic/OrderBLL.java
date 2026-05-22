package business_logic;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Business Logic Layer class for handling order related operations.
 * <p>
 * Manages the process of placing orders, calculating the total price, and filling the combo boxes with client and product data.
 */
public class OrderBLL {

    /**
     * Places an order .
     * <p>
     * Validates the quantity and product availability, calculates the total price, saves the order to the database,
     * and updates the product stock.
     * Shows error message if the stock is insufficient, if the quantity of product is not a valid number or another error occurred.
     *
     * @param client is the name of the client placing the order.
     * @param productName is the name of the product being ordered.
     * @param nrOfProducts is the number of products that were ordered.
     * @param price is the unit price of the ordered product.
     */
    public static void placeOrder(String client, String productName, String nrOfProducts, String price) {
        try {
            int quantity = Integer.parseInt(nrOfProducts);
            double unitPrice = Double.parseDouble(price);

            Product product = new ProductDAO().getProductByName(productName);

            if (product == null) {
                JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (quantity > product.getStock()) {
                JOptionPane.showMessageDialog(null,
                        "Not enough stock. Current stock for " + productName + " is: " + product.getStock(),
                        "Stock Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalPrice = unitPrice * quantity;

            Order order = new Order();
            order.setClient(client);
            order.setProduct(productName);
            order.setNr_of_products(quantity);
            order.setTotal_price(totalPrice);

            new OrderDAO().add(order);

            int newStock = product.getStock() - quantity;
            new ProductBLL().updateProduct(
                    String.valueOf(product.getId()),
                    product.getName(),
                    String.valueOf(newStock),
                    String.valueOf(product.getPrice())
            );

            Bill bill = new Bill(client, productName, quantity, (unitPrice * quantity), java.time.LocalDateTime.now());
            new dao.BillDAO().insert(bill);

            JOptionPane.showMessageDialog(null,
                    "Order placed successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid number for quantity.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retrieves the unit price of a product by its name.
     *
     * @param productName the name of the product being searched for.
     * @return the unit price of the product, or 0.0 if the product is not found.
     */
    public static double getProductPrice(String productName) {
        Product product = new ProductDAO().getProductByName(productName);
        return product != null ? product.getPrice() : 0.0;
    }

    /**
     * Updates the final price field in the GUI based on the unit price and quantity.
     * <p>
     * The final price field is cleared if inputs are invalid.
     *
     * @param priceField is the JTextField containing the unit price of the ordered product.
     * @param quantityField is the JTextField containing the quantity of product.
     * @param finalPriceField is the JTextField to display the calculated final price (quantity x unit price).
     */
    public static void updateFinalPrice(JTextField priceField, JTextField quantityField, JTextField finalPriceField) {
        try {
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            finalPriceField.setText(String.valueOf(price * quantity));
        } catch (NumberFormatException e) {
            finalPriceField.setText("");
        }
    }

    /**
     * Fills the combo box with the names of all products in the database.
     *
     * @param productComboBox the JComboBox to be filled.
     */
    public static void populateComboBoxWithProducts(JComboBox<String> productComboBox) {
        for (Product product : new ProductDAO().findAll()) {
            productComboBox.addItem(product.getName());
        }
    }

    /**
     * Fills the combo box with the names of all clients in the database.
     *
     * @param clientComboBox the JComboBox to be filled.
     */
    public static void populateComboBoxWithClients(JComboBox<String> clientComboBox) {
        for (Client client : new ClientDAO().findAll()) {
            clientComboBox.addItem(client.getName());
        }
    }
}
