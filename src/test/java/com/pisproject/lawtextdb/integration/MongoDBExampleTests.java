package com.pisproject.lawtextdb.integration;

import com.mongodb.client.*;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MongoDBExampleTests {
    @Test
    void readDataFromMongoDB() {
        String expectedName = "LawText1";
        boolean expectedAccepted = false;
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("test");
            MongoCollection<Document> coll = db.getCollection("lawText");
            try (MongoCursor<Document> cursor = coll.find().iterator()) {
                if (cursor.hasNext()) {
                    Document databaseDoc = cursor.next();
                    assertEquals(expectedName, databaseDoc.getString("name"));
                    assertEquals(expectedAccepted, databaseDoc.getBoolean("accepted"));
                }
            }
        }
    }
}