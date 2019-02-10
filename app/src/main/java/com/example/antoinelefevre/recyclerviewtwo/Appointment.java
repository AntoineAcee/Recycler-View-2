package com.example.antoinelefevre.recyclerviewtwo;

import android.media.Image;

import java.sql.Timestamp;

public class Appointment {
    public enum Status {
        CANCELED, ACCEPTED, NOT_SHOW
    }
    private User user;
    private Timestamp date;
    private Timestamp startedAt;
    private boolean amOrPm;
    private String reason;
    private Status status;

    public Appointment(User user, Timestamp date, Timestamp startedAt, boolean amOrPm, String reason) {
        this.user = user;
        this.date = date;
        this.startedAt = startedAt;
        this.amOrPm = amOrPm;
        this.reason = reason;
    }

    public Appointment(User user, Timestamp date, Timestamp startedAt, boolean amOrPm, String reason, Status status) {
        this.user = user;
        this.date = date;
        this.startedAt = startedAt;
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

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public boolean getAmOrPm() {
        return amOrPm;
    }

    public String getReason() {
        return reason;
    }

    public Status getStatus() {
        return status;
    }
}
