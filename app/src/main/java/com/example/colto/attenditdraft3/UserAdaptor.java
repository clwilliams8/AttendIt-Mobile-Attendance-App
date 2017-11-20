package com.example.colto.attenditdraft3;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

/**
 * Created by colto on 11/19/2017.
 */

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.UserViewHolder>  {

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class  UserViewHolder extends RecyclerView.ViewHolder {

        TextView itemClassName, itemClassTimes, itemClassDaysPerWeek;


        public UserViewHolder(View itemView) {
            super(itemView);

            itemClassName = (TextView) itemView.findViewById(R.id.itemClassName);
            itemClassTimes = (TextView) itemView.findViewById(R.id.itemClassTimes);
            itemClassDaysPerWeek = (TextView) itemView.findViewById(R.id.itemClassDaysPerWeek);
        }

    }
}
