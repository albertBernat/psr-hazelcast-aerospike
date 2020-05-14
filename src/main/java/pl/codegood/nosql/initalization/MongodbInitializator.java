package pl.codegood.nosql.initalization;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.repository.RepositoryWrapper;
import pl.codegood.nosql.repository.ZooRepository;

public class MongodbInitializator implements ClientInitializator{

    @Override
    public RepositoryWrapper createRepositories() {
        return null;
    }
}
