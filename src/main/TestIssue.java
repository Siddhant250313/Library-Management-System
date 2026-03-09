package main;

import java.util.*;
import java.sql.Date;
import dao.IssueDAO;
import Model.Issue;

public class TestIssue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IssueDAO dao = new IssueDAO();

        while (true) {
            System.out.println("\n===== Issue Menu =====");
            System.out.println("1. Add Issue");
            System.out.println("2. View All Issues");
            System.out.println("3. Update Issue");
            System.out.println("4. Delete Issue");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bid = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Issue Date (yyyy-mm-dd): ");
                    String issueDateStr = sc.nextLine();
                    System.out.print("Return Date (yyyy-mm-dd): ");
                    String returnDateStr = sc.nextLine();
                    System.out.print("Status (Issued/Returned/Overdue): ");
                    String status = sc.nextLine();

                    Date issueDate = Date.valueOf(issueDateStr);
                    Date returnDate = Date.valueOf(returnDateStr);

                    Issue i = new Issue(sid, bid, issueDate, returnDate, status);
                    dao.addIssue(i);
                    break;

                case 2:
                    List<Issue> list = dao.getAllIssues();
                    System.out.println("\nID | Student_ID | Book_ID | Issue_Date | Return_Date | Status");
                    System.out.println("---------------------------------------------------------------");
                    for (Issue issue : list) {
                        System.out.println(issue.getIssueId() + " | " + issue.getStudentId() + " | " +
                                           issue.getBookId() + " | " + issue.getIssueDate() + " | " +
                                           issue.getReturnDate() + " | " + issue.getStatus());
                    }
                    break;

                case 3:
                    System.out.print("Enter Issue ID to update: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Return Date (yyyy-mm-dd): ");
                    String rDateStr = sc.nextLine();
                    System.out.print("New Status (Issued/Returned/Overdue): ");
                    String newStatus = sc.nextLine();
                    dao.updateIssue(idU, Date.valueOf(rDateStr), newStatus);
                    break;

                case 4:
                    System.out.print("Enter Issue ID to delete: ");
                    int idD = sc.nextInt();
                    dao.deleteIssue(idD);
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
