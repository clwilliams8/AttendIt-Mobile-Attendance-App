package com.example.colto.attenditdraft3;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import java.util.List;

/**
 * Created by colto on 11/19/2017.
 */

public class StudentRecordsAdaptor extends RecyclerView.Adapter<StudentRecordsAdaptor.UserViewHolder>  {

    private List<StudentRecordModel> list;

    public StudentRecordsAdaptor(List<StudentRecordModel> list) {
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item5, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        StudentRecordModel myRecord = list.get(position);

        holder.itemClassName.setText(myRecord.className);
        holder.itemRecordDate.setText(myRecord.date);
        if(myRecord.present = true){holder.itemRecordDate.setText("Present");}
        if(myRecord.late = true){holder.itemLateMark.setText("Late");}
        if(myRecord.absent = true){holder.itemAbsentMark.setText("Absent");}

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "test?");
                menu.add(holder.getAdapterPosition(), 1, 0, "test2?");

            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class  UserViewHolder extends RecyclerView.ViewHolder {

        TextView itemClassName, itemRecordDate, itemPresentMark, itemLateMark, itemAbsentMark;


        public UserViewHolder(View itemView) {
            super(itemView);

            itemClassName = (TextView) itemView.findViewById(R.id.itemClassName_records);
            itemRecordDate = (TextView) itemView.findViewById(R.id.item_record_date);
            itemPresentMark = (TextView) itemView.findViewById(R.id.itemPresentMark);
            itemLateMark = (TextView) itemView.findViewById(R.id.itemLateMark);
            itemAbsentMark = (TextView) itemView.findViewById(R.id.itemAbsentMark);

        }

    }
}
