package com.devil.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Date currentTime = Calendar.getInstance().getTime();
        TextView dt = findViewById(R.id.Curr_Datetime);
        dt.setText(currentTime.toString());
    }
}