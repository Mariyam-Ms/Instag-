package com.project1.mvvmcrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.project1.mvvmcrud.databinding.ActivityShowBinding;
import com.project1.mvvmcrud.model.DataItem;
import com.project1.mvvmcrud.viewmodel.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity implements EachItemAdapter.ItemClickListener {
    private ActivityShowBinding binding;
    private DataViewModel dataViewModel;
    private EachItemAdapter adapter;
    private List<DataItem> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeVariable();
        getAllData();
        clickEvents();
    }
    private void clickEvents() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this, MainActivity.class));
            }
        });
        binding.deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdailog=new AlertDialog.Builder(ShowActivity.this);
                alertdailog.setMessage("Are you sure to Delete all ?");
                alertdailog.setTitle("Alert...");
                alertdailog.setCancelable(false);
                alertdailog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dataViewModel.deleteAllData();
                    }
                });
                alertdailog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertdailog.setCancelable(true);
                    }
                });
                alertdailog.show();

            }
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setFilterList(newText);
                return false;
            }
        });
    }

    private void setFilterList(String newText) {
        dataViewModel.searchByUserName(newText).observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                adapter.setDataItemList(dataItems);
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void initializeVariable() {
        mList = new ArrayList<>();
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        adapter = new EachItemAdapter();
        adapter.setItemClickListener(this);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }
    private void getAllData(){
//        dataViewModel.getAllData().observe(this, new Observer<List<DataItem>>() {
//            @Override
//            public void onChanged(List<DataItem> dataItems) {
//                adapter.setDataItemList(dataItems);
//                adapter.notifyDataSetChanged();
        setDataToAdapter();
//            }
//        });
    }

    @Override
    public void onDeleteClicked(DataItem dataItem,int position) {

        Snackbar snackbar = Snackbar.make(binding.parentLayout, "Deleted Successfully", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataToAdapter();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                if (Snackbar.Callback.DISMISS_EVENT_TIMEOUT == event) {
                    dataViewModel.deleteData(dataItem);
                }
            }
            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
                adapter.deleteClicked(position);
            }
        });
        snackbar.show();
    }

    @Override
    public void onEditClicked(DataItem dataItem) {
        Intent intent = new Intent(this , MainActivity.class);
        intent.putExtra("dataItem" , dataItem);
        startActivity(intent);
    }

    @Override
    public void onAgeClick(int id, String userAge) {
        Intent intent = new Intent(this , MainActivity.class);
        intent.putExtra("id" , id);
        intent.putExtra("age" , userAge);
        startActivity(intent);
    }


    public void setDataToAdapter() {

        dataViewModel.getAllData().observe(ShowActivity.this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {

                mList.addAll(dataItems);
                adapter.setDataItemList(dataItems);
                adapter.notifyDataSetChanged();

            }
        });
    }
}