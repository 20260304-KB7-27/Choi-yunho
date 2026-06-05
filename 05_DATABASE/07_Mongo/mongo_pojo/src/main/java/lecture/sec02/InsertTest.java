package lecture.sec02;

import app.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

//public class InsertTest {

//    public static void main(String[] args) {
//
//        // MongoCollection : 컬렉션에 접근하기 위한 객체 -> 쿼리문 사용가능
//        MongoCollection<Document> collection = Database.getCollection("todo");
//
//        Document document = new Document();
//        document.append("title", "MongoDB");
//        document.append("desc", "MongoDB 자바 연결");
//        document.append("done", false);
//
//        InsertOneResult result = collection.insertOne(document);
//
//        System.out.println("==> insertResult : " + result.getInsertedId());
//
//        Database.close();
//    }
//}
