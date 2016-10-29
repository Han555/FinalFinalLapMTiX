/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 *
 * @author catherinexiong
 */
@Entity
public class ManpowerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String staffRole;
    private Integer number;
    private Boolean standard;
    private Integer price;
    
    @ManyToOne
    private PropertyEntity property;
    @ManyToMany
    private List<Event> events = new ArrayList<Event>();
    @ManyToMany
    private List<SubEvent> subEvents = new ArrayList<SubEvent>();
    
    public void createManpower(String staffRole, Integer number, Boolean standard, Integer price){
        this.setStaffRole(staffRole);
        this.setNumber(number);
        this.setStandard(standard);
        this.setPrice(price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManpowerEntity)) {
            return false;
        }
        ManpowerEntity other = (ManpowerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Manpower[ id=" + id + " ]";
    }

  

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the standard
     */
    public Boolean getStandard() {
        return standard;
    }

    /**
     * @param standard the standard to set
     */
    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    /**
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return the property
     */
    public PropertyEntity getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(PropertyEntity property) {
        this.property = property;
    }

 

    /**
     * @return the staffRole
     */
    public String getStaffRole() {
        return staffRole;
    }

    /**
     * @param staffRole the staffRole to set
     */
    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * @return the subEvents
     */
    public List<SubEvent> getSubEvents() {
        return subEvents;
    }

    /**
     * @param subEvents the subEvents to set
     */
    public void setSubEvents(List<SubEvent> subEvents) {
        this.subEvents = subEvents;
    }
    
}
