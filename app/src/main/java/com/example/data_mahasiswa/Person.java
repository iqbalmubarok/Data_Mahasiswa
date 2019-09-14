package com.example.data_mahasiswa;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    int nomor;
    String nama;
    String tanggal_lahir;
    String jenis_kelamin;
    String alamat;

    public Person() {

    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.nomor);
        dest.writeString(this.nama);
        dest.writeString(this.tanggal_lahir);
        dest.writeString(this.jenis_kelamin);
        dest.writeString(this.alamat);
    }

    protected Person(Parcel in) {
        this.nomor = in.readInt();
        this.nama = in.readString();
        this.tanggal_lahir = in.readString();
        this.jenis_kelamin = in.readString();
        this.alamat = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
