package dao;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDAO {

    /**
     * Inserts a generated bill into the log table.
     */
    public void insert(Bill bill) {
        String query = "INSERT INTO log (client_name, product_name, quantity, total_price, order_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bill.clientName());
            stmt.setString(2, bill.productName());
            stmt.setInt(3, bill.quantity());
            stmt.setDouble(4, bill.totalPrice());
            stmt.setObject(5, bill.orderDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}