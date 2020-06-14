package pl.codegood.nosql.repository.column;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import pl.codegood.nosql.model.AnimalEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CassandraAnimalRepository implements ZooColumnRepository<AnimalEntity, Integer> {

    private final Session cassandraSession;
    private final String tableName;

    public CassandraAnimalRepository(Session cassandraSession) {
        this.cassandraSession = cassandraSession;
        this.tableName = "animals";
        createTable(cassandraSession);
    }

    @Override
    public void deleteAll() {
        String query = "TRUNCATE " + tableName + ";";
        cassandraSession.execute(query);
    }

    private void createTable(Session cassandraSession) {
        String query = "CREATE TABLE IF NOT EXISTS " +
                tableName + "(" +
                "internalId int PRIMARY KEY, " +
                "name text, " +
                "race text, " +
                "birthDate text, " +
                "deathDate text);";
        cassandraSession.execute(query);
    }

    @Override
    public AnimalEntity save(AnimalEntity entry) {
        String query = "INSERT INTO " + tableName + "(internalId,name,race,birthDate,deathDate) VALUES(" + entry.toStringInsertStatement() + ");";
        cassandraSession.execute(query);
        return entry;
    }

    @Override
    public AnimalEntity findByKey(Integer key) {
        String query = "SELECT * FROM " + tableName + " WHERE internalID=" + key + ";";
        ResultSet resultSet = cassandraSession.execute(query);
        AnimalEntity animalEntity = new AnimalEntity();
        for (Row result : resultSet) {
            animalEntity = getAnimalEntityFromResultSetRow(result);
        }
        return animalEntity;
    }

    @Override
    public List<AnimalEntity> findAll() {
        String query = "SELECT * FROM " + tableName;
        ResultSet resultSet = cassandraSession.execute(query);
        ArrayList<AnimalEntity> animals = new ArrayList<>();
        resultSet.forEach(result -> animals.add(getAnimalEntityFromResultSetRow(result)));
        return animals;
    }

    private AnimalEntity getAnimalEntityFromResultSetRow(Row result) {
        return new AnimalEntity(
                (long) result.getInt("internalId"),
                result.getString("name"),
                result.getString("race"),
                parseDate(result.getString("birthDate")),
                parseDate(result.getString("deathDate")),
                null
        );
    }

    private LocalDate parseDate(String date) {
        return date.equals("null") ? null : LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public AnimalEntity delete(AnimalEntity entry) {
        this.deleteByKey(Math.toIntExact(entry.getInternalId()));
        return entry;
    }

    @Override
    public AnimalEntity deleteByKey(Integer key) {
        String query = "DELETE FROM " + tableName + " WHERE internalId=" + key;
        cassandraSession.execute(query);
        return null;
    }

    @Override
    public List<AnimalEntity> saveAll(List<AnimalEntity> entries) {
        entries.forEach(this::save);
        return entries;
    }

    @Override
    public Collection<AnimalEntity> getAllAliveAnimals() {
        String query = "SELECT * FROM " + tableName + " WHERE deathDate IS NULL";
        ResultSet execute = cassandraSession.execute(query);
        ArrayList<AnimalEntity> aliveAnimals = new ArrayList<>();
        execute.forEach(result -> aliveAnimals.add(getAnimalEntityFromResultSetRow(result)));
        return aliveAnimals;
    }

    @Override
    public Collection<AnimalEntity> getAllCats() {
        String query = "SELECT * FROM " + tableName + " WHERE race='cat'";
        ResultSet execute = cassandraSession.execute(query);
        ArrayList<AnimalEntity> cats = new ArrayList<>();
        execute.forEach(result -> cats.add(getAnimalEntityFromResultSetRow(result)));
        return cats;
    }

}
