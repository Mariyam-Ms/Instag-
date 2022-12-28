package com.project1.mvvmcrud.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project1.mvvmcrud.model.DataItem;

import java.util.List;

@Dao
public interface DataDao {


    @Insert
    void insert(DataItem dataItem);

    @Update
    void update(DataItem dataItem);

    @Delete
    void delete(DataItem dataItem);

    @Query("SELECT * FROM data_table")
    LiveData<List<DataItem>> getAllData();

    @Query("DELETE FROM data_table")
    void deleteAll();

    @Query("UPDATE data_table SET userAge= :age WHERE id = :id")
    void updateAge(int id ,String age);

    @Query("SELECT * FROM data_table WHERE userName Like '%'|| :userName || '%' ")
    LiveData<List<DataItem>> searchByUserName(String userName);


}
