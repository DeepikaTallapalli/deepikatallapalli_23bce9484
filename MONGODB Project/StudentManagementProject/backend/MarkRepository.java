package com.example.studentmanagement;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MarkRepository extends MongoRepository<Mark, String> {
    List<Mark> findByRollNumber(String rollNumber);
    Mark findByRollNumberAndSubject(String rollNumber, String subject);
}