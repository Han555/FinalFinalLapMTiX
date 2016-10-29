/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 *
 * @author catherinexiong
 */
@Entity
public class SeatEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberInSection;
    
    
    @ManyToOne
    private SectionEntity section;
    
    public void createSeat(Integer numberInSection){
        this.setNumberInSection(numberInSection);
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
        if (!(object instanceof SeatEntity)) {
            return false;
        }
        SeatEntity other = (SeatEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Seat[ id=" + id + " ]";
    }

  

    /**
     * @return the section
     */
    public SectionEntity getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(SectionEntity section) {
        this.section = section;
    }

    /**
     * @return the numberInSection
     */
    public Integer getNumberInSection() {
        return numberInSection;
    }


    /**
     * @param numberInSection the numberInSection to set
     */
    public void setNumberInSection(Integer numberInSection) {
        this.numberInSection = numberInSection;
    }
    
}
