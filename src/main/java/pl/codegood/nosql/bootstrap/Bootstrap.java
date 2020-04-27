package pl.codegood.nosql.bootstrap;

import pl.codegood.nosql.model.AnimalEntity;
import pl.codegood.nosql.model.EmployeeEntity;
import pl.codegood.nosql.model.LocationEntity;
import pl.codegood.nosql.model.TicketEntity;
import pl.codegood.nosql.model.enums.GenderEnum;
import pl.codegood.nosql.model.enums.TicketTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public interface Bootstrap {
    void loadAnimals();
    void loadTickets();
    void loadEmplyees();

    default Map<Long, TicketEntity> getTickets() {
        Map<Long, TicketEntity> tickets = new HashMap<>();
        tickets.put(1L, new TicketEntity(LocalDateTime.of(2020,2,2,13,2),new BigDecimal(20), TicketTypeEnum.FULL));
        tickets.put(2L, new TicketEntity(LocalDateTime.of(2020,2,2,12,1),new BigDecimal(20), TicketTypeEnum.JUNIOR));
        tickets.put(3L, new TicketEntity(LocalDateTime.of(2020,2,3,11,11),new BigDecimal(20), TicketTypeEnum.JUNIOR));
        tickets.put(4L, new TicketEntity(LocalDateTime.of(2020,2,4,15,44),new BigDecimal(20), TicketTypeEnum.NORMAL));
        tickets.put(5L, new TicketEntity(LocalDateTime.of(2020,2,4,16,41),new BigDecimal(20), TicketTypeEnum.NORMAL));
        tickets.put(6L, new TicketEntity(LocalDateTime.of(2020,2,5,14,22),new BigDecimal(20), TicketTypeEnum.NORMAL));
        tickets.put(7L, new TicketEntity(LocalDateTime.of(2020,2,6,15,42),new BigDecimal(20), TicketTypeEnum.NORMAL));
        tickets.put(8L, new TicketEntity(LocalDateTime.of(2020,2,7,12,12),new BigDecimal(20), TicketTypeEnum.NORMAL));
        return tickets;
    }

    default Map<Long, AnimalEntity> getAnimals() {
        Map<Long, AnimalEntity> animals = new HashMap<>();
        animals.put(1L, new AnimalEntity("Joker", "Tiger", LocalDate.of(2000, 1, 2), null, null));
        animals.put(2L, new AnimalEntity("James", "Lion", LocalDate.of(2010, 1, 2), null, new LocationEntity("Building A", "A2", 12)));
        animals.put(3L, new AnimalEntity("Kundu", "Parrot", LocalDate.of(2020, 6, 2), null, null));
        animals.put(4L, new AnimalEntity("Bohba", "Cat", LocalDate.of(2007, 1, 2), null, null));
        animals.put(5L, new AnimalEntity("Jack", "Elephant", LocalDate.of(1999, 5, 2), LocalDate.of(2010,1,22), null));
        animals.put(6L, new AnimalEntity("Mandy", "Tiger", LocalDate.of(2010, 1, 2), null, null));
        return animals;
    }

    default Map<Long, EmployeeEntity> getEmployees() {
        Map<Long, EmployeeEntity> employees = new HashMap<>();
        employees.put(1L, new EmployeeEntity("Gregory", "Jameson", LocalDate.of(2005,2,2),null, GenderEnum.MALE));
        employees.put(2L, new EmployeeEntity("Johny", "Walker", LocalDate.of(2010,3,2),null, GenderEnum.MALE));
        employees.put(3L, new EmployeeEntity("Napoleon", "Bonaparte", LocalDate.of(2001,1,2),LocalDate.of(2006,5,1), GenderEnum.MALE));
        employees.put(4L, new EmployeeEntity("Jim", "Beam", LocalDate.of(2005,2,2),null, GenderEnum.MALE));
        employees.put(5L, new EmployeeEntity("Jane", "Doe", LocalDate.of(2005,2,2),LocalDate.of(2005,2,3), GenderEnum.FEMALE));
        return employees;
    }
}
