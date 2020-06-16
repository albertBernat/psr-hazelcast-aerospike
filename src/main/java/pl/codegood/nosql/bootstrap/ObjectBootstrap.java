package pl.codegood.nosql.bootstrap;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.object.ObjectboxRepository;

import java.util.ArrayList;

public class ObjectBootstrap implements Bootstrap{

    private final ObjectboxRepository<AnimalEntity> animalRepository;
    private final ObjectboxRepository<TicketEntity> ticketRepository;
    private final ObjectboxRepository<EmployeeEntity> employeeRepository;

    public ObjectBootstrap(ObjectboxRepository<AnimalEntity> animalRepository, ObjectboxRepository<TicketEntity> ticketRepository, ObjectboxRepository<EmployeeEntity> employeeRepository) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void loadAnimals() {
        this.animalRepository.deleteAll();
        ArrayList<AnimalEntity> animals = new ArrayList<>(getAnimals().values());
        animalRepository.saveAll(animals);
    }

    @Override
    public void loadTickets() {
        this.ticketRepository.deleteAll();
        ArrayList<TicketEntity> tickets = new ArrayList<>(getTickets().values());
        ticketRepository.saveAll(tickets);
    }

    @Override
    public void loadEmplyees() {
        this.employeeRepository.deleteAll();
        ArrayList<EmployeeEntity> employees = new ArrayList<>(getEmployees().values());
        employeeRepository.saveAll(employees);

    }
}
