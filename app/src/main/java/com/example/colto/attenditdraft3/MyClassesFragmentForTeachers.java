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

import com.example.colto.attenditdraft3.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    private TextView emptyText;

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

        emptyText = (TextView) view.findViewById(R.id.text_no_data);

        result = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.teacherClassList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        createResult(); // REMOVE THIS AFTER GETTING UPDATELIST WORKING

        adaptor = new UserAdaptor(result);
        recyclerView.setAdapter(adaptor);

        updateList(); //IF THIS WORKS REMOVE CREATE RESULT ENTIRELY

        checkIfEmpty();

        return view;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                removeUser(item.getGroupId());
                break;
            case 1:
                changeUser(item.getGroupId());
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void createResult() {


        for (int i = 0; i < 10 ; i++) {

            result.add(new MyClassesModel("testname", "testtimes", "testdates"));

        }

    }

    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                result.add(dataSnapshot.getValue(MyClassesModel.class));
                adaptor.notifyDataSetChanged();
                checkIfEmpty();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                MyClassesModel model = dataSnapshot.getValue(MyClassesModel.class);

                int index = getItemIndex(model);

                result.set(index, model);
                adaptor.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                MyClassesModel model = dataSnapshot.getValue(MyClassesModel.class);

                int index = getItemIndex(model);

                result.remove(index);
                adaptor.notifyItemRemoved(index);
                checkIfEmpty();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(MyClassesModel myClass) {

        int index =  -1;

        for(int i = 0; i < result.size(); i++){
            if(result.get(i).className.equals(myClass))
                index = i;
            break;
        }
        return index;
    }

    private void removeUser(int position) {
        reference.child(result.get(position).className).removeValue(); //todo
    }

    private void changeUser(int position) {
        MyClassesModel myClass = result.get(position);
        //in video he changes age to 100

        Map<String, Object> userValues = myClass.toMap();
        Map<String, Object> newUser = new HashMap<>();

        newUser.put(myClass.className, userValues); //todo

        reference.updateChildren(newUser);

    }

    private void checkIfEmpty() {
        if(result.size() == 0) {
            recyclerView.setVisibility(View.INVISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.INVISIBLE);
        }
    }

//NOTE YOU ARE AT 28:38 in the video.

}
