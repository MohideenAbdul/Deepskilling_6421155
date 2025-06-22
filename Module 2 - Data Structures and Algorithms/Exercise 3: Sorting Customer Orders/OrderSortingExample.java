import java.util.Scanner;

public class OrderSortingExample {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "Mohideen", 275.50),
            new Order("O002", "Abdul", 1200.00),
            new Order("O003", "Aziz", 499.99),
            new Order("O004", "Module", 99.00),
            new Order("O005", "Superset", 750.25)
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose sorting algorithm: 1 for Bubble Sort, 2 for Quick Sort");
        int choice = scanner.nextInt();

        if (choice == 1) {
            bubbleSort(orders);
            System.out.println("Sorted by Bubble Sort:");
        } else if (choice == 2) {
            quickSort(orders, 0, orders.length - 1);
            System.out.println("Sorted by Quick Sort:");
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        for (Order o : orders) {
            System.out.println(o.getOrderId() + " - " + o.getCustomerName() + " - $" + o.getTotalPrice());
        }
        // scanner.close(); // Not closed to avoid closing System.in
    }

    static void bubbleSort(Order[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getTotalPrice() > arr[j + 1].getTotalPrice()) {
                    Order temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void quickSort(Order[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Order[] arr, int low, int high) {
        double pivot = arr[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].getTotalPrice() < pivot) {
                i++;
                Order temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Order temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getTotalPrice() { return totalPrice; }
}
