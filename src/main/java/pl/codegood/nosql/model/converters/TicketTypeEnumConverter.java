package pl.codegood.nosql.model.converters;

import io.objectbox.converter.PropertyConverter;
import pl.codegood.nosql.model.enums.TicketTypeEnum;

public class TicketTypeEnumConverter implements PropertyConverter<TicketTypeEnum, String> {
    @Override
    public TicketTypeEnum convertToEntityProperty(String s) {
        return s == null ? null : TicketTypeEnum.valueOf(s);
    }

    @Override
    public String convertToDatabaseValue(TicketTypeEnum ticketTypeEnum) {
        return ticketTypeEnum == null ? null : ticketTypeEnum.name();
    }
}
