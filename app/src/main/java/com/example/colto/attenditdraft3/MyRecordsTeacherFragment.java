package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyRecordsTeacherFragment extends Fragment {

    String userNameValue;

    public MyRecordsTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_records_teacher, container, false);

        Bundle bundle =getArguments();
        if(bundle != null) {
            userNameValue = bundle.getString("USER_NAME");
        }


        return view;
    }

}
