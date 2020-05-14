package pl.codegood.nosql.initalization;

import com.aerospike.client.AerospikeClient;
import pl.codegood.nosql.config.AerospikeConfig;
import pl.codegood.nosql.constatnts.EntitiesConstants;
import pl.codegood.nosql.repository.AerospikeRepository;
import pl.codegood.nosql.repository.RepositoryWrapper;

public class AerospikeInitializator implements ClientInitializator {
    @Override
    public RepositoryWrapper createRepositories() {
        AerospikeClient aerospikeClient = AerospikeConfig.getClient();
        RepositoryWrapper repositoryWrapper = new RepositoryWrapper();
        repositoryWrapper.setAnimalRepository(new AerospikeRepository<>(aerospikeClient, EntitiesConstants.ANIMALS));
        repositoryWrapper.setEmployeesRepository(new AerospikeRepository<>(aerospikeClient, EntitiesConstants.EMPLOYEES));
        repositoryWrapper.setTicketRepository(new AerospikeRepository<>(aerospikeClient, EntitiesConstants.TICKETS));
        return repositoryWrapper;
    }
}
