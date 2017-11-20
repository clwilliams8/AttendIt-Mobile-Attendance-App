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
public class MyRecordsFragment extends Fragment {

    String userNameValue;

    public MyRecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_records, container, false);

        Bundle bundle =getArguments();
        if(bundle != null) {
            userNameValue = bundle.getString("USER_NAME");
        }


        return view;
    }

}
