package main;

import java.util.*;
import dao.StudentDAO;
import Model.Student;

public class TestStudent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n===== Students Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Class: ");
                    String cls = sc.nextLine();
                    System.out.print("RollNo: ");
                    String roll = sc.nextLine();
                    Student s = new Student(name, cls, roll);
                    dao.addStudent(s);
                    break;

                case 2:
                    List<Student> students = dao.getAllStudents();
                    System.out.println("\nID | Name | Class | RollNo");
                    System.out.println("-----------------------------");
                    for (Student st : students)
                        System.out.println(st.getStudentId() + " | " + st.getName() + " | " +
                                           st.getStudentClass() + " | " + st.getRollNo());
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String n = sc.nextLine();
                    System.out.print("New Class: ");
                    String c = sc.nextLine();
                    System.out.print("New RollNo: ");
                    String r = sc.nextLine();
                    dao.updateStudent(idU, n, c, r);
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    int idD = sc.nextInt();
                    dao.deleteStudent(idD);
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }
}
