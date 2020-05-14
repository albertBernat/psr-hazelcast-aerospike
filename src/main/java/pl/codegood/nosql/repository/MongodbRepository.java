package pl.codegood.nosql.repository;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import pl.codegood.nosql.config.MongodbConfig;
import pl.codegood.nosql.model.AnimalEntity;

import java.util.Collection;
import java.util.Map;

public class MongodbRepository<MODEL> implements ZooRepository<String, MODEL> {

    private final MongoClient clientInstance;
    private final Gson gsonParser;

    private MongoCollection<Document> documentCollection;

    public MongodbRepository(MongoClient clientInstance, String documentStoreName, Gson gsonParser) {
        this.clientInstance = clientInstance;
        this.gsonParser = gsonParser;
        this.documentCollection = clientInstance.getDatabase(MongodbConfig.getDatabaseName()).getCollection(documentStoreName);
    }

    @Override
    public MODEL save(MODEL zooEntry) {
        String zooEntryAsJson = gsonParser.toJson(zooEntry);
        documentCollection.insertOne(Document.parse(zooEntryAsJson));
        return zooEntry;
    }

    @Override
    public MODEL save(String key, MODEL entry) {
        return save(entry);
    }

    @Override
    public MODEL findByKey(String key) {
        return null;
    }

    @Override
    public Map<String, MODEL> findAll() {
        return null;
    }

    @Override
    public MODEL delete(MODEL entry) {
        return null;
    }

    @Override
    public MODEL deleteByKey(String key) {
        return null;
    }

    @Override
    public Map<String, MODEL> saveAll(Map<String, MODEL> entries) {
        return null;
    }

    @Override
    public Collection<AnimalEntity> getAllAliveAnimals() {
        return null;
    }

    @Override
    public Collection<AnimalEntity> getAllCats() {
        return null;
    }
}
