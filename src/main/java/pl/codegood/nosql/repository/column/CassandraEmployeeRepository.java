package pl.codegood.nosql.repository.column;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.model.enums.GenderEnum;
import pl.codegood.nosql.model.enums.TicketTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CassandraEmployeeRepository implements ZooColumnRepository<EmployeeEntity, Integer>{

    private final Session cassandraSession;
    private final String tableName;

    public CassandraEmployeeRepository(Session cassandraSession) {
        this.cassandraSession = cassandraSession;
        this.tableName = "employees";
        createTable(cassandraSession);
    }

    private void createTable(Session cassandraSession) {
        String query = "CREATE TABLE IF NOT EXISTS " +
                tableName + "(" +
                "internalId int PRIMARY KEY, " +
                "name text, " +
                "familyName text, " +
                "workStartDate text, " +
                "workEndDate text, " +
                "genderEnum text);";
        cassandraSession.execute(query);
    }

    @Override
    public EmployeeEntity save(EmployeeEntity entry) {
        String query = "INSERT INTO " + tableName + "(internalId,name,familyName,workStartDate,workEndDate,genderEnum) " +
                "VALUES(" + entry.toStringInsertStatement() + ");";
        cassandraSession.execute(query);
        return entry;
    }

    @Override
    public EmployeeEntity findByKey(Integer key) {
        String query = "SELECT * FROM " + tableName + " WHERE internalId=" + key;
        cassandraSession.execute(query);
        ResultSet resultSet = cassandraSession.execute(query);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        for (Row result : resultSet) {
            employeeEntity = getEmployeeEntityFromResultSetRow(result);
        }
        return employeeEntity;
    }

    private EmployeeEntity getEmployeeEntityFromResultSetRow(Row result) {
        return new EmployeeEntity(
                (long) result.getInt("internalId"),
                result.getString("name"),
                result.getString("familyName"),
                parseDate(result.getString("workStartDate")),
                parseDate(result.getString("workEndDate")),
                GenderEnum.valueOf((result.getString("genderEnum"))));
    }

    private LocalDate parseDate(String date) {
        return date == null ? null : LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
    }

    @Override
    public List<EmployeeEntity> findAll() {
        String query = "SELECT * FROM " + tableName + ";";
        ResultSet resultSet = cassandraSession.execute(query);
        ArrayList<EmployeeEntity> employees = new ArrayList<>();
        resultSet.forEach(result -> employees.add(getEmployeeEntityFromResultSetRow(result)));
        return employees;
    }

    @Override
    public EmployeeEntity delete(EmployeeEntity entry) {
        this.deleteByKey(Math.toIntExact(entry.getInternalId()));
        return entry;
    }

    @Override
    public EmployeeEntity deleteByKey(Integer key) {
        String query = "DELETE FROM " + tableName + " WHERE internalId=" + key;
        cassandraSession.execute(query);
        return null;
    }

    @Override
    public List<EmployeeEntity> saveAll(List<EmployeeEntity> entries) {
        entries.forEach(this::save);
        return entries;
    }

    @Override
    public Collection<AnimalEntity> getAllAliveAnimals() {
        return null;
    }

    @Override
    public Collection<AnimalEntity> getAllCats() {
        return null;
    }

    @Override
    public void deleteAll() {
        String query = "TRUNCATE " + tableName + ";";
        cassandraSession.execute(query);
    }
}
