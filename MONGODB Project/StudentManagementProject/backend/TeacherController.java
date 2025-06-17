package com.example.studentmanagement;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private MarkRepository markRepository;

    // Add or Update Marks
    @PostMapping("/updateMarks")
    public String updateMarks(@RequestParam String rollNumber,
                              @RequestParam String subject,
                              @RequestParam int marks) {
        Mark existing = markRepository.findByRollNumberAndSubject(rollNumber, subject);
        if (existing != null) {
            existing.setMarks(marks);
            markRepository.save(existing);
            return "✅ Marks updated";
        } else {
            Mark mark = new Mark(rollNumber, subject, marks);
            markRepository.save(mark);
            return "✅ Marks added";
        }
    }

    // View Marks by Roll Number
    @GetMapping("/viewMarks")
    public List<Mark> viewMarks(@RequestParam String rollNumber) {
        return markRepository.findByRollNumber(rollNumber);
    }

    // Delete Marks
    @PostMapping("/deleteMarks")
    public String deleteMarks(@RequestParam String rollNumber,
                              @RequestParam String subject) {
        if (subject.equalsIgnoreCase("all")) {
            List<Mark> all = markRepository.findByRollNumber(rollNumber);
            if (!all.isEmpty()) {
                markRepository.deleteAll(all);
                return "✅ All marks deleted";
            } else {
                return "⚠️ No marks found for this student.";
            }
        } else {
            Mark mark = markRepository.findByRollNumberAndSubject(rollNumber, subject);
            if (mark != null) {
                markRepository.delete(mark);
                return "✅ Marks for " + subject + " deleted";
            } else {
                return "❌ Subject not found for this student";
            }
        }
    }
}