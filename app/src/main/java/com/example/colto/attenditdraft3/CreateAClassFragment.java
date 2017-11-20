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
    String teacherUserNameValue;
    //ChildEventListener childEventListener;


   // TextView createClassTitle, className, startDate, endDate,startTime,endTime,studentLateTime,absentTime,daysOfWeek,setLocationTitle;
    EditText classNameInput,startDateInput,endDateInput,startTimeInput,endTimeInput,studentLateInput,absentTimeInput,daysOfWeekInput;
    Button createClassButton;

    public CreateAClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_aclass, container, false);

        //GET TEACHER USERNAME FROM BUNDLE
        Bundle bundle = getArguments();
        if(bundle != null){
            teacherUserNameValue = bundle.getString("TEACHER_NAME");
        }


        //Firebase
        database = FirebaseDatabase.getInstance();
        classOwner = database.getReference("TeacherClass");

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
                //final String myClassOwner = yourUserNameInput.getText().toString();
                final TeacherClass teacherClass = new TeacherClass(classNameInput.getText().toString(),
                        teacherUserNameValue,
                        startDateInput.getText().toString(),
                        endDateInput.getText().toString(),
                        startTimeInput.getText().toString(),
                        endTimeInput.getText().toString(),
                        studentLateInput.getText().toString(),
                        absentTimeInput.getText().toString(),
                        daysOfWeekInput.getText().toString(),
                        "location",
                        "students enrolled");

                classes = classOwner.child(teacherUserNameValue); //need to add the each class they create under their name.
                teacherUser = database.getReference("Users"); //need to add class ID to teacher that creates class so it will show up under MyClasses
                teacherUser = teacherUser.child(teacherUserNameValue);
                teacherUser = teacherUser.child("MyClasses");

                classes.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(teacherClass.getClassName()).exists())
                            Toast.makeText(getActivity(), "Class name already exists.", Toast.LENGTH_SHORT).show();
                        else {
                            classes.child(teacherClass.getClassName()).setValue(teacherClass);
                            teacherUser.push().setValue(classNameInput.getText().toString());
                           // teacherUser.child("ClassName").setValue(classNameInput.getText().toString());
                           // teacherUser.child("ClassTimes").setValue(startTimeInput.getText().toString());
                            //teacherUser.child("DaysOfWeek").setValue(daysOfWeekInput.getText().toString());

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

