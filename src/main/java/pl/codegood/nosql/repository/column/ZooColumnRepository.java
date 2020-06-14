package pl.codegood.nosql.repository.column;

import com.datastax.driver.core.Session;
import pl.codegood.nosql.model.AnimalEntity;

import java.util.Collection;
import java.util.List;

public interface ZooColumnRepository<MODEL_CLASS, KEY_TYPE> {

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
