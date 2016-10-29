/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author JingYing
 */
@Entity
public class WebContentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventTitle;
    private String synopsis;
    private String fileName;
    private String otherDetails;
    private String admissionRules;
    private String programDetails;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date start;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date end;

    private Boolean reviewWebsite;
    private Boolean approved;
    private String reviewComment;

    private Event event;
    private SubEvent subevent;
    
    public void create(String eventTitle, String synopsis, String fileName, String otherDetails, String admissionRules,
            String programDetails, Date start, Date end){
        this.setEventTitle(eventTitle);
        this.setSynopsis(synopsis);
        this.setFileName(fileName);
        this.setOtherDetails(otherDetails);
        this.setAdmissionRules(admissionRules);
        this.setProgramDetails(programDetails);
        this.setStart(start);
        this.setEnd(end);
        
        this.setReviewWebsite(false);
        this.setApproved(false);
        this.setReviewComment("");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getAdmissionRules() {
        return admissionRules;
    }

    public void setAdmissionRules(String admissionRules) {
        this.admissionRules = admissionRules;
    }

    public String getProgramDetails() {
        return programDetails;
    }

    public void setProgramDetails(String programDetails) {
        this.programDetails = programDetails;
    }

    public Boolean getReviewWebsite() {
        return reviewWebsite;
    }

    public void setReviewWebsite(Boolean reviewWebsite) {
        this.reviewWebsite = reviewWebsite;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public SubEvent getSubevent() {
        return subevent;
    }

    public void setSubevent(SubEvent subevent) {
        this.subevent = subevent;
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

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
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
        if (!(object instanceof WebContentEntity)) {
            return false;
        }
        WebContentEntity other = (WebContentEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WebContentEntity[ id=" + id + " ]";
    }

}
