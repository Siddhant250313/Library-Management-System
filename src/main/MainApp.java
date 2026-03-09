package main;

import java.util.*;
import dao.BookDAO;
import Model.Book;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO dao = new BookDAO();

        while (true) {
            System.out.println("\n===== Library Books Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Price: ");
                    int price = sc.nextInt();
                    Book b = new Book(title, author, category, qty, price);
                    dao.addBook(b);
                    break;

                case 2:
                    List<Book> books = dao.getAllBooks();
                    System.out.println("\nBook_ID | Title | Author | Category | Quantity | Price");
                    System.out.println("-----------------------------------------------------------");
                    for (Book book : books) {
                        System.out.println(book.getBookId() + " | " + book.getTitle() + " | " +
                                book.getAuthor() + " | " + book.getCategory() + " | " +
                                book.getQuantity() + " | ₹" + book.getPrice());
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to update: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Title: ");
                    String t = sc.nextLine();
                    System.out.print("New Author: ");
                    String a = sc.nextLine();
                    System.out.print("New Category: ");
                    String c = sc.nextLine();
                    System.out.print("New Quantity: ");
                    int q = sc.nextInt();
                    System.out.print("New Price: ");
                    int p = sc.nextInt();
                    dao.updateBook(idU, t, a, c, q, p);
                    break;

                case 4:
                    System.out.print("Enter Book ID to delete: ");
                    int idD = sc.nextInt();
                    dao.deleteBook(idD);
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
