package Connection;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.Scanner;

public class StudentCRUD {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("Student_Management");
        MongoCollection<Document> students = db.getCollection("BTECHstudents");

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Student Management ======");
            System.out.println("1. Add Student (Create)");
            System.out.println("2. View All Students (Read)");
            System.out.println("3. Update Student (Update)");
            System.out.println("4. Delete Student (Delete)");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createStudent(students);
                    break;
                case 2:
                    readStudents(students);
                    break;
                case 3:
                    updateStudent(students);
                    break;
                case 4:
                    deleteStudent(students);
                    break;
                case 5:
                    searchStudent(students);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        mongoClient.close();
        sc.close();
    }

    public static void createStudent(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter roll number: ");
        String rollNo = sc.nextLine();
        System.out.print("Enter branch: ");
        String branch = sc.nextLine();
        System.out.print("Enter year: ");
        int year = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        Document student = new Document("name", name)
                .append("rollNo", rollNo)
                .append("branch", branch)
                .append("year", year)
                .append("email", email);

        collection.insertOne(student);
        System.out.println("✅ Student inserted.");
    }

    public static void readStudents(MongoCollection<Document> collection) {
        System.out.println("📘 All Students:");
        for (Document student : collection.find()) {
            System.out.println(student.toJson());
        }
    }

    public static void updateStudent(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter roll number to update: ");
        String rollNo = sc.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = sc.nextLine();

        Document query = new Document("rollNo", rollNo);
        Document update = new Document("$set", new Document("email", newEmail));

        collection.updateOne(query, update);
        System.out.println("🔁 Student updated.");
    }

    public static void deleteStudent(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter roll number to delete: ");
        String rollNo = sc.nextLine();

        Document query = new Document("rollNo", rollNo);
        collection.deleteOne(query);
        System.out.println("❌ Student deleted.");
    }

    public static void searchStudent(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("🔍 Search by:");
        System.out.println("1. Roll Number");
        System.out.println("2. Name");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        sc.nextLine(); // consume newline

        Document query = new Document();
        switch (option) {
            case 1:
                System.out.print("Enter roll number: ");
                String rollNo = sc.nextLine();
                query.append("rollNo", rollNo);
                break;
            case 2:
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                query.append("name", name);
                break;
            default:
                System.out.println("⚠️ Invalid search option.");
                return;
        }

        FindIterable<Document> results = collection.find(query);
        boolean found = false;
        for (Document doc : results) {
            System.out.println(doc.toJson());
            found = true;
        }

        if (!found) {
            System.out.println("❌ No matching student found.");
        }
    }
}