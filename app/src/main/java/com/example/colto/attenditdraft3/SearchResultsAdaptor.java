package com.example.colto.attenditdraft3;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by colto on 11/19/2017.
 */

public class SearchResultsAdaptor extends RecyclerView.Adapter<SearchResultsAdaptor.UserViewHolder>  {

    FirebaseDatabase database;
    DatabaseReference studentEnrolledRef;
    DatabaseReference studentUserMyClassesRef;
    String studentUserName;
    private List<MyClassesModel> list;

    public SearchResultsAdaptor(List<MyClassesModel> list, String studentsName) {


        studentUserName = studentsName;
        this.list = list;

    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        MyClassesModel myClass = list.get(position);

        holder.itemClassName.setText(myClass.className);
        holder.itemClassTimes.setText(myClass.classTimes);
        holder.itemClassDaysPerWeek.setText(myClass.classDays);
        holder.itemTeacherName.setText(myClass.teacherName);


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

        TextView itemClassName, itemClassTimes, itemClassDaysPerWeek, itemTeacherName;
        Button   joinAClassButton;



        public UserViewHolder(final View itemView) {
            super(itemView);

            itemClassName = (TextView) itemView.findViewById(R.id.itemClassName);
            itemClassTimes = (TextView) itemView.findViewById(R.id.itemClassTimes);
            itemClassDaysPerWeek = (TextView) itemView.findViewById(R.id.itemClassDaysPerWeek);



            //Implement Join Class button here?
            joinAClassButton = (Button) itemView.findViewById(R.id.JoinClassButton);

            joinAClassButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Firebase References needed
                    database = FirebaseDatabase.getInstance();
                    studentEnrolledRef = database.getReference("TeacherClass")
                            .child(itemTeacherName.getText().toString())
                            .child(itemClassName.getText().toString())
                            .child("StudentsEnrolled");
                    studentUserMyClassesRef = database.getReference("Users")
                            .child(studentUserName)
                            .child("MyClasses");

                    //Writing to Firebase that the class has a new student enrolled in it
                    //And this student has a new class.

                    





                    Toast.makeText(itemView.getContext(), "You Have Successfully Joined " + itemClassName.getText().toString(), Toast.LENGTH_LONG).show();

                }
            });



        }

    }
}
