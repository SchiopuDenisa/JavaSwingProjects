package model;

import java.time.LocalDateTime;

/**
 * Immutable Bill record generated for each finalized order.
 */
public record Bill(String clientName, String productName, int quantity, double totalPrice, LocalDateTime orderDate) {
}