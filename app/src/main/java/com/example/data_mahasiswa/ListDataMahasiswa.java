package com.example.data_mahasiswa;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListDataMahasiswa extends AppCompatActivity implements RecyclerDataAdapter.OnUserActionListener{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Person> listPerson;
    Context context;

    Button tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_mahasiswa);
        context = this;

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerview();

        tambah = findViewById(R.id.btnIntentInput);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tambahData = new Intent(context, InputData.class);
                startActivity(tambahData);
            }
        });
    }

    public void setupRecyclerview(){
        DatabaseHelper db = new DatabaseHelper(this);
        listPerson = db.selectUserData();
        RecyclerDataAdapter adapter = new RecyclerDataAdapter(this, listPerson, this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed(){
        Intent move = new Intent(this, MainActivity.class);
        startActivity(move);
    }

    @Override
    public void onUserAction(final Person currentPerson) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Pilihan")
                .setPositiveButton("Lihat Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent lihatData = new Intent(context, DetailData.class);
                        lihatData.putExtra("DETAIL_INTENT", currentPerson);
                        context.startActivity(lihatData);
                    }
                })
                .setNegativeButton("Ubah Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent ubahData = new Intent(context, InputData.class);
                        ubahData.putExtra("UPDATE_INTENT", currentPerson);
                        ubahData.putExtra("UPDATE_ACTION", "Update");
                        context.startActivity(ubahData);
                    }
                })
                .setNeutralButton("Hapus Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.delete(currentPerson.getNomor());
                        setupRecyclerview();
                    }
                });
        AlertDialog dialog2 = dialog.create();
        dialog2.show();
    }
}
