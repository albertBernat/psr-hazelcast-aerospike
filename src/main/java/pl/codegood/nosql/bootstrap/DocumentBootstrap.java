package pl.codegood.nosql.bootstrap;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.document.ZooDocumentRepository;

import java.util.ArrayList;

public class DocumentBootstrap implements Bootstrap {

    private final ZooDocumentRepository<Long, AnimalEntity> animalRepository;
    private final ZooDocumentRepository<Long, TicketEntity> ticketRepository;
    private final ZooDocumentRepository<Long, EmployeeEntity> employeeRepository;

    public DocumentBootstrap(ZooDocumentRepository<Long, AnimalEntity> animalRepository,
                             ZooDocumentRepository<Long, TicketEntity> ticketRepository,
                             ZooDocumentRepository<Long, EmployeeEntity> employeeRepository) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    public void clearOldData() {
        animalRepository.deleteAll();
        ticketRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Override
    public void loadAnimals() {
        ArrayList<AnimalEntity> animals = new ArrayList<>(getAnimals().values());
        animalRepository.saveAll(animals);
    }

    @Override
    public void loadTickets() {
        ArrayList<TicketEntity> tickets = new ArrayList<>(getTickets().values());
        ticketRepository.saveAll(tickets);
    }

    @Override
    public void loadEmplyees() {
        ArrayList<EmployeeEntity> employees = new ArrayList<>(getEmployees().values());
        employeeRepository.saveAll(employees);
    }
}
