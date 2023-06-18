package com.example.final_exame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_exame.database.DatabaseClassroom;
import com.example.final_exame.model.Classroom;

public class AddClassroomActivity extends AppCompatActivity implements View.OnClickListener {
    //database
    private DatabaseClassroom db;

    private Button btnAdd;
    private EditText edtClassId,edtName,edtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);
        initView();
        btnAdd.setOnClickListener(this);
    }

    private void initView(){
        db = new DatabaseClassroom(this);
        btnAdd = findViewById(R.id.btnAddclassData);
        edtClassId = findViewById(R.id.edtClassID);
        edtName = findViewById(R.id.edtNameClass);
        edtNote = findViewById(R.id.edtNote);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddclassData:
                if(isCheckForm()){
                    Classroom classroom = new Classroom(String.valueOf(edtClassId.getText()),
                            String.valueOf(edtName.getText()),String.valueOf(edtNote.getText()));
                    db.addClassroom(classroom);
                    setResult(RESULT_OK);
                    finish();
                }else{
                    Toast.makeText(this,"Vui lòng điền đầy đủ các trường!",Toast.LENGTH_LONG).show();
                }

        }
    }

    private boolean isCheckForm(){
        if(edtClassId.getText() == null || edtName.getText() == null || edtNote.getText() == null){
            return false;
        }
        return true;
    }
}