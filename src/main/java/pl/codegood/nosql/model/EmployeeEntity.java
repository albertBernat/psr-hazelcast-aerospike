package pl.codegood.nosql.model;

import pl.codegood.nosql.model.enums.GenderEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeEntity implements Serializable {
    private String name;
    private String familyName;
    private LocalDate workStartDate;
    private LocalDate workEndDate;
    private GenderEnum gender;

    public EmployeeEntity(String name, String familyName, LocalDate workStartDate, LocalDate workEndDate, GenderEnum gender) {
        this.name = name;
        this.familyName = familyName;
        this.workStartDate = workStartDate;
        this.workEndDate = workEndDate;
        this.gender = gender;
    }

    public EmployeeEntity() { }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public LocalDate getWorkEndDate() {
        return workEndDate;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(familyName, that.familyName) &&
                Objects.equals(workStartDate, that.workStartDate) &&
                Objects.equals(workEndDate, that.workEndDate) &&
                gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, familyName, workStartDate, workEndDate, gender);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", workStartDate=" + workStartDate +
                ", workEndDate=" + workEndDate +
                ", gender=" + gender +
                '}';
    }
}
