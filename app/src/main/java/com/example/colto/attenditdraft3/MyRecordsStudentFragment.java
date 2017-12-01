package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyRecordsStudentFragment extends Fragment {

    String studentUserNameValue;

    private RecyclerView recyclerView;
    private List<StudentRecordModel> result;
    private StudentRecordsAdaptor adaptor;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private TextView emptyText;

    public MyRecordsStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_records_student2, container, false);

        Bundle bundle =getArguments();
        if(bundle != null) {
            studentUserNameValue = bundle.getString("USER_NAME");
        }

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("TeacherClass")
                .child("DrMilanova")
                .child("Capstone")
                .child("StudentsEnrolled")
                .child(studentUserNameValue)
                .child("StudentRecord");

        emptyText = (TextView) view.findViewById(R.id.text_no_data_student_record);

        result = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.studentClassList_records);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), llm.getOrientation());

        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(itemDecoration);

        adaptor = new StudentRecordsAdaptor(result);
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


    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                result.add(dataSnapshot.getValue(StudentRecordModel.class));
                adaptor.notifyDataSetChanged();
                checkIfEmpty();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                StudentRecordModel model = dataSnapshot.getValue(StudentRecordModel.class);

                int index = getItemIndex(model);

                result.set(index, model);
                adaptor.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                StudentRecordModel model = dataSnapshot.getValue(StudentRecordModel.class);

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

    private int getItemIndex(StudentRecordModel myClass) {

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
        StudentRecordModel myClass = result.get(position);
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
