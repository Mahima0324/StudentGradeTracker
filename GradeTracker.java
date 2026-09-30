import java.util.*;
import java.io.*;

// Student class
class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    // Method to return grade category
    String getGradeCategory() {
        if (grade >= 90) return "A";
        else if (grade >= 75) return "B";
        else if (grade >= 60) return "C";
        else if (grade >= 40) return "D";
        else return "F";
    }
}

// Main class
public class GradeTracker {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Student Grade Tracker ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Report");
            System.out.println("3. Save Report to File");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewReport();
                case 3 -> saveReport();
                case 4 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter grade: ");
        int grade = sc.nextInt();
        sc.nextLine();
        students.add(new Student(name, grade));
        System.out.println("Student added successfully!");
    }

    static void viewReport() {
        if (students.isEmpty()) {
            System.out.println("No students added yet!");
            return;
        }

        int sum = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        System.out.println("\n--- Report ---");
        for (Student s : students) {
            System.out.println(s.name + " : " + s.grade + " (" + s.getGradeCategory() + ")");
            sum += s.grade;
            if (s.grade > highest) { highest = s.grade; topStudent = s.name; }
            if (s.grade < lowest) { lowest = s.grade; lowStudent = s.name; }
        }

        double average = (double) sum / students.size();
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest + " (" + topStudent + ")");
        System.out.println("Lowest Score: " + lowest + " (" + lowStudent + ")");
    }

    static void saveReport() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("GradeReport.txt"))) {
            pw.println("=== Student Grade Report ===");
            for (Student s : students) {
                pw.println(s.name + " : " + s.grade + " (" + s.getGradeCategory() + ")");
            }
            System.out.println("Report saved to GradeReport.txt");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
