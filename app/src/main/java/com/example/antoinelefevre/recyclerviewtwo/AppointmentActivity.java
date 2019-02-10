package com.example.antoinelefevre.recyclerviewtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class AppointmentActivity extends AppCompatActivity {
    @BindView(R.id.appointment_rv) RecyclerView appointmentRv;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);
        setupCalendar();


        ArrayList<Appointment> appointments = new ArrayList<>();

        User user1 = new User("Amily Watson");
        User user2 = new User("Mordin Chereladan");
        User user3 = new User("Raul Gupta");
        User user4 = new User("Ayush Kushwaha");
        User user5 = new User("Anchal Sharma");

        try { date = dateFormat.parse("12/01/2019 8:30"); } catch (ParseException e) {}
        appointments.add(new Appointment(user1, new Timestamp(date.getTime()), true, "Video Consultation", Appointment.Status.ACCEPTED));

        try { date = dateFormat.parse("12/01/2019 11:30"); } catch (ParseException e) {}
        appointments.add(new Appointment(user2, new Timestamp(date.getTime()), true, "Phone Consultation"));

        try { date = dateFormat.parse("12/01/2019 4:30"); } catch (ParseException e) {}
        appointments.add(new Appointment(user3, new Timestamp(date.getTime()), false, "Video Consultation"));

        try { date = dateFormat.parse("12/01/2019 5:30"); } catch (ParseException e) {}
        appointments.add(new Appointment(user4, new Timestamp(date.getTime()), false, "Chat conversation", Appointment.Status.CANCELED));

        try { date = dateFormat.parse("12/01/2019 8:30"); } catch (ParseException e) {}
        appointments.add(new Appointment(user5, new Timestamp(date.getTime()), true, "Phone Consultation", Appointment.Status.NO_SHOW));

        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(appointments);

        appointmentRv.setAdapter(appointmentAdapter);
    }

    private void setupCalendar(){
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                System.out.println(date);
            }
        });
    }
}
