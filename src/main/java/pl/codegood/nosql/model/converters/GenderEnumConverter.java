package pl.codegood.nosql.model.converters;

import io.objectbox.converter.PropertyConverter;
import pl.codegood.nosql.model.enums.GenderEnum;

public class GenderEnumConverter implements PropertyConverter<GenderEnum, String> {
    @Override
    public GenderEnum convertToEntityProperty(String s) {
        return s == null ? null : GenderEnum.valueOf(s);
    }

    @Override
    public String convertToDatabaseValue(GenderEnum genderEnum) {
        return genderEnum == null ? null : genderEnum.name();
    }
}
