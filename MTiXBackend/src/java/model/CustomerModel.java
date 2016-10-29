/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hyc528
 */
public class CustomerModel {
    private Long userId;
    private String username;
//    private String password;
//    private String salt;
    private String mobileNumber;
   

    private String firstName;
    private String lastName;
    private Integer age;
     private String loyaltyCardId;
    private Integer loyaltyPoints;
//    private Double cumulativeSpending;
    private String joinDate;

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

//    /**
//     * @return the password
//     */
//    public String getPassword() {
//        return password;
//    }
//
//    /**
//     * @param password the password to set
//     */
//    public void setPassword(String password) {
//        this.password = password;
//    }

//    /**
//     * @return the salt
//     */
//    public String getSalt() {
//        return salt;
//    }
//
//    /**
//     * @param salt the salt to set
//     */
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

 
   

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the loyaltyCardId
     */
    public String getLoyaltyCardId() {
        return loyaltyCardId;
    }

    /**
     * @param loyaltyCardId the loyaltyCardId to set
     */
    public void setLoyaltyCardId(String loyaltyCardId) {
        this.loyaltyCardId = loyaltyCardId;
    }

    /**
     * @return the loyaltyPoints
     */
    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * @param loyaltyPoints the loyaltyPoints to set
     */
    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

//    /**
//     * @return the cumulativeSpending
//     */
//    public Double getCumulativeSpending() {
//        return cumulativeSpending;
//    }
//
//    /**
//     * @param cumulativeSpending the cumulativeSpending to set
//     */
//    public void setCumulativeSpending(Double cumulativeSpending) {
//        this.cumulativeSpending = cumulativeSpending;
//    }

    /**
     * @return the joinDate
     */
    public String getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate the joinDate to set
     */
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
