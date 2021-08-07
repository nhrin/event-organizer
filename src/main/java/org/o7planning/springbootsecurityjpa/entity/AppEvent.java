package org.o7planning.springbootsecurityjpa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "App_Event")
public class AppEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Event_Id", nullable = false, unique = true)
    private Long eventId;

    @Column(name = "Event_Name", length = 30)
    private String eventName;

    @Column(name = "Event_Place", length = 100, nullable = false)
    private String eventPlace;

    @Column(name = "Event_Description", length = 300)
    private String eventDescription;

    @Column(name = "Event_Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;

    @ManyToMany
    @JoinTable(
            name = "visitors",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<AppUser> goes;


    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Set<AppUser> getGoes() {
        return goes;
    }

    public void setGoes(Set<AppUser> goes) {
        this.goes = goes;
    }

    @Override
    public String toString() {
        return this.getEventName() + " " + this.getEventDate();
    }
}