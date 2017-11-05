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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAClassFragment extends Fragment {


    FirebaseDatabase database;
    DatabaseReference classes;
    DatabaseReference teacherUser;

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
        teacherUser = database.getReference("User");
        classes = database.getReference("Class");

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
                createClass(yourUserNameInput.getText().toString(),
                        classNameInput.getText().toString(),
                        startDateInput.getText().toString(),
                        endDateInput.getText().toString(),
                        startTimeInput.getText().toString(),
                        endTimeInput.getText().toString(),
                        studentLateInput.getText().toString(),
                        absentTimeInput.getText().toString(),
                        daysOfWeekInput.getText().toString());
            }
        });

        return view;
    }

        private void createClass(final String teacherUserName, String classNameInput, final String startDateInput, final String endDateInput, final String startTimeInput,
                                 final String endTimeInput, final String studentLateInput, final String absentTimeInput, final String daysOfWeekInput) {

        }


    }
