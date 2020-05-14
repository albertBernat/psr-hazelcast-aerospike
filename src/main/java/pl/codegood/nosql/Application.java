package pl.codegood.nosql;

import pl.codegood.nosql.bootstrap.Bootstrap;
import pl.codegood.nosql.bootstrap.ZooBootstrap;
import pl.codegood.nosql.initalization.AerospikeInitializator;
import pl.codegood.nosql.initalization.ClientInitializator;
import pl.codegood.nosql.initalization.DatabaseContext;
import pl.codegood.nosql.initalization.HazelcastInitializator;
import pl.codegood.nosql.initalization.MongodbInitializator;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.RepositoryWrapper;
import pl.codegood.nosql.repository.ZooRepository;
import pl.codegood.nosql.view.ZooView;

public class Application {


    public static void main(String[] args) throws InterruptedException {
        //create repositories and connection for given database
        RepositoryWrapper repositoryWrapper = getRepostioriesForSpecificDatabase(args[0]);
        ZooRepository<Long, AnimalEntity> animalRepository = repositoryWrapper.getAnimalRepository();
        ZooRepository<Long, TicketEntity> ticketRepository = repositoryWrapper.getTicketRepository();
        ZooRepository<Long, EmployeeEntity> employeesRepository = repositoryWrapper.getEmployeesRepository();

        Bootstrap dataBootstrap = new ZooBootstrap(animalRepository, ticketRepository, employeesRepository);
        // Load some data
        dataBootstrap.loadAnimals();
        dataBootstrap.loadTickets();
        dataBootstrap.loadEmplyees();

        //Perform demo
        ZooView zooView = new ZooView();
        Demo demo = new Demo(zooView, animalRepository, ticketRepository, employeesRepository);
        demo.animalCrudDemo();
        demo.ticketCrudDemo();
        demo.employeeCrudDemo();
        demo.predicatesDemo();
    }

    private static RepositoryWrapper getRepostioriesForSpecificDatabase(String dbName) {
        if ("hazelcast".equals(dbName)) {
            return createRepositories(new HazelcastInitializator());
        } else if ("aerospike".equals(dbName)) {
            return createRepositories(new AerospikeInitializator());
        } else if ("mongodb".equals(dbName)) {
            return createRepositories(new MongodbInitializator());
        } else {
            throw new UnsupportedOperationException("No such database implemented");
        }
    }

    private static RepositoryWrapper createRepositories(ClientInitializator clientInitializator) {
        DatabaseContext databaseContext = new DatabaseContext(clientInitializator);
        return databaseContext.initializeRepositories();
    }
}
