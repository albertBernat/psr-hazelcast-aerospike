package pl.codegood.nosql.bootstrap;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.column.CassandraAnimalRepository;
import pl.codegood.nosql.repository.column.CassandraTicketRepository;

import java.util.ArrayList;

public class ColumnBootstrap implements Bootstrap {

    private final CassandraAnimalRepository cassandraAnimalRepository;
    private final CassandraTicketRepository cassandraTicketRepository;

    public ColumnBootstrap(CassandraAnimalRepository cassandraAnimalRepository, CassandraTicketRepository cassandraTicketRepository) {
        this.cassandraAnimalRepository = cassandraAnimalRepository;
        this.cassandraTicketRepository = cassandraTicketRepository;
    }

    public void clearOldData() {
        cassandraAnimalRepository.deleteAll();
        cassandraTicketRepository.deleteAll();
//        cassandraEmployeeRepository.deleteAll();
    }

    @Override
    public void loadAnimals() {
        ArrayList<AnimalEntity> animals = new ArrayList<>(getAnimals().values());
        cassandraAnimalRepository.saveAll(animals);    }

    @Override
    public void loadTickets() {
        ArrayList<TicketEntity> tickets = new ArrayList<>(getTickets().values());
        cassandraTicketRepository.saveAll(tickets);
    }

    @Override
    public void loadEmplyees() {

    }
}
