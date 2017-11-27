package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colto.attenditdraft3.Model.TeacherClass;
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
    private DatabaseReference reference;



   // TextView createClassTitle, className, startDate, endDate,startTime,endTime,studentLateTime,absentTime,daysOfWeek,setLocationTitle;
    EditText classNameInput,startDateInput,endDateInput,startTimeInput,endTimeInput,studentLateInput,absentTimeInput;
    EditText day1,day2,day3,day4,day5,day6,day7;
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
        day1 = (EditText) view.findViewById(R.id.day1);
        day2 = (EditText) view.findViewById(R.id.day2);
        day3 = (EditText) view.findViewById(R.id.day3);
        day4 = (EditText) view.findViewById(R.id.day4);
        day5 = (EditText) view.findViewById(R.id.day5);
        day6 = (EditText) view.findViewById(R.id.day6);
        day7 = (EditText) view.findViewById(R.id.day7);

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
                        day1.getText().toString(),
                        day2.getText().toString(),
                        day3.getText().toString(),
                        day4.getText().toString(),
                        day5.getText().toString(),
                        day6.getText().toString(),
                        day7.getText().toString(),
                        "location");

                classes = classOwner.child(teacherUserNameValue); //need to add the each class they create under their name.
                teacherUser = database.getReference("Users").child(teacherUserNameValue).child("MyClasses");
                //need to add class ID to teacher that creates class so it will show up under MyClasses

                classes.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(teacherClass.getClassName()).exists())
                            Toast.makeText(getActivity(), "Class name already exists.", Toast.LENGTH_SHORT).show();
                        else {
                            classes.child(teacherClass.getClassName()).setValue(teacherClass);
                            MyClassesModel myClass = new MyClassesModel(classNameInput.getText().toString(),
                                    startTimeInput.getText().toString(),
                                    teacherUserNameValue,
                                    day1.getText().toString(),
                                    day2.getText().toString(),
                                    day3.getText().toString(),
                                    day4.getText().toString(),
                                    day5.getText().toString(),
                                    day6.getText().toString(),
                                    day7.getText().toString());
                            teacherUser.child(myClass.getClassName()).setValue((myClass));
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

