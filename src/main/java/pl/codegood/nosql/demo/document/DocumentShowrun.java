package pl.codegood.nosql.demo.document;

import pl.codegood.nosql.constatnts.EntitiesConstants;
import pl.codegood.nosql.demo.Showrun;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.document.ZooDocumentRepository;
import pl.codegood.nosql.view.ZooView;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class DocumentShowrun implements Showrun {

    private final ZooView zooView;
    private final ZooDocumentRepository<Long, AnimalEntity> animalRepository;
    private final ZooDocumentRepository<Long, TicketEntity> ticketRepository;
    private final ZooDocumentRepository<Long, EmployeeEntity> emplyeeRepository;

    public DocumentShowrun(ZooView zooView,
                           ZooDocumentRepository<Long, AnimalEntity> animalRepository,
                           ZooDocumentRepository<Long, TicketEntity> ticketRepository,
                           ZooDocumentRepository<Long, EmployeeEntity> emplyeeRepository) {
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

    private void getAllEntities(String entityName, ZooDocumentRepository animalRepository) {
        zooView.displayOperationTitle("ALL " + entityName.toUpperCase());
        zooView.displayCollection(animalRepository.findAll());
    }

    private void addAnimal() {
        zooView.displayOperationTitle("ADDING ANIMAL");
        this.animalRepository.save(new AnimalEntity(55L, "Arniz", "Cow", null, null, null));
        zooView.displayCollection(animalRepository.findAll());
    }

    private void deleteAnimal() {
        zooView.displayOperationTitle("DELETING ANIMAL WITH ID 1");
        animalRepository.deleteByKey(1L);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void editAnimal() {
        zooView.displayOperationTitle("EDITNG NAME OF ENTITY ANIMAL WITH ID 2, AFTER EDIT");
        AnimalEntity foundAnimal = animalRepository.findByKey(2L);
        foundAnimal.setName("NEW NAME");
        animalRepository.deleteByKey(2L);
        animalRepository.save(foundAnimal);
        zooView.displayCollection(animalRepository.findAll());
    }

    private void addTicket() {
        zooView.displayOperationTitle("ADDING TICKET");
        ticketRepository.save(new TicketEntity());
        zooView.displayCollection(ticketRepository.findAll());
    }

    private void deleteTicket() {
        zooView.displayOperationTitle("DELETING TICKIET WITH ID 1");
        ticketRepository.deleteByKey(1L);
        zooView.displayCollection(ticketRepository.findAll());
    }

    private void editTicket() {
        zooView.displayOperationTitle("EDITNG PRICE OF ENTITY TICKET WITH ID 2, AFTER EDIT");
        TicketEntity foundTicket = ticketRepository.findByKey(2L);
        foundTicket.setPrice(new BigDecimal(20));
        ticketRepository.deleteByKey(2L);
        ticketRepository.save(foundTicket);
        zooView.displayCollection(ticketRepository.findAll());
    }

    private void addEmployee() {
        zooView.displayOperationTitle("ADDING EMPLOYEE");
        emplyeeRepository.save(new EmployeeEntity());
        zooView.displayCollection(emplyeeRepository.findAll());;
    }

    private void deleteEmployee() {
        zooView.displayOperationTitle("DELETING EMPLOYEE WITH ID 1");
        emplyeeRepository.deleteByKey(1L);
        zooView.displayCollection(emplyeeRepository.findAll());
    }

    private void editEmployee() {
        zooView.displayOperationTitle("EDITNG NAME OF ENTITY TICKET WITH ID 2, AFTER EDIT");
        EmployeeEntity foundEmployee = emplyeeRepository.findByKey(2L);
        foundEmployee.setName("NEW NAME");
        emplyeeRepository.deleteByKey(2L);
        emplyeeRepository.save(foundEmployee);
        zooView.displayCollection(emplyeeRepository.findAll());
    }
}
