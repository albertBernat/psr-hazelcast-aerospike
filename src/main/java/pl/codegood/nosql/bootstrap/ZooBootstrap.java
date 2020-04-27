package pl.codegood.nosql.bootstrap;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.ZooRepository;

public class ZooBootstrap implements Bootstrap {

    private final ZooRepository<Long, AnimalEntity> animalHazelcastRepository;
    private final ZooRepository<Long, TicketEntity> ticketHazelcastRepository;
    private final ZooRepository<Long, EmployeeEntity> employeeHazelcastRepository;

    public ZooBootstrap(ZooRepository<Long, AnimalEntity> animalHazelcastRepository,
                        ZooRepository<Long, TicketEntity> ticketHazelcastRepository,
                        ZooRepository<Long, EmployeeEntity> employeeHazelcastRepository) {
        this.animalHazelcastRepository = animalHazelcastRepository;
        this.ticketHazelcastRepository = ticketHazelcastRepository;
        this.employeeHazelcastRepository = employeeHazelcastRepository;
    }

    @Override
    public void loadAnimals() {
        animalHazelcastRepository.saveAll(this.getAnimals());
    }

    @Override
    public void loadTickets() {
        ticketHazelcastRepository.saveAll(this.getTickets());
    }

    @Override
    public void loadEmplyees() {
        employeeHazelcastRepository.saveAll(this.getEmployees());
    }

}
