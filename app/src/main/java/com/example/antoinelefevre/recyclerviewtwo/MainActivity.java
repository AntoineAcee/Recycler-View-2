package com.example.antoinelefevre.recyclerviewtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date;


    @BindView(R.id.recyclerView) RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<File> files = new ArrayList<>();
        try { date = dateFormat.parse("20/02/2018"); } catch (ParseException e) {}
        files.add(new File("Bill Report", "You", new Timestamp(date.getTime()), File.FileType.EXCEL));

        try { date = dateFormat.parse("12/02/2018"); } catch (ParseException e) {}
        files.add(new File("Diagnostics Reports", "Ayush Kumar", new Timestamp(date.getTime()), File.FileType.EXCEL));

        try { date = dateFormat.parse("09/02/2018"); } catch (ParseException e) {}
        files.add(new File("Design Effort", "Ayush Kumar", new Timestamp(date.getTime()), File.FileType.PDF));

        try { date = dateFormat.parse("12/01/2018"); } catch (ParseException e) {}
        files.add(new File("Lab Tests", "Samar Mahajan", new Timestamp(date.getTime()), File.FileType.PDF));

        try { date = dateFormat.parse("18/01/2018"); } catch (ParseException e) {}
        files.add(new File("Medication Guidlines", "Ayush Kumar", new Timestamp(date.getTime()), File.FileType.WORD));

        try { date = dateFormat.parse("14/01/2018"); } catch (ParseException e) {}
        files.add(new File("Pill Reports", "Rajesh Goel", new Timestamp(date.getTime()), File.FileType.EXCEL));

        try { date = dateFormat.parse("01/01/2018"); } catch (ParseException e) {}
        files.add(new File("Medical Card", "Vikash Sexena", new Timestamp(date.getTime()), File.FileType.WORD));

        try { date = dateFormat.parse("20/01/2018"); } catch (ParseException e) {}
        files.add(new File("Record for Ayush Kushwaha", "Ayush Goel", new Timestamp(date.getTime()), File.FileType.PDF));


        FileAdapter fileAdapter = new FileAdapter(files);
        recyclerView.setAdapter(fileAdapter);
    }
}
