package com.retail.demo.domain.bill;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bill {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull(message = "storeName can not be null")
    private String storeName;

    public Bill() {
        
    }
    
    public Bill(Long id, String storeName) {
        this.id = id;
        this.storeName = storeName;
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
    
}
