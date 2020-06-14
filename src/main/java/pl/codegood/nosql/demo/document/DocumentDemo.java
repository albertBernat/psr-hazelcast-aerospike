package pl.codegood.nosql.demo.document;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
//import com.softmotions.ejdb2.EJDB2;
import pl.codegood.nosql.bootstrap.DocumentBootstrap;
import pl.codegood.nosql.config.EJDBConfig;
import pl.codegood.nosql.config.MongodbConfig;
import pl.codegood.nosql.constatnts.EntitiesConstants;
import pl.codegood.nosql.demo.ApplicationAlgorithm;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.document.DocumentRepositoryWrapper;
//import pl.codegood.nosql.repository.document.EjdbRepository;
import pl.codegood.nosql.repository.document.MongodbRepository;
import pl.codegood.nosql.repository.document.ZooDocumentRepository;
import pl.codegood.nosql.view.ZooView;

public class DocumentDemo implements ApplicationAlgorithm {

    @Override
    public void performDemo(String dbName) throws InterruptedException {
        DocumentRepositoryWrapper keyValueRepositoryWrapper = createRepositories(dbName);
        ZooDocumentRepository<Long, AnimalEntity> animalRepository = keyValueRepositoryWrapper.getAnimalRepository();
        ZooDocumentRepository<Long, TicketEntity> ticketRepository = keyValueRepositoryWrapper.getTicketRepository();
        ZooDocumentRepository<Long, EmployeeEntity> employeesRepository = keyValueRepositoryWrapper.getEmployeesRepository();

        DocumentBootstrap dataBootstrap = new DocumentBootstrap(animalRepository, ticketRepository, employeesRepository);
        // Load some data
        dataBootstrap.clearOldData();
        dataBootstrap.loadAnimals();
        dataBootstrap.loadTickets();
        dataBootstrap.loadEmplyees();

        //Perform demo
        ZooView zooView = new ZooView();
        DocumentShowrun documentShowrun = new DocumentShowrun(zooView, animalRepository, ticketRepository, employeesRepository);
        documentShowrun.animalCrudDemo();
        documentShowrun.ticketCrudDemo();
        documentShowrun.employeeCrudDemo();
        documentShowrun.predicatesDemo();
    }

    private DocumentRepositoryWrapper createRepositories(String dbName) {
        Gson gson = new Gson();
        if ("mongodb".equals(dbName)) {
            MongoClient mongoClient = MongodbConfig.getClient();
            DocumentRepositoryWrapper documentRepositoryWrapper = new DocumentRepositoryWrapper();
            documentRepositoryWrapper.setAnimalRepository(new MongodbRepository<>(mongoClient, EntitiesConstants.ANIMALS, gson, AnimalEntity.class));
            documentRepositoryWrapper.setEmployeesRepository(new MongodbRepository<>(mongoClient, EntitiesConstants.EMPLOYEES, gson, EmployeeEntity.class));
            documentRepositoryWrapper.setTicketRepository(new MongodbRepository<>(mongoClient, EntitiesConstants.TICKETS, gson, TicketEntity.class));
            return documentRepositoryWrapper;
        } else if ("ejdb".equals(dbName)){
//            EJDB2 client = new EJDBConfig().getClient();
//            DocumentRepositoryWrapper documentRepositoryWrapper = new DocumentRepositoryWrapper();
//            documentRepositoryWrapper.setAnimalRepository(new EjdbRepository<>(client, EntitiesConstants.ANIMALS, gson, AnimalEntity.class));
//            documentRepositoryWrapper.setEmployeesRepository(new EjdbRepository<>(client, EntitiesConstants.EMPLOYEES, gson, EmployeeEntity.class));
//            documentRepositoryWrapper.setTicketRepository(new EjdbRepository<>(client, EntitiesConstants.TICKETS, gson, TicketEntity.class));
//            return documentRepositoryWrapper;
            return null;
        } else {
            throw new UnsupportedOperationException("No such database implemented");
        }
    }
}
