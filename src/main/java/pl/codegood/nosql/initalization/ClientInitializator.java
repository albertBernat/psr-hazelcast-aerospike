package pl.codegood.nosql.initalization;

import pl.codegood.nosql.repository.RepositoryWrapper;

public interface ClientInitializator {
    RepositoryWrapper createRepositories();
}
