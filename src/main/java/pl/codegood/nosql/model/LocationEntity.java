package pl.codegood.nosql.model;

import java.io.Serializable;
import java.util.Objects;

public class LocationEntity implements Serializable {
    private String name;
    private String floor;
    private Integer roomNumber;

    public LocationEntity(String name, String floor, Integer roomNumber) {
        this.name = name;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public LocationEntity() {
    }

    public String getName() {
        return name;
    }

    public String getFloor() {
        return floor;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationEntity that = (LocationEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(floor, that.floor) &&
                Objects.equals(roomNumber, that.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, floor, roomNumber);
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "name='" + name + '\'' +
                ", floor='" + floor + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}

