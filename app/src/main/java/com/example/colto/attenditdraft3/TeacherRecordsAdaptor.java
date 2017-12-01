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

public class TeacherRecordsAdaptor extends RecyclerView.Adapter<TeacherRecordsAdaptor.UserViewHolder>  {

    private List<StudentRecordModel> list;

    public TeacherRecordsAdaptor(List<StudentRecordModel> list) {
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item6, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        StudentRecordModel myRecord = list.get(position);

        holder.itemClassName.setText(myRecord.className);
        holder.itemRecordDate.setText(myRecord.date);
        holder.itemStudentNameValue.setText(myRecord.studentName);

        if(myRecord.present.booleanValue())
            holder.itemPresentMark.setText("Present");
        else
            holder.itemPresentMark.setText("");


        if(myRecord.late.booleanValue())
            holder.itemLateMark.setText("Late");
        else
            holder.itemLateMark.setText("");


        if(myRecord.absent.booleanValue())
            holder.itemAbsentMark.setText("Absent");
        else
            holder.itemAbsentMark.setText("");


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

        TextView itemClassName, itemRecordDate, itemPresentMark, itemLateMark, itemAbsentMark, itemStudentNameValue;


        public UserViewHolder(View itemView) {
            super(itemView);

            itemClassName = (TextView) itemView.findViewById(R.id.itemClassName_records_teacher);
            itemRecordDate = (TextView) itemView.findViewById(R.id.item_record_date_teacher);
            itemPresentMark = (TextView) itemView.findViewById(R.id.itemPresentMark_teacher);
            itemLateMark = (TextView) itemView.findViewById(R.id.itemLateMark_teacher);
            itemAbsentMark = (TextView) itemView.findViewById(R.id.itemAbsentMark_teacher);
            itemStudentNameValue = (TextView) itemView.findViewById(R.id.studentNameValue_teacher);

        }

    }
}
