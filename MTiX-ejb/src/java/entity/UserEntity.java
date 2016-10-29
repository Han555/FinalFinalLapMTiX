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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Student-ID
 */
@Entity
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String salt;
    private String mobileNumber;
    private boolean firstLogin;
    private boolean resetPassword;
    private ArrayList<String> roles;

    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DOB;
//    private String phone;
    private String loyaltyCardId;
    private Integer loyaltyPoints;
    private Double cumulativeSpending;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date joinDate;

    @OneToMany(orphanRemoval = true, mappedBy = "user")
    private Collection<TicketSales> ticketSales = new ArrayList<TicketSales>();

    @OneToMany(mappedBy = "user")
    private Collection<SubEvent> subEvents = new ArrayList<SubEvent>();

    @OneToMany(mappedBy = "user")
    private Collection<Event> events = new ArrayList<Event>();

    @OneToMany
    private Collection<MessageEntity> messages = new ArrayList<MessageEntity>();

    @ManyToMany
    private Collection<BulletinEntity> bulletins = new ArrayList<BulletinEntity>();

    @ManyToMany
    private Collection<RightsEntity> rights = new ArrayList<RightsEntity>();

    @OneToMany
    private Collection<ShopCartRecordEntity> payments = new ArrayList<ShopCartRecordEntity>();

    @OneToMany
    private Collection<BookingFees> bookingfees = new ArrayList<BookingFees>();

    @OneToMany
    private Collection<LicensePaymentEntity> licensePayments = new ArrayList<LicensePaymentEntity>();

    @OneToOne(orphanRemoval = true, mappedBy = "user")
    private CompanyProfile companyProfile;
    
    @OneToMany
    private Collection<TicketTakingsEntity> ticketTakings = new ArrayList<TicketTakingsEntity> ();

    public UserEntity() {
    }

    public Collection<TicketTakingsEntity> getTicketTakings() {
        return ticketTakings;
    }

    public void setTicketTakings(Collection<TicketTakingsEntity> ticketTakings) {
        this.ticketTakings = ticketTakings;
    }

    public Collection<LicensePaymentEntity> getLicensePayments() {
        return licensePayments;
    }

    public void setLicensePayments(Collection<LicensePaymentEntity> licensePayments) {
        this.licensePayments = licensePayments;
    }

    public Collection<BookingFees> getBookingfees() {
        return bookingfees;
    }

    public void setBookingfees(Collection<BookingFees> bookingfees) {
        this.bookingfees = bookingfees;
    }

    public Collection<ShopCartRecordEntity> getPayments() {
        return payments;
    }

    public void setPayments(Collection<ShopCartRecordEntity> payments) {
        this.payments = payments;
    }

    public Collection<RightsEntity> getRights() {
        return rights;
    }

    public void setRights(Collection<RightsEntity> rights) {
        this.rights = rights;
    }

    public Collection<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Collection<MessageEntity> messages) {
        this.messages = messages;
    }

    public Collection<BulletinEntity> getBulletins() {
        return bulletins;
    }

    public void setBulletins(Collection<BulletinEntity> bulletins) {
        this.bulletins = bulletins;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public void createAccount(String username, String password, String mobileNumber, String salt) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.firstLogin = true;
        this.resetPassword = false;
        this.salt = salt;
        ArrayList<String> roles = new ArrayList();
        roles.add("customer");
        this.roles = roles;

        ArrayList<String> dynamic = new ArrayList();
        dynamic.add("buy tickets");
        dynamic.add("finances");
        RightsEntity rights = new RightsEntity();
        rights.createRight("customer", dynamic);
        this.rights.add(rights);
    }

    public void createCustomer(String username, String password, String mobileNumber, String salt, String firstName, String lastName, Integer age, Date DOB, Integer loyaltyPoints) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.firstLogin = true;
        this.resetPassword = false;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.age = age;
        this.loyaltyPoints = 0;
        this.joinDate = new Date();
        ArrayList<String> roles = new ArrayList();
        roles.add("customer");
        this.roles = roles;

        ArrayList<String> dynamic = new ArrayList();
        dynamic.add("buy tickets");
        dynamic.add("finances");
        RightsEntity rights = new RightsEntity();
        rights.createRight("customer", dynamic);
        this.rights.add(rights);
    }

    public Collection<SubEvent> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(Collection<SubEvent> subEvents) {
        this.subEvents = subEvents;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserEntity[ id=" + userId + " ]";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getLoyaltyCardId() {
        return loyaltyCardId;
    }

    public void setLoyaltyCardId(String loyaltyCardId) {
        this.loyaltyCardId = loyaltyCardId;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Double getCumulativeSpending() {
        return cumulativeSpending;
    }

    public void setCumulativeSpending(Double cumulativeSpending) {
        this.cumulativeSpending = cumulativeSpending;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Collection<TicketSales> getTicketSales() {
        return ticketSales;
    }

    public void setTicketSales(Collection<TicketSales> ticketSales) {
        this.ticketSales = ticketSales;
    }
}
