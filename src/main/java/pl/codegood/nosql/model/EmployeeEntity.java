package pl.codegood.nosql.model;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import pl.codegood.nosql.model.converters.GenderEnumConverter;
import pl.codegood.nosql.model.converters.LocalDateConverter;
import pl.codegood.nosql.model.enums.GenderEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class EmployeeEntity implements Serializable {
    @Id
    private Long objectBoxId;
    private Long internalId;
    private String name;
    private String familyName;
    @Convert(converter = LocalDateConverter.class, dbType = String.class)
    private LocalDate workStartDate;
    @Convert(converter = LocalDateConverter.class, dbType = String.class)
    private LocalDate workEndDate;
    @Convert(converter = GenderEnumConverter.class, dbType = String.class)
    private GenderEnum gender;

    public EmployeeEntity(String name, String familyName, LocalDate workStartDate, LocalDate workEndDate, GenderEnum gender) {
        this.name = name;
        this.familyName = familyName;
        this.workStartDate = workStartDate;
        this.workEndDate = workEndDate;
        this.gender = gender;
    }

    public EmployeeEntity(Long internalId, String name, String familyName, LocalDate workStartDate, LocalDate workEndDate, GenderEnum gender) {
        this.internalId = internalId;
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

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setWorkStartDate(LocalDate workStartDate) {
        this.workStartDate = workStartDate;
    }

    public void setWorkEndDate(LocalDate workEndDate) {
        this.workEndDate = workEndDate;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(internalId, that.internalId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(familyName, that.familyName) &&
                Objects.equals(workStartDate, that.workStartDate) &&
                Objects.equals(workEndDate, that.workEndDate) &&
                gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId, name, familyName, workStartDate, workEndDate, gender);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "objectBoxId=" + objectBoxId +
                ", internalId=" + internalId +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", workStartDate=" + workStartDate +
                ", workEndDate=" + workEndDate +
                ", gender=" + gender +
                '}';
    }

    public String toStringInsertStatement() {
        return internalId + ",'" + name + "', '" + familyName + "'," + parseDate(workStartDate) +  ", " + parseDate(workEndDate) +", '" + gender.toString() + "'";
    }

    public String parseDate(LocalDate date) {
        return date == null ? "null":"'"+date.format(DateTimeFormatter.BASIC_ISO_DATE)+"'";
    }

    public Long getObjectBoxId() {
        return objectBoxId;
    }

    public void setObjectBoxId(Long objectBoxId) {
        this.objectBoxId = objectBoxId;
    }
}
