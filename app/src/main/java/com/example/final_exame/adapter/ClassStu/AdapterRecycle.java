package com.example.final_exame.adapter.ClassStu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_exame.R;
import com.example.final_exame.database.DatabaseClassroom;
import com.example.final_exame.database.DatabaseStudent;
import com.example.final_exame.model.Classroom;
import com.example.final_exame.model.Student;
import com.example.final_exame.model.StudentClass;

import java.text.ParseException;
import java.util.List;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder> {
    private List<StudentClass> list;
    private Context context;
    private DatabaseClassroom dbClass;
    private DatabaseStudent dbStu;
    public AdapterRecycle(Context context, List<StudentClass> list, DatabaseClassroom dbClass,DatabaseStudent dbStu){
        this.list = list;
        this.context = context;
        this.dbClass = dbClass;
        this.dbStu = dbStu;
    }

    public void setList(List<StudentClass> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_stu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentClass  stuClass = list.get(position);
        if(stuClass == null){
            return;
        }
        Classroom classroom = null;
        Student student = null;
        try {
            student = dbStu.getStudent(stuClass.getStudentId());
            classroom = dbClass.getClassroom(stuClass.getClassroomId());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        holder.tvClass.setText(classroom.getName());
        holder.tvStu.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvStu,tvClass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStu = itemView.findViewById(R.id.tvStuClass);
            tvClass = itemView.findViewById(R.id.tvClassStu);

        }
    }
}
