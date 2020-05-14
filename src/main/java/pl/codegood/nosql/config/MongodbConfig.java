package pl.codegood.nosql.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongodbConfig {

    public static MongoClient getClient() {
        String user = "user";
        String password = "xxx";
        String host = "localhost:27017";
        String database = "zoo";

        String clientURI = "mongodb://" + user + ":" + password + "@" + host + "/" + database;
        MongoClientURI uri = new MongoClientURI(clientURI);

        return new MongoClient(uri);
    }
}
