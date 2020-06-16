package pl.codegood.nosql.config;

import io.objectbox.BoxStore;
import pl.codegood.nosql.model.MyObjectBox;

public class ObjectboxConfig {

    public BoxStore getClient() {
        return MyObjectBox.builder().name("zoo-db").build();
    }
}
