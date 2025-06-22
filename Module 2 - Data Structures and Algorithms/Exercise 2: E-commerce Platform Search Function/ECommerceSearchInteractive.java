import java.util.Arrays;
import java.util.Scanner;

public class ECommerceSearchInteractive {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Mouse", "Electronics"),
            new Product("P003", "Shirt", "Apparel"),
            new Product("P004", "Book", "Books"),
            new Product("P005", "Phone", "Electronics")
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Available Products:");
        for (Product p : products) {
            System.out.println(p.getProductId() + " - " + p.getProductName() + " (" + p.getCategory() + ")");
        }

        boolean running = true;
        while (running) {
            System.out.print("\nEnter Product ID to search (or type 'exit' to quit): ");
            String searchId = scanner.nextLine();
            if (searchId.equalsIgnoreCase("exit")) {
                running = false;
                break;
            }

            System.out.print("Choose search method (1 for Linear, 2 for Binary): ");
            String methodInput = scanner.nextLine();
            int method;
            try {
                method = Integer.parseInt(methodInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }

            Product result = null;

            if (method == 1) {
                result = linearSearch(products, searchId);
            } else if (method == 2) {
                Arrays.sort(products, (a, b) -> a.getProductId().compareTo(b.getProductId()));
                result = binarySearch(products, searchId);
            } else {
                System.out.println("Invalid option.");
                continue;
            }

            if (result != null) {
                System.out.println("Product found: " + result.getProductName() + " (" + result.getCategory() + ")");
            } else {
                System.out.println("Product not found.");
            }
        }
        // scanner.close(); // Not closed to avoid closing System.in
        System.out.println("Exiting search. Goodbye!");
    }

    static Product linearSearch(Product[] products, String targetId) {
        for (Product product : products) {
            if (product.getProductId().equals(targetId)) {
                return product;
            }
        }
        return null;
    }

    static Product binarySearch(Product[] products, String targetId) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = products[mid].getProductId().compareTo(targetId);
            if (cmp == 0) return products[mid];
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}

class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
}
