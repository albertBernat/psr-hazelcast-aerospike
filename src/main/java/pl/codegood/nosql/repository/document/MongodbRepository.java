package pl.codegood.nosql.repository.document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import pl.codegood.nosql.config.MongodbConfig;
import pl.codegood.nosql.model.AnimalEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class MongodbRepository<MODEL> implements ZooDocumentRepository<Long, MODEL> {

    private final Gson gsonParser;
    private final MongoCollection<Document> documentCollection;
    private final Class<MODEL> classTypeForGsonDeserialziation;

    public MongodbRepository(MongoClient clientInstance, String documentStoreName, Gson gsonParser, Class<MODEL> classType) {
        this.gsonParser = gsonParser;
        this.documentCollection = clientInstance.getDatabase(MongodbConfig.getDatabaseName()).getCollection(documentStoreName);
        this.classTypeForGsonDeserialziation = classType;
    }

    @Override
    public MODEL save(MODEL zooEntry) {
        String zooEntryAsJson = gsonParser.toJson(zooEntry);
        documentCollection.insertOne(Document.parse(zooEntryAsJson));
        return zooEntry;
    }

    @Override
    public MODEL findByKey(Long key) {
        Document found = documentCollection.find(eq("internalId", key)).first();
        return gsonParser.fromJson(found.toJson(), classTypeForGsonDeserialziation);
    }

    @Override
    public List<MODEL> findAll() {
        List<Document> into = documentCollection.find().into(new ArrayList<>());
        return into.stream().map(el -> gsonParser.fromJson(el.toJson(), classTypeForGsonDeserialziation)).collect(Collectors.toList());
    }

    @Override
    public MODEL delete(MODEL entry) {
        documentCollection.deleteOne(eq(gsonParser.toJson(entry)));
        return entry;
    }

    @Override
    public MODEL deleteByKey(Long key) {
        documentCollection.deleteOne(eq("internalId", key));
        return null;
    }

    @Override
    public void deleteAll() {
        documentCollection.deleteMany(new BasicDBObject());
    }

    @Override
    public List<MODEL> saveAll(List<MODEL> entries) {
        List<Document> documents = entries.stream().map(entry -> Document.parse(gsonParser.toJson(entry))).collect(Collectors.toList());
        documentCollection.insertMany(documents);
        return entries;
    }

    @Override
    public Collection<AnimalEntity> getAllAliveAnimals() {
        ArrayList<Document> aliveAnimals = documentCollection.find(eq("deathDate", null)).into(new ArrayList<>());
        return aliveAnimals.stream().map(animal -> gsonParser.fromJson(animal.toJson(), AnimalEntity.class)).collect(Collectors.toList());
    }

    @Override
    public Collection<AnimalEntity> getAllCats() {
        ArrayList<Document> aliveAnimals = documentCollection.find(eq("race", "Cat")).into(new ArrayList<>());
        return aliveAnimals.stream().map(animal -> gsonParser.fromJson(animal.toJson(), AnimalEntity.class)).collect(Collectors.toList());
    }
}
