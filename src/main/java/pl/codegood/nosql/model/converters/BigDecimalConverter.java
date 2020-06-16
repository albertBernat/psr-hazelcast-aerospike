package pl.codegood.nosql.model.converters;

import io.objectbox.converter.PropertyConverter;

import java.math.BigDecimal;

public class BigDecimalConverter implements PropertyConverter<BigDecimal, String> {

    @Override
    public BigDecimal convertToEntityProperty(String s) {
        return s == null ? null : new BigDecimal(s);
    }

    @Override
    public String convertToDatabaseValue(BigDecimal bigDecimal) {
        return bigDecimal == null ? null : bigDecimal.toString();
    }
}
