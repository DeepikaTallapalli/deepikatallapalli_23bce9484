package com.example.studentmanagement;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Add Student
    @PostMapping("/addStudent")
    public String addStudent(@RequestParam String name,
                             @RequestParam String rollNumber,
                             @RequestParam String branch,
                             @RequestParam int year,
                             @RequestParam String email) {
        Student student = new Student(name, rollNumber, branch, year, email);
        studentRepository.save(student);
        return "✅ Student added successfully";
    }

    // Update Student Email
    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam String rollNumber,
                                @RequestParam String newEmail) {
        Optional<Student> studentOpt = studentRepository.findByRollNumber(rollNumber);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setEmail(newEmail);
            studentRepository.save(student);
            return "✅ Email updated";
        } else {
            return "❌ Student not found";
        }
    }

    // Delete Student
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam String rollNumber) {
        Optional<Student> studentOpt = studentRepository.findByRollNumber(rollNumber);
        if (studentOpt.isPresent()) {
            studentRepository.delete(studentOpt.get());
            return "✅ Student deleted";
        } else {
            return "❌ Student not found";
        }
    }

    // Search Student by rollNumber or name
    @GetMapping("/searchStudent")
    public List<Student> searchStudent(@RequestParam(required = false) String rollNumber,
                                       @RequestParam(required = false) String name) {
        if (rollNumber != null && !rollNumber.isEmpty()) {
            return studentRepository.findByRollNumber(rollNumber)
                    .map(List::of).orElse(List.of());
        } else if (name != null && !name.isEmpty()) {
            return studentRepository.findByNameContainingIgnoreCase(name);
        } else {
            return List.of();
        }
    }

    // View All Students
    @GetMapping("/api/students/view")
    public List<Student> viewAllStudents() {
        return studentRepository.findAll();
    }

    // Register New User
    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("Student_Management");
            MongoCollection<Document> users = database.getCollection("users");

            Document existingUser = users.find(new Document("username", username)).first();
            if (existingUser != null) {
                return "❌ Username already exists.";
            }

            Document newUser = new Document("username", username)
                    .append("password", password)
                    .append("role", role);
            users.insertOne(newUser);

            return "✅ User registered successfully.";
        }
    }

    // User Login
    @PostMapping("/loginUser")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("Student_Management");
            MongoCollection<Document> users = database.getCollection("users");

            Document query = new Document("username", username)
                    .append("password", password);
            Document user = users.find(query).first();

            if (user != null) {
                return "✅ Login successful as " + user.getString("role");
            } else {
                return "❌ Login failed. Invalid credentials.";
            }
        }
    }
}