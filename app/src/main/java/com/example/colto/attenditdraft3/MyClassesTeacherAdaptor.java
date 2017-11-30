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

public class MyClassesTeacherAdaptor extends RecyclerView.Adapter<MyClassesTeacherAdaptor.UserViewHolder>  {

    private List<MyClassesModel> list;

    public MyClassesTeacherAdaptor(List<MyClassesModel> list) {
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        MyClassesModel myClass = list.get(position);

        holder.itemClassName.setText(myClass.className);
        holder.itemClassTimes.setText(myClass.classStartTime);
        holder.itemDay1.setText(myClass.day1);
        holder.itemDay2.setText(myClass.day2);
        holder.itemDay3.setText(myClass.day3);

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

        TextView itemClassName, itemClassTimes, itemDay1, itemDay2, itemDay3;


        public UserViewHolder(View itemView) {
            super(itemView);

            itemClassName = (TextView) itemView.findViewById(R.id.itemClassName);
            itemClassTimes = (TextView) itemView.findViewById(R.id.itemClassTimes);
            itemDay1 = (TextView) itemView.findViewById(R.id.itemDay1);
            itemDay2 = (TextView) itemView.findViewById(R.id.itemDay2);
            itemDay3 = (TextView) itemView.findViewById(R.id.itemDay3);

        }

    }
}
