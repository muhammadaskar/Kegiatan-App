package com.askar.validasi.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.askar.validasi.DB.DAO.KegiatanDao;
import com.askar.validasi.DB.Entity.Kegiatan;

@Database(entities = {Kegiatan.class}, version = 1)
public abstract class DatabaseKegiatan extends RoomDatabase {

    public abstract KegiatanDao kegiatanDao();
    public final static String DATABASE_NAME = "_kegiatan";
}
