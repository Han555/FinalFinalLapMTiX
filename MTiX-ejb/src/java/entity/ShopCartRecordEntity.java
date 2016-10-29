/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Student-ID
 */
@Entity
public class ShopCartRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String ticketQuantity;
    private String paymentStatus;
    private String amount;
    private String payer;
    private String receiver;
    private String address;
    private String city;
    private String zip;
    private String country;
    private String promotion;
    
     @OneToMany
     private Collection<SeatEntity> seats = new ArrayList<SeatEntity> ();
     
     @OneToOne
     private SectionEntity section = new SectionEntity();
     
     @OneToOne
     private SessionEntity session = new SessionEntity();
    
//     @OneToOne
//     private Promotion promo = new Promotion();
     
     
    public ShopCartRecordEntity() {
    }

   
    
    

    public Collection<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(Collection<SeatEntity> seats) {
        this.seats = seats;
    }

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }
    
    public void createRecord(String payer, String receiver, String eventName, String ticketQuantity, String amount, String promotion) {
        this.payer = payer;
        this.receiver = receiver;
        
        SessionEntity s = new SessionEntity();
        s = this.getSession();
        if(s.getEvent()==null) {
            this.eventName = s.getSubEvent().getName();
        } else {
            this.eventName = s.getEvent().getName();
        }
        
        this.ticketQuantity = ticketQuantity;
        this.amount = amount;
        this.promotion = promotion;
        this.city = "";
        this.address = "";
        this.zip = "";
        this.country = "";
        this.paymentStatus = "unpaid";
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(String ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(object instanceof ShopCartRecordEntity)) {
            return false;
        }
        ShopCartRecordEntity other = (ShopCartRecordEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PaymentRecord[ id=" + id + " ]";
    }
    
}
