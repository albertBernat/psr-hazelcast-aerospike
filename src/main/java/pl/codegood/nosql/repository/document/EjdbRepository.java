//package pl.codegood.nosql.repository.document;
//
//import com.google.gson.Gson;
//import com.softmotions.ejdb2.EJDB2;
//import pl.codegood.nosql.model.AnimalEntity;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class EjdbRepository<MODEL> implements ZooDocumentRepository<Long, MODEL> {
//
//    private final Gson gsonParser;
//    private final EJDB2 client;
//    private final Class<MODEL> classTypeForGsonDeserialziation;
//    private final String collectionName;
//
//    public EjdbRepository( EJDB2 client, String collectionName, Gson gsonParser, Class<MODEL> classTypeForGsonDeserialziation) {
//        this.gsonParser = gsonParser;
//        this.client = client;
//        this.classTypeForGsonDeserialziation = classTypeForGsonDeserialziation;
//        this.collectionName = collectionName;
//    }
//
//    @Override
//    public MODEL save(MODEL zooEntry) {
//        String s = gsonParser.toJson(zooEntry);
//        client.put(collectionName, s);
//        return zooEntry;
//    }
//
//    @Override
//    public MODEL findByKey(Long key) {
//        String found = client.createQuery("@" + collectionName + "/[internalId=:?]").setString(0, String.valueOf(key)).firstJson();
//        return gsonParser.fromJson(found,classTypeForGsonDeserialziation);
//    }
//
//    @Override
//    public List<MODEL> findAll() {
//        List<String> foundDocuments = new ArrayList<>();
//        client.createQuery("@"+collectionName+"/*").execute((docId, doc) -> { foundDocuments.add(doc); return 1L; } );
//        return foundDocuments.stream().map(document -> gsonParser.fromJson(document,classTypeForGsonDeserialziation)).collect(Collectors.toList());
//    }
//
//    @Override
//    public MODEL delete(MODEL entry) {
//        return null;
//    }
//
//    @Override
//    public MODEL deleteByKey(Long key) {
//        String s = client.createQuery("@" + collectionName + "/[internalId=:?] | del").setString(0, String.valueOf(key)).firstJson();
//        return gsonParser.fromJson(s, classTypeForGsonDeserialziation);
//    }
//
//    @Override
//    public void deleteAll() {
//        client.removeCollection(collectionName);
//    }
//
//    @Override
//    public List<MODEL> saveAll(List<MODEL> entries) {
//        return entries.stream().map(this::save).collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<AnimalEntity> getAllAliveAnimals() {
//       List<String> foundDocuments = new ArrayList<>();
//       client.createQuery("@"+collectionName+"/*").execute((docId, doc) -> { foundDocuments.add(doc); return 1L; } );
//       List<AnimalEntity> collect = foundDocuments.stream().map(document -> gsonParser.fromJson(document, AnimalEntity.class)).collect(Collectors.toList());
//       return collect.stream().filter(animalEntity -> animalEntity.getDeathDate() == null).collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<AnimalEntity> getAllCats() {
//        List<String> cats = new ArrayList<>();
//        client.createQuery("@animals/[race=:?]").setString(0, "Cat").execute(
//                (docId, doc) -> {
//                    cats.add(doc);
//                    return 1L;
//                });
//        return cats.stream().map(s -> gsonParser.fromJson(s,AnimalEntity.class)).collect(Collectors.toList());
//
//    }
//}
