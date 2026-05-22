package model;

/**
 * Model class for Order objects.
 * Contains getters and setters for each attribute.
 */
public class Order {
    private int id;
    private String client;
    private String product;
    private int nr_of_products;
    private double total_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNr_of_products() {
        return nr_of_products;
    }

    public void setNr_of_products(int nr_of_products) {
        this.nr_of_products = nr_of_products;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
