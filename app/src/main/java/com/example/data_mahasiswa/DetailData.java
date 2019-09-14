package com.example.data_mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DetailData extends AppCompatActivity {

    EditText edtNo, edtNama, edtTgl, edtJenkel, edtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        edtNo = findViewById(R.id.txtNo);
        edtNama = findViewById(R.id.txtNama);
        edtTgl = findViewById(R.id.txtTgl);
        edtJenkel = findViewById(R.id.txtJenkel);
        edtAlamat = findViewById(R.id.txtAlamat);

        edtNo.setFocusable(false);
        edtNama.setFocusable(false);
        edtTgl.setFocusable(false);
        edtJenkel.setFocusable(false);
        edtAlamat.setFocusable(false);

        Person person = getIntent().getParcelableExtra("DETAIL_INTENT");
        edtNo.setText(String.valueOf(person.getNomor()));
        edtNama.setText(person.getNama());
        edtTgl.setText(person.getTanggal_lahir());
        edtJenkel.setText(person.getJenis_kelamin());
        edtAlamat.setText(person.getAlamat());
    }
}
