package com.example.final_exame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_exame.AddClassStuActivity;
import com.example.final_exame.R;
import com.example.final_exame.adapter.ClassStu.AdapterRecycle;
import com.example.final_exame.database.DatabaseClassroom;
import com.example.final_exame.database.DatabaseStudent;
import com.example.final_exame.database.DatabaseStudentClassroom;
import com.example.final_exame.model.StudentClass;

import java.util.List;

public class HomeFrag extends Fragment implements View.OnClickListener{
    private DatabaseStudent dbStu;
    private DatabaseClassroom dbClass;
    private DatabaseStudentClassroom dbStuClass;
    private List<StudentClass> list;
    private RecyclerView recyclerView;
    private Button btnAdd;
    private View view;
    private AdapterRecycle adapterRecycle;

    public static final int REQUEST_CODE_ADD_STUDENT_CLASSROOM = 10011;

    public HomeFrag(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home_fragment, container, false);
        initView();
        setupRecycle();
        btnAdd.setOnClickListener(this);
        return view;
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycleClassStu);
        btnAdd = view.findViewById(R.id.btnRegister);
        dbStu = new DatabaseStudent(this.getContext());
        dbClass = new DatabaseClassroom(this.getContext());
        dbStuClass = new DatabaseStudentClassroom(this.getContext());
        list = dbStuClass.getListStudentClass();
    }

    private void setupRecycle(){
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        adapterRecycle = new AdapterRecycle(this.getContext(),list,dbClass,dbStu);
        recyclerView.setAdapter(adapterRecycle);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                Intent intent = new Intent(getActivity(), AddClassStuActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ADD_STUDENT_CLASSROOM);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_STUDENT_CLASSROOM){
            list = dbStuClass.getListStudentClass();
            adapterRecycle.setList(list);
        }
    }
}
