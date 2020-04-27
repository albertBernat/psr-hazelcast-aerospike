package pl.codegood.nosql.view;

import java.util.Collection;
import java.util.Map;

public class ZooView {

    private static final String SPACER = "=======================================";

    public void displayOperationTitle(String title) {
        System.out.println(SPACER);
        System.out.println(title);
        System.out.println(SPACER);
    }

    public void displayMap(Map toDisplay) {
        toDisplay.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    public void displayCollection(Collection toDisplay) {
        toDisplay.forEach(System.out::println);
    }
}
