package Model;

public class Student {
    private int studentId;
    private String name;
    private String studentClass;
    private String rollNo;

    // Constructor with ID (for reading from DB)
    public Student(int studentId, String name, String studentClass, String rollNo) {
        this.studentId = studentId;
        this.name = name;
        this.studentClass = studentClass;
        this.rollNo = rollNo;
    }

    // Constructor without ID (for inserting new record)
    public Student(String name, String studentClass, String rollNo) {
        this.name = name;
        this.studentClass = studentClass;
        this.rollNo = rollNo;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentClass() { return studentClass; }
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
}
