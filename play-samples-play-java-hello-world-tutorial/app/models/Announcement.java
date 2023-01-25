package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

//@Entity
public class Announcement {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String sessionId;
    private String heading;
    private String date;
    private String description;

    public Announcement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id='" + id + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", heading='" + heading + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //    private String colour;
//    private String orgId;
//    private Boolean active;
//    private Boolean emailCheck;
//    //        private List<Label> labels;
//    private String createdDate;
//    private String updatedDate;
//    private boolean isDeleted;
//    private String createdBy;
//    private String createdUsername;
//    private String updatedBy;
//    private List<String> tags;
//
//    public Announcement() {
//    }
//
//    public Announcement(String id, String sessionId, String heading, String date, String description, String colour, String orgId, Boolean active, Boolean emailCheck, String createdDate, String updatedDate, boolean isDeleted, String createdBy, String createdUsername, String updatedBy, List<String> tags) {
//        this.id = id;
//        this.sessionId = sessionId;
//        this.heading = heading;
//        this.date = date;
//        this.description = description;
//        this.colour = colour;
//        this.orgId = orgId;
//        this.active = active;
//        this.emailCheck = emailCheck;
//        this.createdDate = createdDate;
//        this.updatedDate = updatedDate;
//        this.isDeleted = isDeleted;
//        this.createdBy = createdBy;
//        this.createdUsername = createdUsername;
//        this.updatedBy = updatedBy;
//        this.tags = tags;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getSessionId() {
//        return sessionId;
//    }
//
//    public void setSessionId(String sessionId) {
//        this.sessionId = sessionId;
//    }
//
//    public String getHeading() {
//        return heading;
//    }
//
//    public void setHeading(String heading) {
//        this.heading = heading;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getColour() {
//        return colour;
//    }
//
//    public void setColour(String colour) {
//        this.colour = colour;
//    }
//
//    public String getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(String orgId) {
//        this.orgId = orgId;
//    }
//
//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
//
//    public Boolean getEmailCheck() {
//        return emailCheck;
//    }
//
//    public void setEmailCheck(Boolean emailCheck) {
//        this.emailCheck = emailCheck;
//    }
//
//    public String getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(String createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public String getUpdatedDate() {
//        return updatedDate;
//    }
//
//    public void setUpdatedDate(String updatedDate) {
//        this.updatedDate = updatedDate;
//    }
//
//    public boolean isDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        isDeleted = deleted;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public String getCreatedUsername() {
//        return createdUsername;
//    }
//
//    public void setCreatedUsername(String createdUsername) {
//        this.createdUsername = createdUsername;
//    }
//
//    public String getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(String updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public List<String> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<String> tags) {
//        this.tags = tags;
//    }
//
//    @Override
//    public String toString() {
//        return "Announcement{" +
//                "id='" + id + '\'' +
//                ", sessionId='" + sessionId + '\'' +
//                ", heading='" + heading + '\'' +
//                ", date='" + date + '\'' +
//                ", description='" + description + '\'' +
//                ", colour='" + colour + '\'' +
//                ", orgId='" + orgId + '\'' +
//                ", active=" + active +
//                ", emailCheck=" + emailCheck +
//                ", createdDate='" + createdDate + '\'' +
//                ", updatedDate='" + updatedDate + '\'' +
//                ", isDeleted=" + isDeleted +
//                ", createdBy='" + createdBy + '\'' +
//                ", createdUsername='" + createdUsername + '\'' +
//                ", updatedBy='" + updatedBy + '\'' +
//                ", tags=" + tags +
//                '}';
//    }
}
