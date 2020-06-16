package pl.codegood.nosql.model.converters;

import io.objectbox.converter.PropertyConverter;
import pl.codegood.nosql.model.LocationEntity;

public class LocationEntityConverter implements PropertyConverter<LocationEntity,String> {
    @Override
    public LocationEntity convertToEntityProperty(String s) {
        return null;
    }

    @Override
    public String convertToDatabaseValue(LocationEntity locationEntity) {
        return null;
    }
}
