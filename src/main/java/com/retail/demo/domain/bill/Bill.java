package com.retail.demo.domain.bill;

import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bill {

    @JsonProperty
    @NotNull(message = "id can not be null")
    private Long id;

    @JsonProperty
    @NotNull(message = "storeName can not be null")
    private String storeName;
    
    private List<Item> items = null;

    public Bill() {
        
    }
    
    public Bill(Long id, String storeName, List<Item> items) {
        this.id = id;
        this.storeName = storeName;
        this.items = items;
    }
    
    public final Long getId() {
        return id;
    }
    
    public final void setId(Long id) {
        this.id = id;
    }
    
    public final String getStoreName() {
        return storeName;
    }

    public final void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
    public final List<Item> getItems() {
        return items;
    }
    
    public final void setItems(List<Item> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName())
        .append(" [")
        .append(id)
        .append(',')
        .append(storeName)
        .append(',')
        .append('[');

        if(items != null && !items.isEmpty()) {
            for(int i = 0; i < items.size(); i++) {
                sb.append(items.get(i).toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        else {
            sb.append("null");
        }
        
        return sb.append(']').toString();
    }
}
