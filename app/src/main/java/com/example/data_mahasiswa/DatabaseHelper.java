package com.example.data_mahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DataMahasiswa";
    private static final String TABLE_NAME = "tbl_user";
    private static final String KEY_NOMOR = "nomor";
    private static final String KEY_NAME = "nama";
    private static final String KEY_TGL = "tgl_lahir";
    private static final String KEY_JENKEL = "jenkel";
    private static final String KEY_ALAMAT = "alamat";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "Create Table "+TABLE_NAME+"("+KEY_NOMOR+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_TGL+" TEXT,"+KEY_JENKEL+" TEXT,"+KEY_ALAMAT+" TEXT"+")";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = ("drop table if exists " +TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert (Person person) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOMOR,person.getNomor());
        values.put(KEY_NAME,person.getNama());
        values.put(KEY_TGL,person.getTanggal_lahir());
        values.put(KEY_JENKEL,person.getJenis_kelamin());
        values.put(KEY_ALAMAT,person.getAlamat());

        db.insert(TABLE_NAME,null,values);
    }

    public List<Person> selectUserData() {
        ArrayList<Person> userList = new ArrayList<Person>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_NOMOR,KEY_NAME,KEY_TGL,KEY_JENKEL,KEY_ALAMAT};

        Cursor c = db.query(TABLE_NAME, columns,null, null, null,null,null);

        while (c.moveToNext()) {
            int nomor = c.getInt(0);
            String nama = c.getString(1);
            String tgl = c.getString(2);
            String jenkel = c.getString(3);
            String alamat = c.getString(4);

            Person person = new Person();
            person.setNomor(nomor);
            person.setNama(nama);
            person.setTanggal_lahir(tgl);
            person.setJenis_kelamin(jenkel);
            person.setAlamat(alamat);
            userList.add(person);
        }
        return userList;
    }

    public void delete(int nomor) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_NOMOR+"='"+nomor+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }

    public void update (Person person) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,person.getNama());
        values.put(KEY_TGL,person.getTanggal_lahir());
        values.put(KEY_JENKEL,person.getJenis_kelamin());
        values.put(KEY_ALAMAT,person.getAlamat());
        String whereClause = KEY_NOMOR+"='"+person.getNomor()+"'";
        db.update(TABLE_NAME,values,whereClause,null);
    }
}
