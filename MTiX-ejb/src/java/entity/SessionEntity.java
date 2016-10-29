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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author JingYing
 */
@Entity
public class SessionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date timeStart;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date timeEnd;
    private String descriptions;
    private String seatOption;

    @ManyToOne //Owning side, has foreign key!
    private Event event;
    @ManyToOne //Owning side, has foreign key!
    private SubEvent subEvent;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "session")
    private Collection<SessionCategoryPrice> price = new ArrayList<SessionCategoryPrice>();
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "session")
    private Collection<SessionSeatsInventory> seatsInventory = new ArrayList<SessionSeatsInventory>();
    @OneToOne(cascade={CascadeType.REMOVE}, mappedBy = "session")
    private Alert alert;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "session")
    private Collection<TicketSales> ticketSales = new ArrayList<TicketSales>();
    
    
    public void createSession(String name, Date start, Date end, String descriptions) {
        this.setName(name);
        this.setDescriptions(descriptions);
        this.setTimeStart(start);
        this.setTimeEnd(end);
        this.setSeatOption(null);
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

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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

    public Collection<SessionCategoryPrice> getPrice() {
        return price;
    }

    public void setPrice(Collection<SessionCategoryPrice> price) {
        this.price = price;
    }

    public String getSeatOption() {
        return seatOption;
    }

    public void setSeatOption(String seatOption) {
        this.seatOption = seatOption;
    }

    public Collection<SessionSeatsInventory> getSeatsInventory() {
        return seatsInventory;
    }

    public void setSeatsInventory(Collection<SessionSeatsInventory> seatsInventory) {
        this.seatsInventory = seatsInventory;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Collection<TicketSales> getTicketSales() {
        return ticketSales;
    }

    public void setTicketSales(Collection<TicketSales> ticketSales) {
        this.ticketSales = ticketSales;
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
        if (!(object instanceof SessionEntity)) {
            return false;
        }
        SessionEntity other = (SessionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Session[ id=" + id + " ]";
    }

}
