package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.colto.attenditdraft3.Model.TeacherClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class JoinAClassFragment extends Fragment {

    //Firebase variables
    FirebaseDatabase database;
    DatabaseReference teachersClasses; //reference to teacherClasses-<teachername><allteachers child classes>

    EditText teacherUsernameInput;
    Button searchForClassesButton;
    String studentUserNameValue;


    public JoinAClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_aclass, container, false);

        Bundle bundle = getArguments();
        if(bundle != null) {
            studentUserNameValue = bundle.getString("STUDENT_NAME");
        }


        //Local variables
        database = FirebaseDatabase.getInstance();
        teacherUsernameInput = (EditText) view.findViewById(R.id.teacherUsernameInput);
        searchForClassesButton = (Button) view.findViewById(R.id.searchForClassesButton);

       /* searchForClassesButton.setOnClickListener(new View.OnClickListener()) {
            @Override
                    /// TODO: 11/15/2017 onButtonClick read database reference of all teachers classes, put in recyler view
            public void onClick(View v) {
                teachersClasses = database.getReference("TeacherClass").child(teacherUsernameInput.getText().toString());
                teachersClasses.chi
                        //setContentView(recylerview fragment)
            }
        };*/






        // Inflate the layout for this fragment
        return view;
    }

}
