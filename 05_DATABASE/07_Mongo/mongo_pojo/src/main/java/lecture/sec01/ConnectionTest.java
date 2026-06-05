package lecture.sec01;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionTest {

    public static void main(String[] args) {

        String uri = "mongodb://localhost:27017";
        String db = "todo_db_v2";

        // MongoClient : MongoDB 서버와의 연결을 담당하는 객체
        try (MongoClient client = MongoClients.create(uri)) {

            // MongoDatabase : MongoDB의 특정 데이터베이스에 접근하기 위한
            MongoDatabase database = client.getDatabase(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
