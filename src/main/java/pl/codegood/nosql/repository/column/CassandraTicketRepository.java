package pl.codegood.nosql.repository.column;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.model.enums.TicketTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CassandraTicketRepository implements ZooColumnRepository<TicketEntity, Integer> {

    private final Session cassandraSession;
    private final String tableName;

    public CassandraTicketRepository(Session cassandraSession) {
        this.cassandraSession = cassandraSession;
        this.tableName = "tickets";
        createTable(cassandraSession);
    }

    private void createTable(Session cassandraSession) {
        String query = "CREATE TABLE IF NOT EXISTS " +
                tableName + "(" +
                "internalId int PRIMARY KEY, " +
                "buyDateTime text, " +
                "price int, " +
                "ticketTypeEnum text);";
        cassandraSession.execute(query);
    }

    @Override
    public TicketEntity save(TicketEntity entry) {
        String query = "INSERT INTO " + tableName + "(internalId,buyDateTime,price,ticketTypeEnum) VALUES(" + entry.toStringInsertStatement() + ");";
        cassandraSession.execute(query);
        return entry;
    }

    @Override
    public TicketEntity findByKey(Integer key) {
        String query = "SELECT * FROM " + tableName + " WHERE internalId=" + key;
        ResultSet resultSet = cassandraSession.execute(query);

        TicketEntity ticketEntity = new TicketEntity();
        for (Row result : resultSet) {
            ticketEntity = getTicketEntityFromResultSetRow(result);
        }
        return ticketEntity;
    }

    private TicketEntity getTicketEntityFromResultSetRow(Row result) {
        return new TicketEntity(
                (long) result.getInt("internalId"),
                parseDate(result.getString("buyDateTime")),
                new BigDecimal(result.getInt("price")),
                TicketTypeEnum.valueOf(result.getString("ticketTypeEnum")));
    }

    private LocalDateTime parseDate(String date) {
        return date.equals("null") ? null : LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public List<TicketEntity> findAll() {
        String query = "SELECT * FROM " + tableName + ";";
        ResultSet resultSet = cassandraSession.execute(query);
        ArrayList<TicketEntity> tickets = new ArrayList<>();
        resultSet.forEach(result -> tickets.add(getTicketEntityFromResultSetRow(result)));
        return tickets;
    }

    @Override
    public TicketEntity delete(TicketEntity entry) {
        String query = "DELETE FROM " + tableName + " WHERE internalId=" + entry.getInternalId();
        cassandraSession.execute(query);
        return entry;
    }

    @Override
    public TicketEntity deleteByKey(Integer key) {
        String query = "DELETE FROM " + tableName + " WHERE internalId=" + key;
        cassandraSession.execute(query);
        return null;
    }

    @Override
    public List<TicketEntity> saveAll(List<TicketEntity> entries) {
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
