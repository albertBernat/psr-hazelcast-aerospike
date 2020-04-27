package pl.codegood.nosql.config;

import com.aerospike.client.AerospikeClient;

public class AerospikeConfig {

    public static AerospikeClient getClient() {
        return new AerospikeClient("localhost", 3000);
    }
}
