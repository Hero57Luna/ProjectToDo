package com.example.projecttodo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.projecttodo.Database.DBHelper;
import com.example.projecttodo.Fragments.LaterFragment;

public class MainActivity extends AppCompatActivity implements LaterFragment.OnFragmentInteractionListener {

    // DBHelper dbHelper;
    // ArrayAdapter<String> arrayAdapter;
    // ListView dataList;
    LaterFragment laterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        laterFragment = new LaterFragment();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
