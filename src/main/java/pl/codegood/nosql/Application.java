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
        if (false) {
            // Create objects to DI
            HazelcastInstance hazelcastInstance = HazelcastConfig.getHazelcastInstance();
            ZooRepository<Long, AnimalEntity> animalRepository = new HazelcastRepository<>(hazelcastInstance, ANIMALS);
            ZooRepository<Long, TicketEntity> ticketRepository = new HazelcastRepository<>(hazelcastInstance, TICKETS);
            ZooRepository<Long, EmployeeEntity> employeesRepository = new HazelcastRepository<>(hazelcastInstance, EMPLOYEES);
            Bootstrap hazelcastBootstrap = new ZooBootstrap(animalRepository, ticketRepository, employeesRepository);

            // Load some data
            hazelcastBootstrap.loadAnimals();
            hazelcastBootstrap.loadTickets();
            hazelcastBootstrap.loadEmplyees();

            //Perform demo
            ZooView zooView = new ZooView();
            Demo demo = new Demo(zooView,animalRepository, ticketRepository, employeesRepository);
            demo.animalCrudDemo();
            demo.ticketCrudDemo();
            demo.employeeCrudDemo();
            demo.predicatesDemo();

        } else {
            AerospikeClient client = AerospikeConfig.getClient();
            ZooRepository<Long, AnimalEntity> animalRepository = new AerospikeRepository<>(client, "animals");
            ZooRepository<Long, TicketEntity> ticketRepository = new AerospikeRepository<>(client, "tickets");
            ZooRepository<Long, EmployeeEntity> employeeRepository = new AerospikeRepository<>(client, "employees");
            Bootstrap bootstrap = new ZooBootstrap(animalRepository, ticketRepository, employeeRepository);
            bootstrap.loadEmplyees();
            bootstrap.loadAnimals();
            bootstrap.loadTickets();
            ZooView zooView = new ZooView();
            Demo demo = new Demo(zooView, animalRepository, ticketRepository, employeeRepository);
            demo.animalCrudDemo();
            demo.employeeCrudDemo();
            demo.ticketCrudDemo();
            demo.predicatesDemo();
        }

    }
}
