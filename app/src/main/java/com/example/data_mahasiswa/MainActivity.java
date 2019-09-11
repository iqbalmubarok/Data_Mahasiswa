package com.example.data_mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button lihatdata, inputdata, informasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lihatdata = findViewById(R.id.btnLihatData);
        inputdata = findViewById(R.id.btnInputData);
        informasi = findViewById(R.id.btnInformasi);

        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihatData = new Intent(MainActivity.this, ListDataMahasiswa.class);
                startActivity(lihatData);
            }
        });

        inputdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputData = new Intent(MainActivity.this, InputData.class);
                startActivity(inputData);
            }
        });

        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Informasi = new Intent(MainActivity.this, Informasi.class);
                startActivity(Informasi);
            }
        });
    }
}
