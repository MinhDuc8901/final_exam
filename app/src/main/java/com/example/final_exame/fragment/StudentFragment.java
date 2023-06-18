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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.final_exame.AddStudentActivity;
import com.example.final_exame.R;
import com.example.final_exame.adapter.student.AdapterRecycle;
import com.example.final_exame.adapter.student.AdapterSpinner;
import com.example.final_exame.database.DatabaseStudent;
import com.example.final_exame.model.Student;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class StudentFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final int REQUEST_CODE_ADD_STUDENT = 1000;

    private View view;
    private DatabaseStudent databaseStudent;

    private Button btnAdd,btnSearch;
    private Spinner filterStu;
    private RecyclerView recyclerViewStu;
    private EditText edtSearchStu;
    private AdapterRecycle adapterRecycleStu;
    private List<Student> listStu;

    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student, container, false);
        initView();
        loadDataStudent();
        setupRecycle();
        setupSpinner();
        btnAdd.setOnClickListener(this);

        return view;
    }

    private void initView(){
        btnAdd = view.findViewById(R.id.btnAddStu);
        btnSearch = view.findViewById(R.id.btnSearchStu);
        filterStu = view.findViewById(R.id.spinerStu);
        edtSearchStu = view.findViewById(R.id.searchStu);
        recyclerViewStu = view.findViewById(R.id.recycleStu);
        databaseStudent = new DatabaseStudent(this.getContext());
    }

    private void setupRecycle(){
        adapterRecycleStu = new AdapterRecycle(this.getContext(),listStu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        recyclerViewStu.setLayoutManager(linearLayoutManager);
        recyclerViewStu.setAdapter(adapterRecycleStu);
    }

    private void setupSpinner(){
        List<String> listYearStu = new ArrayList<>();
        listYearStu.add("Tất cả");
        listYearStu.add(1+"");
        listYearStu.add(2+"");
        listYearStu.add(3+"");
        listYearStu.add(4+"");
        AdapterSpinner adapter = new AdapterSpinner(listYearStu,this.getContext());
        filterStu.setAdapter(adapter);
        filterStu.setOnItemSelectedListener(this);
    }

    private void loadDataStudent()  {
        try {
            listStu = databaseStudent.getListStudent();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddStu:
                Intent intent = new Intent(getActivity(), AddStudentActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ADD_STUDENT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_STUDENT){

                try {
                    listStu = databaseStudent.getListStudent();
                } catch (ParseException e) {
                    listStu = new ArrayList<>();
                    e.printStackTrace();
                }
                adapterRecycleStu.setList(listStu);
                Toast.makeText(this.getContext(),"Thành công",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        List<Student> liststu = new ArrayList<>();
        for (Student item : listStu){
            if(i == 0){
                liststu.add(item);
            }else{
                if(item.getYear_stu() == i){
                    liststu.add(item);
                }
            }
        }
        adapterRecycleStu.setList(liststu);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}