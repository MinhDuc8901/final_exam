package com.example.final_exame.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.final_exame.AddClassroomActivity;
import com.example.final_exame.R;
import com.example.final_exame.adapter.classroom.AdapterRecycle;
import com.example.final_exame.database.DatabaseClassroom;
import com.example.final_exame.model.Classroom;

import java.util.List;

public class ClassFragment extends Fragment implements View.OnClickListener{
    private View view;
    // database
    private DatabaseClassroom db;

    private RecyclerView recyclerView;
    private Button btnAdd;

    private List<Classroom> listClass;
    private AdapterRecycle adapterRecycle;

    private static final int REQUEST_CODE_CLASSROOM = 10001;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_class, container, false);
        initView();
        btnAdd.setOnClickListener(this);
        return view;
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycleClass);
        btnAdd = view.findViewById(R.id.btnAddClass);

        db = new DatabaseClassroom(getContext());
        try {
        listClass = db.getClassrooms();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setupRecycle(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        adapterRecycle = new AdapterRecycle(listClass,this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecycle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddClass:
                Intent intent = new Intent(getContext(), AddClassroomActivity.class);
                startActivityForResult(intent,REQUEST_CODE_CLASSROOM);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CLASSROOM){
            try {
                listClass = db.getListClassroom();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}