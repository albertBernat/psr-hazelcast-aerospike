package pl.codegood.nosql.demo.column;

import pl.codegood.nosql.constatnts.EntitiesConstants;
import pl.codegood.nosql.demo.Showrun;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.model.enums.GenderEnum;
import pl.codegood.nosql.model.enums.TicketTypeEnum;
import pl.codegood.nosql.repository.column.CassandraAnimalRepository;
import pl.codegood.nosql.repository.column.CassandraEmployeeRepository;
import pl.codegood.nosql.repository.column.CassandraTicketRepository;
import pl.codegood.nosql.repository.column.ZooColumnRepository;
import pl.codegood.nosql.view.ZooView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ColumnShowrun implements Showrun {

    private final ZooView zooView;
    private final CassandraAnimalRepository animalRepository;
    private final CassandraTicketRepository ticketRepository;
    private final CassandraEmployeeRepository emplyeeRepository;

    public ColumnShowrun(ZooView zooView, CassandraAnimalRepository animalRepository, CassandraTicketRepository ticketRepository, CassandraEmployeeRepository emplyeeRepository) {
        this.zooView = zooView;
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.emplyeeRepository = emplyeeRepository;
    }

    @Override
    public void animalCrudDemo() throws InterruptedException {
        zooView.displayOperationTitle("ANIMAL CRUD DEMO");
        this.getAllEntities("ANIMALS", animalRepository);
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.addAnimal();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.deleteAnimal();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.editAnimal();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
    }

    private void editAnimal() {
        zooView.displayOperationTitle("EDITNG NAME OF ENTITY ANIMAL WITH ID 2, AFTER EDIT");
        AnimalEntity foundAnimal = animalRepository.findByKey(2);
        foundAnimal.setName("NEW NAME");
        animalRepository.deleteByKey(2);
        animalRepository.save(foundAnimal);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void deleteAnimal() {
        zooView.displayOperationTitle("DELETING ANIMAL WITH ID 1");
        animalRepository.deleteByKey(1);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void addAnimal() {
        zooView.displayOperationTitle("ADDING ANIMAL");
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setInternalId(123L);
        animalRepository.save(animalEntity);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void getAllEntities(String entityName, ZooColumnRepository<?,?> zooRepository) {
        zooView.displayOperationTitle("ALL " + entityName.toUpperCase());
        zooView.displayCollection(zooRepository.findAll());
    }

    @Override
    public void ticketCrudDemo() throws InterruptedException {
        zooView.displayOperationTitle("TICKET CRUD DEMO");
        this.getAllEntities("TICKETS", ticketRepository);
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.addTicket();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.deleteTicket();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.editTicket();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);

    }

    private void editTicket() {
        zooView.displayOperationTitle("EDITNG PRICE OF ENTITY TICKET WITH ID 2, AFTER EDIT");
        TicketEntity foundTicket = ticketRepository.findByKey(2);
        foundTicket.setPrice(new BigDecimal(299999));
        ticketRepository.deleteByKey(2);
        ticketRepository.save(foundTicket);
        zooView.displayCollection(ticketRepository.findAll());
    }

    private void deleteTicket() {
        zooView.displayOperationTitle("DELETING TICKIET WITH ID 1");
        ticketRepository.deleteByKey(1);
        zooView.displayCollection(ticketRepository.findAll());
    }

    private void addTicket() {
        zooView.displayOperationTitle("ADDING TICKET");
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setInternalId(123L);
        ticketEntity.setTicketTypeEnum(TicketTypeEnum.FULL);
        ticketEntity.setBuyDateTime(LocalDateTime.now());
        ticketRepository.save(ticketEntity);
        zooView.displayCollection(ticketRepository.findAll());
    }

    @Override
    public void predicatesDemo() throws InterruptedException {

    }

    @Override
    public void employeeCrudDemo() throws InterruptedException {
        zooView.displayOperationTitle("EMPLOYEE CRUD DEMO");
        this.getAllEntities("EMPLOYEES", emplyeeRepository);
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.addEmployee();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.deleteEmployee();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
        this.editEmployee();
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);

    }

    private void editEmployee() {
        zooView.displayOperationTitle("EDITNG NAME OF ENTITY TICKET WITH ID 2, AFTER EDIT");
        EmployeeEntity foundEmployee = emplyeeRepository.findByKey(2);
        foundEmployee.setName("NEW NAME");
        emplyeeRepository.deleteByKey(2);
        emplyeeRepository.save(foundEmployee);
        zooView.displayCollection(emplyeeRepository.findAll());
    }

    private void deleteEmployee() {
        zooView.displayOperationTitle("DELETING EMPLOYEE WITH ID 1");
        emplyeeRepository.deleteByKey(1);
        zooView.displayCollection(emplyeeRepository.findAll());
    }

    private void addEmployee() {
        zooView.displayOperationTitle("ADDING EMPLOYEE");
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setInternalId(123L);
        employeeEntity.setGender(GenderEnum.MALE);
        employeeEntity.setWorkEndDate(LocalDate.now());
        employeeEntity.setWorkStartDate(LocalDate.now());
        emplyeeRepository.save(employeeEntity);
        zooView.displayCollection(emplyeeRepository.findAll());
    }
}
