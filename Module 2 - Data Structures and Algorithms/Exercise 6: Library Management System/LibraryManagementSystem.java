import java.util.Arrays;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "The Psychology of Money", "Morgan Housel"),
            new Book("B002", "Atomic Habits", "James Clear"),
            new Book("B003", "Two States", "Chetan Bhagat"),
            new Book("B004", "Harry Potter and the Philosopher's Stone", "J.K. Rowling"),
            new Book("B005", "The Alchemist", "Paulo Coelho")
        };

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Linear Search by Title\n2. Binary Search by Title\n3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter book title to search: ");
                String title = scanner.nextLine();
                Book result = linearSearchByTitle(books, title);
                if (result != null)
                    System.out.println("Found: " + result.getBookId() + ", " + result.getTitle() + ", " + result.getAuthor());
                else
                    System.out.println("Book not found.");
            } else if (option == 2) {
                Arrays.sort(books, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
                System.out.print("Enter book title to search: ");
                String title = scanner.nextLine();
                Book result = binarySearchByTitle(books, title);
                if (result != null)
                    System.out.println("Found: " + result.getBookId() + ", " + result.getTitle() + ", " + result.getAuthor());
                else
                    System.out.println("Book not found.");
            } else if (option == 3) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        // scanner.close();
        System.out.println("Exiting Library Management System.");
    }

    static Book linearSearchByTitle(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    static Book binarySearchByTitle(Book[] books, String title) {
        int left = 0, right = books.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(title);
            if (cmp == 0) return books[mid];
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}

class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
}
