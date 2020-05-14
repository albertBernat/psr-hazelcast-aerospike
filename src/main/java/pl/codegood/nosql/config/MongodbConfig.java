package pl.codegood.nosql.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongodbConfig {

    private static final String USER = "user";
    private static final String PASSWORD = "xxx";
    private static final String HOST = "localhost:27017";
    private static final String DATABASE = "zoo";

    public static MongoClient getClient() {
        String clientURI = "mongodb://" + USER + ":" + PASSWORD + "@" + HOST + "/" + DATABASE;
        MongoClientURI uri = new MongoClientURI(clientURI);
        return new MongoClient(uri);
    }

    public static String getDatabaseName() {
        return DATABASE;
    }
}
