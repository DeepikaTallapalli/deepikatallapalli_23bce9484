package Connection;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.Scanner;

public class Logincheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // MongoDB connection
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase db = mongoClient.getDatabase("Student_Management");
            MongoCollection<Document> users = db.getCollection("users");

            // User input
            System.out.print("Enter Username: ");
            String username = sc.nextLine().trim();
            System.out.print("Enter Password: ");
            String password = sc.nextLine().trim();

            // Query user
            Document query = new Document("username", username).append("password", password);
            Document user = users.find(query).first();

            if (user != null) {
                String role = user.getString("role");
                System.out.println("✅ Login successful as " + role);

                if ("student".equalsIgnoreCase(role)) {
                    String studentRoll = user.getString("rollNumber"); // assumes stored
                    showStudentData(db, studentRoll);
                } else if ("teacher".equalsIgnoreCase(role)) {
                    showAllStudents(db);
                } else {
                    System.out.println("⚠️ Unknown role.");
                }
            } else {
                System.out.println("❌ Login failed. Invalid credentials.");
            }
        }
    }

    public static void showStudentData(MongoDatabase db, String rollNumber) {
        MongoCollection<Document> students = db.getCollection("BTECHstudents");

        Document student = students.find(new Document("rollNumber", rollNumber)).first();

        if (student != null) {
            System.out.println("📘 Student Info:");
            System.out.println(student.toJson());
        } else {
            System.out.println("⚠️ Student record not found for roll number: " + rollNumber);
        }
    }

    public static void showAllStudents(MongoDatabase db) {
        MongoCollection<Document> students = db.getCollection("BTECHstudents");

        System.out.println("📚 All Students:");
        for (Document doc : students.find()) {
            System.out.println(doc.toJson());
        }
    }
}