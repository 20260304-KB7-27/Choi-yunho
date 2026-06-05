package app;

import app.domain.Todo;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class Application {

    public static void main(String[] args) {

        MongoCollection<Todo> collection = Database.getCollection("todo", Todo.class);

        insertMany(collection);
        updateById(collection, "6a225e773c716426af1eb384");
        deleteById(collection, "6a225e773c716426af1eb384");
        findById(collection, "6a225e773c716426af1eb384");

        Database.close();
    }

    private static void deleteById(MongoCollection<Todo> collection, String id) {
        // 조건
        Bson query = eq("_id", new ObjectId(id));

        collection.deleteOne(query);
    }

    private static void updateById(MongoCollection<Todo> collection, String id) {
        // 조건
        Bson query = eq("_id", new ObjectId(id));

        // 어떻게 바꿀지
        Bson updates = combine(set("title", "Updated Title2"), set("done", true));

        collection.updateOne(query, updates);
    }

    private static void findById(MongoCollection<Todo> collection, String id) {

        Bson query = eq("_id", new ObjectId(id));

        Todo todo = collection.find(query).first();

        System.out.println(todo);
    }

    private static void insertMany(MongoCollection<Todo> collection) {

        List<Todo> newTodo = Arrays.asList(
                new Todo(null, "POJO1", "POJO1 설명", false),
                new Todo(null, "POJO2", "POJO2 설명", false),
                new Todo(null, "POJO3", "POJO3 설명", false)
        );

        collection.insertMany(newTodo);
    }


}
