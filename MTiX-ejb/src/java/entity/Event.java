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
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author JingYing
 */
@Entity
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 255)
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date start;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date end;
    private boolean hasSubEvent;

    @OneToMany(orphanRemoval= true,mappedBy = "event")
    private List<SessionEntity> sessions = new ArrayList<SessionEntity>();

    @OneToMany(orphanRemoval= true,mappedBy = "event")
    private List<SubEvent> subEvents = new ArrayList<SubEvent>();

    @ManyToOne //Owning side, has foreign key!
    private UserEntity user;
    
    @OneToMany(orphanRemoval= true, mappedBy = "event")
    private List<Promotion> promotions = new ArrayList<Promotion>();

    @ManyToOne
    private PropertyEntity property;
    
    private String type;
    
    @OneToOne(orphanRemoval= true, mappedBy = "event")
    private WebContentEntity content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropertyEntity getProperty() {
        return property;
    }

    public void setProperty(PropertyEntity property) {
        this.property = property;
    }

    public List<EquipmentEntity> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<EquipmentEntity> equipments) {
        this.equipments = equipments;
    }

    public List<ManpowerEntity> getManpower() {
        return manpower;
    }

    public void setManpower(List<ManpowerEntity> manpower) {
        this.manpower = manpower;
    }
    @ManyToMany
    private List<EquipmentEntity> equipments = new ArrayList<EquipmentEntity>();
    @ManyToMany
    private List<ManpowerEntity> manpower = new ArrayList<ManpowerEntity>();
    
    public void createEvent(String name, String equipment, Integer manpower, Date start, Date end) {
        this.setName(name);
        this.setEnd(end);
        this.setStart(start);
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getHasSubEvent() {
        return hasSubEvent;
    }

    public void setHasSubEvent(Boolean has) {
        hasSubEvent = has;
    }

    public Collection<SessionEntity> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionEntity> sessions) {
        this.sessions = sessions;
    }

    public List<SubEvent> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(List<SubEvent> subEvents) {
        this.subEvents = subEvents;
    }

    public boolean isHasSubEvent() {
        return hasSubEvent;
    }

    public void setHasSubEvent(boolean hasSubEvent) {
        this.hasSubEvent = hasSubEvent;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

   

    public Collection<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public WebContentEntity getContent() {
        return content;
    }

    public void setContent(WebContentEntity content) {
        this.content = content;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Event[ id=" + id + " ]";
    }

}
