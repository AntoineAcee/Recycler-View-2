package com.example.antoinelefevre.recyclerviewtwo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AppointmentHeader {
    private boolean isAm;
    private int nbAppointment;
    private Timestamp endDate;
    private Timestamp startDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");


    public AppointmentHeader(boolean isAm, int nbAppointment, Timestamp startDate, Timestamp endDate) {
        this.isAm = isAm;
        this.nbAppointment = nbAppointment;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public boolean isAm() {
        return isAm;
    }

    public String getAmOrPm() {
        return isAm ? "MORNING" : "EVENING";
    }

    public int getNbAppointment() {
        return nbAppointment;
    }

    public String getEndDate() {
        return dateFormat.format(endDate);
    }

    public String getStartDate() {
        return dateFormat.format(startDate) + " - ";
    }
}
