package com.devil.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Button sub = findViewById(R.id.submit);
        sub.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccount.this,Dashboard.class);
            startActivity(intent);
        });
    }
}