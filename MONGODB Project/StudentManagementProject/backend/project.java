package Connection;

import com.mongodb.client.MongoClient; import com.mongodb.client.MongoClients; import com.mongodb.client.MongoDatabase; import com.mongodb.client.MongoCollection; import com.mongodb.client.FindIterable; import org.bson.Document;

public class project {

public static void main(String[] args) {
    try {
        // Correct way to create a MongoClient instance
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // Get the database
        MongoDatabase database = mongoClient.getDatabase("Student_Management");

        // Get the collection
        MongoCollection<Document> studentCollection = database.getCollection("BTECHstudents");

        // Find and display all documents
        FindIterable<Document> students = studentCollection.find();
        System.out.println("=== Student Records ===");
        for (Document student : students) {
            System.out.println(student.toJson());
        }

        // Close the client
        mongoClient.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

}