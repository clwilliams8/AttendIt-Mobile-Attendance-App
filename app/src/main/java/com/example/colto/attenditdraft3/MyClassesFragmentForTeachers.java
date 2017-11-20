package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyClassesFragmentForTeachers extends Fragment {

    String teacherUserNameValue;

    private RecyclerView recyclerView;
    private List<MyClassesModel> result;
    private UserAdaptor adaptor;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    public MyClassesFragmentForTeachers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_classes, container, false);

        Bundle bundle =getArguments();
        if(bundle != null) {
            teacherUserNameValue = bundle.getString("TEACHER_NAME");
        }

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(teacherUserNameValue).child("MyClasses");

        result = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.teacherClassList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        createResult();

        adaptor = new UserAdaptor(result);
        recyclerView.setAdapter(adaptor);


        return view;
    }


//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case 0:
//                break;
//            case 1:
//                break;
//        }
//
//        return super.onContextItemSelected(item);
//    }

    private void createResult() {


        for (int i = 0; i < 10 ; i++) {

            result.add(new MyClassesModel("testname", "testtimes", "testdates"));

        }

    }
//
//    private void updateList() {
//
//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                result.add(dataSnapshot.getValue(MyClassesModel.class));
//                adaptor.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                MyClassesModel model = dataSnapshot.getValue(MyClassesModel.class);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private int getItemIndex(MyClassesModel model) {
//
//        int index =  -1;
//
//        for(int i = 0; i < result.size(); i++){
//            if(result.get(i).className.equals(myClass))
//                index = i;
//            break;
//        }
//        return index;
//    }

}
