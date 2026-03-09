package dao;

import java.sql.*;
import java.util.*;
import Connection1.DBConn;
import Model.Student;

public class StudentDAO {
    Connection conn = DBConn.getConnection();

    // CREATE
    public void addStudent(Student s) {
        String sql = "INSERT INTO students (Name, Class, RollNo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getStudentClass());
            ps.setString(3, s.getRollNo());
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("⚠️ Roll number already exists. Must be unique.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("Student_ID"),
                        rs.getString("Name"),
                        rs.getString("Class"),
                        rs.getString("RollNo")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateStudent(int id, String name, String studentClass, String rollNo) {
        String sql = "UPDATE students SET Name=?, Class=?, RollNo=? WHERE Student_ID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, studentClass);
            ps.setString(3, rollNo);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student updated successfully!");
            else
                System.out.println("⚠️ No student found with ID " + id);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("⚠️ Roll number already exists. Must be unique.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE Student_ID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student deleted successfully!");
            else
                System.out.println("⚠️ No student found with ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
