package com.example.projecttodo.Tasks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.projecttodo.Database.DatabaseHelper;
import com.example.projecttodo.R;

import java.util.ArrayList;

public class ImportantActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important);

        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listImportant);
        ambilData();
    }

    private void ambilData() {
        ArrayList<String> arrayList = databaseHelper.getDataImportant();
        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter<>(this, R.layout.row_important, R.id.later_task, arrayList);
            listView.setAdapter(arrayAdapter);
        }
        else {
            arrayAdapter.clear();
            arrayAdapter.addAll();
            arrayAdapter.notifyDataSetChanged();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
       // icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_add2:
                final EditText tambah = new EditText(this);
                AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                        .setTitle("Catatan Penting")
                        .setMessage("Tambahkan Catatan Penting Anda")
                        .setView(tambah)
                        .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String catatan = String.valueOf(tambah.getText());
                                databaseHelper.insertDataImportant(catatan);
                                ambilData();
                            }
                        })
                        .setNegativeButton("Kembali", null)
                        .create();
                alertDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hapusDataImportant(View view) {

    }

}
