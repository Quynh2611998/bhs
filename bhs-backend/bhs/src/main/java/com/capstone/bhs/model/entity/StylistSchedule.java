package com.capstone.bhs.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "stylist_schedule")
public class StylistSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date_time")
	private Date dateTime;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "schedule")
    @JsonIgnoreProperties(value = { "schedule" }, allowSetters = true)
    private Set<ScheduleBooking> scheduleBooking = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "stylistSchedule" }, allowSetters = true)
    private HairStylist stylist;

    @ManyToOne
    @JsonIgnoreProperties(value = { "stylistSchedule" }, allowSetters = true)
    private Shift shift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StylistSchedule id(Long id) {
        this.id = id;
        return this;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public StylistSchedule dateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Set<ScheduleBooking> getScheduleBooking() {
        return this.scheduleBooking;
    }

    public StylistSchedule scheduleBookings(Set<ScheduleBooking> scheduleBooking) {
        this.setScheduleBooking(scheduleBooking);
        return this;
    }

    public StylistSchedule addScheduleBooking(ScheduleBooking scheduleBooking) {
        this.scheduleBooking.add(scheduleBooking);
        scheduleBooking.setSchedule(this);
        return this;
    }

    public StylistSchedule removeScheduleBooking(ScheduleBooking scheduleBooking) {
        this.scheduleBooking.remove(scheduleBooking);
        scheduleBooking.setSchedule(null);
        return this;
    }

    public void setScheduleBooking(Set<ScheduleBooking> scheduleBookings) {
        if (this.scheduleBooking != null) {
            this.scheduleBooking.forEach(i -> i.setSchedule(null));
        }
        if (scheduleBooking != null) {
            scheduleBooking.forEach(i -> i.setSchedule(this));
        }
        this.scheduleBooking = scheduleBookings;
    }

    public HairStylist getStylist() {
        return this.stylist;
    }

    public StylistSchedule stylistId(HairStylist hairStylist) {
        this.setStylist(hairStylist);
        return this;
    }

    public void setStylist(HairStylist hairStylist) {
        this.stylist = hairStylist;
    }

    public Shift getShift() {
        return this.shift;
    }

    public StylistSchedule shiftId(Shift shift) {
        this.setShiftId(shift);
        return this;
    }

    public void setShiftId(Shift shift) {
        this.shift = shift;
    }


}
