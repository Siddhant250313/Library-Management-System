package dao;

import java.sql.*;
import java.util.*;
import java.sql.Date;
import Connection1.DBConn;
import Model.Issue;

public class IssueDAO {
    Connection conn = DBConn.getConnection();

    // CREATE — Issue a new book
    public void addIssue(Issue i) {
        String sql = "INSERT INTO issues (Student_ID, Book_ID, Issue_date, Return_date, Status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, i.getStudentId());
            ps.setInt(2, i.getBookId());
            ps.setDate(3, i.getIssueDate());
            ps.setDate(4, i.getReturnDate());
            ps.setString(5, i.getStatus());
            ps.executeUpdate();
            System.out.println("✅ Book issued successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ — View all issued books
    public List<Issue> getAllIssues() {
        List<Issue> list = new ArrayList<>();
        String sql = "SELECT * FROM issues";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Issue i = new Issue(
                    rs.getInt("Issue_ID"),
                    rs.getInt("Student_ID"),
                    rs.getInt("Book_ID"),
                    rs.getDate("Issue_date"),
                    rs.getDate("Return_date"),
                    rs.getString("Status")
                );
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE — Change status or return date
    public void updateIssue(int issueId, Date returnDate, String status) {
        String sql = "UPDATE issues SET Return_date=?, Status=? WHERE Issue_ID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, returnDate);
            ps.setString(2, status);
            ps.setInt(3, issueId);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Issue updated successfully!");
            else
                System.out.println("⚠️ No record found with ID " + issueId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE — Remove issue record (optional)
    public void deleteIssue(int issueId) {
        String sql = "DELETE FROM issues WHERE Issue_ID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, issueId);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Issue record deleted!");
            else
                System.out.println("⚠️ No record found with ID " + issueId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
