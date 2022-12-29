package com.project1.mvvmcrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.project1.mvvmcrud.databinding.ActivityMainBinding;
import com.project1.mvvmcrud.model.DataItem;
import com.project1.mvvmcrud.viewmodel.DataViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DataViewModel viewModel;
    private DataItem dataItem;
    private int id ;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initailVariables();
       // setDataToWidgets();
        registerClickEvents();


    }


    private void initailVariables() {

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataItem=getIntent().getParcelableExtra("dataItem");
        id=getIntent().getIntExtra("id",0);
        age=getIntent().getStringExtra("age");

        if(dataItem != null) {
            setDataToWidgets();
        }
        if (id != 0){
            binding.userAge.setText(String.valueOf(age));
            binding.saveBtn.setText("Update Age");
        }

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
    }
    private void setDataToWidgets() {
        binding.userName.setText(dataItem.getUserName());
        binding.userEmail.setText(dataItem.getUserEmail());
        binding.userAge.setText(String.valueOf(dataItem.getUserAge()));
        binding.saveBtn.setText("Update");



    }

    private void registerClickEvents() {
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String takenUserName = binding.userName.getText().toString();
                String takenUserEmail = binding.userEmail.getText().toString();
                String takenUserAge = binding.userAge.getText().toString();
                DataItem dataItem12;// = new DataItem(inputUserName, inputUserEmail, inputUserAge);
                if (dataItem != null) {
                    if (!takenUserName.equals(dataItem.getUserName()) ||
                            !takenUserEmail.equals(dataItem.getUserEmail()) ||
                           !takenUserAge .equals( dataItem.getUserAge())) {
                        dataItem12 = new DataItem(takenUserName, takenUserEmail, takenUserAge);
                        dataItem12.setId(dataItem.getId());
                        viewModel.updateData(dataItem12);
                        Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Please update any field", Toast.LENGTH_SHORT).show();
                    }
                } else if (id != 0) {
                    if (!takenUserAge .equals( age)) {
                        viewModel.updateAge(id, takenUserAge);

                    } else {
                        Toast.makeText(MainActivity.this, "Please update age", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    // TODO: 12/25/2022 Add Validations for empty Input Check

                   // Integer i;
                     // i = Integer.parseInt("takenUserAge");
                   // String userAge = String.valueOf(takenUserAge);
                    if (!takenUserName.isEmpty() && !takenUserEmail.isEmpty() && !takenUserAge.isEmpty()) {
                        int age=Integer.parseInt(takenUserAge);
                        dataItem12 = new DataItem(takenUserName, takenUserEmail, takenUserAge);
                        viewModel.insertData(dataItem12);
                        Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        //}
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        binding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });


    }
}