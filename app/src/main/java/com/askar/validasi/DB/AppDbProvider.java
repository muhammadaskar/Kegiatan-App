package com.askar.validasi.DB;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider {

    private static AppDatabase instance;
    private static DatabaseKegiatan ins;

    public static AppDatabase getInstance(Context context){
        if (AppDbProvider.instance == null){
            AppDbProvider.instance = Room.databaseBuilder(
                    context, AppDatabase.class, instance.DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return AppDbProvider.instance;
    }

    public static DatabaseKegiatan databaseKegiatan(Context context){
        if (AppDbProvider.ins == null){
            AppDbProvider.ins = Room.databaseBuilder(
                    context, DatabaseKegiatan.class, ins.DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return AppDbProvider.ins;
    }
}
