package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colto.attenditdraft3.Model.TeacherClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAClassFragment extends Fragment {


    FirebaseDatabase database;
    DatabaseReference classes;
    DatabaseReference teacherUser;
    DatabaseReference classOwner;
    //ChildEventListener childEventListener;


   // TextView createClassTitle, className, startDate, endDate,startTime,endTime,studentLateTime,absentTime,daysOfWeek,setLocationTitle;
    EditText yourUserNameInput,classNameInput,startDateInput,endDateInput,startTimeInput,endTimeInput,studentLateInput,absentTimeInput,daysOfWeekInput;
    Button createClassButton;

    public CreateAClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_aclass, container, false);

        //Firebase
        database = FirebaseDatabase.getInstance();
        teacherUser = database.getReference("User"); //need to add class ID to teacher that creates class so it will show up under MyClasses
        classOwner = database.getReference("TeacherClass");

        yourUserNameInput = (EditText) view.findViewById(R.id.yourUserNameInput);
        classNameInput = (EditText) view.findViewById(R.id.classNameInput);
        startDateInput = (EditText) view.findViewById(R.id.startDateInput);
        endDateInput = (EditText) view.findViewById(R.id.endDateInput);
        startTimeInput = (EditText) view.findViewById(R.id.startTimeInput);
        endTimeInput = (EditText) view.findViewById(R.id.endTimeInput);
        studentLateInput = (EditText) view.findViewById(R.id.studentLateInput);
        absentTimeInput = (EditText) view.findViewById(R.id.absentTimeInput);
        daysOfWeekInput = (EditText) view.findViewById(R.id.daysOfWeekInput);
        createClassButton = (Button) view.findViewById(R.id.createClassButton);

        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                final String myClassOwner = yourUserNameInput.getText().toString();
                final TeacherClass teacherClass = new TeacherClass(classNameInput.getText().toString(),
                        yourUserNameInput.getText().toString(),
                        startDateInput.getText().toString(),
                        endDateInput.getText().toString(),
                        startTimeInput.getText().toString(),
                        endTimeInput.getText().toString(),
                        studentLateInput.getText().toString(),
                        absentTimeInput.getText().toString(),
                        daysOfWeekInput.getText().toString(),
                        "location",
                        "students enrolled");

                classes = classOwner.child(myClassOwner); //need to add the each class they create under their name.

                classes.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(teacherClass.getClassName()).exists())
                            Toast.makeText(getActivity(), "Class name already exists.", Toast.LENGTH_SHORT).show();
                        else {
                            classes.child(teacherClass.getClassName()).setValue(teacherClass);
                            Toast.makeText(getActivity(), "Successfully created class!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        return view;
    }
}

