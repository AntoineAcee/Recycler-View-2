package com.example.antoinelefevre.recyclerviewtwo;

import android.graphics.Color;
import android.media.Image;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Appointment {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    public enum Status {
        CANCELED, ACCEPTED, NO_SHOW
    }
    private User user;
    private Timestamp date;
    private boolean amOrPm;
    private String reason;
    private Status status;

    public Appointment(User user, Timestamp date, boolean amOrPm, String reason) {
        this.user = user;
        this.date = date;
        this.amOrPm = amOrPm;
        this.reason = reason;
    }

    public Appointment(User user, Timestamp date, boolean amOrPm, String reason, Status status) {
        this.user = user;
        this.date = date;
        this.amOrPm = amOrPm;
        this.reason = reason;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getAmOrPm() {
        return amOrPm ? "AM" : "PM";
    }

    public boolean isAm() {
        return amOrPm;
    }

    public String getReason() {
        return reason;
    }

    public int getStatusColor(){
        if (status == Status.ACCEPTED)
            return Color.GREEN;
        return Color.RED;
    }

    public String getStatus() {
        if (status == null) {
            return "";
        }
        switch (status) {
            case ACCEPTED:
                return "Accepted";
            case CANCELED:
                return "Canceled";
            case NO_SHOW:
                return "No Show";
        }
        return "";
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setAmOrPm(boolean amOrPm) {
        this.amOrPm = amOrPm;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFormatedDate() {
        return dateFormat.format(date);
    }
}
