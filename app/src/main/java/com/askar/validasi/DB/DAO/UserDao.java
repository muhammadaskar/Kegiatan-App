package com.askar.validasi.DB.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.askar.validasi.DB.Entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * From User")
    List<User> getAll();

    @Insert
    long insertRegister(User user);

    @Query("Select * From User Where username = :username AND re_password = :password")
    User findByUsernameAndPassword(String username, String password);

    @Query("Select * From User Where username = :username Limit 1")
    User selectDetailUser(String username);
}
