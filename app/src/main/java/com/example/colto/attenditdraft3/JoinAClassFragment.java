package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    EditText teacherUsernameInput, studentRealNameInput;
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
        studentRealNameInput = view.findViewById(R.id.studentRealNameValue);
        database = FirebaseDatabase.getInstance();
        teacherUsernameInput = (EditText) view.findViewById(R.id.teacherUsernameInput);
        searchForClassesButton = (Button) view.findViewById(R.id.searchForClassesButton);

        searchForClassesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Query database for Users-><teacherusernameinput>->set reference for
                //teachersClasses = database.getReference("Users").child(teacherUsernameInput.getText().toString()).child("MyClasses");

                Bundle bundle = new Bundle();
                bundle.putString("TEACHER_NAME",teacherUsernameInput.getText().toString());
                bundle.putString("STUDENT_USER_NAME", studentUserNameValue);
                bundle.putString("STUDENT_FULL_NAME", studentRealNameInput.getText().toString());
                SearchResultsFragment searchResultsFragment = new SearchResultsFragment();
                searchResultsFragment.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, searchResultsFragment, searchResultsFragment.getTag())
                        .commit();

            }
        });






        // Inflate the layout for this fragment
        return view;
    }

}
