package pl.codegood.nosql.constatnts;

public class EntitiesConstants {

    public static final String ANIMALS = "animals";
    public static final String TICKETS = "tickets";
    public static final String EMPLOYEES = "employees";
    public static final long SLEEP_DURATION_IN_SECONDS = 3L;

    private EntitiesConstants() {
        throw new UnsupportedOperationException("Cannot create instance of this class");
    }
}
