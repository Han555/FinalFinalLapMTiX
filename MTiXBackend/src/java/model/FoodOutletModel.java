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
public class FoodOutletModel {
    private Long id;
    private String outletId;
    private String outletName;
    private String outletType;
    private String outletDescription;

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


}
