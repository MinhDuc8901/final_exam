package com.example.final_exame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_exame.database.DatabaseStudent;
import com.example.final_exame.model.Student;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener{
    public static final SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

    private DatabaseStudent dbStu;

    private Button btnAddStu;
    private EditText edtName,edtBirth,edtPlace,edtYearStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();
        btnAddStu.setOnClickListener(this);
    }

    private void initView(){
        btnAddStu = findViewById(R.id.btnAddStuData);
        edtName = findViewById(R.id.edtName);
        edtPlace = findViewById(R.id.edtPlace);
        edtBirth = findViewById(R.id.edtBirth);
        edtYearStu = findViewById(R.id.edtYearStu);
        // khởi tạo database student
        dbStu = new DatabaseStudent(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddStuData:
                if(ischeckFormAdd()){
                    try{
                        Student student = new Student(String.valueOf(edtName.getText()),sf.parse(String.valueOf(edtBirth.getText())),
                                String.valueOf(edtPlace.getText()),Integer.parseInt(String.valueOf(edtYearStu.getText())));
                        dbStu.addStudent(student);
                        // lấy dữ liệu từ trong csdl
                        List<Student> list = dbStu.getListStudent();
                        Intent intent = new Intent();
                        intent.putExtra("data", (Serializable) list);
                        setResult(RESULT_OK,intent);// chuyền ra cho activity main
                        finish();
                    }catch (ParseException e){
                        e.printStackTrace();
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }else{
                    Toast.makeText(this,"Vui lòng không để trống!",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean ischeckFormAdd(){
        if(String.valueOf(edtName.getText()) == null && String.valueOf(edtBirth.getText()) == null
            && String.valueOf(edtPlace.getText()) == null && String.valueOf(edtYearStu.getText()) == null){
            return false;
        }else return true;
    }
}