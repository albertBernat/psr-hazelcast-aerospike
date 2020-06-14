package pl.codegood.nosql.model;

import pl.codegood.nosql.model.enums.TicketTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TicketEntity implements Serializable {
    private Long internalId;
    private LocalDateTime buyDateTime;
    private BigDecimal price;
    private TicketTypeEnum ticketTypeEnum;

    public TicketEntity(LocalDateTime buyDateTime, BigDecimal price, TicketTypeEnum ticketTypeEnum) {
        this.buyDateTime = buyDateTime;
        this.price = price;
        this.ticketTypeEnum = ticketTypeEnum;
    }

    public TicketEntity(Long internalId, LocalDateTime buyDateTime, BigDecimal price, TicketTypeEnum ticketTypeEnum) {
        this.internalId = internalId;
        this.buyDateTime = buyDateTime;
        this.price = price;
        this.ticketTypeEnum = ticketTypeEnum;
    }

    public TicketEntity() {
    }

    public LocalDateTime getBuyDateTime() {
        return buyDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TicketTypeEnum getTicketTypeEnum() {
        return ticketTypeEnum;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public void setTicketTypeEnum(TicketTypeEnum ticketTypeEnum) {
        this.ticketTypeEnum = ticketTypeEnum;
    }

    public void setBuyDateTime(LocalDateTime buyDateTime) {
        this.buyDateTime = buyDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(internalId, that.internalId) &&
                Objects.equals(buyDateTime, that.buyDateTime) &&
                Objects.equals(price, that.price) &&
                ticketTypeEnum == that.ticketTypeEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId, buyDateTime, price, ticketTypeEnum);
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "internalId=" + internalId +
                ", buyDateTime=" + buyDateTime +
                ", price=" + price +
                ", ticketTypeEnum=" + ticketTypeEnum +
                '}';
    }

    public String toStringInsertStatement() {
        return internalId + ", '" + buyDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "', " + price + ", '" +ticketTypeEnum.toString()+"'";
    }
}
