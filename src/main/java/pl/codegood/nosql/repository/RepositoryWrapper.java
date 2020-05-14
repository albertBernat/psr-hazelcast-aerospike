package pl.codegood.nosql.repository;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;

public class RepositoryWrapper {

    private ZooRepository<Long, AnimalEntity> animalRepository;
    private ZooRepository<Long, TicketEntity> ticketRepository;
    private ZooRepository<Long, EmployeeEntity> employeesRepository;

    public void setAnimalRepository(ZooRepository<Long, AnimalEntity> animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void setTicketRepository(ZooRepository<Long, TicketEntity> ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void setEmployeesRepository(ZooRepository<Long, EmployeeEntity> employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public ZooRepository<Long, AnimalEntity> getAnimalRepository() {
        return animalRepository;
    }

    public ZooRepository<Long, TicketEntity> getTicketRepository() {
        return ticketRepository;
    }

    public ZooRepository<Long, EmployeeEntity> getEmployeesRepository() {
        return employeesRepository;
    }
}
