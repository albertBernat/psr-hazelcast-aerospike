package pl.codegood.nosql.repository.keyvalue;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;

public class KeyValueRepositoryWrapper {

    private ZooKeyValueRepository<Long, AnimalEntity> animalRepository;
    private ZooKeyValueRepository<Long, TicketEntity> ticketRepository;
    private ZooKeyValueRepository<Long, EmployeeEntity> employeesRepository;

    public void setAnimalRepository(ZooKeyValueRepository<Long, AnimalEntity> animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void setTicketRepository(ZooKeyValueRepository<Long, TicketEntity> ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void setEmployeesRepository(ZooKeyValueRepository<Long, EmployeeEntity> employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public ZooKeyValueRepository<Long, AnimalEntity> getAnimalRepository() {
        return animalRepository;
    }

    public ZooKeyValueRepository<Long, TicketEntity> getTicketRepository() {
        return ticketRepository;
    }

    public ZooKeyValueRepository<Long, EmployeeEntity> getEmployeesRepository() {
        return employeesRepository;
    }
}
