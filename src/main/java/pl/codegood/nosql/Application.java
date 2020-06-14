package pl.codegood.nosql;

import pl.codegood.nosql.demo.ApplicationContext;
import pl.codegood.nosql.demo.column.ColumnDemo;
import pl.codegood.nosql.demo.document.DocumentDemo;
import pl.codegood.nosql.demo.keyvalue.KeyValueDemo;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        String dbName = args[0];
        if ("hazelcast".equals(dbName) || "aerospike".equals(dbName)) {
            new ApplicationContext(new KeyValueDemo()).performDemo(dbName);
        } else if ("mongodb".equals(dbName) || "ejdb".equals(dbName)) {
            new ApplicationContext(new DocumentDemo()).performDemo(dbName);
        } else if ("cassandra".equals(dbName)) {
            new ApplicationContext(new ColumnDemo()).performDemo(dbName);
        }
    }
}
