package com.devil.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth = FirebaseAuth.getInstance();

        EditText email_input = findViewById(R.id.email_input);
        EditText password_input = findViewById(R.id.password_in);
        Button crt_acc = findViewById(R.id.button);
        crt_acc.setOnClickListener(v -> {
            String email = email_input.getText().toString();
            String password = password_input.getText().toString();
            signinUser(email,password);
        });

        findViewById(R.id.button2).setOnClickListener(v -> {
            String email = email_input.getText().toString();
            String password = password_input.getText().toString();
            loginUser(email,password);
        });
    }

    private void signinUser(String email, String password) {
        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(MainActivity.this,CreateAccount.class);
                Toast.makeText(MainActivity.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Login Unsucessful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String email, String password){
        mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(MainActivity.this,CreateAccount.class);
                Toast.makeText(MainActivity.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Login Unsucessful", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        user = mauth.getCurrentUser();
        if (user!= null){
            reload();
        }
    }

    private void reload() {
        Intent intent = new Intent(MainActivity.this,Dashboard.class);
        startActivity(intent);
    }
}