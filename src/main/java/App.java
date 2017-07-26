import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import org.bson.BsonArray;
import org.bson.BsonDouble;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

/**
 * Created by RENT on 2017-07-26.
 */
public class App {

    public static void main(String[] args) {
       // MongoClientURI uri = new MongoClientURI('mongodb://localhost:270')

        MongoClient mongoClient = new MongoClient();
        System.out.println("Connected!");

        List<String> names = mongoClient.getDatabaseNames();

        names.forEach((name) -> {
            System.out.println(name);
        });

        System.out.println("");
        MongoDatabase people = mongoClient.getDatabase("people");
        MongoIterable<String> collectionNames = people.listCollectionNames();
        //MongoCursor<String> cursor = collectionNames.iterator();

        collectionNames.forEach((Block<? super String>) (name) ->{

            System.out.println(name);
        });




        MongoCollection<Document> p = people.getCollection("people");

//        BsonArray a = new BsonArray();
//        a.add(new BsonDouble(12.0));
//        a.add(new BsonDouble(3.0));
//        a.add(new BsonDouble(71.0));
//        a.add(new BsonDouble(66.0));
//
//        Document document = new Document()
//                .append("name", "Test")
//                .append("surname", "Testowy")
//                .append("numbers", a);
//
//        p.insertOne(document);

       FindIterable<Document> documents = p.find(new BasicDBObject().append("_id", new ObjectId("5978da7ceccb6707b845dad6")));
               documents.forEach((Block<? super Document>) (Document d) -> {
                   d.replace("name","Test2");
                   p.replaceOne(new BasicDBObject().append("_id", new ObjectId("5978da7ceccb6707b845dad6")), d);
               });



        p.find().forEach((Block<? super Document>) (Document d)-> {
            System.out.println("Document:");
            d.entrySet().forEach((entry) -> {
                System.out.println(entry.getKey() + ": " + entry.getValue().toString());
            });
        });






//        while(cursor.hasNext()){
//            System.out.println(cursor.toString());
//            cursor.next();
//        }



    }

}
