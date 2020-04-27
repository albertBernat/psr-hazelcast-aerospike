package pl.codegood.nosql.repository;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.Statement;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicates;
import pl.codegood.nosql.model.AnimalEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AerospikeRepository<MODEL> implements ZooRepository<Long, MODEL> {

    // default pre created namespace name in aerospike docker image
    private static final String NAMESPACE_NAME = "test";

    private final AerospikeClient clientInstance;
    private final String mapName;

    public AerospikeRepository(AerospikeClient clientInstance, String mapName) {
        this.clientInstance = clientInstance;
        this.mapName = mapName;

        clientInstance.writePolicyDefault.sendKey = true;
        clientInstance.readPolicyDefault.sendKey = true;
        clientInstance.scanPolicyDefault.sendKey = true;
    }

    @Override
    public MODEL save(MODEL zooEntry) {
        clientInstance.put(null, new Key(NAMESPACE_NAME, mapName, UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE), new Bin(null, zooEntry));
        return zooEntry;
    }

    @Override
    public MODEL save(Long key, MODEL entry) {
        clientInstance.put(null, new Key(NAMESPACE_NAME, mapName, key), new Bin(null, entry));
        return entry;
    }

    @Override
    public MODEL findByKey(Long key) {
        Record record = clientInstance.get(null, new Key(NAMESPACE_NAME, mapName, key));
        return (MODEL) record.bins.values().stream().findFirst().orElse(null);
    }

    @Override
    public Map<Long, MODEL> findAll() {
        Map<Long, MODEL> foundEntries = new HashMap<>();
        clientInstance.scanAll(null, NAMESPACE_NAME, mapName, (Key key, Record record) ->
                record.bins.forEach((recordKey, recordValue) ->
                        foundEntries.put(key.userKey.toLong(), (MODEL) recordValue)));
        return foundEntries;
    }

    @Override
    public MODEL delete(MODEL entry) {
        return null;
    }

    @Override
    public MODEL deleteByKey(Long key) {
        Record record = clientInstance.get(null, new Key(NAMESPACE_NAME, mapName, key));
        clientInstance.delete(null, new Key(NAMESPACE_NAME, mapName, key));
        return (MODEL) record.bins.values().stream().findFirst().orElse(null);
    }

    @Override
    public Map<Long, MODEL> saveAll(Map<Long, MODEL> entries) {
        entries.forEach((key, value) -> {
            clientInstance.put(null, new Key(NAMESPACE_NAME, mapName, key), new Bin(key.toString(), value));
        });
        return entries;
    }

    @Override
    public Collection<AnimalEntity> getAllAliveAnimals() {
        AerospikeRepository<AnimalEntity> animalRepository = new AerospikeRepository<>(clientInstance, mapName);
        Map<Long, AnimalEntity> allAnimals = animalRepository.findAll();
        return allAnimals.values().stream().filter(animalEntity -> animalEntity.getDeathDate() == null).collect(Collectors.toList());
    }

    @Override
    public Collection<AnimalEntity> getAllCats() {
        AerospikeRepository<AnimalEntity> animalRepository = new AerospikeRepository<>(clientInstance, mapName);
        Map<Long, AnimalEntity> allAnimals = animalRepository.findAll();
        return  allAnimals.values().stream().filter(animalEntity -> "Cat".equals(animalEntity.getRace())).collect(Collectors.toList());
    }
}
