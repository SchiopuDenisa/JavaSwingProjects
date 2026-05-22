package business_logic;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;

/**
 * Business Logic Layer for handling product related operations.
 * <p>
 * Provides validation and business rule enforcement before performing operations (adding, updating, or deleting products)
 * using the ProductDAO.
 */
public class ProductBLL {

    /**
     * Adds a new product to the database after validating and parsing the input data.
     * <p>
     * Creates a Product object and calls the addition operation.
     * Shows error message for invalid inputs.
     *
     * @param name is the name of the product.
     * @param stockStr is the stock quantity as a string.
     * @param priceStr is the price as a string.
     */
    public void addProduct(String name, String stockStr, String priceStr) {
        try {
            int stock = Integer.parseInt(stockStr);
            double price = Double.parseDouble(priceStr);

            if (price <= 0) {
                JOptionPane.showMessageDialog(null, "Price must be strictly greater than 0.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product product = new Product();
            product.setName(name);
            product.setStock(stock);
            product.setPrice(price);

            new ProductDAO().add(product);
            JOptionPane.showMessageDialog(null, "Added Successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format for stock or price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates an existing product in the database after validating and parsing the input data.
     *<p>
     * Creates the Product object that holds the updated information and calls the update operation.
     * Shows error message for invalid inputs.
     *
     * @param idStr is the ID of the product as a string.
     * @param name is the new name of the product.
     * @param stockStr is the new stock quantity as a string.
     * @param priceStr is the new price as a string.
     */
    public void updateProduct(String idStr, String name, String stockStr, String priceStr) {
        try {
            int id = Integer.parseInt(idStr);
            int stock = Integer.parseInt(stockStr);
            double price = Double.parseDouble(priceStr);

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setStock(stock);
            product.setPrice(price);

            new ProductDAO().update(product);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deletes a product from the database using the product ID.
     *<p>
     * Calls the delete operation to delete the product from the database.
     * Shows an error if the ID is not a valid integer.
     *
     * @param idStr is the ID of the product being deleted.
     */
    public void deleteProduct(String idStr) {
        try {
            new ProductDAO().delete(Integer.parseInt(idStr));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
