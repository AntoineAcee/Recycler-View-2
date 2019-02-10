package com.example.antoinelefevre.recyclerviewtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentActivity extends AppCompatActivity {
    @BindView(R.id.appointment_rv) RecyclerView appointmentRv;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);


        ArrayList<Appointment> appointments = new ArrayList<>();

        User user1 = new User("Antoine Lefevre");
        User user2 = new User("Alexandre Menielle");

        try { date = dateFormat.parse("12/01/2019 10:20"); } catch (ParseException e) {}
        appointments.add(new Appointment(user1, new Timestamp(date.getTime()), true, "Dentiste"));

        try { date = dateFormat.parse("12/01/2019 20:20"); } catch (ParseException e) {}
        appointments.add(new Appointment(user1, new Timestamp(date.getTime()), false, "Cheval"));

        try { date = dateFormat.parse("12/01/2019 6:20"); } catch (ParseException e) {}
        appointments.add(new Appointment(user2, new Timestamp(date.getTime()), true, "Sodo"));

        try { date = dateFormat.parse("12/01/2019 4:20"); } catch (ParseException e) {}
        appointments.add(new Appointment(user2, new Timestamp(date.getTime()), false, "Apex"));

        try { date = dateFormat.parse("12/01/2019 22:00"); } catch (ParseException e) {}
        appointments.add(new Appointment(user2, new Timestamp(date.getTime()), true, "Dodo"));

        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(appointments);

        appointmentRv.setAdapter(appointmentAdapter);
    }
}
