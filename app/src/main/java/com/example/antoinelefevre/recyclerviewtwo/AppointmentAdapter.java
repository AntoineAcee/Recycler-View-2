package com.example.antoinelefevre.recyclerviewtwo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Appointment> appointments;
    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Integer> sectionIndexes = new ArrayList<>();

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public AppointmentAdapter(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
        this.sortAppointment();
    }

    private void sortAppointment() {
        ArrayList<Appointment> amAppointments =  new ArrayList<>();
        ArrayList<Appointment> pmAppointments = new ArrayList<>();

        Collections.sort(appointments, new Comparator<Appointment>() {
            @Override
            public int compare(Appointment appointment1, Appointment appointment2) {
                return appointment1.getDate().compareTo(appointment2.getDate());
            }
        });

        for (Appointment appointment : appointments) {
            if (appointment.isAm()) {
                amAppointments.add(appointment);
                continue;
            }
            pmAppointments.add(appointment);
        }

        if (amAppointments.size() > 0) {
            sectionIndexes.add(0);
            AppointmentHeader amHeader = new AppointmentHeader(true, amAppointments.size(), amAppointments.get(0).getDate(), amAppointments.get(amAppointments.size()-1).getDate());
            objects.add(amHeader);
        }
        objects.addAll(amAppointments);

        if (pmAppointments.size() > 0) {
            sectionIndexes.add(amAppointments.size()+1);
            AppointmentHeader pmHeader = new AppointmentHeader(false, pmAppointments.size(), pmAppointments.get(0).getDate(), pmAppointments.get(pmAppointments.size()-1).getDate());
            objects.add(pmHeader);
        }
        objects.addAll(pmAppointments);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        if (viewType == TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_header, parent, false);
            return new AppointmentAdapter.HeaderViewHolder(itemView);
        }
        return new AppointmentAdapter.AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_HEADER) {
            final AppointmentHeader appointmentHeader = (AppointmentHeader) objects.get(position);
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

            headerViewHolder.appointmentHeaderTitle.setText(appointmentHeader.getAmOrPm());
            headerViewHolder.meetNumber.setText("("+appointmentHeader.getNbAppointment()+")");
            headerViewHolder.startHour.setText(appointmentHeader.getStartDate());
            headerViewHolder.endHour.setText(appointmentHeader.getEndDate());

            return;
        }
        final Appointment appointment = (Appointment) objects.get(position);

        AppointmentViewHolder appointmentViewHolder = (AppointmentViewHolder) holder;
        appointmentViewHolder.startHour.setText(appointment.getFormatedDate());
        appointmentViewHolder.amOrPm.setText(appointment.getAmOrPm());
        appointmentViewHolder.userImage.setImageResource(appointment.getUser().getImageRessource());
        appointmentViewHolder.userName.setText(appointment.getUser().getName());
        appointmentViewHolder.reason.setText(appointment.getReason());
        if (!appointment.getStatus().isEmpty()) {
            appointmentViewHolder.status.setText(appointment.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return sectionIndexes.contains(position);
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.appointment_header_title) TextView appointmentHeaderTitle;
        @BindView(R.id.appointment_header_meet_number) TextView meetNumber;
        @BindView(R.id.appointment_item_start_hour) TextView startHour;
        @BindView(R.id.appointment_header_end_hour) TextView endHour;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.appointment_item_start_hour) TextView startHour;
        @BindView(R.id.appointment_item_am_or_pm) TextView amOrPm;
        @BindView(R.id.appointment_item_image) ImageView userImage;
        @BindView(R.id.appointment_item_user_name) TextView userName;
        @BindView(R.id.appointment_item_reason) TextView reason;
        @BindView(R.id.appointment_item_status) TextView status;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
