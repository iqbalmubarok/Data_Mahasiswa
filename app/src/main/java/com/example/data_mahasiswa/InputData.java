package com.example.data_mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputData extends AppCompatActivity {

    EditText edtNo, edtName, edtTanggal, edtJenis, edtAlamat;
    Button btnSubmit;
    Context context;
    Person updated;
    String aksi, nomor = "Submit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        context = this;

        aksi = getIntent().getStringExtra("UPDATE_ACTION");
        updated = getIntent().getParcelableExtra("UPDATE_INTENT");
        if (aksi == null){
            aksi = "Submit";
        }else {
            nomor = String.valueOf(updated.getNomor());
        }

        edtNo = findViewById(R.id.edtNomor);
        edtName = findViewById(R.id.edtNama);
        edtTanggal = findViewById(R.id.edtTglLahir);
        edtJenis = findViewById(R.id.edtJenkel);
        edtAlamat = findViewById(R.id.edtAlamat);
        btnSubmit = findViewById(R.id.btnSimpan);

        if (aksi.equals("Update")){
            btnSubmit.setText("Update");
            edtNo.setText(nomor);
            edtNo.setFocusable(false);
            edtName.setText(updated.getNama());
            edtTanggal.setText(updated.getTanggal_lahir());
            edtJenis.setText(updated.getJenis_kelamin());
            edtAlamat.setText(updated.getAlamat());

            Log.d("test", updated.getNama());
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(context);
                Person person = new Person();
                String label = btnSubmit.getText().toString();
                if (label.equals("Simpan")){
                    person.setNomor(Integer.parseInt(edtNo.getText().toString()));
                    person.setNama(edtName.getText().toString());
                    person.setTanggal_lahir(edtTanggal.getText().toString());
                    person.setJenis_kelamin(edtJenis.getText().toString());
                    person.setAlamat(edtAlamat.getText().toString());
                    db.insert(person);
                    Intent move = new Intent(context, ListDataMahasiswa.class);
                    context.startActivity(move);
                }
                if (label.equals("Update")){
                    person.setNomor(Integer.parseInt(edtNo.getText().toString()));
                    person.setNama(edtName.getText().toString());
                    person.setTanggal_lahir(edtTanggal.getText().toString());
                    person.setJenis_kelamin(edtJenis.getText().toString());
                    person.setAlamat(edtAlamat.getText().toString());
                    db.update(person);
                    Intent move = new Intent(context, ListDataMahasiswa.class);
                    context.startActivity(move);
                }
            }
        });
    }
}
