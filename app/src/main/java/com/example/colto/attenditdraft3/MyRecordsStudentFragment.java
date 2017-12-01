package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyRecordsStudentFragment extends Fragment {

    String studentUserNameValue;
    public MyRecordsStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_records_student2, container, false);

        Bundle bundle =getArguments();
        if(bundle != null) {
            studentUserNameValue = bundle.getString("USER_NAME");
        }






        return view;
    }

}
