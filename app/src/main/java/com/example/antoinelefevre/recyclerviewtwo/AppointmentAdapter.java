package com.example.antoinelefevre.recyclerviewtwo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Appointment> appointments;

    public AppointmentAdapter(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
