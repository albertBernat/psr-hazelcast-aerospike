package pl.codegood.nosql;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.hazelcast.core.HazelcastInstance;
import pl.codegood.nosql.bootstrap.Bootstrap;
import pl.codegood.nosql.bootstrap.ZooBootstrap;
import pl.codegood.nosql.config.AerospikeConfig;
import pl.codegood.nosql.config.HazelcastConfig;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.AerospikeRepository;
import pl.codegood.nosql.repository.HazelcastRepository;
import pl.codegood.nosql.repository.ZooRepository;
import pl.codegood.nosql.view.ZooView;

public class Application {

    private static final String ANIMALS = "animals";
    private static final String TICKETS = "tickets";
    private static final String EMPLOYEES = "employees";

    public static void main(String[] args) throws InterruptedException {
        ZooRepository<Long, AnimalEntity> animalRepository = null;
        ZooRepository<Long, TicketEntity> ticketRepository = null;
        ZooRepository<Long, EmployeeEntity> employeesRepository = null;

        if ("hazelcast".equals(args[0])) {
            HazelcastInstance hazelcastInstance = HazelcastConfig.getHazelcastInstance();
            animalRepository = new HazelcastRepository<>(hazelcastInstance, ANIMALS);
            ticketRepository = new HazelcastRepository<>(hazelcastInstance, TICKETS);
            employeesRepository = new HazelcastRepository<>(hazelcastInstance, EMPLOYEES);
        } else if ("aerospike".equals(args[0])) {
            AerospikeClient aerospikeClient = AerospikeConfig.getClient();
            animalRepository = new AerospikeRepository<>(aerospikeClient, ANIMALS);
            ticketRepository = new AerospikeRepository<>(aerospikeClient, TICKETS);
            employeesRepository = new AerospikeRepository<>(aerospikeClient, EMPLOYEES);
        }
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
}
