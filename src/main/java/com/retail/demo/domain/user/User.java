package com.retail.demo.domain.user;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty
    private Long id = null;
    
    @JsonProperty
    private String username = null;
    
    @JsonProperty
    private String firstName = null;
    
    @JsonProperty
    private String lastName = null;
    
    /**
     * <ul>
     *  <li>EMPLOYEE</li>
     *  <li>AFFILIATE</li>
     *  <li>CUSTOMER</li>
     * </ul>
     */
    @JsonProperty
    private String type = null;
    
    @JsonProperty
    private Date joinDate = null;
    
    public User() {
        
    }
    
    public User(Long id, String username, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public final String getType() {
        return type;
    }
    
    public final void setType(String type) {
        this.type = type;
    }

    public final Long getId() {
        return id;
    }
    
    public final void setId(Long id) {
        this.id = id;
    }
    
    public final String getUsername() {
        return username;
    }
    
    public final void setUsername(String username) {
        this.username = username;
    }
    
    public final String getFirstName() {
        return firstName;
    }
    
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public final String getLastName() {
        return lastName;
    }
    
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public final Date getJoinDate() {
        return joinDate;
    }
    
    public final void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    
    @Override
    public String toString() {
        return username;
    }
}
