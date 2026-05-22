package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO class for product related operations in the database.
 * Inherits generic CRUD operations from AbstractDAO.
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Retrieves a product from the database by its name.
     *
     * @param name is the name of the product being searched.
     * @return the Product object if found, or null if not found.
     */
    public Product getProductByName(String name) {
        String query = "SELECT * FROM product WHERE name = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}