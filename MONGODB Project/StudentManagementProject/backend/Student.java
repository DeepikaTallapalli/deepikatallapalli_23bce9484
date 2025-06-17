package com.example.studentmanagement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashMap;

import java.util.Map;

@Document(collection = "BTECHstudent")
public class Student {

    @Id
    private String id;

    private String name;
    private String rollNumber;
    private String branch;
    private int year;
    private String email;

    private Map<String, Integer> marks=new HashMap<>();

    // Constructors
    public Student() {}

    public Student(String name, String rollNumber, String branch, int year, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.year = year;
        this.email = email;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getBranch() {
        return branch;
    }

    public int getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }
}