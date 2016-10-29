/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author catherinexiong
 */
@Entity
public class FoodOutletEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String outletId;
    private String outletName;
    private String outletType;
    private String location;
    @Column(length = 255)
    private String outletDescription;
    @ManyToOne
    private PropertyEntity property;
    
    public void createFoodOutlets(String outletId, String outletName, String outletType,String outletDescription){
        this.setOutletId(outletId);
        this.setOutletName(outletName);
        this.setOutletType(outletType);
        this.setOutletDescription(outletDescription);
    }
    
    public void createOutlet(String name, String type, String description, String location) {
        this.outletName = name;
        this.outletType = type;
        this.outletDescription = description;
        this.setLocation(location);
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
        if (!(object instanceof FoodOutletEntity)) {
            return false;
        }
        FoodOutletEntity other = (FoodOutletEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FoodOutlet[ id=" + id + " ]";
    }

    /**
     * @return the outletName
     */
    public String getOutletName() {
        return outletName;
    }

    /**
     * @param outletName the outletName to set
     */
    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    /**
     * @return the outletType
     */
    public String getOutletType() {
        return outletType;
    }

    /**
     * @param outletType the outletType to set
     */
    public void setOutletType(String outletType) {
        this.outletType = outletType;
    }

    /**
     * @return the outletDescription
     */
    public String getOutletDescription() {
        return outletDescription;
    }

    /**
     * @param outletDescription the outletDescription to set
     */
    public void setOutletDescription(String outletDescription) {
        this.outletDescription = outletDescription;
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
     * @return the outletId
     */
    public String getOutletId() {
        return outletId;
    }

    /**
     * @param outletId the outletId to set
     */
    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
}
