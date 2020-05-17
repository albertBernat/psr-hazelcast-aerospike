package pl.codegood.nosql.repository.keyvalue;

import pl.codegood.nosql.model.AnimalEntity;

import java.util.Collection;
import java.util.Map;

public interface ZooKeyValueRepository<KEY_TYPE, MODEL_CLASS> {

    MODEL_CLASS save(MODEL_CLASS zooEntry);

    MODEL_CLASS save(KEY_TYPE key, MODEL_CLASS entry);

    MODEL_CLASS findByKey(KEY_TYPE key);

    Map<KEY_TYPE, MODEL_CLASS> findAll();

    MODEL_CLASS delete(MODEL_CLASS entry);

    MODEL_CLASS deleteByKey(KEY_TYPE key);

    Map<KEY_TYPE, MODEL_CLASS> saveAll(Map<KEY_TYPE, MODEL_CLASS> entries);

    Collection<AnimalEntity> getAllAliveAnimals();

    Collection<AnimalEntity> getAllCats();
}

