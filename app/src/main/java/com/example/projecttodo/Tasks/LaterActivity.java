package com.example.projecttodo.Tasks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projecttodo.Database.DatabaseHelper;
import com.example.projecttodo.R;

import java.util.ArrayList;

public class LaterActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_later);

        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listLater);
        ambilData();
    }

    private void ambilData() {
        ArrayList<String> arrayList = databaseHelper.getDataLater();
        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter<>(this, R.layout.row_later, R.id.later_task, arrayList);
            listView.setAdapter(arrayAdapter);
        }
        else {
            arrayAdapter.clear();
            arrayAdapter.addAll(arrayList);
            arrayAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                final EditText tambah = new EditText(this);
                AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                        .setTitle("Tambah Catatan Baru")
                        .setMessage("Silahkan tambahkan catatan Anda")
                        .setView(tambah)
                        .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String catatan = String.valueOf(tambah.getText());
                                databaseHelper.insertDataLater(catatan);
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

    public void hapusData(View view) {
        View view1 = (View)view.getParent();
        TextView catatanView = view1.findViewById(R.id.later_task);
        Log.e("String", (String) catatanView.getText());
        String catatan = String.valueOf(catatanView.getText());
        databaseHelper.deleteDataLater(catatan);
        ambilData();
    }

    public void editData(View view) {
        final EditText editText = new EditText(this);
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setTitle("Edit Catatan Anda")
                .setMessage("Silahkan Edit Catatan Anda")
                .setView(editText)
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String catat = (editText.getText().toString());
                        databaseHelper.updateData(catat);
                        ambilData();
                    }
                })
                .setNegativeButton("Batal", null)
                .create();
        alertDialog.show();
    }
}
