package com.askar.validasi.DB.Entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kegiatan implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nama_kegiatan")
    private String namaKegiatan;

    @ColumnInfo(name = "tanggal")
    private String tanggalDanHariKegiatan;

    @ColumnInfo(name = "deskripsi")
    private String Deskripsi;

    public Kegiatan() {
    }

    public Kegiatan(String namaKegiatan, String tanggalDanHariKegiatan, String deskripsi) {
        this.namaKegiatan = namaKegiatan;
        this.tanggalDanHariKegiatan = tanggalDanHariKegiatan;
        Deskripsi = deskripsi;
    }

    public Kegiatan(String namaKegiatan, String tanggalDanHariKegiatan) {
        this.namaKegiatan = namaKegiatan;
        this.tanggalDanHariKegiatan = tanggalDanHariKegiatan;
    }

    public Kegiatan(int id, String namaKegiatan, String tanggalDanHariKegiatan, String deskripsi) {
        this.id = id;
        this.namaKegiatan = namaKegiatan;
        this.tanggalDanHariKegiatan = tanggalDanHariKegiatan;
        Deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getTanggalDanHariKegiatan() {
        return tanggalDanHariKegiatan;
    }

    public void setTanggalDanHariKegiatan(String tanggalDanHariKegiatan) {
        this.tanggalDanHariKegiatan = tanggalDanHariKegiatan;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    protected Kegiatan(Parcel in) {
        id = in.readInt();
        namaKegiatan = in.readString();
        tanggalDanHariKegiatan = in.readString();
        Deskripsi = in.readString();
    }

    public static final Creator<Kegiatan> CREATOR = new Creator<Kegiatan>() {
        @Override
        public Kegiatan createFromParcel(Parcel in) {
            return new Kegiatan(in);
        }

        @Override
        public Kegiatan[] newArray(int size) {
            return new Kegiatan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(namaKegiatan);
        parcel.writeString(tanggalDanHariKegiatan);
        parcel.writeString(Deskripsi);
    }
}
