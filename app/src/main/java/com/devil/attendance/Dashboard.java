package com.devil.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class Dashboard extends AppCompatActivity {
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Date currentTime = Calendar.getInstance().getTime();
        TextView dt = findViewById(R.id.Curr_Datetime);
        Button present = findViewById(R.id.submit_attendance);
//        dt.setText(currentTime.toString());

        mauth = FirebaseAuth.getInstance();
        databaseValue dat = new databaseValue(currentTime.toString(), "Present");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User").child(mauth.getCurrentUser().getEmail().toString());
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.setValue(dat).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Dashboard.this, "Attendance marked sucessful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Dashboard.this, "Retry....", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

class databaseValue {
    private String date;
    private String status;

    public databaseValue(String toString, String present) {
    }
}