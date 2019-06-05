package com.retail.store.domain.bill;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @JsonProperty
    @NotNull(message = "price can not be null")
    private Double price = null;

    @JsonProperty
    @NotNull(message = "name can not be null")
    private String name = null;

    /**
     * <ul>
     * <li>GROCERY</li>
     * <li>CLEANING</li>
     * <li>CLOTHING</li>
     * <li>HARDWARE</li>
     * <li>COMPUTER</li>
     * <li>STATIONARY</li>
     * </ul>
     */
    @JsonProperty
    @NotNull(message = "type can not be null")
    private ItemType type = null;

    public Item() {

    }

    public Item(Double price, String name, ItemType type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public final Double getPrice() {
        return price;
    }

    public final void setPrice(Double price) {
        this.price = price;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final ItemType getType() {
        return type;
    }

    public final void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        //@formatter:off
        
        return new StringBuilder()
                .append(this.getClass().getSimpleName())
                .append('[')
                .append(name)
                .append(',')
                .append(type)
                .append(',')
                .append(price)
                .append(']')
                .toString();
        
        //@formatter:on
    }
}
