package com.example.colto.attenditdraft3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultsFragment extends Fragment {

    String teacherSearchValue;
    String studentUserNameValue;
    String studentRealNameValue;

    private RecyclerView recyclerView;
    private List<MyClassesModel> result;
    private SearchResultsAdaptor adaptor;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    public SearchResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);

        Bundle bundle =getArguments();
        if(bundle != null) {
            teacherSearchValue = bundle.getString("TEACHER_NAME");
            studentUserNameValue = bundle.getString("STUDENT_USER_NAME");
            studentRealNameValue = bundle.getString("STUDENT_FULL_NAME");
        }


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(teacherSearchValue).child("MyClasses");

        result = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.resultClassList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), llm.getOrientation());

        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(itemDecoration);

        adaptor = new SearchResultsAdaptor(result,studentUserNameValue, studentRealNameValue);
        recyclerView.setAdapter(adaptor);

        updateList(); //IF THIS WORKS REMOVE CREATE RESULT ENTIRELY


        return view;
    }

    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                result.add(dataSnapshot.getValue(MyClassesModel.class));
                adaptor.notifyDataSetChanged();
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


}
