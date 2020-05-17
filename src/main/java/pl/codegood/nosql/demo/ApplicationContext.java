package pl.codegood.nosql.demo;

public class ApplicationContext {

    private final ApplicationAlgorithm applicationAlgorithm;

    public ApplicationContext(ApplicationAlgorithm applicationAlgorithm) {
        this.applicationAlgorithm = applicationAlgorithm;
    }

    public void performDemo(String dbName) throws InterruptedException {
        applicationAlgorithm.performDemo(dbName);
    }
}
