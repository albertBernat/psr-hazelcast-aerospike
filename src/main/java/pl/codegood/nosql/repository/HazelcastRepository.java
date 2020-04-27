package pl.codegood.nosql.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicates;
import pl.codegood.nosql.model.AnimalEntity;

import java.util.Collection;
import java.util.Map;

public class HazelcastRepository<MODEL> implements ZooRepository<Long, MODEL> {

    private final HazelcastInstance clientInstance;
    private final String mapName;

    public HazelcastRepository(HazelcastInstance clientInstance, String mapName) {
        this.clientInstance = clientInstance;
        this.mapName = mapName;
    }

    @Override
    public MODEL save(MODEL zooEntry) {
        FlakeIdGenerator flakeIdGenerator = clientInstance.getFlakeIdGenerator(mapName);
        clientInstance.getMap(mapName).put(flakeIdGenerator.newId(), zooEntry);
        return zooEntry;
    }

    @Override
    public MODEL save(Long key, MODEL entry) {
        clientInstance.getMap(mapName).put(key, entry);
        return entry;
    }

    @Override
    public MODEL findByKey(Long key) {
        IMap<Long, MODEL> map = clientInstance.getMap(mapName);
        return map.get(key);
    }

    @Override
    public Map<Long, MODEL> findAll() {
        return clientInstance.getMap(mapName);
    }

    @Override
    public MODEL delete(MODEL entry) {
        IMap<Long, MODEL> map = clientInstance.getMap(mapName);
        return map.values().removeIf(model -> (model.equals(entry))) ? entry : null;
    }

    @Override
    public MODEL deleteByKey(Long key) {
        IMap<Long, MODEL> map = clientInstance.getMap(mapName);
        MODEL toDelete = map.get(key);
        map.evict(key);
        return toDelete;
    }

    @Override
    public Map<Long, MODEL> saveAll(Map<Long, MODEL> entries) {
        IMap<Long, MODEL> map = clientInstance.getMap(mapName);
        map.putAll(entries);
        return map;
    }

    @Override
    public Collection<AnimalEntity> getAllAliveAnimals() {
        IMap<Long, AnimalEntity> animals = clientInstance.getMap(mapName);
        return animals.values(Predicates.and(Predicates.equal("deathDate", null)));
    }

    @Override
    public Collection<AnimalEntity> getAllCats() {
        IMap<Long, AnimalEntity> animals = clientInstance.getMap(mapName);
        return animals.values(Predicates.and(Predicates.equal("race", "Cat")));
    }

}
