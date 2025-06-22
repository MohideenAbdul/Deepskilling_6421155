import java.util.HashMap;

public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.addProduct(new Product("P001", "Laptop", 10, 1200.0));
        manager.addProduct(new Product("P002", "Mouse", 50, 25.0));
        manager.addProduct(new Product("P003", "Keyboard", 30, 45.0));

        Product p = manager.getProduct("P001");
        System.out.println(p.getProductName() + ": " + p.getQuantity() + " units, Rs " + p.getPrice());

        manager.updateProduct("P002", 60, 24.0);
        Product p2 = manager.getProduct("P002");
        System.out.println(p2.getProductName() + ": " + p2.getQuantity() + " units, Rs " + p2.getPrice());

        manager.deleteProduct("P003");
        Product p3 = manager.getProduct("P003");
        System.out.println(p3 == null ? "Product P003 not found" : p3.getProductName());
    }
}

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
}

class InventoryManager {
    private HashMap<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(String productId, int quantity, double price) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(quantity);
            product.setPrice(price);
        }
    }

    public void deleteProduct(String productId) {
        inventory.remove(productId);
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }
}
