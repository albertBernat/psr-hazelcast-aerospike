package pl.codegood.nosql.initalization;

import pl.codegood.nosql.repository.RepositoryWrapper;

public class DatabaseContext {

    private final ClientInitializator strategy;

    public DatabaseContext(ClientInitializator strategy) {
        this.strategy = strategy;
    }

    public RepositoryWrapper initializeRepositories() {
        return strategy.createRepositories();
    }
}
