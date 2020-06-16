package pl.codegood.nosql.demo.object;

import io.objectbox.BoxStore;
import pl.codegood.nosql.bootstrap.Bootstrap;
import pl.codegood.nosql.bootstrap.ObjectBootstrap;
import pl.codegood.nosql.config.ObjectboxConfig;
import pl.codegood.nosql.demo.ApplicationAlgorithm;
import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.object.ObjectboxRepository;
import pl.codegood.nosql.view.ZooView;

public class ObjectDemo implements ApplicationAlgorithm {
    @Override
    public void performDemo(String dbName) throws InterruptedException {
        BoxStore client = new ObjectboxConfig().getClient();

        ObjectboxRepository<AnimalEntity> animalRepository = new ObjectboxRepository<>(client, AnimalEntity.class);
        ObjectboxRepository<TicketEntity> ticketRepository = new ObjectboxRepository<>(client, TicketEntity.class);
        ObjectboxRepository<EmployeeEntity> employeeRepository = new ObjectboxRepository<>(client, EmployeeEntity.class);

        Bootstrap bootstrap = new ObjectBootstrap(animalRepository, ticketRepository, employeeRepository);
        bootstrap.loadAnimals();
        bootstrap.loadTickets();
        bootstrap.loadEmplyees();

        ZooView zooView = new ZooView();
        ObjectShowrun objectShowrun = new ObjectShowrun(zooView, animalRepository, ticketRepository,employeeRepository);
        objectShowrun.animalCrudDemo();
        objectShowrun.ticketCrudDemo();
        objectShowrun.employeeCrudDemo();
        objectShowrun.predicatesDemo();
        client.close();
        client.deleteAllFiles();
    }

}
