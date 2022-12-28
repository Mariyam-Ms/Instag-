package com.project1.mvvmcrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project1.mvvmcrud.model.DataItem;

import java.util.List;

public class EachItemAdapter extends RecyclerView.Adapter<EachItemAdapter.MyViewHolder> {

    private List<DataItem> dataItemList;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public void setDataItemList(List<DataItem> dataItemList) {
        this.dataItemList = dataItemList;
        notifyDataSetChanged();
    }
    public void deleteClicked(int position){
        dataItemList.remove(position);
        notifyItemRemoved(position);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_each,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataItem dataItem = dataItemList.get(position);
        holder.userEmail.setText(dataItem.getUserEmail());
        holder.userName.setText(dataItem.getUserName());
        holder.userAge.setText(String.valueOf(dataItem.getUserAge()));

        holder.editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onEditClicked(dataItem);
            }
        });

        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onDeleteClicked(dataItem,holder.getAdapterPosition());
            }
        });

        holder.userAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onAgeClick(dataItem.getId(),dataItem.getUserAge());
            }
        });
    }

    @Override
    public int getItemCount() {

        if (dataItemList != null) {
            return dataItemList.size();
        }  else{
        return 0;}
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName, userEmail, userAge;
        private ImageView editIv , deleteIv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userEmail = itemView.findViewById(R.id.userEmailPlace);
            userName = itemView.findViewById(R.id.userNamePlace);
            userAge = itemView.findViewById(R.id.userAgePlace);
            editIv = itemView.findViewById(R.id.editItem);
            deleteIv = itemView.findViewById(R.id.deleteItem);
        }
    }
    public interface ItemClickListener{
        void onDeleteClicked(DataItem dataItem,int position);
        void onEditClicked(DataItem dataItem);
        void onAgeClick(int id, String userAge);
    }
}
