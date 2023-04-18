package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.data.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDbHandler db = new MyDbHandler(MainActivity.this);
       // User cn = new User("Vipul","email","password");
       // db.addUser(cn);

        User user = db.getUser("email","password");


        Log.d("vipull",user.getName()+" "+user.getEmail());

    }
}