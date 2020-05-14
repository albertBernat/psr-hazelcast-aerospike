package pl.codegood.nosql.initalization;

import com.hazelcast.core.HazelcastInstance;
import pl.codegood.nosql.config.HazelcastConfig;
import pl.codegood.nosql.constatnts.EntitiesConstants;
import pl.codegood.nosql.repository.HazelcastRepository;
import pl.codegood.nosql.repository.RepositoryWrapper;

public class HazelcastInitializator implements ClientInitializator {
    @Override
    public RepositoryWrapper createRepositories() {
        HazelcastInstance hazelcastInstance = HazelcastConfig.getHazelcastInstance();
        RepositoryWrapper repositoryWrapper = new RepositoryWrapper();
        repositoryWrapper.setAnimalRepository(new HazelcastRepository<>(hazelcastInstance, EntitiesConstants.ANIMALS));
        repositoryWrapper.setEmployeesRepository(new HazelcastRepository<>(hazelcastInstance, EntitiesConstants.EMPLOYEES));
        repositoryWrapper.setTicketRepository(new HazelcastRepository<>(hazelcastInstance, EntitiesConstants.TICKETS));
        return repositoryWrapper;
    }
}
