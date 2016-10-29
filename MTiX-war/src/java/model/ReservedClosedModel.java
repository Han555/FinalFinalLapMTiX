/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author catherinexiong
 */
public class ReservedClosedModel {
    private Long id;
    private Integer numberInProperty;
    private Integer capacity;
   

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getNumberInProperty() {
        return numberInProperty;
    }

    public void setNumberInProperty(Integer numberInProperty) {
        this.numberInProperty = numberInProperty;
    }
    
    
    
}