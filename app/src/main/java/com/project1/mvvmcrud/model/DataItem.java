package com.project1.mvvmcrud.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "data_table")
public class DataItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id=0;
    private String userName;
    private String userEmail;

    protected DataItem(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        userEmail = in.readString();
        userAge = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(userName);
        dest.writeString(userEmail);
        dest.writeString(userAge);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }





    private String userAge;

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserAge() {
        return userAge;
    }

    public DataItem(String userName, String userEmail, String userAge){
        this.userName=userName;
        this.userEmail=userEmail;
        this.userAge=userAge;
    }
}
