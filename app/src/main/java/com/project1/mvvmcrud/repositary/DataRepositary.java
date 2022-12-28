package com.project1.mvvmcrud.repositary;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.project1.mvvmcrud.data.dao.DataDao;
import com.project1.mvvmcrud.data.db.ItemDatabase;
import com.project1.mvvmcrud.model.DataItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DataRepositary {

    private DataDao dataDao;
    private Executor executor;

    public DataRepositary(Application application){
        dataDao = ItemDatabase.getInstance(application).dataDao();
        executor=Executors.newSingleThreadExecutor();
    }

    public void insertData(DataItem dataItem){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.insert(dataItem);
            }
        });
    }
    public void updateData(DataItem dataItem){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.update(dataItem);
            }
        });
    }
    public void deleteData(DataItem dataItem){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.delete(dataItem);
            }
        });
    }
    public LiveData<List<DataItem>> getAllData(){
        return dataDao.getAllData();
    }

    public LiveData<List<DataItem>> searchByUserName(String userName){
        return dataDao.searchByUserName(userName);
    }

    public void deleteAllData(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteAll();
            }
        });
    }

    public void updateAge(int id , String age){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.updateAge(id , age);
            }
        });
    }


}
