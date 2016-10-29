/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author JingYing
 */
@Entity
public class SessionSeatsInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean reserveTickets;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reservationEndDate;
    private Boolean stopTicketsSales;
    private String reason;
    
    @ManyToOne
    private SessionEntity session;
    @ManyToOne //Uni-Direction
    private SectionEntity sectionEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReserveTickets() {
        return reserveTickets;
    }

    public void setReserveTickets(Boolean reserveTickets) {
        this.reserveTickets = reserveTickets;
    }

    public Date getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public Boolean getStopTicketsSales() {
        return stopTicketsSales;
    }

    public void setStopTicketsSales(Boolean stopTicketsSales) {
        this.stopTicketsSales = stopTicketsSales;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public SectionEntity getSectionEntity() {
        return sectionEntity;
    }

    public void setSectionEntity(SectionEntity sectionEntity) {
        this.sectionEntity = sectionEntity;
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
        if (!(object instanceof SessionSeatsInventory)) {
            return false;
        }
        SessionSeatsInventory other = (SessionSeatsInventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SessionSeatsInventory[ id=" + id + " ]";
    }
    
}
