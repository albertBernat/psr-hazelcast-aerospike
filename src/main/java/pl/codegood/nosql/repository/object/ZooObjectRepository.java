package pl.codegood.nosql.repository.object;

import pl.codegood.nosql.model.AnimalEntity;

import java.util.Collection;
import java.util.List;

public interface ZooObjectRepository<MODEL_CLASS, KEY_TYPE> {
    MODEL_CLASS save(MODEL_CLASS entry);

    MODEL_CLASS findByKey(KEY_TYPE key);

    List<MODEL_CLASS> findAll();

    MODEL_CLASS delete(MODEL_CLASS entry);

    MODEL_CLASS deleteByKey(KEY_TYPE key);

    List<MODEL_CLASS> saveAll(List<MODEL_CLASS> entries);

    Collection<AnimalEntity> getAllAliveAnimals();

    Collection<AnimalEntity> getAllCats();

    void deleteAll();
}
