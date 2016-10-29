/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hyc528
 */
public class ManpowerModel {
    private Long id;
    private String staffRole;
    private String number;
    private Boolean standard;
    private Integer price;

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
     * @return the staffRole
     */
    public String getStaffRole() {
        return staffRole;
    }

    /**
     * @param staffRole the staffRole to set
     */
    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the standard
     */
    public Boolean getStandard() {
        return standard;
    }

    /**
     * @param standard the standard to set
     */
    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    /**
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
