package pl.codegood.nosql.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;

public class ApplicationConfig {

    public void getConfiguration() {
        HazelcastInstance instance = HazelcastConfig.getHazelcastInstance();
        HazelcastInstance hazelcastClient = HazelcastConfig.getHazelcastClient();
    }
}
