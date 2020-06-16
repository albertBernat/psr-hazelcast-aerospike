package pl.codegood.nosql.demo.object;

import pl.codegood.nosql.constatnts.EntitiesConstants;
import pl.codegood.nosql.demo.Showrun;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.model.enums.GenderEnum;
import pl.codegood.nosql.model.enums.TicketTypeEnum;
import pl.codegood.nosql.repository.object.ObjectboxRepository;
import pl.codegood.nosql.view.ZooView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ObjectShowrun implements Showrun {
    private final ZooView zooView;
    private final ObjectboxRepository<AnimalEntity> animalRepository;
    private final ObjectboxRepository<TicketEntity> ticketRepository;
    private final ObjectboxRepository<EmployeeEntity> emplyeeRepository;

    public ObjectShowrun(ZooView zooView, ObjectboxRepository<AnimalEntity> animalRepository, ObjectboxRepository<TicketEntity> ticketRepository, ObjectboxRepository<EmployeeEntity> emplyeeRepository) {
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
        AnimalEntity foundAnimal = animalRepository.findByKey(2L);
        foundAnimal.setName("NEW NAME");
        animalRepository.deleteByKey(2L);
        animalRepository.save(foundAnimal);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void deleteAnimal() {
        zooView.displayOperationTitle("DELETING ANIMAL WITH ID 1");
        animalRepository.deleteByKey(1L);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void addAnimal() {
        zooView.displayOperationTitle("ADDING ANIMAL");
        AnimalEntity animalEntity = new AnimalEntity();
        animalRepository.save(animalEntity);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void getAllEntities(String entityName, ObjectboxRepository<?> repository) {
        zooView.displayOperationTitle("ALL " + entityName.toUpperCase());
        zooView.displayCollection(repository.findAll());
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

    private void deleteTicket() {
        zooView.displayOperationTitle("DELETING TICKIET WITH ID 1");
        ticketRepository.deleteByKey(1L);
        zooView.displayCollection(ticketRepository.findAll());
    }

    private void editTicket() {
        zooView.displayOperationTitle("EDITNG PRICE OF ENTITY TICKET WITH ID 2, AFTER EDIT");
        TicketEntity foundTicket = ticketRepository.findByKey(2L);
        foundTicket.setPrice(new BigDecimal(299999));
        ticketRepository.deleteByKey(2L);
        ticketRepository.save(foundTicket);
        zooView.displayCollection(ticketRepository.findAll());
    }

    private void addTicket() {
        zooView.displayOperationTitle("ADDING TICKET");
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicketTypeEnum(TicketTypeEnum.FULL);
        ticketEntity.setBuyDateTime(LocalDateTime.now());
        ticketRepository.save(ticketEntity);
        zooView.displayCollection(ticketRepository.findAll());
    }

    @Override
    public void predicatesDemo() throws InterruptedException {
        zooView.displayOperationTitle("PREDICATES DEMO");
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);

        zooView.displayOperationTitle("ALL ALIVE ANIMALS");
        zooView.displayCollection(this.animalRepository.getAllAliveAnimals());
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);

        zooView.displayOperationTitle("ALL CATS");
        zooView.displayCollection(this.animalRepository.getAllCats());
        TimeUnit.SECONDS.sleep(EntitiesConstants.SLEEP_DURATION_IN_SECONDS);
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
        EmployeeEntity foundEmployee = emplyeeRepository.findByKey(2L);
        foundEmployee.setName("NEW NAME");
        emplyeeRepository.deleteByKey(2L);
        emplyeeRepository.save(foundEmployee);
        zooView.displayCollection(emplyeeRepository.findAll());
    }

    private void deleteEmployee() {
        zooView.displayOperationTitle("DELETING EMPLOYEE WITH ID 1");
        emplyeeRepository.deleteByKey(1L);
        zooView.displayCollection(emplyeeRepository.findAll());
    }

    private void addEmployee() {
        zooView.displayOperationTitle("ADDING EMPLOYEE");
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setGender(GenderEnum.MALE);
        employeeEntity.setWorkEndDate(LocalDate.now());
        employeeEntity.setWorkStartDate(LocalDate.now());
        emplyeeRepository.save(employeeEntity);
        zooView.displayCollection(emplyeeRepository.findAll());

    }
}
