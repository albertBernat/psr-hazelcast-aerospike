package pl.codegood.nosql.initalization;

import com.mongodb.MongoClient;
import pl.codegood.nosql.config.MongodbConfig;
import pl.codegood.nosql.repository.RepositoryWrapper;

public class MongodbInitializator implements ClientInitializator {

    @Override
    public RepositoryWrapper createRepositories() {
        MongoClient client = MongodbConfig.getClient();

        return null;
    }
}
