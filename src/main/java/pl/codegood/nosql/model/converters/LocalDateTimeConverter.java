package pl.codegood.nosql.model.converters;

import io.objectbox.converter.PropertyConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements PropertyConverter<LocalDateTime, String> {
    @Override
    public LocalDateTime convertToEntityProperty(String s) {
        if (s == null) {
            return null;
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String convertToDatabaseValue(LocalDateTime localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
