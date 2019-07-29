package com.askar.validasi.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.askar.validasi.DB.Entity.Kegiatan;

import java.util.List;

@Dao
public interface KegiatanDao {

    @Query("Select * From Kegiatan")
    List<Kegiatan> getAll();
//
    @Query("Select * From Kegiatan Where id = :id Limit 1")
    Kegiatan selectDetail(int id);
//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAll(Kegiatan kegiatan);
//
    @Delete
    int delete(Kegiatan kegiatan);
//
    @Update
    int update(Kegiatan kegiatan);


}
