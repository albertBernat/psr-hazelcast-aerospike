package pl.codegood.nosql.bootstrap;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.keyvalue.ZooKeyValueRepository;

public class KeyValueBootstrap implements Bootstrap {

    private final ZooKeyValueRepository<Long, AnimalEntity> animalRepository;
    private final ZooKeyValueRepository<Long, TicketEntity> ticketRepository;
    private final ZooKeyValueRepository<Long, EmployeeEntity> employeeRepository;

    public KeyValueBootstrap(ZooKeyValueRepository<Long, AnimalEntity> animalRepository,
                             ZooKeyValueRepository<Long, TicketEntity> ticketRepository,
                             ZooKeyValueRepository<Long, EmployeeEntity> employeeRepository) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void loadAnimals() {
        animalRepository.saveAll(this.getAnimals());
    }

    @Override
    public void loadTickets() {
        ticketRepository.saveAll(this.getTickets());
    }

    @Override
    public void loadEmplyees() {
        employeeRepository.saveAll(this.getEmployees());
    }

}
