package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyClassesFragmentForStudents extends Fragment {

    String studentUserNameValue;


    public MyClassesFragmentForStudents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_classes_fragment_for_students, container, false);

        Bundle bundle =getArguments();
        if(bundle != null) {
            studentUserNameValue = bundle.getString("STUDENT_NAME");
        }


        return view;
    }

}
