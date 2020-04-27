package pl.codegood.nosql.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastConfig {

    public static HazelcastInstance getHazelcastClient() {
        return HazelcastClient.newHazelcastClient(getClientConfig());
    }

    public static HazelcastInstance getHazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }

    private static ClientConfig getClientConfig() {
        ClientConfig config = new ClientConfig();
        ClientNetworkConfig network = config.getNetworkConfig();
        network.addAddress("localhost");
        return config;
    }
}
