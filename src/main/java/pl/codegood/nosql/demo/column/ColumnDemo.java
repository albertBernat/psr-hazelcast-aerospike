package pl.codegood.nosql.demo.column;

import com.datastax.driver.core.Session;
import pl.codegood.nosql.bootstrap.ColumnBootstrap;
import pl.codegood.nosql.config.CassandraConfig;
import pl.codegood.nosql.demo.ApplicationAlgorithm;
import pl.codegood.nosql.demo.Showrun;
import pl.codegood.nosql.repository.column.CassandraAnimalRepository;
import pl.codegood.nosql.repository.column.CassandraEmployeeRepository;
import pl.codegood.nosql.repository.column.CassandraTicketRepository;
import pl.codegood.nosql.view.ZooView;

public class ColumnDemo implements ApplicationAlgorithm {

    @Override
    public void performDemo(String dbName) throws InterruptedException {
        Session client = new CassandraConfig().getClient();
        CassandraAnimalRepository cassandraAnimalRepository = new CassandraAnimalRepository(client);
        CassandraTicketRepository cassandraTicketRepository = new CassandraTicketRepository(client);
        CassandraEmployeeRepository cassandraEmployeeRepository = new CassandraEmployeeRepository(client);
        ColumnBootstrap dataBootstrap = new ColumnBootstrap(cassandraAnimalRepository,cassandraTicketRepository, cassandraEmployeeRepository);
        // Load some data
        dataBootstrap.clearOldData();
        dataBootstrap.loadAnimals();
        dataBootstrap.loadTickets();
        dataBootstrap.loadEmplyees();

        Showrun showrun = new ColumnShowrun(new ZooView(),cassandraAnimalRepository, cassandraTicketRepository, cassandraEmployeeRepository);
//        showrun.animalCrudDemo();
//        showrun.ticketCrudDemo();
        showrun.employeeCrudDemo();
        client.close();
    }
}
