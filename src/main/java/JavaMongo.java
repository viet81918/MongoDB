import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class JavaMongo {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://viet81918:conchode239@cluster0.hzr2fsy.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

                // To get all database names        
                MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
                System.out.println("Below are list of databases present in MongoDB");
                while (dbsCursor.hasNext()) {
                    System.out.println(dbsCursor.next());
                }

                // Create a new database and a collection, then insert a document
                MongoDatabase newDatabase = mongoClient.getDatabase("myNewDatabase");
                MongoCollection<Document> collection = newDatabase.getCollection("myNewCollection");

          

                // Retrieve and print documents from myNewCollection
                MongoCursor<Document> cursor = collection.find().iterator();
                System.out.println("Documents in myNewCollection:");
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }

                
          

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
