package pl.codegood.nosql.demo.keyvalue;

import com.aerospike.client.AerospikeClient;
import com.hazelcast.core.HazelcastInstance;
import pl.codegood.nosql.bootstrap.Bootstrap;
import pl.codegood.nosql.bootstrap.KeyValueBootstrap;
import pl.codegood.nosql.config.AerospikeConfig;
import pl.codegood.nosql.config.HazelcastConfig;
import pl.codegood.nosql.constatnts.EntitiesConstants;
import pl.codegood.nosql.demo.ApplicationAlgorithm;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.keyvalue.AerospikeRepository;
import pl.codegood.nosql.repository.keyvalue.HazelcastRepository;
import pl.codegood.nosql.repository.keyvalue.KeyValueRepositoryWrapper;
import pl.codegood.nosql.repository.keyvalue.ZooKeyValueRepository;
import pl.codegood.nosql.view.ZooView;

public class KeyValueDemo implements ApplicationAlgorithm {

    @Override
    public void performDemo(String dbName) throws InterruptedException {
        KeyValueRepositoryWrapper keyValueRepositoryWrapper = createRepositories(dbName);
        ZooKeyValueRepository<Long, AnimalEntity> animalRepository = keyValueRepositoryWrapper.getAnimalRepository();
        ZooKeyValueRepository<Long, TicketEntity> ticketRepository = keyValueRepositoryWrapper.getTicketRepository();
        ZooKeyValueRepository<Long, EmployeeEntity> employeesRepository = keyValueRepositoryWrapper.getEmployeesRepository();

        Bootstrap dataBootstrap = new KeyValueBootstrap(animalRepository, ticketRepository, employeesRepository);
        // Load some data
        dataBootstrap.loadAnimals();
        dataBootstrap.loadTickets();
        dataBootstrap.loadEmplyees();

        //Perform demo
        ZooView zooView = new ZooView();
        KeyValueShowrun keyValueShowrun = new KeyValueShowrun(zooView, animalRepository, ticketRepository, employeesRepository);
        keyValueShowrun.animalCrudDemo();
        keyValueShowrun.ticketCrudDemo();
        keyValueShowrun.employeeCrudDemo();
        keyValueShowrun.predicatesDemo();
    }

    private KeyValueRepositoryWrapper createRepositories(String dbName) {
        if ("hazelcast".equals(dbName)) {
            HazelcastInstance hazelcastInstance = HazelcastConfig.getHazelcastInstance();
            KeyValueRepositoryWrapper keyValueRepositoryWrapper = new KeyValueRepositoryWrapper();
            keyValueRepositoryWrapper.setAnimalRepository(new HazelcastRepository<>(hazelcastInstance, EntitiesConstants.ANIMALS));
            keyValueRepositoryWrapper.setEmployeesRepository(new HazelcastRepository<>(hazelcastInstance, EntitiesConstants.EMPLOYEES));
            keyValueRepositoryWrapper.setTicketRepository(new HazelcastRepository<>(hazelcastInstance, EntitiesConstants.TICKETS));
            return keyValueRepositoryWrapper;
        } else if ("aerospike".equals(dbName)) {
            AerospikeClient aerospikeClient = AerospikeConfig.getClient();
            KeyValueRepositoryWrapper keyValueRepositoryWrapper = new KeyValueRepositoryWrapper();
            keyValueRepositoryWrapper.setAnimalRepository(new AerospikeRepository<>(aerospikeClient, EntitiesConstants.ANIMALS));
            keyValueRepositoryWrapper.setEmployeesRepository(new AerospikeRepository<>(aerospikeClient, EntitiesConstants.EMPLOYEES));
            keyValueRepositoryWrapper.setTicketRepository(new AerospikeRepository<>(aerospikeClient, EntitiesConstants.TICKETS));
            return keyValueRepositoryWrapper;
        } else {
            throw new UnsupportedOperationException("No such database implemented");
        }
    }
}
