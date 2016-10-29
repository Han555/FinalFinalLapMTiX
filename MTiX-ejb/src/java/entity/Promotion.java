/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author JingYing
 */
@Entity
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double discountRate;
    private String descriptions;
    private String requirements; //one word for example, volume discount -- 2, 2 tickets to get the discount/ 
    private int numberOfTickets;                            //or visa -- use visa to get the discount
    
    @ManyToOne
    private Event event; 
    @ManyToOne
    private SubEvent subEvent; 
    @ManyToMany
    private Collection<PromotionType> promotionsType = new ArrayList<PromotionType>();
    
    
    public void create(String name, Double discountRate, String descriptions, String requirements){
        this.setName(name);
        this.setDiscountRate(discountRate);
        this.setDescriptions(descriptions);
        this.setRequirements(requirements);
        this.setNumberOfTickets(0);
    }
    
    public void createDummy(String name, Double discountRate, String descriptions, String requirements) {
        this.setName(name);
        this.setDiscountRate(discountRate);
        this.setDescriptions(descriptions);
        this.setRequirements(requirements);
        Integer i=1;
        Event e = new Event();
        e.setId(Long.valueOf(i.longValue()));
        this.setEvent(e);
        SubEvent s = new SubEvent();
        s.setId(Long.valueOf(i.longValue()));
        this.setSubEvent(s);
        this.setNumberOfTickets(0);
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public SubEvent getSubEvent() {
        return subEvent;
    }

    public void setSubEvent(SubEvent subEvent) {
        this.subEvent = subEvent;
    }

    public Collection<PromotionType> getPromotionsType() {
        return promotionsType;
    }

    public void setPromotionsType(Collection<PromotionType> promotionsType) {
        this.promotionsType = promotionsType;
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
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Promotion[ id=" + id + " ]";
    }
    
}
