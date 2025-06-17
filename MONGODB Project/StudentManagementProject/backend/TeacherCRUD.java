package Connection;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.Scanner;

public class TeacherCRUD {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("Student_Management");
        MongoCollection<Document> students = db.getCollection("BTECHstudents");

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Teacher: Marks Management ======");
            System.out.println("1. Add/Update Student Marks");
            System.out.println("2. View Student Marks");
            System.out.println("3. Delete Student Marks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    updateStudentMarks(students);
                    break;
                case 2:
                    viewStudentMarks(students);
                    break;
                case 3:
                    deleteStudentMarks(students);
                    break;
                case 4:
                    System.out.println("👋 Exiting...");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice.");
            }
        } while (choice != 4);

        mongoClient.close();
        sc.close();
    }

    // Function to Add or Update Student Marks
    public static void updateStudentMarks(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Roll Number: ");
        String rollNo = sc.nextLine();

        System.out.print("Enter Subject Name: ");
        String subject = sc.nextLine();

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        Document query = new Document("rollNo", rollNo);
        Document update = new Document("$set", new Document("marks." + subject, marks));
        collection.updateOne(query, update);

        System.out.println("✅ Marks updated for subject: " + subject);
    }

    // Function to View Student Marks
    public static void viewStudentMarks(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Roll Number: ");
        String rollNo = sc.nextLine();

        Document query = new Document("rollNo", rollNo);
        Document student = collection.find(query).first();

        if (student != null && student.containsKey("marks")) {
            System.out.println("📘 Marks for Roll No: " + rollNo);
            Document marks = (Document) student.get("marks");
            for (String subject : marks.keySet()) {
                System.out.println(subject + ": " + marks.get(subject));
            }
        } else {
            System.out.println("⚠️ No marks found for this student.");
        }
    }

    // Function to Delete Student Marks (for a subject or all)
    public static void deleteStudentMarks(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Roll Number: ");
        String rollNo = sc.nextLine();

        System.out.print("Enter Subject Name to delete (or 'all' to remove all marks): ");
        String subject = sc.nextLine();

        Document query = new Document("rollNo", rollNo);
        Document update;

        if (subject.equalsIgnoreCase("all")) {
            update = new Document("$unset", new Document("marks", ""));
        } else {
            update = new Document("$unset", new Document("marks." + subject, ""));
        }

        collection.updateOne(query, update);
        System.out.println("🗑️ Marks deleted.");
    }
}