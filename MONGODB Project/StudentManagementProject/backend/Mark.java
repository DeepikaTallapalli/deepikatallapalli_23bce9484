package com.example.studentmanagement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Marks")
public class Mark {

    @Id
    private String id;
    private String rollNumber;
    private String subject;
    private Integer marks;

    public Mark() {}

    public Mark(String rollNumber, String subject, Integer marks) {
        this.rollNumber = rollNumber;
        this.subject = subject;
        this.marks = marks;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getRollNumber() { return rollNumber; }
    public String getSubject() { return subject; }
    public Integer getMarks() { return marks; }

    public void setId(String id) { this.id = id; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setMarks(Integer marks) { this.marks = marks; }
}