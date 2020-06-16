package pl.codegood.nosql.model.converters;

import io.objectbox.converter.PropertyConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements PropertyConverter<LocalDate, String> {
    @Override
    public LocalDate convertToEntityProperty(String s) {
        if (s == null) {
            return null;
        }
        return LocalDate.parse(s, DateTimeFormatter.BASIC_ISO_DATE);
    }

    @Override
    public String convertToDatabaseValue(LocalDate localDate) {
        if(localDate == null) {
            return null;
        }
        return localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
    }
}
