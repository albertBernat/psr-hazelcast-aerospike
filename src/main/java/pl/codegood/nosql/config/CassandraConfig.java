package pl.codegood.nosql.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConfig {
    private Cluster cluster;

    private Session session;

    public Session getClient() {
        this.connect("localhost", 9024);
        createZooKeyspace();
        session.execute("USE zoo;");
        return this.session;
    }

    private void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint("127.0.0.1");
//        if (port != null) {
//            b.withPort(port);
//        }
        cluster = b.build();

        session = cluster.connect();
    }

    private void createZooKeyspace() {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append("zoo").append(" WITH replication = {")
                        .append("'class':'").append("SimpleStrategy")
                        .append("','replication_factor':").append("1")
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }

    public void close() {
        session.close();
        cluster.close();
    }
}
