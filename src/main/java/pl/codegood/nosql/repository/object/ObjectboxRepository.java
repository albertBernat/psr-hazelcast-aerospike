package pl.codegood.nosql.repository.object;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.MyObjectBox;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectboxRepository<MODEL> implements ZooObjectRepository<MODEL, Long> {

    private final Box<MODEL> box;
    private final Class<MODEL> enittyClass;

    public ObjectboxRepository(BoxStore store, Class<MODEL> enityClass) {
        this.box = store.boxFor(enityClass);
        this.enittyClass = enityClass;
    }

    @Override
    public MODEL save(MODEL entry) {
        this.box.put(entry);
        return entry;
    }

    @Override
    public MODEL findByKey(Long key) {
        return this.box.get(key);
    }

    @Override
    public List<MODEL> findAll() {
        return this.box.getAll();
    }

    @Override
    public MODEL delete(MODEL entry) {
        this.box.remove(entry);
        return entry;
    }

    @Override
    public MODEL deleteByKey(Long key) {
        this.box.remove(key);
        return null;
    }

    @Override
    public List<MODEL> saveAll(List<MODEL> entries) {
        for (MODEL entry : entries) {
            this.box.put(entry);
        }
        return entries;
    }

    @Override
    public Collection<AnimalEntity> getAllAliveAnimals() {
        List<AnimalEntity> allAnimals = (List<AnimalEntity>) this.box.getAll();
        return allAnimals.stream().filter(animalEntity -> animalEntity.getDeathDate() == null).collect(Collectors.toList());
    }

    @Override
    public Collection<AnimalEntity> getAllCats() {
        List<AnimalEntity> allAnimals = (List<AnimalEntity>) this.box.getAll();
        return allAnimals.stream().filter(animalEntity -> "Cat".equals(animalEntity.getRace())).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        this.box.removeAll();
    }
}
