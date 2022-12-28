package com.project1.mvvmcrud.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project1.mvvmcrud.model.DataItem;
import com.project1.mvvmcrud.repositary.DataRepositary;

import java.util.List;


public class DataViewModel extends AndroidViewModel {

    private DataRepositary repositary;
    public DataViewModel(@NonNull Application application) {
        super(application);
        repositary= new DataRepositary(application);

    }
    public void insertData(DataItem dataItem){
        repositary.insertData(dataItem);
    }
    public void updateData(DataItem dataItem){
        repositary.updateData(dataItem);
    }
    public void deleteData(DataItem dataItem){
        repositary.deleteData(dataItem);
    }

    public LiveData<List<DataItem>> getAllData(){
        return repositary.getAllData();
    }


    public LiveData<List<DataItem>> searchByUserName(String userName){
        return repositary.searchByUserName(userName);
    }

    public void deleteAllData(){
        repositary.deleteAllData();}

    public void updateAge(int id , String age) {
        repositary.updateAge(id , age);
    }
}
