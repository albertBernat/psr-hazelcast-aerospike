package pl.codegood.nosql.repository.document;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;

public class DocumentRepositoryWrapper {

    private ZooDocumentRepository<Long, AnimalEntity> animalRepository;
    private ZooDocumentRepository<Long, TicketEntity> ticketRepository;
    private ZooDocumentRepository<Long, EmployeeEntity> employeesRepository;

    public ZooDocumentRepository<Long, AnimalEntity> getAnimalRepository() {
        return animalRepository;
    }

    public void setAnimalRepository(ZooDocumentRepository<Long, AnimalEntity> animalRepository) {
        this.animalRepository = animalRepository;
    }

    public ZooDocumentRepository<Long, TicketEntity> getTicketRepository() {
        return ticketRepository;
    }

    public void setTicketRepository(ZooDocumentRepository<Long, TicketEntity> ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public ZooDocumentRepository<Long, EmployeeEntity> getEmployeesRepository() {
        return employeesRepository;
    }

    public void setEmployeesRepository(ZooDocumentRepository<Long, EmployeeEntity> employeesRepository) {
        this.employeesRepository = employeesRepository;
    }
}
