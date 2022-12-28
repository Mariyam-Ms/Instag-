package com.project1.mvvmcrud.data.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project1.mvvmcrud.data.dao.DataDao;
import com.project1.mvvmcrud.model.DataItem;

@Database(entities = DataItem.class,version = 2)
public abstract class ItemDatabase extends RoomDatabase {

    public static ItemDatabase instance;
    public abstract DataDao dataDao();

    public static synchronized ItemDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ItemDatabase.class,"ItemDatabase")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
