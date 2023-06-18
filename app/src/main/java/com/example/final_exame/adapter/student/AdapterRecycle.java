package com.example.final_exame.adapter.student;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_exame.R;
import com.example.final_exame.model.Student;

import java.util.List;


public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder> {
    private Context context;
    private List<Student> list;
    public AdapterRecycle(Context context,List<Student> students){
        this.context = context;
        this.list = students;
    }

    public void setList(List<Student> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = list.get(position);
        if(student == null){
            return;
        }
        Log.e("hello",student.getId()+"");
        holder.tvID.setText(student.getId()+"");
        holder.tvName.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class     ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.nameStu);
            tvID = itemView.findViewById(R.id.idStu);
        }
    }
}
