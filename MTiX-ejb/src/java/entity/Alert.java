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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Student-ID
 */
@Entity
public class Alert implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sales; //Below percentage
    private String alertType; //Urgent, Important, Inform
    private String inChargePersonEmail;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alertStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alertEndDate;
    
    @OneToOne
    private SessionEntity session; //The one that has the foriegn key
    
    public void createAlert(int percentage, String alertType, String email, Date start, Date end){
        this.setAlertType(alertType);
        this.setInChargePersonEmail(email);
        this.setAlertStartDate(start);
        this.setAlertEndDate(end);
        this.setSales(percentage);
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInChargePersonEmail() {
        return inChargePersonEmail;
    }

    public void setInChargePersonEmail(String inChargePersonEmail) {
        this.inChargePersonEmail = inChargePersonEmail;
    }

    public Date getAlertStartDate() {
        return alertStartDate;
    }

    public void setAlertStartDate(Date alertStartDate) {
        this.alertStartDate = alertStartDate;
    }

    public Date getAlertEndDate() {
        return alertEndDate;
    }

    public void setAlertEndDate(Date alertEndDate) {
        this.alertEndDate = alertEndDate;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
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
        if (!(object instanceof Alert)) {
            return false;
        }
        Alert other = (Alert) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Alert[ id=" + id + " ]";
    }
    
}
