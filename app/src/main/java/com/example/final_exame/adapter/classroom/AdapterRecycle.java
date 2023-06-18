package com.example.final_exame.adapter.classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_exame.R;
import com.example.final_exame.model.Classroom;

import java.util.List;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder> {
    private Context context;
    private List<Classroom> list;

    public AdapterRecycle(List<Classroom> list,Context context){
        this.context = context;
        this.list = list;
    }

    public void setList(List<Classroom> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classroom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classroom classroom = list.get(position);
        if(classroom == null){
            return ;
        }
        holder.tvClassId.setText(classroom.getClass_index());
        holder.tvName.setText(classroom.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvClassId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameClass);
            tvClassId = itemView.findViewById(R.id.tvIDClass);
        }
    }
}
