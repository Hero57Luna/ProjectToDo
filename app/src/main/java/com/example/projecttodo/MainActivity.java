package com.example.projecttodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;


import com.example.projecttodo.Tasks.ImportantActivity;
import com.example.projecttodo.Tasks.LaterActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void laterHandler(View view) {
        Intent intent = new Intent(this, LaterActivity.class);
        startActivity(intent);
    }

    public void importantHandler(View view) {
        Intent intent = new Intent(this, ImportantActivity.class);
        startActivity(intent);
    }
}
