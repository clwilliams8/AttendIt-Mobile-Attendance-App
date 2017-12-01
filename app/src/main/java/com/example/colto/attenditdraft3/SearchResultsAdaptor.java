package com.example.colto.attenditdraft3;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by colto on 11/19/2017.
 */

public class SearchResultsAdaptor extends RecyclerView.Adapter<SearchResultsAdaptor.UserViewHolder>  {

    private FirebaseDatabase database;
    private DatabaseReference studentEnrolledRef;
    private DatabaseReference studentUserMyClassesRef;
    private String studentsUserNameValue, studentsRealNameValue;
    private List<MyClassesModel> list;

    public SearchResultsAdaptor(List<MyClassesModel> list, String studentsUserNamePass, String studentsRealNamePass) {
        this.list = list;
        studentsUserNameValue = studentsUserNamePass;
        studentsRealNameValue = studentsRealNamePass;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        MyClassesModel myClass = list.get(position);

        holder.itemClassName.setText(myClass.className);
        holder.itemClassStartTime.setText(myClass.classStartTime);
        holder.itemClassLateTime.setText(myClass.classLateTime);
        holder.itemClassAbsentTime.setText(myClass.classAbsentTime);
        holder.itemClassEndTime.setText(myClass.classEndTime);
        holder.itemTeacherName.setText(myClass.teacherName);
        holder.day1.setText(myClass.day1);
        holder.day2.setText(myClass.day2);
        holder.day3.setText(myClass.day3);
        holder.day4.setText(myClass.day4);
        holder.day5.setText(myClass.day5);
        holder.day6.setText(myClass.day6);
        holder.day7.setText(myClass.day7);

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

        TextView itemClassName, itemClassStartTime, itemClassLateTime, itemClassAbsentTime, itemClassEndTime, itemTeacherName;
        TextView day1, day2, day3, day4, day5, day6, day7;
        Button   joinAClassButton;


        public UserViewHolder(final View itemView) {
            super(itemView);

            itemClassName = (TextView) itemView.findViewById(R.id.itemClassName);
            itemClassStartTime = (TextView) itemView.findViewById(R.id.itemClassTimes);
            itemClassLateTime = (TextView) itemView.findViewById(R.id.classLateTime);
            itemClassAbsentTime = (TextView) itemView.findViewById(R.id.classAbsentTime);
            itemClassEndTime = (TextView) itemView.findViewById(R.id.classEndTime2);
            itemTeacherName = (TextView) itemView.findViewById(R.id.itemTeacherName);
            joinAClassButton = (Button) itemView.findViewById(R.id.JoinClassButton);
            day1 = (TextView) itemView.findViewById(R.id.day1);
            day2 = (TextView) itemView.findViewById(R.id.day2);
            day3 = (TextView) itemView.findViewById(R.id.day3);
            day4 = (TextView) itemView.findViewById(R.id.day4);
            day5 = (TextView) itemView.findViewById(R.id.day5);
            day6 = (TextView) itemView.findViewById(R.id.day6);
            day7 = (TextView) itemView.findViewById(R.id.day7);

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
                            .child(studentsUserNameValue)
                            .child("MyClasses");



                    //Writing to Firebase that the class has a new student enrolled in it
                    //And this student has a new class.

                    studentEnrolledRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            StudentsEnrolledModel studentsEnrolledModel =
                                    new StudentsEnrolledModel(studentsRealNameValue, studentsUserNameValue);

                            if (dataSnapshot.child(studentsUserNameValue).exists()) {
                                Toast.makeText(itemView.getContext(), "You have already joined this class.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                studentEnrolledRef.child(studentsEnrolledModel.getStudentUsername()).setValue(studentsEnrolledModel);
                                MyClassesModel myClass = new MyClassesModel(itemClassName.getText().toString(),
                                        itemClassStartTime.getText().toString(),
                                        itemClassLateTime.getText().toString(),
                                        itemClassAbsentTime.getText().toString(),
                                        itemClassEndTime.getText().toString(),
                                        itemTeacherName.getText().toString(),
                                        studentsRealNameValue,
                                        studentsUserNameValue,
                                        day1.getText().toString(),
                                        day2.getText().toString(),
                                        day3.getText().toString(),
                                        day4.getText().toString(),
                                        day5.getText().toString(),
                                        day6.getText().toString(),
                                        day7.getText().toString());
                                studentUserMyClassesRef.child(myClass.getClassName()).setValue((myClass));
                                Toast.makeText(itemView.getContext(), "You Have Successfully Joined " + itemClassName.getText().toString(), Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            });



        }

    }
}
