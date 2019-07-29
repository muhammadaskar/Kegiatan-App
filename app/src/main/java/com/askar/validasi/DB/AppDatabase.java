package com.askar.validasi.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.askar.validasi.DB.DAO.UserDao;
import com.askar.validasi.DB.Entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();


    public final static String DATABASE_NAME = "db_user";
}
