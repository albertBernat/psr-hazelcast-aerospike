package pl.codegood.nosql.model;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import pl.codegood.nosql.model.converters.LocalDateConverter;
import pl.codegood.nosql.model.converters.LocationEntityConverter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class AnimalEntity implements Serializable {

    @Id
    private Long objectBoxId;

    private Long internalId;
    private String name;
    private String race;
    @Convert(converter = LocalDateConverter.class, dbType = String.class)
    private LocalDate birthDate;
    @Convert(converter = LocalDateConverter.class, dbType = String.class)
    private LocalDate deathDate;
    @Convert(converter = LocationEntityConverter.class, dbType = String.class)
    private LocationEntity location;

    public AnimalEntity(String name, String race, LocalDate birthDate, LocalDate deathDate, LocationEntity location) {
        this.name = name;
        this.race = race;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.location = location;
    }

    public AnimalEntity(Long internalId, String name, String race, LocalDate birthDate, LocalDate deathDate, LocationEntity location) {
        this.internalId = internalId;
        this.name = name;
        this.race = race;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.location = location;
    }

    public AnimalEntity() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public Long getObjectBoxId() {
        return objectBoxId;
    }

    public void setObjectBoxId(Long objectBoxId) {
        this.objectBoxId = objectBoxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalEntity that = (AnimalEntity) o;
        return Objects.equals(internalId, that.internalId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(race, that.race) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(deathDate, that.deathDate) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId, name, race, birthDate, deathDate, location);
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "objectBoxId=" + objectBoxId +
                ", internalId=" + internalId +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                ", location=" + location +
                '}';
    }

    public String toStringInsertStatement() {
        return internalId + ", " + "'" + name + "'" + ", " + "'" + race + "'" + ", " + "'" + birthDate + "'" + ", " + "'" + deathDate + "'";
    }
}
