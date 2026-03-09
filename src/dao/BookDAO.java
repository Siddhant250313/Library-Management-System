package dao;

import java.sql.*;
import java.util.*;
import Connection1.DBConn;
import Model.Book;

public class BookDAO {
    Connection conn = DBConn.getConnection();

    // CREATE — Add new book
    public void addBook(Book b) {
        String sql = "INSERT INTO books (Title, Author, Category, Quantity, Price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getCategory());
            ps.setInt(4, b.getQuantity());
            ps.setInt(5, b.getPrice());
            ps.executeUpdate();
            System.out.println("✅ Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ — Get all books
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Book b = new Book(
                    rs.getInt("Book_ID"),
                    rs.getString("Title"),
                    rs.getString("Author"),
                    rs.getString("Category"),
                    rs.getInt("Quantity"),
                    rs.getInt("Price")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE — Modify book details
    public void updateBook(int id, String title, String author, String category, int quantity, int price) {
        String sql = "UPDATE books SET Title=?, Author=?, Category=?, Quantity=?, Price=? WHERE Book_ID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setInt(4, quantity);
            ps.setInt(5, price);
            ps.setInt(6, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Book updated successfully!");
            else
                System.out.println("⚠️ No book found with ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE — Remove a book
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE Book_ID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Book deleted successfully!");
            else
                System.out.println("⚠️ No book found with ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
