
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author catherinexiong
 */
@Entity
public class PropertyEntity implements Serializable {
    private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyName;
    private Integer propertyNo;
    private Integer capacity;
    
    
    @OneToMany(orphanRemoval = true,mappedBy="property")
    private List<Event> events = new ArrayList<Event>();
    
    @OneToMany(orphanRemoval = true, mappedBy="property")
    private List<SubEvent> subEvents = new ArrayList<SubEvent>();
    
    @OneToMany(orphanRemoval = true,mappedBy="property")
    private List<SectionEntity> sections = new ArrayList<SectionEntity>();
    
    @OneToMany(orphanRemoval = true,mappedBy="property")
    private List<ManpowerEntity> manpower = new ArrayList<ManpowerEntity>();
    
    @OneToMany(orphanRemoval = true,mappedBy="property")
    private List<FoodOutletEntity> foodOutlets = new ArrayList<FoodOutletEntity>();
    
    @OneToMany(orphanRemoval = true,mappedBy="property")
    private List<SectionCategoryEntity> category = new ArrayList<SectionCategoryEntity>();
    
    @OneToMany(orphanRemoval = true,mappedBy="property")
    private List<MaintenanceScheduleEntity> maintenanceSchedule = new ArrayList<MaintenanceScheduleEntity>();
    
    @OneToMany(orphanRemoval = true,mappedBy="property")
    private List<EquipmentEntity> equipments = new ArrayList<EquipmentEntity>();
    
    private String types;
    
    private String recommend;
    
//    private String layoutPath;
//    
//    private String mainPath;
    
    public void createProperty(String propertyName, Integer propertyNo, Integer capacity){
        this.setPropertyName(propertyName);
        this.setPropertyNo(propertyNo);
        this.setCapacity(capacity);
      
    
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
        if (!(object instanceof PropertyEntity)) {
            return false;
        }
        PropertyEntity other = (PropertyEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Property[ id=" + getId() + " ]";
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @param propertyName the propertyName to set
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.setCapacity((Integer) capacity);
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
     * @param capacity the capacity to set
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }



    /**
     * @return the manpower
     */
    public List<ManpowerEntity> getManpower() {
        return manpower;
    }

    /**
     * @param manpower the manpower to set
     */
    public void setManpower(List<ManpowerEntity> manpower) {
        this.manpower = manpower;
    }

    /**
     * @return the sections
     */
    public List<SectionEntity> getSections() {
        return sections;
    }

    /**
     * @param sections the sections to set
     */
    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }

    /**
     * @return the foodOutlets
     */
    public List<FoodOutletEntity> getFoodOutlets() {
        return foodOutlets;
    }

    /**
     * @param foodOutlets the foodOutlets to set
     */
    public void setFoodOutlets(List<FoodOutletEntity> foodOutlets) {
        this.foodOutlets = foodOutlets;
    }

    /**
     * @return the propertyNo
     */
    public Integer getPropertyNo() {
        return propertyNo;
    }

    /**
     * @param propertyNo the propertyNo to set
     */
    public void setPropertyNo(Integer propertyNo) {
        this.propertyNo = propertyNo;
    }

    /**
     * @return the maintenanceSchedule
     */
    public List<MaintenanceScheduleEntity> getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    /**
     * @param maintenanceSchedule the maintenanceSchedule to set
     */
    public void setMaintenanceSchedule(List<MaintenanceScheduleEntity> maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
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

    /**
     * @return the category
     */
    public List<SectionCategoryEntity> getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(List<SectionCategoryEntity> category) {
        this.category = category;
    }

    /**
     * @return the equipment
     */
    public List<EquipmentEntity> getEquipments() {
        return equipments;
    }

    /**
     * @param equipment the equipment to set
     */
    public void setEquipments(List<EquipmentEntity> equipment) {
        this.equipments = equipment;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

 

    /**
     * @return the recommend
     */
    public String getRecommend() {
        return recommend;
    }

    /**
     * @param recommend the recommend to set
     */
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

   
    
}
