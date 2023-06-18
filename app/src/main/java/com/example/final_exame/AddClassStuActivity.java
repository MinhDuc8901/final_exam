package com.example.final_exame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.final_exame.adapter.ClassStu.AdapterSpinner;
import com.example.final_exame.database.DatabaseClassroom;
import com.example.final_exame.database.DatabaseStudent;
import com.example.final_exame.database.DatabaseStudentClassroom;
import com.example.final_exame.model.Classroom;
import com.example.final_exame.model.Student;
import com.example.final_exame.model.StudentClass;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AddClassStuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAdd;
    private Spinner spinnerStu,spinnerClass;
    private EditText edtSemeter, edtCredits;
    private List<Classroom> listClass;
    private List<Student> listStu;
    private DatabaseClassroom dbClass;
    private DatabaseStudent dbStu;
    private DatabaseStudentClassroom dbStuClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_stu);
        try {
            initView();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setupSpinner();
        btnAdd.setOnClickListener(this);

    }

    private void initView() throws ParseException {
        btnAdd = findViewById(R.id.btnAddClasStuData);
        spinnerClass = findViewById(R.id.spinerClassId);
        spinnerStu = findViewById(R.id.spinerStuId);
        edtCredits = findViewById(R.id.edtCredits);
        edtSemeter = findViewById(R.id.edtSemeter);

        dbClass = new DatabaseClassroom(this);
        dbStu = new DatabaseStudent(this);
        listClass = dbClass.getListClassroom();
        listStu = dbStu.getListStudent();
        dbStuClass = new DatabaseStudentClassroom(this);
    }

    private void setupSpinner(){
        List<String> StuList = new ArrayList<>();
        for (Student item : listStu){
            StuList.add(item.getId() + " - " + item.getName());
        }
        List<String> ClassList = new ArrayList<>();
        for(Classroom item : listClass){
            ClassList.add(item.getClass_index() + " - "+ item.getName());
        }
        AdapterSpinner <String> adapterStu = new AdapterSpinner<>(StuList,this);
        spinnerStu.setAdapter(adapterStu);
        AdapterSpinner <String> adapterClass = new AdapterSpinner<>(ClassList,this);
        spinnerClass.setAdapter(adapterClass);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddClasStuData:
                StudentClass studentClass = new StudentClass(spinnerStu.getId(),spinnerClass.getId(),
                        edtSemeter.getText().toString(),Integer.parseInt(edtCredits.getText().toString()));
                dbStuClass.addStudentClassroom(studentClass);
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}