/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author catherinexiong
 */
@Entity
public class SectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private Integer numberInProperty;
    private Integer capacity;
    @Column(length = 1000)
    private String coords;
    //@Column(length = 1000)
    //private String seatMap;
    
    @ManyToOne
    private PropertyEntity property;
    
    @OneToMany(cascade={CascadeType.ALL},mappedBy="section")
    private List<SeatEntity> seats = new ArrayList<SeatEntity>();
    
    @ManyToOne
    private SectionCategoryEntity category;
    
    public void createSection(Integer numberInProperty, Integer cpapacity){
        this.setCapacity(capacity);
        this.setNumberInProperty(numberInProperty);
        
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
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SectionEntity)) {
            return false;
        }
        SectionEntity other = (SectionEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SectionEntity[ id=" + getId() + " ]";
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
     * @return the seats
     */
    public List<SeatEntity> getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }

 

    /**
     * @return the capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the numberInProperty
     */
    public Integer getNumberInProperty() {
        return numberInProperty;
    }

  

    /**
     * @param numberInProperty the numberInProperty to set
     */
    public void setNumberInProperty(Integer numberInProperty) {
        this.numberInProperty = numberInProperty;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(SectionCategoryEntity category) {
        this.category = category;
    }

    /**
     * @return the category
     */
    public SectionCategoryEntity getCategory() {
        return category;
    }

    /**
     * @return the coords
     */
    public String getCoords() {
        return coords;
    }

    /**
     * @param coords the coords to set
     */
    public void setCoords(String coords) {
        this.coords = coords;
    }

  
    
}
